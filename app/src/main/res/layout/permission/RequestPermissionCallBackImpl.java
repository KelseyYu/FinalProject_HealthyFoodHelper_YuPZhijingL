package com.healthy.food.helper.permission;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

import com.healthy.food.helper.util.LogUtil;

/**
 * @author bsl
 * @package little.boy.bsl.permission
 * @filename RequestPermissionCallBackImpl
 * @date 18-4-19
 * @email don't tell you
 * @describe TODO
 */

public class RequestPermissionCallBackImpl implements RequestPermissionCallBack {

    private static final String TAG = "RequestPermissionCallBackImpl";
    
    @Override
    public void granted() {
        LogUtil.i(TAG, "granted: user grant your permission request");
    }

    @Override
    public void refuse() {
        LogUtil.i(TAG, "refuse: user refuse your permission request");

        AlertDialog.Builder builder = new AlertDialog.Builder(ApplicationManager.getContext());
        builder.setCancelable(false);
        builder.setTitle("系统提示");
        builder.setMessage("由于您取消了应用必要权限授权，导致应用无法正常运行！\r\n"
            + "如若正常使用应用，请手动授权～");
        builder.setPositiveButton("去授权", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestGrant();
            }
        });
        builder.show();
    }

    // goto setting application
    private void requestGrant() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", ApplicationManager.getContext().getPackageName(),
                null);
        intent.setData(uri);
        ApplicationManager.getContext().startActivity(intent);
    }

}
