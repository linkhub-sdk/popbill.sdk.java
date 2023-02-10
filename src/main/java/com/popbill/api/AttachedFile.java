package com.popbill.api;

/**
 * Class for Popbill AttachedFile Info .
 * 
 * @author KimSeongjun
 * @version 1.0.0
 */
public class AttachedFile {

    private Integer serialNum;
    private String attachedFile;
    private String displayName;
    private String regDT;

    /**
     * returns attachedFile's ID used for deleteFile.
     * 
     * @return attachedFile
     */
    public String getAttachedFile() {
        return attachedFile;
    }

    /**
     * returns DisplayName.
     * 
     * @return DisplayName.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * returns registerd DateTime.
     * 
     * @return regDT
     */
    public String getRegDT() {
        return regDT;
    }

    /**
     * returns serialNum
     * 
     * @return serialNum
     */
    public Integer getSerialNum() {
        return serialNum;
    }

}
