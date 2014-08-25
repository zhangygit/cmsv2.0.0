package com.app.cms.entity.main.base;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.app.common.upload.FileRepository;




/**
 * This is an object that contains data related to the jc_content table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_content"
 */

public abstract class BaseContentPicture  implements Serializable {

	public static String REF = "ContentPicture";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_IMG_PATH = "imgPath";
	public static String PROP_PRIORITY = "priority";

	// constructors
	public BaseContentPicture () {
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseContentPicture (
		java.lang.String imgPath) {

		this.setImgPath(imgPath);
		initialize();
	}

	protected void initialize () {}



	// fields
	private java.lang.String imgPath;
	private java.lang.String description;
	private java.lang.Integer priority;
	private java.lang.String originalPath;
	private java.lang.String defThumb;
	private java.lang.String minThumb;
	private java.lang.String meduimThumb;
	private java.lang.String maxThumb;


		

	/**
	 * Return the value associated with the column: img_path
	 */
	public java.lang.String getImgPath () {
		return imgPath;
	}

	/**
	 * Set the value related to the column: img_path
	 * @param imgPath the img_path value
	 */
	public void setImgPath (java.lang.String imgPath) {
		this.imgPath = imgPath;
	}


	/**
	 * Return the value associated with the column: description
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: description
	 * @param description the description value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}

	public java.lang.Integer getPriority() {
		return priority;
	}

	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
	}

	public java.lang.String getDefThumb() {
		return imgPath;
	}

	public void setDefThumb(java.lang.String defThumb) {
		this.defThumb = defThumb;
	}

	public java.lang.String getMinThumb() {
		if(StringUtils.isNotBlank(imgPath)){
			return FileRepository.getMinThumb(imgPath);
		}
		return imgPath;
	}

	public void setMinThumb(java.lang.String minThumb) {
		this.minThumb = minThumb;
	}

	public java.lang.String getMeduimThumb() {
		if(StringUtils.isNotBlank(imgPath)){
			return FileRepository.getMediumThumb(imgPath);
		}
		return imgPath;
	}

	public void setMeduimThumb(java.lang.String meduimThumb) {
		this.meduimThumb = meduimThumb;
	}

	public java.lang.String getMaxThumb() {
		if(StringUtils.isNotBlank(imgPath)){
			return FileRepository.getMaxThumb(imgPath);
		}
		return imgPath;
	}

	public void setMaxThumb(java.lang.String maxThumb) {
		this.maxThumb = maxThumb;
	}

	public java.lang.String getOriginalPath() {
		if(StringUtils.isNotBlank(imgPath)){
			return FileRepository.getOriginalImg(imgPath);
		}
		return imgPath;
	}

	public void setOriginalPath(java.lang.String originalPath) {
		this.originalPath = originalPath;
	}
	public String toString () {
		return super.toString();
	}


}