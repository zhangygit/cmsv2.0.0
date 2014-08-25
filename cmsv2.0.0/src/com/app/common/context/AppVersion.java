package com.app.common.context;

import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AppVersion {

	private static Log log = LogFactory.getLog(AppVersion.class);
	
	private AppVersion() {}
	
	private static ResourceBundle rb = null;
	
	public static String VERSION = null;
	
	static {
		try {
			rb = ResourceBundle.getBundle("version");
		} catch (Exception e) {
			log.error("读取配置文件出错！", e);
		}
	}

	public static String get() {
		return rb != null && rb.containsKey("version")? rb.getString("version") : null;
	}

}
