package com.app.common.upload;

public class UploadConstants {


	
	/** cms在vfs路径标识(vfs根目录第一层级) */
	public static final String VFSPATH_4CMS_FLAG = "CMS";
	/** cms视频在vfs路径标识 */
	public static final String VFSPATH_4CMS_VEDIO_FLAG = "VEDIO";
	/** cms图片在vfs路径标识 */
	public static final String VFSPATH_4CMS_IMG_FLAG = "IMG";
	/**cms附件vfs路径标识*/
	public static final String VFSPATH_4CMS_ATTACH_FLAG = "attach";
	/** cms视频原文件在vfs路径标识 */
	public static final String VFSPATH_4CMS_VEDIOSOURCE_FLAG = "VEDIOSOURCE";
	/** cms视频转码文件在vfs路径标识 */
	public static final String VFSPATH_4CMS_VEDIOCONVERT_FLAG = "VEDIOCONVERT";
	/** cms视频缩略图在vfs路径标识 */
	public static final String VFSPATH_4CMS_VEDIOTHUMB_FLAG = "VEDIOTHUMB";
	
	/**最小缩略图文件标识 300*200 */
	public static final String THUMB_MIN_FLAG = "-thumb-min";
	/**缩略图文件标识 400*300 */
	public static final String THUMB_DEF_FLAG = "-thumb";
	/**中等缩略图文件标识 600*400 */
	public static final String THUMB_MEDIUM_FLAG = "-thumb-medium";
	/**最大缩略图文件标识  750*500 */
	public static final String THUMB_MAX_FLAG = "-thumb-max";
	/**缩略图格式*/
	public static final String DEFAULT_THUMB_WIDTH = "400";
	/**缩略图格式*/
	public static final String DEFAULT_THUMB_HEIGHT = "300";
	/**缩略图格式*/
	public static final String DEFAULT_CONVERT_THUMB_TYPE = "jpg";
	/**转化格式后文件格式*/
	public static final String DEFAULT_CONVERT_VEDIO_TYPE = "flv";
	/**flash文件格式*/
	public static final String FLASH_VEDIO_TYPE = "swf";

}
