package com.healthy.food.helper.permission;

/**
 * @author bsl
 * @package little.boy.bsl.permission.util
 * @filename RequestPermissionCallBack
 * @date 18-4-19
 * @email don't tell you
 * @describe TODO
 */

public interface RequestPermissionCallBack {

    // grant permission user
    public void granted();

    // refuse user's request
    public void refuse();
}
