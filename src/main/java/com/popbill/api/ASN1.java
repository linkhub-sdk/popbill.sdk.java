package com.popbill.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class ASN1 {
	public static byte[] INTEGER(int V) throws IOException {
		return toDEREncoded(BERTag.INTEGER, BigInteger.valueOf(V).toByteArray() );
	}
	public static byte[] INTEGER(BigInteger V) throws IOException {
		return toDEREncoded(BERTag.INTEGER, V.toByteArray() );
	}
	public static byte[] BOOLEAN_TRUE() throws IOException {
		return toDEREncoded(BERTag.BOOLEAN, new byte[] { (byte)0xff } );
	}
	public static byte[] BOOLEAN_FALSE() throws IOException {
		return toDEREncoded(BERTag.BOOLEAN, new byte[] { (byte)0x0 } );
	}
	public static byte[] NULL() throws IOException {
		return toDEREncoded(BERTag.NULL, new byte[0]);
	}
	public static byte[] UTC_TIME(Date V) throws IOException {
		
		SimpleDateFormat dateF = new SimpleDateFormat("yyMMddHHmmss'Z'");

		dateF.setTimeZone(new SimpleTimeZone(0,"Z"));
        
		return toDEREncoded(BERTag.UTC_TIME,  dateF.format(V).getBytes());
	}
	public static byte[] SEQUENCE_OF_CONSTRUCTED(byte[]... Varray) throws IOException {
		return toDEREncoded(BERTag.SEQUENCE_OF | BERTag.CONSTRUCTED , Varray);
	}
	public static byte[] SET_OF_CONSTRUCTED(byte[]... Varray) throws IOException {
		return toDEREncoded(BERTag.SET_OF | BERTag.CONSTRUCTED , Varray);
	}
	public static byte[] NUMERIC_STRING(Double V) throws IOException {
		return toDEREncoded(BERTag.NUMERIC_STRING, V.toString().getBytes());
	}
	public static byte[] OCTET_STRING(byte[] V) throws IOException {
		return toDEREncoded(BERTag.OCTET_STRING,V);
	}
	public static byte[] UTF8_STRING(String V) throws IOException {
		return toDEREncoded(BERTag.UTF8_STRING,V.getBytes("UTF-8"));
	}
	
	private static byte[] toDEREncoded(int T , byte[]... Varray ) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		// T
		outputStream.write( T ); 
		
		int vSize = 0;
		for(byte[] V : Varray) {
			vSize += V.length;
		}
		
		// L
		if (vSize > 127) {
			int size = 1;
			int val = vSize;
			
			while ((val >>>= 8) != 0) {
                size++;
			}

			outputStream.write((byte)(size | 0x80));

			for (int i = (size - 1) * 8; i >= 0; i -= 8) {
				outputStream.write((byte)(vSize >> i));
			}
		}  else  {
			outputStream.write((byte)vSize);
		}
		
		// V
		for(byte[] V : Varray) {
			outputStream.write( V ); 
		}
		
		return outputStream.toByteArray();
	
	}
	 
	@SuppressWarnings("unused")
	private interface BERTag {
		static final int BOOLEAN             = 0x01;
	    static final int INTEGER             = 0x02;
	    static final int BIT_STRING          = 0x03;
	    static final int OCTET_STRING        = 0x04;
	    static final int NULL                = 0x05;
	    static final int OBJECT_IDENTIFIER   = 0x06;
	    static final int EXTERNAL            = 0x08;
	    static final int ENUMERATED          = 0x0a;
	    static final int SEQUENCE            = 0x10;
	    static final int SEQUENCE_OF         = 0x10; // for completeness
	    static final int SET                 = 0x11;
	    static final int SET_OF              = 0x11; // for completeness

	    static final int NUMERIC_STRING      = 0x12;
	    static final int PRINTABLE_STRING    = 0x13;
	    static final int T61_STRING          = 0x14;
	    static final int VIDEOTEX_STRING     = 0x15;
	    static final int IA5_STRING          = 0x16;
	    static final int UTC_TIME            = 0x17;
	    static final int GENERALIZED_TIME    = 0x18;
	    static final int GRAPHIC_STRING      = 0x19;
	    static final int VISIBLE_STRING      = 0x1a;
	    static final int GENERAL_STRING      = 0x1b;
	    static final int UNIVERSAL_STRING    = 0x1c;
	    static final int BMP_STRING          = 0x1e;
	    static final int UTF8_STRING         = 0x0c;
	    
	    static final int CONSTRUCTED         = 0x20;
	    static final int APPLICATION         = 0x40;
	    static final int TAGGED              = 0x80;
	}
}
