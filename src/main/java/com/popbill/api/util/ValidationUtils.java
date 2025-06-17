package com.popbill.api.util;

import java.util.Arrays;
import java.util.List;

public abstract class ValidationUtils {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static <T> boolean isNullOrEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /** yyyyMMdd */
    private static final String DATE_PATTERN = "^(\\d{4})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$";

    /** yyyyMMddHHmmss */
    private static final String DATETIME_PATTERN = "^(\\d{4})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])(0[0-9]|1[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])$";

    public static boolean isValidDate(String str) {
        if (str == null) return false;
        return str.matches(DATE_PATTERN);
    }

    public static boolean isValidDateTime(String str) {
        if (str == null) return false;
        return str.matches(DATETIME_PATTERN);
    }

    public static String replaceInvalidUriChars(String[] array) {
        return Arrays.toString(array).replaceAll("[^0-9A-Z*,]", "");
    }

    public static String replaceInvalidUriChars(int[] array) {
        return Arrays.toString(array).replaceAll("[^0-9,]", "");
    }

}
