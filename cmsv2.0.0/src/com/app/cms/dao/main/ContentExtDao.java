package com.app.cms.dao.main;

import com.app.cms.entity.main.ContentExt;
import com.app.common.hibernate3.Updater;

public interface ContentExtDao {
	public ContentExt findById(Integer id);

	public ContentExt save(ContentExt bean);

	public ContentExt updateByUpdater(Updater<ContentExt> updater);
}