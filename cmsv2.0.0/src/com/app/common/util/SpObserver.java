package com.app.common.util;

public class SpObserver {

	private static ThreadLocal<String> local = new ThreadLocal<String>();

	public static void putSp(String sp) {
		local.set(sp);
	}

	public static String getSp() {
		return (String)local.get();
	}
	
	public static void clear(){
		local.remove();
	}

}
