package com.popbill.api.taxinvoice;

/**
 * Class for Email and publickKey can be used for InterOperation.
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class EmailPublicKey {

    private String confirmNum;
    private String email;

    /**
     * returns 국세청 인증번호
     * 
     * @return 국세청 인증번호
     */
    public String getConfirmNum() {
        return confirmNum;
    }

    /**
     * returns 유통이메일 주소
     * 
     * @return 유통이메일 주소
     */
    public String getEmail() {
        return email;
    }
}
