package com.popbill.api.fax.test;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.popbill.api.ChargeInfo;
import com.popbill.api.FaxService;
import com.popbill.api.FaxUploadFile;
import com.popbill.api.PopbillException;
import com.popbill.api.Response;
import com.popbill.api.fax.FAXSearchResult;
import com.popbill.api.fax.FaxResult;
import com.popbill.api.fax.FaxServiceImp;
import com.popbill.api.fax.Receiver;
import com.popbill.api.fax.SenderNumber;

public class FaxServiceTEST {

    private final String testLinkID = "TESTER";
    private final String testSecretKey = "SwWxqU+0TErBXy/9TVjIPEnI0VTUMMSQZtJf3Ed8q3I=";

    private FaxService faxService;
    
    public FaxServiceTEST() {
        FaxServiceImp service = new FaxServiceImp();

        service.setLinkID(testLinkID);
        service.setSecretKey(testSecretKey);
        service.setTest(true);
        service.setUseLocalTimeYN(true);
        faxService = service;
    }
    
    @Test
    public void getChargeInfo_TEST() throws PopbillException {
        
        ChargeInfo chrgInfo = faxService.getChargeInfo("1234567890", "지능");

        System.out.println(chrgInfo.getChargeMethod());
        System.out.println(chrgInfo.getRateSystem());
        System.out.println(chrgInfo.getUnitCost());
    }
    
    @Test
    public void getUnitCost_TEST() throws PopbillException {
        
        float UnitCost = faxService.getUnitCost("1234567890", "지능");

        System.out.println(UnitCost);
    }
    
    @Test
    public void getURL_TEST() throws PopbillException {
        
        String url = faxService.getURL("1234567890", "SENDER");

        assertNotNull(url);
        System.out.println(url);
    }
    
    @Test
    public void sendFAX_01_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "070111222",
                "01수신자명",file, null);
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }    
    
    
    @SuppressWarnings("resource")
    @Test
    public void sendFAXBinary_01_TEST() throws PopbillException, FileNotFoundException {
        
        File file = new File("/Users/John/Desktop/test.pdf");
        InputStream tragetStream = new FileInputStream(file);
        
        FaxUploadFile[] fileList = new FaxUploadFile[1];
        FaxUploadFile uf = new FaxUploadFile();
        uf.fileName = "test.pdf";
        uf.fileData = tragetStream;
        
        fileList[0] = uf;
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명40_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명40_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAXBinary("1234567890", "070-4304-2999", "발신자명", receivers, fileList,
                null, "testkorea", false, "title", "");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @SuppressWarnings("resource")
    @Test
    public void sendFAXBinary_02_TEST() throws PopbillException, FileNotFoundException {
        
        File file = new File("/Users/John/Desktop/test.pdf");
        InputStream tragetStream = new FileInputStream(file);
        
        FaxUploadFile[] fileList = new FaxUploadFile[1];
        FaxUploadFile uf = new FaxUploadFile();
        uf.fileName = "test.pdf";
        uf.fileData = tragetStream;
        
        fileList[0] = uf;
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명40_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명40_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAXBinary("1234567890", "070-4304-2999", receivers, fileList,
                null, "testkorea", false, "", "");
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    
    @Test
    public void sendFAX_02_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "070111222",
                "02수신자명",file, null, true);
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }    

    @Test
    public void sendFAX_03_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "070111222",
                "03수신자명",file, null, false, "20180723_fax_03");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_04_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "070111222",
                "04수신자명",file, null, "testkorea");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_05_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "070111222",
                "05수신자명",file, null, "testkorea", false);
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_06_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "070111222",
                "06수신자명",file, null, "testkorea", true, "fax_title_06");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_07_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "070111222",
                "07수신자명",file, null, "testkorea", false, "fax_title_07", "20180723_fax_07");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_08_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명08_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명08_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};        
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, file, null);
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_09_TEST() throws PopbillException {
        
        File file = new File("C:/Users/jhPark/Desktop/fax_test.txt");
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명09_1");
        receiver1.setReceiveNum("070111222");
        receiver1.setInterOPRefKey("20220929-001");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명09_2");
        receiver2.setReceiveNum("070111222");
        receiver2.setInterOPRefKey("20220929-001");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, file, null, false);
        
        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_10_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명08_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명08_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};        
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, file, null,
                false, "title_10");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_11_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명11_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명11_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};        
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, file, null,
                true, "title", "20180723_fax_11");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_12_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명12_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명12_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};        
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, file, null, "testkorea");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_13_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명13_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명13_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};        
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, file, null, "testkorea", "title");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_14_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명14_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명14_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};        
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, file, null,
                "testkorea", "title", "20180723_fax_14");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_15_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명15_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명15_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};        
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, file, null, "testkorea", false);
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_16_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명16_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명16_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};        
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, file, null,
                "testkorea", false, "title");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_17_TEST() throws PopbillException {
        
        File file = new File("./test.pdf");
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명17_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명17_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};        
        
        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, file, null,
                "testkorea", false, "title", "20180723_fax_17");
        
        assertNotNull(receiptNum);        
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_18_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999","111-2222-3333","18수신자명칭", files, null);

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFAX_19_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999","111-2222-3333","19수신자명칭", files, null, false);

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFAX_20_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999","111-2222-3333","20수신자명칭", files, null,
                false, "title");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFAX_21_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999","111-2222-3333","21수신자명칭", files, null,
                false, "title", "20180723_fax_21");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFAX_22_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999","111-2222-3333","22수신자명칭", files, null, "testkorea");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFAX_23_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999","111-2222-3333","23수신자명칭", files, null, 
                "testkorea", "title");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFAX_24_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999","111-2222-3333","24수신자명칭", files, null,
                 "testkorea", "title", "20180723_fax_24");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFAX_25_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999","111-2222-3333","25수신자명칭", files, null,
                "testkorea", false);

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFAX_26_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999","111-2222-3333","26수신자명칭", files, null,
                 "testkorea", false, "title");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFAX_27_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999","111-2222-3333","27수신자명칭", files, null,
                 "testkorea", false, "title", "20180723_fax_27");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }    
    
    @Test
    public void sendFAX_28_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명28_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명28_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, files,
                null, "testkorea");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }        
    
    
    @Test
    public void sendFAX_29_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명29_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명29_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, files,
                null, "testkorea", "title");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_30_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명30_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명30_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, files,
                null, "testkorea", "title", "20180723_fax_30");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_31_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명31_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명31_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, files,
                null, "testkorea", true);

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_32_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명32_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명32_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, files,
                null, "testkorea", false, "title");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_33_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명33_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명33_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", receivers, files,
                null, "testkorea", false, "title", "20180723_fax_33");

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_34_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명34_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명34_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "수신자명34", receivers, files,
                null);
        
        

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    
    @Test
    public void sendFAX_35_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명35_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명35_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "수신자명35", receivers, files,
                null);
        
        

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_36_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명36_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명36_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "수신자명36", receivers, files,
                null, false);
        
        

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_37_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명37_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명37_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "수신자명37", receivers, files,
                null, false, "20180723_fax_37");
        
        

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_38_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명38_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명38_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "수신자명38", receivers, files,
                null, "testkorea");
        
        

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_39_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명39_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명39_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "수신자명39", receivers, files,
                null, "testkorea", "title");
        
        

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_40_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");
        
        Date reserveDT = new Date();
        reserveDT.setDate(reserveDT.getDate() + 1);            

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명40_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명40_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "수신자명40", receivers, files,
                reserveDT, "testkorea", "title", "20180723_fax_50");
        
        

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_41_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명41_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명41_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "수신자명41", receivers, files,
                null, "testkorea", false);
        
        

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_42_TEST() throws PopbillException {
        File[] files = new File[5];
        File file1 = new File("./test.pdf");
        
        Date reserveDT = new Date();
        reserveDT.setDate(reserveDT.getDate() + 1);        

        for (int i = 0; i < 5; i++){
            files[i] = file1;
        }
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명42_1");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명42_2");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            

        String receiptNum = faxService.sendFAX("1234567890", "070-4304-2999", "수신자명42", receivers, files,
                reserveDT, "testkorea", false, "title");
        
        

        assertNotNull(receiptNum);
        System.out.println(receiptNum);
    }
    
    @Test
    public void sendFAX_Single_TEST() throws PopbillException {
        
        File file = new File("/Users/kimhyunjin/Workspace/popbill.sdk.example.py/popbill.fax.example.py/hello.html");
        
        String receiptNum = faxService.sendFAX("1234567890", "070111222", "070111222","수신자명칭",file, null, null, true, "팩스제목");
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);
        
        FaxResult[] results = faxService.getFaxResult("1234567890", receiptNum);
        
        assertNotNull(results);
        
        System.out.println(results[0].getFileNames()[0] + " "+results[0].getTitle());
    }
    
    @Test
    public void resendFAX_01_TEST() throws PopbillException {

        String testCorpNum = "1234567890";
        String orgReceiptNum = "018072316160200001";
        String senderNum = "07043042991";
        String senderName = "발신자명";
        String receiveNum = "070111222";
        String receiveName = "수신자명";
        
        String receiptNum = faxService.resendFAX(testCorpNum, orgReceiptNum, senderNum, senderName, receiveNum, receiveName, null, "testkorea");
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void resendFAX_02_TEST() throws PopbillException {

        String testCorpNum = "1234567890";
        String orgReceiptNum = "018072316160200001";
        String senderNum = "07043042991";
        String senderName = "발신자명";
        String receiveNum = "070111222";
        String receiveName = "수신자명";
        String title = "팩스재전송";
        
        String receiptNum = faxService.resendFAX(testCorpNum, orgReceiptNum, senderNum, senderName, receiveNum, receiveName, null, "testkorea", title);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void resendFAX_03_TEST() throws PopbillException {

        String testCorpNum = "1234567890";
        String orgReceiptNum = "018072316160200001";
        String senderNum = "07043042991";
        String senderName = "발신자명";
        String receiveNum = "070111222";
        String receiveName = "수신자명";
        String title = "팩스재전송";
        
        String receiptNum = faxService.resendFAX(testCorpNum, orgReceiptNum, senderNum, senderName, receiveNum, receiveName, null, "testkorea",
                title, "20180723_resendfax_03");
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void resendFAX_04_TEST() throws PopbillException {

        String testCorpNum = "1234567890";
        String orgReceiptNum = "018072316160200001";
        String senderNum = "07043042991";
        String senderName = "발신자명";
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};        
        
        String receiptNum = faxService.resendFAX(testCorpNum, orgReceiptNum, senderNum, senderName, receivers, null);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void resendFAX_05_TEST() throws PopbillException {

        String testCorpNum = "1234567890";
        String orgReceiptNum = "018072316160200001";
        String senderNum = "07043042991";
        String senderName = "발신자명";
        String receiveNum = "070111222";
        String receiveName = "수신자명";
        String title = "팩스재전송";
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            
        
        String receiptNum = faxService.resendFAX(testCorpNum, orgReceiptNum, senderNum, senderName, receivers, null, "testkorea");
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void resendFAX_06_TEST() throws PopbillException {

        String testCorpNum = "1234567890";
        String orgReceiptNum = "018072316160200001";
        String senderNum = "07043042991";
        String senderName = "발신자명";
        String receiveNum = "070111222";
        String receiveName = "수신자명";
        String title = "팩스재전송";
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            
        
        String receiptNum = faxService.resendFAX(testCorpNum, orgReceiptNum, senderNum, senderName, receivers, null, "testkorea", title);
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void resendFAX_07_TEST() throws PopbillException {

        String testCorpNum = "1234567890";
        String orgReceiptNum = "018072316160200001";
        String senderNum = "07043042991";
        String senderName = "발신자명";
        String receiveNum = "070111222";
        String receiveName = "수신자명";
        String title = "팩스재전송";
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};            
        
        String receiptNum = faxService.resendFAX(testCorpNum, orgReceiptNum, senderNum, senderName, receivers, null, "testkorea", title, 
                "20180723_resendfax_08");
        
        System.out.println(receiptNum);
    }
    
    @Test
    public void resendFAXRN_01_TEST() throws PopbillException {

        String testCorpNum = "1234567890";
        String originalFAXrequestNum = "20180723_fax_03";
        String senderNum = "07043042991";
        String senderName = "발신자명";
        String receiveNum = "070111222";
        String receiveName = "수신자명";
        String title = "팩스재전송";
        String requestNum = "20180723_resendfax_99";
        String receiptNum = faxService.resendFAXRN(testCorpNum, requestNum, senderNum, senderName, 
                receiveNum, receiveName, null, "testkorea", title, originalFAXrequestNum);
        
        System.out.println(receiptNum);
    }
    
    
    @Test
    public void resendFAXRN_02_TEST() throws PopbillException {

        String testCorpNum = "1234567890";
        String originalFAXrequestNum = "20180723_resendfax_99";
        String senderNum = "07043042991";
        String senderName = "발신자명";
        String requestNum = "20180723_resendfax_100";
        
        Receiver receiver1 = new Receiver();
        receiver1.setReceiveName("수신자명");
        receiver1.setReceiveNum("070111222");
        
        Receiver receiver2 = new Receiver();
        receiver2.setReceiveName("수신자명");
        receiver2.setReceiveNum("070111222");
        
        Receiver[] receivers = new Receiver[] {receiver1, receiver2};
        
        String receiptNum = faxService.resendFAXRN(testCorpNum, requestNum, senderNum, 
                senderName, receivers, null, "testkorea", "팩스제목", originalFAXrequestNum);
        
        assertNotNull(receiptNum);
        
        System.out.println(receiptNum);

    }
    
    @Test
    public void sendFAX_MultiFile_TEST() throws PopbillException {
        File[] files = new File[20];
        File file1 = new File("/Users/kimhyunjin/Pictures/", "example.png");

        for (int i = 0; i < 20; i++){
            files[i] = file1;
        }

        String receiptNum = faxService.sendFAX("1234567890", "02-6442-9700","111-2222-3333","수신자명칭", files, null);

        assertNotNull(receiptNum);

        FaxResult[] results = faxService.getFaxResult("1234567890", receiptNum);

        assertNotNull(results);

        System.out.println(results[0].getFileNames()[0] + " "+results[0].getSenderName());
    }
    
    @Test
    public void getFaxResult_TEST() throws PopbillException {
        String receiptNum = "022092917383200001";
        FaxResult[] messages = faxService.getFaxResult("1234567890", receiptNum);
        
        assertNotNull(messages);
        
        int i=0;
        for(i=0; i < messages.length; i++){
            System.out.println(messages[i].getSendState()); 
            System.out.println(messages[i].getConvState()); 
            System.out.println(messages[i].getSendNum()); 
            System.out.println(messages[i].getSenderName()); 
            System.out.println(messages[i].getReceiveNum()); 
            System.out.println(messages[i].getReceiveName()); 
            System.out.println(messages[i].getSendPageCnt()); 
            System.out.println(messages[i].getSuccessPageCnt()); 
            System.out.println(messages[i].getFailPageCnt()); 
            System.out.println(messages[i].getRefundPageCnt()); 
            System.out.println(messages[i].getCancelPageCnt()); 
            System.out.println(messages[i].getReserveDT()); 
            System.out.println(messages[i].getSendDT()); 
            System.out.println(messages[i].getResultDT()); 
            System.out.println(messages[i].getSendResult()); 
            System.out.println(messages[i].getReceiptDT()); 
            System.out.println(messages[i].getTitle()); 
            System.out.println(messages[i].getState()); 
            System.out.println(messages[i].getResult()); 
            System.out.println(messages[i].getReceiptNum()); 
            System.out.println(messages[i].getRequestNum());
            System.out.println(messages[i].getInterOPRefKey());
            System.out.println(messages[i].getiSuccessPageCnt());
            System.out.println(messages[i].getReceiveNumType());
            System.out.println("getChargePageCnt :"+ messages[i].getChargePageCnt());
            System.out.println("getTifFileSize : "+ messages[i].getTiffFileSize());

        }
            
    }        
    
    
    @Test
    public void getFaxResultRN_TEST() throws PopbillException {
        
        String requestNum = "20180723_fax_50";
        
        FaxResult[] messages = faxService.getFaxResultRN("1234567890", requestNum);
        
        assertNotNull(messages);
        
        int i=0;
        for(i=0; i < messages.length; i++){
            System.out.println(messages[i].getSendState()); 
            System.out.println(messages[i].getConvState()); 
            System.out.println(messages[i].getSendNum()); 
            System.out.println(messages[i].getSenderName()); 
            System.out.println(messages[i].getReceiveNum()); 
            System.out.println(messages[i].getReceiveName()); 
            System.out.println(messages[i].getSendPageCnt()); 
            System.out.println(messages[i].getSuccessPageCnt()); 
            System.out.println(messages[i].getFailPageCnt()); 
            System.out.println(messages[i].getRefundPageCnt()); 
            System.out.println(messages[i].getCancelPageCnt()); 
            System.out.println(messages[i].getReserveDT()); 
            System.out.println(messages[i].getSendDT()); 
            System.out.println(messages[i].getResultDT()); 
            System.out.println(messages[i].getSendResult()); 
            System.out.println(messages[i].getReceiptDT()); 
            System.out.println(messages[i].getInterOPRefKey());
            System.out.println(messages[i].getTitle()); 
            System.out.println(messages[i].getState()); 
            System.out.println(messages[i].getResult()); 
            System.out.println(messages[i].getReceiptNum()); 
            System.out.println(messages[i].getRequestNum());
            System.out.println("getChargePageCnt :"+ messages[i].getChargePageCnt());
            System.out.println("getTifFileSize : "+ messages[i].getTiffFileSize());

        }
    }    
    
    @Test
    public void cancelReserve_TEST() throws PopbillException {
        
        String receiptNum = "017030613315600002";
        
        Response response = faxService.cancelReserve("1234567890", receiptNum);
        
        assertNotNull(response);
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void cancelReserveRN_01_TEST() throws PopbillException {
        
        String requestNum = "20180723_fax_50";
        
        Response response = faxService.cancelReserveRN("1234567890", requestNum);
        
        assertNotNull(response);
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void cancelReserveRN_02_TEST() throws PopbillException {
        
        String requestNum = "20180723_fax_50";
        
        Response response = faxService.cancelReserveRN("1234567890", requestNum);
        
        assertNotNull(response);
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void cancelReserve_01_TEST() throws PopbillException {
        
        String requestNum = "";
        
        Response response = faxService.cancelReserve("1234567890", requestNum);
        
        assertNotNull(response);
        
        System.out.println(response.getMessage());
    }
    
    
    @Test
    public void cancelReserve_02_TEST() throws PopbillException {
        
        String requestNum = "";
        
        Response response = faxService.cancelReserve("1234567890", requestNum);
        
        assertNotNull(response);
        
        System.out.println(response.getMessage());
    }
    
    @Test
    public void search_TEST() throws PopbillException{
        String SDate = "20241201";
        String EDate = "20241231";
        String[] State = {"1","2","3","4"};
        Boolean ReserveYN = false;
        Boolean SenderOnlyYN = false;
        int Page = 1;
        int PerPage = 50;
        String Order = "D";
        String QString = "";
        FAXSearchResult response = faxService.search("1234567890", SDate, EDate, State, ReserveYN, SenderOnlyYN, Page, PerPage, Order, QString);
        
        assertNotNull(response);
        System.out.println(response.getList().size());
    }
    
    @Test
    public void getSenderNumberList_TEST() throws PopbillException {
        
        SenderNumber[] listInfo = faxService.getSenderNumberList("1234567890", "testkorea");
        
        assertNotNull(listInfo);
        
        int i;
        for (i=0; i<listInfo.length; i++){
            System.out.println(listInfo[i].getNumber());
            System.out.println(listInfo[i].getState());
            System.out.println(listInfo[i].getRepresentYN());
            System.out.println(listInfo[i].getMemo());
        }
    }

    @Test
    public void getPreviewURL_userID_TEST() throws PopbillException{

        String url = faxService.getPreviewURL("1234567890", "018102609383900001", "testkorea");

        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void getPreviewURL_TEST() throws PopbillException{

        String url = faxService.getPreviewURL("1234567890", "018102609383900001");

        assertNotNull(url);
        System.out.println(url);
    }

    @Test
    public void GetSenderNumberMgtURL_TEST() throws PopbillException {

        String url = faxService.getSenderNumberMgtURL("1234567890","testkorea");

        System.out.println(url);
    }

    @Test
    public void GetSentListURL_TEST() throws PopbillException {

        String url = faxService.getSentListURL("1234567890","testkorea");

        System.out.println(url);
    }

    @Test
    public void checkSenderNumber_TEST() throws PopbillException {
        Response response = faxService.checkSenderNumber("1234567890", "070-4304-2981");
        assertNotNull(response);
        
        System.out.println(response.getCode());
        System.out.println(response.getMessage());
    }

    public static Date addMinutes(Date date, int minutes)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes); //minus number would decrement the days
        return cal.getTime();
    }


    
}
