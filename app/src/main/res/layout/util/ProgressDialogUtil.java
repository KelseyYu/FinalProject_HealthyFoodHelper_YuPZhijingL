package com.healthy.food.helper.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * @author bsl
 * @package com.healthy.food.helper.util
 * @filename ProgressDialogUtil
 * @date 18-4-25
 * @email don't tell you
 * @describe TODO
 */

public class ProgressDialogUtil {

    private static final String TAG = "ProgressDialogUtil";
    
    public ProgressDialog progressDialog;

    private boolean status = false;

    public ProgressDialogUtil(Context context) {
        progressDialog = new ProgressDialog(context);
    }

    // show progressDialog
    public void showProgressDialog(String title, String msg) {
        if(!status) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            progressDialog.show();
            status = true;
        }
    }

    // hide progressDialog
    public void hideProgressDialog() {
        if(status) {
            progressDialog.hide();
            status = false;
        }
    }

}
