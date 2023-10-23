package com.popbill.api;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;

import kr.co.linkhub.auth.Base64;

public class Encryptor {
	
	private String _keyID;
	private String _keyName;
	private String _publicKey;
	
	private static String getVersion() throws PopbillException {
        String version = System.getProperty("java.version");
        if(version == null || version.trim().isEmpty() ) throw new PopbillException(-99999999,"자바 버전을 확인할수 없습니다.");
        if(version.startsWith("1.6") || version.startsWith("1.7") ) {
            return "6";
        }else {
            return "8";
        }
    }
	
	public static Encryptor getInstance(String keyID, String keyName, String publicKey)throws PopbillException {
        if(keyID == null || keyID.trim().isEmpty() ) throw new PopbillException(-99999999,"MLE keyID가 입력되지 않았습니다.");
        if(keyName == null || keyName.trim().isEmpty() ) throw new PopbillException(-99999999,"MLE keyName이 입력되지 않았습니다.");
        if(publicKey == null || publicKey.trim().isEmpty() ) throw new PopbillException(-99999999,"MLE publicKey가 입력되지 않았습니다.");
        
        Encryptor _singleTone = new Encryptor();
        _singleTone._keyID = keyID;
        _singleTone._keyName = keyName;
        _singleTone._publicKey = publicKey;
        
        return _singleTone;
    }
	
	private byte[] EncryptByte(byte[] btKey, byte[] input, byte[] iv) throws Exception {
		Key secureKey = new SecretKeySpec(btKey, 0, btKey.length, "AES");
		
		if("6".equals(getVersion())) {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secureKey,new IvParameterSpec(iv));
			return cipher.doFinal(input);
		} else {
			Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secureKey,new GCMParameterSpec(128,iv));
			return cipher.doFinal(input);
		}
		
	}
	
	private PublicKey getKey(String key) throws Exception{
        byte[] byteKey = Base64.decode(key);
        X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(X509publicKey);
	}
	
	public byte[] Encrypt(byte[] target_plain_text) throws Exception {
		
		byte[] session_key = new byte[16];
		byte[] iv = new byte[16];
		
		if("6".equals(getVersion())) {
			SecureRandom random = new SecureRandom();

	        random.nextBytes(session_key);
	        random.nextBytes(iv);
		} else {
			SecureRandom.getInstanceStrong().nextBytes(session_key);
			SecureRandom.getInstanceStrong().nextBytes(iv);
		}
		
		byte[] cipherText = EncryptByte(session_key,target_plain_text,iv);
		
		PublicKey pubkey = getKey(this._publicKey);
		
		Cipher oaepFromAlgo;
		// --- encrypt given algorithm string
		if("6".equals(getVersion())) {
			oaepFromAlgo = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING" );
			  oaepFromAlgo.init(Cipher.ENCRYPT_MODE, pubkey, new OAEPParameterSpec("SHA-1","MGF1",MGF1ParameterSpec.SHA1,PSource.PSpecified.DEFAULT));
		} else {
			oaepFromAlgo = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
			oaepFromAlgo.init(Cipher.ENCRYPT_MODE, pubkey, new OAEPParameterSpec("SHA-256","MGF1",MGF1ParameterSpec.SHA256,PSource.PSpecified.DEFAULT));
		}
		
		byte[] enc_session_key = oaepFromAlgo.doFinal(session_key);
		
		
		return 		ASN1.SEQUENCE_OF_CONSTRUCTED(
					      ASN1.NUMERIC_STRING( 2.0 ),
					      ASN1.SEQUENCE_OF_CONSTRUCTED(
							  ASN1.UTF8_STRING(this._keyName),
							  ASN1.UTF8_STRING(this._keyID) ),
					      ASN1.SEQUENCE_OF_CONSTRUCTED(
							  ASN1.UTF8_STRING("RSA"),
							  ASN1.UTF8_STRING("ECB"),
							  ASN1.UTF8_STRING("6".equals(getVersion()) ? "OAEPWithSHA-1AndMGF1Padding" : "OAEPWithSHA-256AndMGF1Padding"),
							  ASN1.OCTET_STRING(enc_session_key),
							  ASN1.UTF8_STRING("AES"),
							  ASN1.UTF8_STRING("6".equals(getVersion()) ? "CBC": "GCM"),
							  ASN1.UTF8_STRING("6".equals(getVersion()) ? "PKCS5Padding": "NoPadding"),
							  ASN1.OCTET_STRING(iv) ),
					      ASN1.OCTET_STRING(cipherText) );

	}
}
