package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ImageUtil{


	private static final String TAG 	= "ImageUtil";


	private static final int BASE_IMG_PATH_LEN	= 4;

	// set imageview background by id or imgPath
	// resId default show
	public static void setImageBg(ImageView imageView, String imgPath, int resId) {
		if(imageView == null) {
			return;
		}

		if(imgPath == null || imgPath.length() <= BASE_IMG_PATH_LEN) {
			if(resId > 0) {
				imageView.setBackgroundResource(resId);
			} else {
				LogUtil.w(TAG, "setImageBg: imgPath is null and resId is " + resId);
				return;
			}
		} else {
			LogUtil.i(TAG, "setImageBg: imgPath is " + imgPath);
			Bitmap imgBitmap = BitmapFactory.decodeFile(imgPath);

			imageView.setImageBitmap(imgBitmap);
		}
	}
}
