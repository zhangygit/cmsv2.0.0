package com.app.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;

import com.app.common.exception.FileOperationException;

public abstract class FileUtil {

	private static Log log = LogFactory.getLog(FileUtil.class);
	
	private static FileSystemManager fsm = null;
	static {
		try {
			fsm = VFS.getManager();
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("创建FileSystemManager失败");
			}
		}
	}
	
	public static FileObject getFile(String path) {
		try {
			return fsm.resolveFile(normalize(path));
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("获取文件对象失败：" + path);
			}
			return null;
		}
	}
	
	public static boolean isExistsFile(String path) {
		path = path.replace("%", "%25");
		try {
			FileObject fo = fsm.resolveFile(path);
			return fo.exists();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 判定文件是否存在
	 * 
	 * @param folderUrl
	 * @param fileName
	 * @return
	 */
	public static boolean isExistsFile(String folderUrl, String fileName) {
		folderUrl = folderUrl.replace("%", "%25");
		fileName = fileName.replace("%", "%25");		
		try {
			FileObject fo = fsm.resolveFile(normalize(folderUrl) + "/" + fileName);
			return fo.exists();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 删除文件(夹)
	 * 
	 * @param filePath	文件(夹)路径
	 */
	public static void deleteFile(String filePath) {
		try {
			FileObject fo = fsm.resolveFile(normalize(filePath));
			if(fo.exists()) {
				fo.delete(Selectors.SELECT_ALL);
			}
		} catch (Exception e) {
			
		}
	}
	
	public static boolean mkdirs(String path) {
		try {
			FileObject fo = fsm.resolveFile(normalize(path));
//			if(fo.exists()) {
//				fo.delete();
//			}
			fo.createFolder();
			return true;
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 获取文件类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName) {
		if( fileName.indexOf(".") != -1 ) {
			return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		} else {
			return fileName;
		}
	}
	
	/**
	 * 获取文件名称
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		if( fileName.indexOf(".") != -1 ) {
			return fileName.substring(0, fileName.lastIndexOf("."));
		} else {
			return fileName;
		}
	}
	
	/**
	 * 保存文件到某个目录下并返回相对于应用根目录的路径
	 * @param contextAbsolutePath 应用的绝对路径
	 * @param path
	 * @return 相对于应用根目录的路径,注意：前面没有/
	 */
	public static String saveFile2Path(InputStream file, String contextAbsolutePath, String path) {
		contextAbsolutePath = normalize(contextAbsolutePath);
		
		return null;
	}

	/**
	 * <p>规范化一个文件名称, 实现应该转 "\" 到 "/", 排除多余的 "/"
	 * 输入可以是操作系统文件名称或虚拟文件系统名称
	 * @param original 要规范化的文件名称
	 * @return 规范化后的虚拟文件名称
	 */
	public static String normalize(String original) {
		if(StringUtils.isBlank(original)) {
			return original;
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

		return original;
	}
	
	/**
     * 从路径名称中排除多余的 "/", 通常调用这个方法之前一定要把 "\" 转成 "/"
     * @return  返回排除了多余的"/"的路径
     */
    private static String eliminateRedundantSlassh(String path) {

        boolean isSlash = false;
        StringBuffer result = new StringBuffer(path.length());
        
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            
            if (c != '/' || !isSlash) {
                result.append(c);
            }
            
            isSlash = (c == '/');
        }
        return result.toString();
    }
    
    /**
	 * 创建目录，允许多级
	 * 
	 * @param path
	 * @return
	 */
	public static void mkdirWithPath(String path) {
		path = normalize(path);
		String root = path.substring(0, path.indexOf("/"));
		String fileName = path.substring(path.indexOf("/") + 1, path.length());
		
		recursiveMakeDiectory(fileName, root);
	}
	
	/**
     * 允许创建多级目录，目录名之间用/隔开，暂不支持创建失败回滚的功能
     * @param  fileName 要创建的目录名
     * @return 已创建的最底层的目录
     */
	public synchronized static File recursiveMakeDiectory(String fileName, String root) {
		root = normalize(root);
		StringTokenizer directories = new StringTokenizer(normalize(fileName), "/");
		File parent = new File(root);
		while (directories.hasMoreTokens()) {
			String name = directories.nextToken();
			parent = new File(parent, name);

			if (log.isInfoEnabled()) {
				log.info("will create the directory [" 
				                                     + parent.getAbsolutePath() + "]");
			}
			if (!parent.exists()) {
				if (log.isInfoEnabled()) {
					log.info("creating the directory [" 
							+ parent.getAbsolutePath() + "]");
				}

				if (!parent.mkdir()) {
					String msg = "fail to created the directory [" 
						+ parent.getAbsolutePath() + "]";
					if (log.isInfoEnabled()) {
						log.info(msg);
					}
					throw new FileOperationException(msg);
				}
			}
		}
		return parent;
	}
	
	/**
	 * 根据后缀判定是否是图片
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isImage(String fileName) {
		ResourceGlobalConfig globalConfig = ResourceGlobalConfig.getGlobalConfig();
		globalConfig.initFileType();
		String[] imgFilter = globalConfig.getImgFilter().split(",");
		String type = FileUtil.getFileType(fileName).toLowerCase();
		return ArrayUtils.contains(imgFilter, type);
	}
	
	/**
	 * 判断是否为视频
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isVideo(String fileName) {
		ResourceGlobalConfig globalConfig = ResourceGlobalConfig.getGlobalConfig();
		globalConfig.initFileType();
		String[] videoFilter = globalConfig.getVideoFilter().split(",");
		String type = FileUtil.getFileType(fileName).toLowerCase();
		return ArrayUtils.contains(videoFilter, type);
	}
	
	/**
	 * 判断是否为音频
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isAudio(String fileName) {
		ResourceGlobalConfig globalConfig = ResourceGlobalConfig.getGlobalConfig();
		globalConfig.initFileType();
		String[] audioFilter = globalConfig.getAudioFilter().split(",");
		String type = FileUtil.getFileType(fileName).toLowerCase();
		return ArrayUtils.contains(audioFilter, type);
	}
	
	/**
	 * 判断是否为office文件 用于在线观看
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isOffice(String fileName){
		ResourceGlobalConfig globalConfig = ResourceGlobalConfig.getGlobalConfig();
		globalConfig.initFileType();
		String[] officeFilter = globalConfig.getOfficeFilter().split(",");
		String type = FileUtil.getFileType(fileName).toLowerCase();
		return ArrayUtils.contains(officeFilter, type);
	}
	
	/**
	 * 下载文件
	 * @param request
	 * @param response
	 * @param filePath 文件路径，绝对路径
	 * @param downloadFileName 下载的文件名
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, String filePath, String downloadFileName) {
		File downloadFile = new File(normalize(filePath));
		InputStream reader = null;
		OutputStream out = null;
		
		try {
			FileObject fo = FileUtil.getFile(normalize(filePath));
			reader = fo.getContent().getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			response.setContentType("application/octet-stream");// 均不提供直接打开
			response.setHeader("Content-Disposition",
					"attachment;filename=" + HttpUtils.convert(request, downloadFileName));
			response.setContentLength((int)downloadFile.length());
			out = response.getOutputStream();
			while ((len = reader.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			log.error("读取文件["+filePath+"]报错", e);
		} finally {
			if(reader != null){
				try{
					reader.close();
				}catch(Exception ex){
					log.error("关闭文件流出错", ex);
				}
			}
			if(out != null) {
				try{
					out.close();
				}catch(Exception ex){
					log.error("关闭输出流出错", ex);
				}
			}
		}
	}
	
	/**
	 * 查看图片
	 * @param request
	 * @param response
	 * @param path 图片地址
	 */
	public static void viewImage(HttpServletRequest request, HttpServletResponse response, String path) {
		File file = new File(normalize(path));
		if(!file.exists()) {
			log.error("找不到文件[" + path + "]");
			return;
		}
		response.setContentType("image/jpg"); 
		InputStream reader = null;
		try{
			FileObject fo = FileUtil.getFile(normalize(path));
			reader = fo.getContent().getInputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			
			OutputStream out = response.getOutputStream();
			
			while ((len = reader.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
		}catch(Exception ex){
			log.error("读取文件时发生错误:" + ex.getMessage(),ex);
		} finally {
			if(reader != null){
				try{
					reader.close();
				}catch(Exception ex){
					log.error("关闭文件流出错", ex);
				}
			}
		}
	}
	
	/**
	 * 查看图片
	 * @param request
	 * @param response
	 * @param is
	 */
	public static void viewImage(HttpServletResponse response, InputStream is) {
		response.setContentType("image/jpg"); 
		try{
			byte[] buf = new byte[1024];
			int len = 0;
			
			OutputStream out = response.getOutputStream();
			
			while ((len = is.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
		}catch(Exception ex){
			log.error("读取文件时发生错误:" + ex.getMessage(), ex);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}
	
	/**
	 * 输出文件流
	 * @param response
	 * @param file
	 * @param contentType 默认以image/jpg的格式发送
	 * @param buf 默认1024
	 * @throws Exception
	 */
	public static void outputStream(HttpServletResponse response, File file, String contentType) throws Exception {
		if( null == contentType || "".equals(contentType) ) {
			contentType = "image/jpg";
		}
		int buf = 1024;
		if( null != file && file.canRead() && file.isFile() ) {
			InputStream is = null;
			try {
				is = new FileInputStream(file);
				byte[] b = new byte[buf];
				OutputStream os = response.getOutputStream();
				int len = 0;
				while((len = is.read(b)) != -1 ) {
					os.write(b, 0, len);
				}
				os.close();
			} catch (Exception e) {
				throw new FileOperationException("文件读取错误");
			} finally {
				IOUtils.closeQuietly(is);
			}
		} else {
			throw new FileOperationException("文件不存在或不是文件");
		}
	}
	
	/**
	 * 重命名同名文件
	 * 根据日期时间规则+随机数 重命名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String autoRenameFile(String fileName) {
		String fileType = FileUtil.getFileType(fileName);
		String oldName = fileName.substring(0, fileName.lastIndexOf("."));
		
		SimpleDateFormat format = new SimpleDateFormat("MMddmmss");
		Random random = new Random();
		String newName = oldName + "(" + format.format(new Date()) + random.nextInt(99)%(99-10+1) + ")." + fileType;
		
		return newName;
	}
	
	/**
	 * 复制文件(夹)
	 * 
	 * @param source
	 * @param target
	 */
	public static void copy(String source, String target) {
		try {
			FileObject sourceFO = fsm.resolveFile(source);
			FileObject targetFO = fsm.resolveFile(target);
			
			if( sourceFO.exists() )
				targetFO.copyFrom(sourceFO, Selectors.SELECT_ALL);
		} catch (Exception e) {
			log.error("复制文件失败!");
		}
	}

}
