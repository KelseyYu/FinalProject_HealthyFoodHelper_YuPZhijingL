package com.healthy.food.helper.util;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import java.io.File;


/**
 * @author bsl
 * @package com.tencent.mm.plugin.util
 * @filename FileUtil
 * @date 18-3-6
 * @email don't tell you
 * @describe TODO
 */

public class UriUtil {

    private static final String TAG = "UriUtil";

    private static final String EXTERNAL_STORAGE_DOCUMENT   = "com.android.externalstorage.documents";
    private static final String DOWNLOAD_DOCUMENT           = "com.android.providers.downloads.documents";
    private static final String MEDIA_DOCUMENT              = "com.android.providers.media.documents";

    private static final String DOWNLOAD_URI                = "content://downloads/public_downloads";

    // check whether the document in external storage
    private static boolean isExternalStorageDocument(String authority) {
        return EXTERNAL_STORAGE_DOCUMENT.equals(authority);
    }

    // check whether the document in download
    private static boolean isDownloadDocument(String authority) {
        return DOWNLOAD_DOCUMENT.equals(authority);
    }

    // check whether the document in media document
    private static boolean isMediaDocument(String authority) {
        return MEDIA_DOCUMENT.equals(authority);
    }

    // get the absolute path of file by it's uri
    public static String getAbsolutePathByUri(Uri uri) {
        if(uri == null) {   return null; }

        String path;
        LogUtil.d(TAG, "getAbsolutePathByUri: version of sdk is " + Build.VERSION.SDK_INT);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {          // version 4.4
            path = getAbsolutePathByUriOnKitKat(uri);
        } else {
            path = getAbsolutePathByUriBeforeKitKat(uri);
        }

        return path;
    }

    // sql select in uri
    private static String getPathBySql(Uri uri, String selection, String[] selectionArgs) {
        String path = null;
        if(uri == null) {   return null;    }

        String column       = "_data";
        String[] projection = { column };

        Cursor cursor = com.healthy.food.helper.permission.ApplicationManager.getContext().getContentResolver().query(uri, projection, selection, selectionArgs,null);
        if (cursor != null){
            if(cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(column);
                path = cursor.getString(column_index);
            }
            cursor.close();
        }

        return path;
    }

    // lager than android4.4
    private static String getAbsolutePathByUriOnKitKat(Uri uri) {
        String path = null;
        if(uri == null) {    return null;    }

        String authority= uri.getAuthority();
        String scheme   = uri.getScheme();
        LogUtil.d(TAG, "getAbsolutePathByUriOnKitKat: authority is " + authority + ", scheme is " + scheme);

        if(DocumentsContract.isDocumentUri(com.healthy.food.helper.permission.ApplicationManager.getContext(), uri)) {
            String docId    = DocumentsContract.getDocumentId(uri);
            LogUtil.d(TAG, "getAbsolutePathByUriOnKitKat: docId is " + docId);
            String origin   = "raw:";

            if(docId != null && docId.startsWith(origin)) {                 // 下载内容异常 总结的经验
                path = docId.substring(origin.length(), docId.length());
            } else if (isExternalStorageDocument(authority)) {              // external document
                String[] splits = docId.split(":");
                String type = splits[0];

                if ("primary".equalsIgnoreCase(type)) {
                    path = Environment.getExternalStorageDirectory() + File.separator + splits[1];
                }
            } else if (isDownloadDocument(authority)) {                     // download document
                Uri downloadUri = ContentUris.withAppendedId(Uri.parse(DOWNLOAD_URI), Long.valueOf(docId));
                path = getPathBySql(downloadUri, null, null);
            } else if (isMediaDocument(authority)) {                        // media document
                String[] split = docId.split(":");
                String type = split[0];

                Uri mediaUri = null;
                if ("image".equals(type)) {                                     // image
                    mediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {                              // video
                    mediaUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {                              // audio
                    mediaUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                String selection = "_id=?";
                String[] selectionArgs = new String[]{split[1]};

                assert mediaUri != null;
                path = getPathBySql(mediaUri, selection, selectionArgs);
            }
        } else if("content".equals(scheme)) {                               // content
            path = getPathBySql(uri, null, null);
        } else if("file".equals(scheme)) {                                  // file
            path = uri.getPath();
        }

        return path;
    }

    // smaller than android4.4
    private static String getAbsolutePathByUriBeforeKitKat(Uri uri) {
        String path = null;
        if(uri == null) {   return null;    }

        Cursor cursor = com.healthy.food.helper.permission.ApplicationManager.getContext().getContentResolver().query(uri, null, null, null, null);
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return path;
    }

}
