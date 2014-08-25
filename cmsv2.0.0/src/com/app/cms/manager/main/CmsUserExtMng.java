package com.app.cms.manager.main;

import com.app.cms.entity.main.CmsUser;
import com.app.cms.entity.main.CmsUserExt;

public interface CmsUserExtMng {
	public CmsUserExt save(CmsUserExt ext, CmsUser user);

	public CmsUserExt update(CmsUserExt ext, CmsUser user);
}