package com.app.cms.dao.main;

import com.app.cms.entity.main.CmsUserExt;
import com.app.common.hibernate3.Updater;

public interface CmsUserExtDao {
	public CmsUserExt findById(Integer id);

	public CmsUserExt save(CmsUserExt bean);

	public CmsUserExt updateByUpdater(Updater<CmsUserExt> updater);
}