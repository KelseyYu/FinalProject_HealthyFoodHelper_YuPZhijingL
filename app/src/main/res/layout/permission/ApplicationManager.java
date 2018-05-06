package com.healthy.food.helper.permission;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.healthy.food.helper.util.LogUtil;

import java.security.MessageDigest;
import java.util.Locale;

/**
 * @author bsl
 * @package little.boy.bsl.permission.util
 * @filename ApplicationManager
 * @date 18-4-19
 * @email don't tell you
 * @describe TODO
 */

public class ApplicationManager {

    private static final String TAG = "ApplicationManager";

    private static Context context;

    // the setter for context
    public static void setContext(Context context) {
        com.healthy.food.helper.permission.ApplicationManager.context = context;
    }

    // the getter for context
    public static @NonNull
    Context getContext() {
        return context;
    }

    public static void getPublicKey(){
        String method	= "void getPublicKey";
        String result = null;

        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i]).toUpperCase(Locale.US);
                if (appendString.length() == 1){
                    hexString.append("0");
                }
                hexString.append(appendString);
                hexString.append(":");
            }

            result = hexString.toString();
            result = result.substring(0, result.length()-1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LogUtil.i(TAG, "getPublicKey: public key is " + result);
    }
}
