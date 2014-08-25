package com.app.cms.dao.main;

import com.app.cms.entity.main.CmsConfig;
import com.app.common.hibernate3.Updater;

public interface CmsConfigDao {
	public CmsConfig findById(Integer id);

	public CmsConfig updateByUpdater(Updater<CmsConfig> updater);
}