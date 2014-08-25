package com.app.common.util;

import org.springframework.jdbc.core.JdbcTemplate;


public class ResourceGlobalConfig {

	private String imgFilter="";
	private String videoFilter="";
	private String audioFilter="";
	private String officeFilter="";
	private boolean imgProtection;
	private boolean videoProtection;
	private boolean audioProtection;
	private boolean officeProtection;
	
	private boolean isInit = false;
	private static ResourceGlobalConfig globalConfig = null;
	public static ResourceGlobalConfig getGlobalConfig(){
		if( null != globalConfig ){
			return globalConfig;
		}
		globalConfig = new ResourceGlobalConfig();
		return globalConfig;
	}
	private ResourceGlobalConfig(){
		
	}
	
	private String getValue(String key) {
		return "";
	}
	
	//初始化文件类型 且保证只初始化一次
	public void initFileType(){
		if(!isInit){
			imgFilter = getValue(ResourceConstants.RESOURCE_FILETYPE_PHOTO);
			videoFilter = getValue(ResourceConstants.RESOURCE_FILETYPE_VIDEO);
			audioFilter = getValue(ResourceConstants.RESOURCE_FILETYPE_AUDIO);
			officeFilter = getValue(ResourceConstants.RESOURCE_FILETYPE_DOCUMENT);
			String fileProtectionSwitch = getValue(ResourceConstants.RESOURCE_FILEPROTECTION_SWITCH);
			
			if("1".equals(fileProtectionSwitch)){
				String fileProtectionType = getValue(ResourceConstants.RESOURCE_FILEPROTECTION_TYPE);
				if(null != fileProtectionType){
					if(fileProtectionType.indexOf(ResourceConstants.RESOURCE_FILETYPE_PHOTO) >= 0){
						imgProtection = true;
					}else{
						imgProtection = false;
					}
					if(fileProtectionType.indexOf(ResourceConstants.RESOURCE_FILETYPE_VIDEO) >= 0){
						videoProtection = true;
					}else{
						videoProtection = false;
					}
					if(fileProtectionType.indexOf(ResourceConstants.RESOURCE_FILETYPE_AUDIO) >= 0){
						audioProtection = true;
					}else{
						audioProtection = false;
					}
					if(fileProtectionType.indexOf(ResourceConstants.RESOURCE_FILETYPE_DOCUMENT) >= 0){
						officeProtection = true;
					}else{
						officeProtection = false;
					}
					
				}else{
					imgProtection = false;
					videoProtection = false;
					audioProtection = false;
					officeProtection = false;
				}
			}else{
				imgProtection = false;
				videoProtection = false;
				audioProtection = false;
				officeProtection = false;
			}
			isInit = true;
		}
	}
	//刷新文件类型
	public void reFreshFileType(){
		isInit = false;
		this.initFileType();
	}
	public String getImgFilter() {
		return imgFilter;
	}
	public void setImgFilter(String imgFilter) {
		this.imgFilter = imgFilter;
	}
	public String getVideoFilter() {
		return videoFilter;
	}
	public void setVideoFilter(String videoFilter) {
		this.videoFilter = videoFilter;
	}
	public String getAudioFilter() {
		return audioFilter;
	}
	public void setAudioFilter(String audioFilter) {
		this.audioFilter = audioFilter;
	}
	public String getOfficeFilter() {
		return officeFilter;
	}
	public void setOfficeFilter(String officeFilter) {
		this.officeFilter = officeFilter;
	}
	public boolean isInit() {
		return isInit;
	}
	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}
	public boolean isImgProtection() {
		return imgProtection;
	}
	public void setImgProtection(boolean imgProtection) {
		this.imgProtection = imgProtection;
	}
	public boolean isVideoProtection() {
		return videoProtection;
	}
	public void setVideoProtection(boolean videoProtection) {
		this.videoProtection = videoProtection;
	}
	public boolean isAudioProtection() {
		return audioProtection;
	}
	public void setAudioProtection(boolean audioProtection) {
		this.audioProtection = audioProtection;
	}
	public boolean isOfficeProtection() {
		return officeProtection;
	}
	public void setOfficeProtection(boolean officeProtection) {
		this.officeProtection = officeProtection;
	}
	
	

}
