package com.popbill.api.util.test;

import com.popbill.api.taxinvoice.MgtKeyType;
import com.popbill.api.taxinvoice.Taxinvoice;
import com.popbill.api.util.ValidationUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ValidationUtilsTEST {

    @Test
    public void isNullOrEmpty_TEST() {
        String nullStr = null;
        assertEquals(true, ValidationUtils.isNullOrEmpty(nullStr));
        assertEquals(true, ValidationUtils.isNullOrEmpty(""));
        assertEquals(true, ValidationUtils.isNullOrEmpty(" "));
        assertEquals(true, ValidationUtils.isNullOrEmpty("  "));
        assertEquals(false, ValidationUtils.isNullOrEmpty("TEST"));

        String[] nullArr = null;
        assertEquals(true, ValidationUtils.isNullOrEmpty(nullArr));

        String[] emptyArr = {};
        assertEquals(true, ValidationUtils.isNullOrEmpty(emptyArr));

        String[] strArr = {"TEST1", "TEST2", "TEST3"};
        assertEquals(false, ValidationUtils.isNullOrEmpty(strArr));

        List<String> nullList = null;
        assertEquals(true, ValidationUtils.isNullOrEmpty(nullList));

        List<String> emptyList = new ArrayList<String>();
        assertEquals(true, ValidationUtils.isNullOrEmpty(emptyList));

        List<String> strList = new ArrayList<String>();
        strList.add("TEST");
        assertEquals(false, ValidationUtils.isNullOrEmpty(strList));

        Taxinvoice nullTaxinvoice = null;
        assertEquals(true, ValidationUtils.isNull(nullTaxinvoice));

        Taxinvoice taxinvoice = new Taxinvoice();
        assertEquals(false, ValidationUtils.isNull(taxinvoice));

        MgtKeyType nullKeyType = null;
        assertEquals(true, ValidationUtils.isNull(nullKeyType));

        MgtKeyType keyType = MgtKeyType.SELL;
        assertEquals(false, ValidationUtils.isNull(keyType));
    }

    @Test
    public void isValidDate_TEST() {
        assertEquals(false, ValidationUtils.isValidDate(null));
        assertEquals(false, ValidationUtils.isValidDate(" "));
        assertEquals(false, ValidationUtils.isValidDate(""));
        assertEquals(false, ValidationUtils.isValidDate("20251326"));
        assertEquals(false, ValidationUtils.isValidDate("d20251326"));
        assertEquals(false, ValidationUtils.isValidDate("20251232"));
        assertEquals(true, ValidationUtils.isValidDate("20250526"));
        assertEquals(true, ValidationUtils.isValidDate("20250101"));
    }

    @Test
    public void isValidDateTime_TEST() {
        assertEquals(false, ValidationUtils.isValidDateTime(null));
        assertEquals(false, ValidationUtils.isValidDateTime(" "));
        assertEquals(false, ValidationUtils.isValidDateTime(""));
        assertEquals(false, ValidationUtils.isValidDateTime("d20251326"));
        assertEquals(false, ValidationUtils.isValidDateTime("20250526136000"));
        assertEquals(false, ValidationUtils.isValidDateTime("20250526000060"));
        assertEquals(false, ValidationUtils.isValidDateTime("20250526126000"));
        assertEquals(true, ValidationUtils.isValidDateTime("20250526000000"));
        assertEquals(true, ValidationUtils.isValidDateTime("20250526115759"));
    }

}
