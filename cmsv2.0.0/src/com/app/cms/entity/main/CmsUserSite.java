package com.app.cms.entity.main;

import com.app.cms.entity.main.base.BaseCmsUserSite;

public class CmsUserSite extends BaseCmsUserSite {
	private static final long serialVersionUID = 1L;

	/* [CONSTRUCTOR MARKER BEGIN] */
	public CmsUserSite () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsUserSite (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsUserSite (
		java.lang.Integer id,
		com.app.cms.entity.main.CmsUser user,
		com.app.cms.entity.main.CmsSite site,
		java.lang.Byte checkStep,
		java.lang.Boolean allChannel) {

		super (
			id,
			user,
			site,
			checkStep,
			allChannel);
	}

	/* [CONSTRUCTOR MARKER END] */

}