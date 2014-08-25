package com.app.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;


public class HttpUtils {

	/** 判断是否是中文的正则表达式 */
	private static final String REGEXP_CHINESE = "^[\\u0391-\\uFFE5]+$";
	
	/**
	 * 中文编码转换, 典型的情况是下载中文名的文件时, 浏览器不能正确地显示汉字
	 */
	public static String convert(HttpServletRequest request, String fileName) {
		if (fileName == null) {
			throw new IllegalArgumentException("输入参数是null");
		}
		try{
			String agent = request.getHeader("USER-AGENT");  
			if (null != agent && (-1 != agent.indexOf("MSIE") || -1 != agent.indexOf("Trident"))) {
				return URLEncoder.encode(fileName, "UTF8").replace("+", "%20");  
			}else if (null != agent && -1 != agent.indexOf("Safari")) {  
				return new String(fileName.getBytes("utf-8"), "ISO8859-1");  
			}else if (null != agent && -1 != agent.indexOf("Mozilla")) {  
				return "=?UTF-8?B?"+(new String(Base64.encodeBase64(fileName.getBytes("UTF-8"))))+"?=";  
			}  else {  
				return fileName;  
			}
		}catch(UnsupportedEncodingException ex){
			return fileName;
		}
	}

	/**
	 * 对URL进行编码，一般传递中文的时候用这个方法对url进行编码，与客户端的appEncodeURL方法保持一致
	 * @param url
	 * @return
	 */
	public static String appEncodeURL(String src) {
		if(StringUtils.isEmpty(src)) {
			return src;
		}
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length()*6);

		for (i=0 ; i< src.length() ;i++ ){
			j = src.charAt(i);
			if(RegexpUtil.isMatch(String.valueOf(j), REGEXP_CHINESE)) {
				String chinese;
				if (j < 256){
					chinese = "%" ;
					if (j < 16)
						chinese+= "0";
					chinese += Integer.toString(j,16);
				}else{
					chinese = "%u";
					chinese += Integer.toString(j,16);
				}
				try {
					tmp.append(URLEncoder.encode(chinese, "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					tmp.append(chinese);
				}
			}else{
				tmp.append(j);
			}
		}
		src = tmp.toString();
		if(!src.contains("_app_encoding_tag_")) {
			if(src.contains("?")) {
				src += "&_app_encoding_tag_=1";
			}else{
				src += "?_app_encoding_tag_=1";
			}
		}
		return src;
	}
	
	/**
	 * 对URL参数进行编码，注意编码后url地址需要添加参数：_app_encoding_tag_=1
	 * @param url
	 * @return
	 */
	public static String appEncodeParam(String src) {
		if(StringUtils.isEmpty(src)) {
			return src;
		}
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length()*6);

		for (i=0 ; i< src.length() ;i++ ){
			j = src.charAt(i);
			String chinese;
			if (j < 256){
				chinese = "%" ;
				if (j < 16)
					chinese+= "0";
				chinese += Integer.toString(j,16);
			}else{
				chinese = "%u";
				chinese += Integer.toString(j,16);
			}
			try {
				tmp.append(URLEncoder.encode(chinese, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				tmp.append(chinese);
			}
		}
		return tmp.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(appEncodeURL("http://www.baidu.com?name=生僻 字鬟"));
	}

}
