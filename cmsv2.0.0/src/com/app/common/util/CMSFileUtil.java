package com.app.common.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.util.FileUtil;


public class CMSFileUtil {

	
	
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	
	private static final String SVN_FILE_EXT = "SVN";
	
	
	public static final List<String> excludeFileExtList = ConventionUtils.toList(SVN_FILE_EXT);
	
	
	/**
     * 格式化路径分隔符（自VFSUtil中Copy）
     * @param original
     * @return
     */
	public static String normalize(String original) {
        if (log.isDebugEnabled()) {
        	log.debug("normalize前[" + original + "]");
        }
        
        original = original.replace('\\', '/');
        original = eliminateRedundantSlassh(original);
        
        /**
         * in Linux(Unix like) system, must add prefix "/", unckecked,
         * and in WindowNT, if there is no ":", shoulb add one.
         */
        if (original.indexOf(':') == -1) {
            if (!original.startsWith("/")) {
                original = "/" + original;
            }
        } else {
            if (original.startsWith("/")) {
                original = original.substring(1);
            } 
        }

        if (original.endsWith("/")) {
            original = original.substring(0, original.length() - 1);
        }
        
        if (log.isDebugEnabled()) {
        	log.debug("normalize后[" + original + "]");
        }
        
        return original;
    }
	
	/**
	 * （自VFSUtil中Copy）
     * 从路径名称中排除多余的 "/", 如果路径中有"\", 结果我没有测试过; 通常调用这个方法之前
     * 一定要把 "\" 转成 "/"
     * @return  返回排除了多余的"/"的路径
     */
	public static String eliminateRedundantSlassh(String path) {
        if (log.isDebugEnabled()) {
        	log.debug("要排除多余的'/'之前[" + path + "]");
        }
        
        boolean isSlash = false;
        StringBuffer result = new StringBuffer(path.length());
        
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            
            if (c != '/' || !isSlash) {
                result.append(c);
            }
            
            isSlash = (c == '/');
        }

        if (log.isDebugEnabled()) {
        	log.debug("要排除多余的'/'之前[" + path + "]");
        }
        
        return result.toString();
    }

}
