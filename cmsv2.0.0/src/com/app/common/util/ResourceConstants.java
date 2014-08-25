package com.app.common.util;

public abstract class ResourceConstants {

	// 资源下载审核状态-通过
	public static final String RESOURCE_SUBMISSION_STATE_PASS = "1";
	// 资源下载审核状态-未通过
	public static final String RESOURCE_SUBMISSION_STATE_NOTPASS = "2";
	// 资源转码目录名称
	public static final String RESOURCE_STORE_FOLDER_NAME = "resource_store";
	// 网盘位置
	public static final String RESOURCE_FOLDER_NAME = "resource";
	// 资源备份目录名称
	public static final String RESOURCE_BAK_NAME = "resource_bak";
	// 资源缩略图目录名称
	public static final String RESOURCE_THUMBNAIL_DIR_NAME = "thumbnail";
	// 视频转码目录名称
	public static final String RESOURCE_VIDEO_DIR_NAME = "resource_video";
	// 视频转码备份目录名称
	public static final String RESOURCE_VIDEO_BAK_NAME = "resource_video_bak";
	// 视频缩略图目录名称
	public static final String RESOURCE_VIDEO_THUMBNAIL_NAME = "__videoThumb";
	// 校内通文件上传目录
	public static final String WEB_RESOURSE_ROOT = "webresourse";
	// 校内通个人头像
	public static final String WEB_IM_IOCN ="webimiocn";
	// 个人网盘-文档节点名称
	public static final String RESOURCE_TREE_MY_DOCUMENT = "document";
	// 个人网盘-收藏节点名称
	public static final String RESOURCE_TREE_MY_FAVORITES = "favorites";
	// 个人网盘-分享节点名称
	public static final String RESOURCE_TREE_MY_SHARES = "shares";
	// 资源临时文件
	public static final String RESOURCE_TEMP = "resource_temp";
	//资源库-文件管理系统节点名称
	public static final String COMMON_RESOURCE ="CommonResource";
	//资源库-文件管理系统第一级节点名称
//	public static final String COMMON_RESOURCE_FOLDER="common_folder";
	// 日志-下载操作
	public static final String LOG_ACTION_DOWN = "down";
	// 日志-查看操作
	public static final String LOG_ACTION_VIEW = "view";
	//office文件转swf后目录名称
	public static final String RESOURCE_OFFICE_DIR_NAME = "resource_swf";
	//office文件转pdf后的目录名称
	public static final String RESOURCE_PDF_DIR_NAME = "resource_pdf";
	//默认缩略图目录名称
	public static final String RESOURCE_DEFAULT_PIC_DIRNAME ="resource_default";
	
	//资源库数字资源库节点名称
	public static final String RESOURCE_STORE = "resource_store";
	//资源库我的网盘节点名称
	public static final String RESOURCE_PERSONAL = "resource_personal";
	
	public static final String RESOURCE_PERSONAL_TREE = "resource_personal";
	
	public static final String RESOURCE_STORE_TREE = "resource_store";
	
	/**
	 * 全局设置中涉及的常量
	 */
	//资源库全局设置文件保护开关名称
	public static final String RESOURCE_FILEPROTECTION_SWITCH="fileProtection_switch";
	//资源库全局设置需要文件保护的文件类型名称
	public static final String RESOURCE_FILEPROTECTION_TYPE = "fileProtection_type";
	//资源库全局设置文件类型音频类型名称
	public static final String RESOURCE_FILETYPE_AUDIO = "fileType_audio";
	//资源库全局设置文件类型文档类型名称
	public static final String RESOURCE_FILETYPE_DOCUMENT = "fileType_document";
	//资源库全局设置文件类型图片类型名称
	public static final String RESOURCE_FILETYPE_PHOTO = "fileType_photo";
	//资源库全局设置文件类型视频类型名称
	public static final String RESOURCE_FILETYPE_VIDEO = "fileType_video";
	//资源库全局设置水印开关名称
	public static final String RESOURCE_WATERMARK_SWITCH = "waterMark_switch";
	//资源库全局设置水印类型名称
	public static final String RESOURCE_WATERMARK_TYPE = "waterMark_type";
	//资源库全局设置水印图片名称
	public static final String RESOURCE_WATERMARK_PHOTO = "waterMark_photo";
	//资源库全局设置水印文字名称
	public static final String RESOURCE_WATERMARK_FONT = "waterMark_font";
	//资源库全局设置水印文字颜色名称
	public static final String RESOURCE_WATERMARK_FONTCOLOR = "waterMark_fontColor";
	//资源库全局设置水印文字字体名称
	public static final String RESOURCE_WATERMARK_FONTSTYLE = "waterMark_fontStyle";
	//资源库全局设置水印透明度名称
	public static final String RESOURCE_WATERMARK_TRANSPARENCY = "waterMark_transparency";
	//资源库全局设置水印旋转角度名称
	public static final String RESOURCE_WATERMARK_ANGLE = "waterMark_angle";
	//资源库全局设置水印位置名称
	public static final String RESOURCE_WATERMARK_POSITION = "waterMark_position";
	//个人网盘分享到资源库审核开关 0-关 1-开
	public static final String PERSONAL_RESOURCE_AUDIT = "personal_resource_audit";
	/** Soffice外部服务配置 开始 */
	// soffice外部服务模式(对于使用远程文档转码的情况：则表示远程文档转码开启状态1:开启 0:关闭)
	public static final String RESOURCE_SOFFICE_REMOTE_MODE = "resource_soffice_remote_mode";
	// soffice外部服务地址配置(对于使用远程文档转码的情况：则表示远程文档转码服务器地址)
	public static final String RESOURCE_SOFFICE_REMOTE_PATH = "resource_soffice_remote_path";
/** Soffice外部服务配置 结束 */

}
