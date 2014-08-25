package com.app.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ConvertPathUtil {

	
	private static final String DEFAULT_CONVERT_ROOT_PATH_WINDOWS = "D:/cms_service/converter/win";
	
	private static final String DEFAULT_CONVERT_ROOT_PATH_LINUX = "/opt/cms_service/converter/linux";
	
	private static String CONVERT_ROOT_PATH = "";
	
	private static Log log = LogFactory.getLog(ConvertPathUtil.class);
	
    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();
    public static final boolean isWindows = isWindows();
    public static final boolean isLinux = isLinux();
    
	/** convert 根路径(最后没有/号) */
	static {
		
		try {
			readConvertRootPath();//给CONVERT_ROOT_PATH赋初始值
		} catch (Exception e) {
			log.error("获取convert路径出错！", e);
		}
	}
	
	/**
	 * 读取convert路径(先从数据库中读取,否则读取默认配置)
	 */
	private static void readConvertRootPath() {
		String key = null;
		String sql = "select value from gy_config where config_id = ?";

		if (isWindows) {
			key = "CONVERT_ROOT_PATH_WINDOWS";
		} else {
			key = "CONVERT_ROOT_PATH_LINUX";
		}
		
		// convert路径获取，采用默认路径
		//获取默认配置
		if (isWindows) {
			CONVERT_ROOT_PATH = DEFAULT_CONVERT_ROOT_PATH_WINDOWS;
		} else {
			CONVERT_ROOT_PATH = DEFAULT_CONVERT_ROOT_PATH_LINUX;
		}
		debug("***读取CONVERT_ROOT_PATH默认配置成功：" + CONVERT_ROOT_PATH + "***");
	}
	
	/**
	 * 获取当前的ConvertRootPath  
	 * @return CONVERT_ROOT_PATH
	 */
	public static String getConvertRootPath(){
		return CONVERT_ROOT_PATH;
	}
	
	private static void debug(String msg) {
		if( log.isDebugEnabled() ) {
			log.debug(msg);
		}
	}
	
    private static boolean isLinux() {
        return OS_NAME.startsWith("linux");
    }

    private static boolean isWindows() {
        return OS_NAME.startsWith("windows");
    }


}
