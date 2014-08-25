package com.app.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class RegexpUtil {

	
	public static boolean isMatch(String str, String pattern) {
		return Pattern.compile(pattern).matcher(str).matches();
	}
	
	/**
	 * 解析字符串中符合条件的所有分组（不包括自己）
	 * @param content
	 * @param regexp
	 * @param pattern
	 * @return
	 */
	public static List<String> getAllGroups(String content, String regexp, int pattern) {
		Pattern p = Pattern.compile(regexp, pattern);
		Matcher m = p.matcher(content);
		List<String> matchList = new ArrayList<String>();
		while (m.find()) {
			int groupCount = m.groupCount();
			for (int i = 1; i <= groupCount; i++) {
				matchList.add(m.group(i));
			}
		}
		return matchList;
	}
	
	public static void main(String[] args) {
		System.out.println(RegexpUtil.isMatch("1或者(2并且3)", "\\d+\\s*(并且|或者)\\s*\\d+"));
	}

}
