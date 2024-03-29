package com.app.cms.entity.main;

import java.util.Collection;

import com.app.cms.entity.main.base.BaseCmsRole;

public class CmsRole extends BaseCmsRole {
	private static final long serialVersionUID = 1L;

	public static Integer[] fetchIds(Collection<CmsRole> roles) {
		if (roles == null) {
			return null;
		}
		Integer[] ids = new Integer[roles.size()];
		int i = 0;
		for (CmsRole r : roles) {
			ids[i++] = r.getId();
		}
		return ids;
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public CmsRole () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsRole (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsRole (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.Integer priority,
		java.lang.Boolean m_super) {

		super (
			id,
			name,
			priority,
			m_super);
	}

	/* [CONSTRUCTOR MARKER END] */

}