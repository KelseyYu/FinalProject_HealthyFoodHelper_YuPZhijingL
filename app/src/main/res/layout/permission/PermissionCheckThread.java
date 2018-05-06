package com.healthy.food.helper.permission;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.healthy.food.helper.util.LogUtil;


/**
 * @author bsl
 * @package little.boy.bsl.permission.util
 * @filename PermissionCheckThread
 * @date 18-4-19
 * @email don't tell you
 * @describe TODO
 */

public class PermissionCheckThread extends Thread {

    private static final String TAG = "PermissionCheckThread";

    public static final int PERMISSION_CHECK = 670;

    private Handler handler;

    private String[] perissions;

    /**
     * @param handler       handle of main thread
     * @param perissions    permissions which user want to apply
     */
    public PermissionCheckThread(@NonNull Handler handler, @NonNull String[] perissions) {
        this.handler    = handler;
        this.perissions = perissions;
    }

    @Override
    public void run() {
        try {
            // check whether user grant application the permissions
            while (true) {
                if (com.healthy.food.helper.permission.PermissionManager.isGrantedAll(perissions)) {
                    Message message = new Message();
                    message.what = PERMISSION_CHECK;
                    handler.sendMessage(message);

                    LogUtil.i(TAG, "run: all permission are granted");
                    break;
                }

                // wait a second to listen
                sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
