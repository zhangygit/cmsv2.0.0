package com.app.common.upload;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import com.app.common.util.Num62;
import com.app.common.util.UUIDHexGenerator;
import com.app.common.util.VFSUtil;

public class UploadUtils {
	/**
	 * 日期格式化对象
	 */
	public static final DateFormat MONTH_FORMAT = new SimpleDateFormat(
			"/yyyyMM/ddHHmmss");

	/**
	 * 日期格式化对象
	 */
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("/yyyyMMdd/");
	
	public static String generateFilename(String path, String ext) {
		return path + MONTH_FORMAT.format(new Date())
				+ RandomStringUtils.random(4, Num62.N36_CHARS) + "." + ext;
	}

	protected static final Pattern ILLEGAL_CURRENT_FOLDER_PATTERN = Pattern
			.compile("^[^/]|[^/]$|/\\.{1,2}|\\\\|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}");

	/**
	 * Sanitizes a filename from certain chars.<br />
	 * 
	 * This method enforces the <code>forceSingleExtension</code> property and
	 * then replaces all occurrences of \, /, |, :, ?, *, &quot;, &lt;, &gt;,
	 * control chars by _ (underscore).
	 * 
	 * @param filename
	 *            a potentially 'malicious' filename
	 * @return sanitized filename
	 */
	public static String sanitizeFileName(final String filename) {

		if (StringUtils.isBlank(filename))
			return filename;

		String name = forceSingleExtension(filename);

		// Remove \ / | : ? * " < > 'Control Chars' with _
		return name.replaceAll("\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
	}

	/**
	 * Sanitizes a folder name from certain chars.<br />
	 * 
	 * This method replaces all occurrences of \, /, |, :, ?, *, &quot;, &lt;,
	 * &gt;, control chars by _ (underscore).
	 * 
	 * @param folderName
	 *            a potentially 'malicious' folder name
	 * @return sanitized folder name
	 */
	public static String sanitizeFolderName(final String folderName) {

		if (StringUtils.isBlank(folderName))
			return folderName;

		// Remove . \ / | : ? * " < > 'Control Chars' with _
		return folderName.replaceAll(
				"\\.|\\\\|/|\\||:|\\?|\\*|\"|<|>|\\p{Cntrl}", "_");
	}

	/**
	 * Checks whether a path complies with the FCKeditor File Browser <a href="http://docs.fckeditor.net/FCKeditor_2.x/Developers_Guide/Server_Side_Integration#File_Browser_Requests"
	 * target="_blank">rules</a>.
	 * 
	 * @param path
	 *            a potentially 'malicious' path
	 * @return <code>true</code> if path complies with the rules, else
	 *         <code>false</code>
	 */
	public static boolean isValidPath(final String path) {
		if (StringUtils.isBlank(path))
			return false;

		if (ILLEGAL_CURRENT_FOLDER_PATTERN.matcher(path).find())
			return false;

		return true;
	}

	/**
	 * Replaces all dots in a filename with underscores except the last one.
	 * 
	 * @param filename
	 *            filename to sanitize
	 * @return string with a single dot only
	 */
	public static String forceSingleExtension(final String filename) {
		return filename.replaceAll("\\.(?![^.]+$)", "_");
	}

	/**
	 * Checks if a filename contains more than one dot.
	 * 
	 * @param filename
	 *            filename to check
	 * @return <code>true</code> if filename contains severals dots, else
	 *         <code>false</code>
	 */
	public static boolean isSingleExtension(final String filename) {
		return filename.matches("[^\\.]+\\.[^\\.]+");
	}

	/**
	 * Checks a directory for existence and creates it if non-existent.
	 * 
	 * @param dir
	 *            directory to check/create
	 */
	public static void checkDirAndCreate(File dir) {
		if (!dir.exists())
			dir.mkdirs();
	}

	/**
	 * Iterates over a base name and returns the first non-existent file.<br />
	 * This method extracts a file's base name, iterates over it until the first
	 * non-existent appearance with <code>basename(n).ext</code>. Where n is a
	 * positive integer starting from one.
	 * 
	 * @param file
	 *            base file
	 * @return first non-existent file
	 */
	public static File getUniqueFile(final File file) {
		if (!file.exists())
			return file;

		File tmpFile = new File(file.getAbsolutePath());
		File parentDir = tmpFile.getParentFile();
		int count = 1;
		String extension = FilenameUtils.getExtension(tmpFile.getName());
		String baseName = FilenameUtils.getBaseName(tmpFile.getName());
		do {
			tmpFile = new File(parentDir, baseName + "(" + count++ + ")."
					+ extension);
		} while (tmpFile.exists());
		return tmpFile;
	}

	
	/** 获取日期字符串 @eg:/20131112/ */
	public static String generateDateStr(){
		return DATE_FORMAT.format(new Date());
	}
	
	/** 生成文件名*/
	public static String generateFileNameStr(){
		return UUIDHexGenerator.get();
	}
	
	/** 获取VFS下视频原文件绝对路径 */
	public static String getVFSVedioSourcePathBySitePathAndExt(String sitePath,String fileExt){
		String filePath = UploadConstants.VFSPATH_4CMS_FLAG + "/" + sitePath + "/" + UploadUtils.generateDateStr() 
			+ "/" + UploadConstants.VFSPATH_4CMS_VEDIO_FLAG + "/" + UploadConstants.VFSPATH_4CMS_VEDIOSOURCE_FLAG 
			+ "/" + UploadUtils.generateFileNameStr() + "." + fileExt;
		return VFSUtil.getVFSPath(filePath);
	}
	
	/** 获取VFS下视频原文件路径 */
	public static String getVedioSourcePathBySitePathAndExt(String sitePath,String fileExt){
		String filePath = UploadConstants.VFSPATH_4CMS_FLAG + "/" + sitePath + "/" + UploadUtils.generateDateStr() 
		+ "/" + UploadConstants.VFSPATH_4CMS_VEDIO_FLAG + "/" + UploadConstants.VFSPATH_4CMS_VEDIOSOURCE_FLAG 
		+ "/" + UploadUtils.generateFileNameStr() + "." + fileExt;
		return filePath;
	}
	
	/** 获取VFS下视频原文件路径 */
	public static String getImgPathBySitePathAndExt(String sitePath,String fileExt){
		String filePath = UploadConstants.VFSPATH_4CMS_FLAG + "/" + sitePath + "/" + UploadUtils.generateDateStr() 
		+ "/" + UploadConstants.VFSPATH_4CMS_IMG_FLAG + "/" + UploadUtils.generateFileNameStr() + "." + fileExt;
		return filePath;
	}
	
	/** 获取VFS下视频转码文件绝对路径 */
	public static String getVedioConvertPathBySitePathAndFileName(String sitePath,String fileName){
		String filePath = UploadConstants.VFSPATH_4CMS_FLAG + "/" + sitePath + "/" + UploadUtils.generateDateStr() 
		+ "/" + UploadConstants.VFSPATH_4CMS_VEDIO_FLAG + "/" + UploadConstants.VFSPATH_4CMS_VEDIOCONVERT_FLAG 
		+ "/" + fileName + "." + UploadConstants.DEFAULT_CONVERT_VEDIO_TYPE; 
		return filePath;
	}
	
	/** 获取VFS下视频转码文件绝对路径 */
	public static String getVFSVedioConvertPathBySitePathAndFileName(String sitePath,String fileName){
		String filePath = UploadConstants.VFSPATH_4CMS_FLAG + "/" + sitePath + "/" + UploadUtils.generateDateStr() 
		+ "/" + UploadConstants.VFSPATH_4CMS_VEDIO_FLAG + "/" + UploadConstants.VFSPATH_4CMS_VEDIOCONVERT_FLAG 
		+ "/" + fileName + "." + UploadConstants.DEFAULT_CONVERT_VEDIO_TYPE; 
		return VFSUtil.getVFSPath(filePath);
	}
	/** 获取VFS下视频文件默认缩略图绝对路径 */
	public static String getVFSDefVedioThumbPathBySitePathAndFileName(String sitePath,String fileName){
		String filePath = UploadConstants.VFSPATH_4CMS_FLAG + "/" + sitePath + "/" + UploadUtils.generateDateStr() 
		+ "/" + UploadConstants.VFSPATH_4CMS_VEDIO_FLAG + "/" + UploadConstants.VFSPATH_4CMS_VEDIOTHUMB_FLAG
		+ "/" + fileName + UploadConstants.THUMB_DEF_FLAG + "." + UploadConstants.DEFAULT_CONVERT_THUMB_TYPE; 
		return VFSUtil.getVFSPath(filePath);
	}
	/** 获取VFS下视频文件默认缩略图绝对路径 */
	public static String getDefVedioThumbPathBySitePathAndFileName(String sitePath,String fileName){
		String filePath = UploadConstants.VFSPATH_4CMS_FLAG + "/" + sitePath + "/" + UploadUtils.generateDateStr() 
		+ "/" + UploadConstants.VFSPATH_4CMS_VEDIO_FLAG + "/" + UploadConstants.VFSPATH_4CMS_VEDIOTHUMB_FLAG
		+ "/" + fileName + UploadConstants.THUMB_DEF_FLAG + "." + UploadConstants.DEFAULT_CONVERT_THUMB_TYPE; 
		return filePath;
	}
	/** 获取VFS下视频文件最小缩略图绝对路径 */
	public static String getVFSMinVedioThumbPathBySitePathAndFileName(String sitePath,String fileName){
		String filePath = UploadConstants.VFSPATH_4CMS_FLAG + "/" + sitePath + "/" + UploadUtils.generateDateStr() 
		+ "/" + UploadConstants.VFSPATH_4CMS_VEDIO_FLAG + "/" + UploadConstants.VFSPATH_4CMS_VEDIOTHUMB_FLAG
		+ "/" + fileName + UploadConstants.THUMB_MIN_FLAG + "." + UploadConstants.DEFAULT_CONVERT_THUMB_TYPE; 
		return VFSUtil.getVFSPath(filePath);
	}
	/** 获取VFS下视频文件中间缩略图绝对路径 */
	public static String getVFSMediumVedioThumbPathBySitePathAndFileName(String sitePath,String fileName){
		String filePath = UploadConstants.VFSPATH_4CMS_FLAG + "/" + sitePath + "/" + UploadUtils.generateDateStr() 
		+ "/" + UploadConstants.VFSPATH_4CMS_VEDIO_FLAG + "/" + UploadConstants.VFSPATH_4CMS_VEDIOTHUMB_FLAG
		+ "/" + fileName + UploadConstants.THUMB_MEDIUM_FLAG + "." + UploadConstants.DEFAULT_CONVERT_THUMB_TYPE; 
		return VFSUtil.getVFSPath(filePath);
	}
	/** 获取VFS下视频文件最大缩略图绝对路径 */
	public static String getVFSMaxVedioThumbPathBySitePathAndFileName(String sitePath,String fileName){
		String filePath = UploadConstants.VFSPATH_4CMS_FLAG + "/" + sitePath + "/" + UploadUtils.generateDateStr() 
		+ "/" + UploadConstants.VFSPATH_4CMS_VEDIO_FLAG + "/" + UploadConstants.VFSPATH_4CMS_VEDIOTHUMB_FLAG
		+ "/" + fileName + UploadConstants.THUMB_MAX_FLAG + "." + UploadConstants.DEFAULT_CONVERT_THUMB_TYPE; 
		return VFSUtil.getVFSPath(filePath);
	}
	
	
	public static void main(String[] args) {
		System.out.println(generateFilename("/base", "gif"));
	}

}
