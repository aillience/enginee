package com.yfl.library.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author yfl 存储各种用到的规范协议
 */
public class MyPattern {
	// 判断手机号
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,6-8])|(18[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// 判断名字是否符合要求
	public static boolean checkNameChinese(String name) {
		// 以1-6个汉字开头，0-9数字字母下划线结尾的组合或1-9数字字母下划线
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(([\\u4e00-\\u9fa5]){1,6}\\w{0,9})|(\\w{1,9})$");
			Matcher matcher = regex.matcher(name);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// 判断密码是否符合要求
	public static boolean checkPassWord(String pwd) {
		// 4-16个字符的组合
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^.{4,16}$");
			Matcher matcher = regex.matcher(pwd);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// 判断身份证是否符合要求
	public static boolean checkIDCard(String card) {
		// 15或18个数字或17个数字加X的组合
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^\\d{15}|\\d{18}|(\\d{17}X)$");
			Matcher matcher = regex.matcher(card);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	// 判断邮箱是否符合要求
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	//判断门禁密码
	public static boolean checkDoorPwd(String pwd){
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("\\d{4,8}");
			Matcher matcher = regex.matcher(pwd);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	public static String HidePhone(String phone){
		boolean flag = false;
		try {
			Pattern regex = Pattern
					.compile("^(((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,6-8])|(18[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(phone);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		if(!flag){
			phone="未知手机号";
		}
		return phone;
	}
	//截取web参数
	public static String[] Split(String url){
		String[] splits=null;
		String[] url1=url.split("&");
		splits=new String[url1.length];
		for(int i=0;i<url1.length;i++){
			String[] url2=url1[i].split("=");
			splits[i]=url2[1];
		}
		return splits;
	}
	//十六进制转换十进制
	public static int ParseInt(String str){
		return Integer.valueOf(str, 16);
	}
	//十进制转换十六进制
	public static String ToHexString(int num){
		return Integer.toHexString(num);
	}
}
