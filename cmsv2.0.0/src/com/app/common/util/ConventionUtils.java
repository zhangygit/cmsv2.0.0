package com.app.common.util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ClassUtils;


public class ConventionUtils {

	
	private static Log logger = LogFactory.getLog(ConventionUtils.class);
	/** 属性值不为null的标记*/
	private static int PROPVALUE_NOT_NULL_FLAG = 1;
	/**属性值不忽略null的标记*/
	private static int PROPVALUE_NOT_IGNORE_NULL_FLAG = 3;
	/** 属性值不为null和空字符串的标记**/
	private static int PROPVALUE_NOT_EMPTY_FLAG = 2;


	/**
	 * 把map中的key 由数据库列的形式 转成 java bean形式，例如 ROLE_ID 转成roleId
	 * 
	 * @param map
	 * @return
	 */
	public static Map converseMap(Map map) {
		Map resutlMap = new HashMap();
		Set keySet = map.keySet();
		for (Iterator iter = keySet.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			resutlMap.put(translateFromColumnToProperty(key), map.get(key));
		}
		return resutlMap;
	}
	
	
	/**
	 * 拷贝对应的BaseObject的属性值到DBOColumn中(如果属性值为空则不拷贝)
	 * @param origObject 
	 * @param table 对象对应的表结构
	 * @param convetionStrategy java属性和数据库字段的转换策略
	 * @return List nested ColumnBean 拷贝后的结果(忽略BaseObject中属性值为空的属性)
	 */
	/*public static List copyProperty2DBOColumn(BaseObject origObject,TableBean table, IConvetionStrategy convetionStrategy){
		ArrayList resultList = new ArrayList();
		if(origObject != null){
			PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(origObject);
			if(!ArrayUtils.isEmpty(properties)){
				for(int i = 0; i < properties.length; i++){
					String propertyName = properties[i].getName();
					if(!PropertyUtils.isReadable(origObject, propertyName)){
						continue;
					}
					ColumnBean column = table.getColumnByProp(propertyName, convetionStrategy);
					try{
						Object propertyValue = ObjectUtils.defaultIfNull(PropertyUtils.getProperty(origObject, propertyName),"");
						if(column != null && StringUtils.isNotBlank(String.valueOf(propertyValue))){
							column.setPropValue(propertyValue);
							resultList.add(column);
						}
					}catch(Exception ex){
						logger.info(ex);
					}
				}
			}
		}
		return resultList;
	} */
	
	/**
	 * 从数据库字段到java属性名的转换
	 * <p>
	 * 将一个字符串中的大写字母转换成下划线加小写字母,如:ROLE_ID 转换成 roleId
	 * <p>
	 * 注意：如果首字符为小写而接下来就是大写时(例如属性为：fKey)，这种要将首字符大写(将fKey转换为FKey),以便于拷贝属性的时候与反射得到的类的属性值一致
	 * @param src
	 * @return
	 */
	public static String translateFromColumnToProperty(String src) {
		if (src.indexOf("_") < 0) {
			return src.toLowerCase();
		}
		StringBuffer des = new StringBuffer();
		char[] chars = src.toCharArray();
		boolean isUp = false;
		for (int i = 0; i < chars.length; i++) {
			if (src.charAt(i) == '_') {
				isUp = true;
			} else {
				if (isUp) {
					des.append(Character.toUpperCase(chars[i]));
				} else {
					des.append(Character.toLowerCase(chars[i]));
				}
				isUp = false;
			}
		}
		/**
		 * 如果首字符为小写而接下来就是大写时(例如属性为：fKey)，
		 *  {@link Introspector#decapitalize(String)}方法会判断这种属性名称是缩写,
		 *  用PropertyDescriptor返回时的名称就变成了FKey,所以要考虑转换的时候考虑将fKey转换为FKey
		 */
		if(des.length() > 1 && Character.isLowerCase(des.charAt(0)) && Character.isUpperCase(des.charAt(1))){
			des.setCharAt(0, Character.toUpperCase(des.charAt(0)));
		}
		return des.toString();
	}

	/**
	 * 从java属性名到数据库字段名的转换
	 * <p>
	 * 将一个字符串中的大写字母转换成下划线加小写字母,如:roleId 转换成 role_id
	 * 
	 * @param src
	 * @return
	 */
	public static String translateFromPropertyToColumn(String src) {
		StringBuffer des = new StringBuffer();
		char[] chars = src.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Character.isUpperCase(src.charAt(i))) {
				des.append("_" + Character.toLowerCase(chars[i]));
			} else {
				des.append(chars[i]);
			}
		}
		return des.toString();
	}
	
	
	
	
	
	
	
	public static String firstLetterUpper(String s) {
		if (null == s)
			return null;
		else if (s.length() == 0)
			return s;
		else {
			return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
		}
	}

	public static String firstLetterLower(String s) {
		if (null == s)
			return null;
		else if (s.length() == 0)
			return s;
		else {
			return s.substring(0, 1).toLowerCase() + s.substring(1, s.length());
		}
	}

	public static String getClassPart(String fullClassName) {
		if (null == fullClassName)
			return null;
		int index = fullClassName.lastIndexOf(".");
		if (index == -1)
			return fullClassName;
		else
			return fullClassName.substring(index + 1, fullClassName.length());
	}

	private static final char[] vowelList = { 'A', 'E', 'I', 'O', 'U' };

	public static String getPropName(String tableName) {
		if (tableName.toUpperCase().equals(tableName)) {
			boolean vowelsFound = false;
			for (int i = 0; i < tableName.toCharArray().length; i++) {
				char c = tableName.toCharArray()[i];
				for (int j = 0; j < vowelList.length; j++) {
					if (c == vowelList[j])
						vowelsFound = true;
				}
			}
			if (vowelsFound) {
				tableName = tableName.toLowerCase();
			}
		}
		return getJavaNameCap(tableName);
	}

	public static String getJavaNameCap(String s) {
		if (s.indexOf("_") < 0 && s.indexOf("-") < 0) {
			return firstLetterUpper(s);
		} else {
			StringBuffer sb = new StringBuffer();
			boolean upper = true;
			char[] arr = s.toCharArray();
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == '_' || arr[i] == '-')
					upper = true;
				else if (upper) {
					sb.append(Character.toUpperCase(arr[i]));
					upper = false;
				} else
					sb.append(Character.toLowerCase(arr[i]));
			}
			return sb.toString();
		}
	}

	public static String getJavaName(String s) {
		if (null == s)
			return null;
		else
			return firstLetterLower(getJavaNameCap(s));
	}

	public static String getPackagePart(String fullClassName) {
		if (null == fullClassName)
			return null;
		int index = fullClassName.lastIndexOf(".");
		if (index == -1)
			return "";
		else
			return fullClassName.substring(0, index);
	}

	public static String addPackageExtension(String packageStr, String extension) {
		if (null == packageStr || packageStr.trim().length() == 0)
			return extension;
		else
			return packageStr + "." + extension;
	}

	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}

	

	/**
	 * Replace the contents of the from string with the contents of the to
	 * string in the base string
	 * 
	 * @param base
	 *            the string to replace part of
	 * @param from
	 *            the string to be replaced
	 * @param to
	 *            the string to replace
	 */
	public static String stringReplace(String base, String from, String to) {
		StringBuffer sb1 = new StringBuffer(base);
		StringBuffer sb2 = new StringBuffer(base.length() + 50);
		char[] f = from.toCharArray();
		char[] t = to.toCharArray();
		char[] test = new char[f.length];

		for (int x = 0; x < sb1.length(); x++) {

			if (x + test.length > sb1.length()) {
				sb2.append(sb1.charAt(x));
			} else {
				sb1.getChars(x, x + test.length, test, 0);
				if (aEquals(test, f)) {
					sb2.append(t);
					x = x + test.length - 1;
				} else {
					sb2.append(sb1.charAt(x));
				}
			}
		}
		return sb2.toString();
	}

	static private boolean aEquals(char[] a, char[] b) {
		if (a.length != b.length) {
			return false;
		}
		for (int x = 0; x < a.length; x++) {
			if (a[x] != b[x]) {
				return false;
			}
		}
		return true;
	}

	public static String lowerFirst(String string) {
		char firstChar = string.charAt(0);
		String firstCharStr = String.valueOf(firstChar).toUpperCase();
		return firstCharStr + string.substring(1);
	}
	
	public static int getJdbcType(String datatype) {
		int jdbcType = 0;
		if ("BLOB".equals(datatype)) {
			jdbcType = Types.BLOB;
		} else if ("CHAR".equals(datatype)) {
			jdbcType = Types.CHAR;
		} else if ("CLOB".equals(datatype)) {
			jdbcType = Types.CLOB;
		} else if ("DATE".equals(datatype)) {
			jdbcType = Types.DATE;
		} else if ("FLOAT".equals(datatype)) {
			jdbcType = Types.FLOAT;
		} else if ("LONG".equals(datatype)) {
			jdbcType = Types.LONGVARCHAR;
		} else if ("NUMBER".equals(datatype)) {
			jdbcType = Types.NUMERIC;
		} else if ("TIMESTAMP".equals(datatype)) {
			jdbcType = Types.TIMESTAMP;
		} else if ("VARCHAR2".equals(datatype)) {
			jdbcType = Types.VARCHAR;
		}
		return jdbcType;
	}
	
	
	public static Map toMap(Object... objs) {
		Map map = new HashMap();
		Object key = null;
		for (int i = 0; i < objs.length; i++) {
			if(i%2 == 0){
				key = objs[i];
			}else{
				map.put(key, objs[i]);
			}
		}
		return map;
	}
	
	public static Map m(Object... objs) {
		return toMap(objs);
	}
	
	public static Map map(Object... objs) {
		return toMap(objs);
	}
	
	public static List toList(Object... objs) {
		List result = new ArrayList();
		for (int i = 0; i < objs.length; i++) {
			result.add(objs[i]);
		}
		return result;
	}

	public static List list(Object... objs) {
		return toList(objs);
	}


}
