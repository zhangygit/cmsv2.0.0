package com.app.common.upload;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.app.common.image.AverageImageScale;
import com.app.common.util.CMSFileUtil;
import com.app.common.util.ConvertVideo;
import com.app.common.util.Num62;



/**
 * 本地文件存储
 */
public class FileRepository implements ServletContextAware {
	private Logger log = LoggerFactory.getLogger(FileRepository.class);

	/**
	 * 日期格式化对象
	 */
	public static final DateFormat MONTH_FORMAT = new SimpleDateFormat("ddHHmmss");
	/**原始文件夹*/
	public static final String RESOURCE_VEDIO_FLOD = "resource";
	/**转化格式后文件夹*/
	public static final String CONVERT_VEDIO_FLOD = "convert";
	/**缩略图文件夹*/
	public static final String CONVERT_VEDIO_THUMB_FLOD = "thumb";
	/**缩略图文件标识*/
	public static final String THUMB_DEF_FLAG = "-thumb";
	/**最小缩略图文件标识*/
	public static final String THUMB_MIN_FLAG = "-thumb-min";
	/**中等缩略图文件标识*/
	public static final String THUMB_MEDIUM_FLAG = "-thumb-medium";
	/**最大缩略图文件标识*/
	public static final String THUMB_MAX_FLAG = "-thumb-max";
	/**缩略图文件标识*/
	/**缩略图格式*/
	public static final String DEFAULT_CONVERT_THUMB_TYPE = "jpg";
	/**缩略图格式*/
	public static final String DEFAULT_THUMB_WIDTH = "400";
	/**缩略图格式*/
	public static final String DEFAULT_THUMB_HEIGHT = "300";
	/**转化格式后文件格式*/
	public static final String DEFAULT_CONVERT_VEDIO_TYPE = "flv";
	/**flash文件格式*/
	public static final String FLASH_VEDIO_TYPE = "swf";
	
	
	
	public String storeByExt(String path, String ext, MultipartFile file)
			throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		File dest = new File(ctx.getRealPath(filename));
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		return filename;
	}
	
	/**
	 * 根据扩展名保存图片
	 * @param path
	 * @param ext
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String storeByExt4Image(String path, String ext, MultipartFile file)
			throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		String thumbFilename = getThumbPathByExt(filename,ext);
		File dest = new File(ctx.getRealPath(filename));
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		generateThumb(dest, thumbFilename);
		File thumbFile = new File(ctx.getRealPath(thumbFilename));
		AverageImageScale.resizeFix(dest, thumbFile, Integer.parseInt(DEFAULT_THUMB_WIDTH), Integer.parseInt(DEFAULT_THUMB_HEIGHT));
		return thumbFilename;
	}
	
	/**
	 * 生成缩略图 300*200  600*400  750*500
	 * @param originalFile
	 * @param thumbPath
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void generateThumb(File originalFile,String thumbPath) throws NumberFormatException, IOException{
		File thumbPathMin = new File(ctx.getRealPath(thumbPath.replace(THUMB_DEF_FLAG, THUMB_MIN_FLAG)));
		File thumbPathMedium = new File(ctx.getRealPath(thumbPath.replace(THUMB_DEF_FLAG, THUMB_MEDIUM_FLAG)));
		File thumbPathMax = new File(ctx.getRealPath(thumbPath.replace(THUMB_DEF_FLAG, THUMB_MAX_FLAG)));
		AverageImageScale.resizeFix(originalFile, thumbPathMin, 300, 200);
		AverageImageScale.resizeFix(originalFile, thumbPathMedium, 600, 400);
		AverageImageScale.resizeFix(originalFile, thumbPathMax, 750, 500);
	}
	
	
	/**
	 * 获取缩略图文件名
	 * @param filename
	 * @param ext
	 * @return
	 */
	private String getThumbPathByExt(String filename,String ext) {
		return filename.substring(0,filename.lastIndexOf(".")) + THUMB_DEF_FLAG + "." + ext;
	}
	
	/**
	 * 获取缩略图文件名
	 * @param filename
	 * @param ext
	 * @return
	 */
	private String getThumbPathByExt(String filename,String ext,int width,int height) {
		return filename.substring(0,filename.lastIndexOf(".")) + THUMB_DEF_FLAG + "." + ext;
	}

	/**
	 * 根据扩展名保存视频文件
	 * @param path
	 * @param ext
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String storeByExt4Veido(String path, String ext, MultipartFile file)
			throws IOException {
		String filename = CMSFileUtil.normalize(UploadUtils.generateFilename(path, ext));
		String saveFileName = filename;
		String filePath = CMSFileUtil.normalize(ctx.getRealPath(filename));
		String resourceFilePath = convertVedioResourcePath(filePath);
		File dest = new File(resourceFilePath);
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		//将非FLV转换为FLV格式名保存
		if(!DEFAULT_CONVERT_VEDIO_TYPE.equalsIgnoreCase(ext) && !FLASH_VEDIO_TYPE.equals(ext)){
			saveFileName = convertVedioConvertPath(convertVedioConvertType(filename));
			String convertFilePath = convertVedioConvertPath(convertVedioConvertType(filePath));
			checkMkdirIsHas(convertFilePath);
			String convertThumbPath = convertVedioConvertThumb(filePath);
			checkMkdirIsHas(convertThumbPath);
			convertVideo(resourceFilePath, convertFilePath, convertThumbPath,"22050", "6");
		}else{
			saveFileName = convertVedioResourcePath(saveFileName);
			String convertThumbPath = convertVedioConvertThumb(filePath);
			checkMkdirIsHas(convertThumbPath);
			convertVideo4Thumb(resourceFilePath, convertThumbPath);
		}
		return saveFileName;
	}

	public String storeByFilename(String filename, MultipartFile file)
			throws IOException {
		File dest = new File(ctx.getRealPath(filename));
		store(file, dest);
		return filename;
	}
	
	/**
	 * 根据文件名保存图片
	 * @param filename
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String storeByFilename4Image(String filename, MultipartFile file)
			throws IOException {
		String thumbFilename = filename;
		File dest = new File(ctx.getRealPath(filename));
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		store(file, dest);
		if(filename.indexOf(THUMB_DEF_FLAG) < 0){
			thumbFilename = getThumbPathByExt(filename, ext);
			File thumbFile = new File(ctx.getRealPath(thumbFilename));
			generateThumb(dest, thumbFilename);
			AverageImageScale.resizeFix(dest, thumbFile, Integer.parseInt(DEFAULT_THUMB_WIDTH), Integer.parseInt(DEFAULT_THUMB_HEIGHT));
		}
		return thumbFilename;
	}
	
	/**
	 * 根据文件名保存视频文件
	 * @param filename
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String storeByFilename4Vedio(String filename, MultipartFile file)
			throws IOException {
		String saveFileName = "";
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		String filePath = CMSFileUtil.normalize(ctx.getRealPath(filename));
		String convertFilePath = "";
		String resourceFilePath = "";
		String convertThumbPath = "";
		if(filePath.indexOf(CONVERT_VEDIO_FLOD) > 0){
			deleteOldVedioFile(filePath);
			convertFilePath = filePath;
			saveFileName = filename;
			resourceFilePath = convertFilePath.replace(CONVERT_VEDIO_FLOD, RESOURCE_VEDIO_FLOD);
			convertThumbPath = convertVedioConvertThumbType(convertFilePath.replace(CONVERT_VEDIO_FLOD, CONVERT_VEDIO_THUMB_FLOD));
		}else if(filePath.indexOf(RESOURCE_VEDIO_FLOD) > 0){
			deleteOldVedioFile(filePath);
			saveFileName = generalNewFileName(filename);
			resourceFilePath = CMSFileUtil.normalize(ctx.getRealPath(saveFileName));
			convertThumbPath = convertVedioConvertThumbType(resourceFilePath.replace(CONVERT_VEDIO_FLOD, CONVERT_VEDIO_THUMB_FLOD));
		}else{
			saveFileName = convertVedioConvertPath(filename);
			resourceFilePath = convertVedioResourcePath(filePath);
			convertFilePath = convertVedioConvertPath(convertVedioConvertType(filePath));
			convertThumbPath = convertVedioConvertThumb(resourceFilePath);
		}
		checkMkdirIsHas(resourceFilePath);
		checkMkdirIsHas(convertFilePath);
		checkMkdirIsHas(convertThumbPath);
		File dest = new File(resourceFilePath);
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		//将非FLV转换为FLV格式名保存
		if(!DEFAULT_CONVERT_VEDIO_TYPE.equalsIgnoreCase(ext)  && !FLASH_VEDIO_TYPE.equals(ext)){
			saveFileName = convertVedioConvertPath(convertVedioConvertType(filename));
			convertVideo(resourceFilePath, convertFilePath, convertThumbPath,"22050", "6");
		}else if(saveFileName.indexOf(RESOURCE_VEDIO_FLOD) > 0){
			convertVideo4Thumb(resourceFilePath, convertThumbPath);
		}else{
			saveFileName = saveFileName.replace(CONVERT_VEDIO_FLOD, RESOURCE_VEDIO_FLOD);
			convertVideo4Thumb(resourceFilePath, convertThumbPath);
		}
		return saveFileName;
	}

	public String storeByExt(String path, String ext, File file)
			throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		File dest = new File(ctx.getRealPath(filename));
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		return filename;
	}
	
	/**
	 * 根据扩展名保存图片
	 * @param path
	 * @param ext
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String storeByExt4Image(String path, String ext, File file)
			throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		String thumbFilename = getThumbPathByExt(filename,ext);
		File dest = new File(ctx.getRealPath(filename));
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		File thumbFile = new File(ctx.getRealPath(thumbFilename));
		generateThumb(dest, thumbFilename);
		AverageImageScale.resizeFix(dest, thumbFile, Integer.parseInt(DEFAULT_THUMB_WIDTH), Integer.parseInt(DEFAULT_THUMB_HEIGHT));
		return thumbFilename;
	}

	

	public String storeByFilename(String filename, File file)
			throws IOException {
		File dest = new File(ctx.getRealPath(filename));
		store(file, dest);
		return filename;
	}
	
	/**
	 * 根据文件名保存图片
	 * @param filename
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String storeByFilename4Image(String filename, File file)
			throws IOException {
		String thumbFilename = filename;
		File dest = new File(ctx.getRealPath(filename));
		store(file, dest);
		if(filename.indexOf(THUMB_DEF_FLAG) < 0){
			String ext = filename.substring(filename.lastIndexOf(".") + 1);
			thumbFilename = getThumbPathByExt(filename, ext);
			File thumbFile = new File(ctx.getRealPath(thumbFilename));
			AverageImageScale.resizeFix(dest, thumbFile, Integer.parseInt(DEFAULT_THUMB_WIDTH), Integer.parseInt(DEFAULT_THUMB_HEIGHT));
		}
		return thumbFilename;
	}

	private void store(MultipartFile file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());
			file.transferTo(dest);
		} catch (IOException e) {
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}

	private void store(File file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());
			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}

	public File retrieve(String name) {
		return new File(ctx.getRealPath(name));
	}

	private ServletContext ctx;

	public void setServletContext(ServletContext servletContext) {
		this.ctx = servletContext;
	}
	
	/**
	 * 转换视频为FLV格式
	 * @param sourcePath
	 * @param targetPath
	 * @param fileName
	 * @param audioRate
	 * @param picQuality
	 */
	public synchronized void convertVideo(String sourcePath, String targetPath, String convertThumbPath, String audioRate, String picQuality) {
		final ConvertVideo cv = new ConvertVideo(sourcePath, targetPath,convertThumbPath,audioRate,picQuality,DEFAULT_THUMB_WIDTH,DEFAULT_THUMB_HEIGHT);
		Runnable convertVideoRunnable = new Runnable() {
				@Override
				public void run() {
					try {
						cv.process();
					} catch (IOException e) {
						log.error(e.getMessage());
					}
				}
			};
			new Thread(convertVideoRunnable).start();
	}
	/**
	 * 生成缩略图
	 * @param sourcePath
	 * @param targetPath
	 * @param fileName
	 * @param audioRate
	 * @param picQuality
	 */
	public void convertVideo4Thumb(String sourcePath, String convertThumbPath) {
		ConvertVideo cv = new ConvertVideo(sourcePath, null,convertThumbPath,null,null);
		try {
			cv.ffmpegTransImage(DEFAULT_THUMB_WIDTH,DEFAULT_THUMB_HEIGHT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 转换上传路径为原始文件路径
	 * @param vedioPath
	 * @return
	 */
	public String convertVedioResourcePath(String vedioPath){
		String resourceFlod = vedioPath.substring(0,vedioPath.lastIndexOf("/") + 1) + RESOURCE_VEDIO_FLOD;
		//File file = new File(ctx.getRealPath(resourceFlod));
		//if(!file.exists()) FileUtil.mkdirWithPath(resourceFlod);
		return resourceFlod + vedioPath.substring(vedioPath.lastIndexOf("/"));
	}
	/**
	 * 转换上传路径为转换格式后文件路径
	 * @param vedioPath
	 * @return
	 * @throws IOException 
	 */
	public String convertVedioConvertPath(String vedioPath) throws IOException{
		String convertVedioFlod = vedioPath.substring(0,vedioPath.lastIndexOf("/") + 1) + CONVERT_VEDIO_FLOD;
		return convertVedioFlod + vedioPath.substring(vedioPath.lastIndexOf("/"));
	}
	/**
	 * 转换上传路径为转换格式后文件类型路径
	 * @param vedioPath
	 * @return
	 */
	public String convertVedioConvertType(String vedioPath){
		return  vedioPath.substring(0,vedioPath.lastIndexOf(".") + 1) + DEFAULT_CONVERT_VEDIO_TYPE;
	}
	/**
	 * 转换上传路径为转换格式后文件类型路径
	 * @param vedioPath
	 * @return
	 */
	public String convertVedioConvertThumbType(String vedioPath){
		return  vedioPath.substring(0,vedioPath.lastIndexOf(".") + 1) + DEFAULT_CONVERT_THUMB_TYPE;
	}
	/**
	 * 转换上传路径为缩略图
	 * @param vedioPath
	 * @return
	 */
	public String convertVedioConvertThumb(String vedioPath){
		String convertThumbFlod = vedioPath.substring(0,vedioPath.lastIndexOf("/") + 1) + CONVERT_VEDIO_THUMB_FLOD;
		String vedioName = vedioPath.substring(vedioPath.lastIndexOf("/"),vedioPath.lastIndexOf(".") + 1);
		return  convertThumbFlod + vedioName + DEFAULT_CONVERT_THUMB_TYPE;
	}
	/**
	 * 删除之前上传的文件
	 * @param filePath
	 */
	public void deleteOldVedioFile(String filePath){
		String replacePath = CONVERT_VEDIO_FLOD;
		if(filePath.indexOf(RESOURCE_VEDIO_FLOD) > 0){
			replacePath = RESOURCE_VEDIO_FLOD;
		}
		String resourceVedioPath = filePath.replace(replacePath, RESOURCE_VEDIO_FLOD);
		String convertVedioThumbPath = convertVedioConvertThumbType(filePath.replace(replacePath, CONVERT_VEDIO_THUMB_FLOD));
		File resourceVedioFile = new File(resourceVedioPath);
		File convertVedioFile = new File(filePath);
		File convertVedioThumbFile = new File(convertVedioThumbPath);
		if(resourceVedioFile.exists()) {
			resourceVedioFile.delete();
		}
		if(convertVedioFile.exists()) {
			convertVedioFile.delete();
		}
		if(convertVedioThumbFile.exists()) {
			convertVedioThumbFile.delete();
		}

	}
	
	/**
	 * 重新生成文件名字
	 * @param filePath
	 * @return
	 */
	public String generalNewFileName(String filePath){
		return filePath.substring(0, filePath.lastIndexOf("/") + 1) +MONTH_FORMAT.format(new Date()) + RandomStringUtils.random(4, Num62.N36_CHARS) + filePath.substring(filePath.lastIndexOf("."));
	}
	
	/**
	 * 检查路径文件是否存在（没有就创建）
	 * @param path
	 */
	public void checkMkdirIsHas(String path){
		if(StringUtils.isBlank(path)) return;
		File pathFile = new File(path.substring(0,path.lastIndexOf("/")));
		if(!pathFile.exists()) pathFile.mkdirs();
	}
	
	/**
	 * 删除文件（非项目路径）
	 * @param filePath
	 * @return
	 */
	public boolean deleteFileByPath(String filePath){
		String realFilePath = ctx.getRealPath(filePath);
		File deleteFile = new File(realFilePath);
		if(deleteFile.exists()){
			return deleteFile.delete();
		}else{
			return false;
		}
	}
	
	
	/**
	 * 获取最大缩略图
	 * @param thumbPath
	 * @return
	 */
	public static String getMaxThumb(String thumbPath){
		if(StringUtils.isNotBlank(thumbPath)){
			return thumbPath.replace(THUMB_DEF_FLAG, THUMB_MAX_FLAG);
		}
		return "";
	}
	
	/**
	 * 获取中间大小缩略图
	 * @param thumbPath
	 * @return
	 */
	public static String getMediumThumb(String thumbPath){
		if(StringUtils.isNotBlank(thumbPath)){
			return thumbPath.replace(THUMB_DEF_FLAG, THUMB_MEDIUM_FLAG);
		}
		return "";
	}
	
	/**
	 * 获取最小缩略图
	 * @param thumbPath
	 * @return
	 */
	public static String getMinThumb(String thumbPath){
		if(StringUtils.isNotBlank(thumbPath)){
			return thumbPath.replace(THUMB_DEF_FLAG, THUMB_MIN_FLAG);
		}
		return "";
	}
	
	/**
	 * 获取中间大小缩略图（600*400）
	 * @param thumbPath
	 * @return
	 */
	public static String getOriginalImg(String thumbPath){
		if(StringUtils.isNotBlank(thumbPath)){
			return thumbPath.replace(THUMB_DEF_FLAG, "");
		}
		return "";
	}
	
	public static String getMediaThumbImgPath(String mediaPath){
		if(StringUtils.isBlank(mediaPath)){
			return "";
		}
		mediaPath = mediaPath.substring(0,mediaPath.lastIndexOf(".") + 1);
		if(mediaPath.indexOf(CONVERT_VEDIO_FLOD) >= 0){
			mediaPath = mediaPath.replace(CONVERT_VEDIO_FLOD, CONVERT_VEDIO_THUMB_FLOD);
		}else if(mediaPath.indexOf(RESOURCE_VEDIO_FLOD) >= 0){
			mediaPath = mediaPath.replace(RESOURCE_VEDIO_FLOD, CONVERT_VEDIO_THUMB_FLOD);
		}
		return mediaPath + DEFAULT_CONVERT_THUMB_TYPE;
	}
}
