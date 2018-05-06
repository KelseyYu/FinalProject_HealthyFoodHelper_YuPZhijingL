package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util;

import java.util.List;

public class CommonUtil {

	
	// translate List<String> to String and split by split chars
	public static String list2String(List<String> lists, String split) {
		if(lists == null || split == null) {
			return null;
		}
		
		StringBuilder builder 	= new StringBuilder();
		String result			= null;
		for(String list : lists) {
			builder.append(list);
			builder.append(split);
		}
		
		result	= builder.toString();
		result 	= result.substring(0, result.length() - split.length());
		
		return result;
	}
	
}
