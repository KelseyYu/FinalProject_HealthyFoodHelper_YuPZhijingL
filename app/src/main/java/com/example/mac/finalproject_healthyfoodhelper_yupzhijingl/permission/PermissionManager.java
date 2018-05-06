package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionManager {

    private static final String TAG = "PermissionManager";

    private static final int REQUEST_CODE   = 666;

    // judge whether grant all permissions to user
    public static boolean isGrantedAll(@NonNull String[] permissions) {
        for(String permission : permissions) {
            if(ContextCompat.checkSelfPermission(ApplicationManager.getContext(), permission)
                    == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }

        return true;
    }

    // request permission
    public static void requestPermission(@NonNull final String[] permissions,
                                         @NonNull String[] interprets) {
        // params check
        if(isGrantedAll(permissions)) {
            return;
        }

        // get permission name
        StringBuilder permissionNameBuilder = new StringBuilder();
        for(String interpret : interprets) {
            permissionNameBuilder.append(interpret);
        }

        // dialog to tips {true: request, false: shutdown}
        AlertDialog.Builder builder = new AlertDialog.Builder(ApplicationManager.getContext());
        builder.setCancelable(false);
        builder.setTitle("权限请求");
        builder.setMessage("您好，为了保持应用的正常运行，我们需要向您请求如下权限：\r\n" +
                permissionNameBuilder.toString());
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions((Activity) (ApplicationManager.getContext()) , permissions, REQUEST_CODE);
            }
        });

        builder.show();
    }

    // deal with request result
    public static void onRequestPermissionsResult(int requestCode,
                                                  @NonNull String[] permissions,
                                                  @NonNull int[] grantResults,
                                                  @NonNull RequestPermissionCallBack request) {
        switch (requestCode) {
            case PermissionManager.REQUEST_CODE:
                for(int i = 0; i < grantResults.length; i ++) {
                    if(grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        request.refuse();
                    } else {
                        request.granted();
                    }
                }
                break;
            default:
                break;
        }
    }

}
