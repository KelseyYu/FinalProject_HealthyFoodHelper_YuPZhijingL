package com.healthy.food.helper.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bsl
 * @package com.healthy.food.helper.util
 * @filename VerificationUtil
 * @date 18-4-25
 * @email don't tell you
 * @describe TODO
 */

public class VerificationUtil {

    // whether is email
    public static boolean verifyEmail(String email) {
        if(email == null) {
            return false;
        }

        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    // whether is satisfied
    public static boolean verifyNormal(String data, int length) {
        if(data == null || data.length() < length) {
            return false;
        }

        return true;
    }

}
