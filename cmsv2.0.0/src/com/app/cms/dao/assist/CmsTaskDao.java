package com.app.cms.dao.assist;

import java.util.List;

import com.app.cms.entity.assist.CmsTask;
import com.app.common.hibernate3.Updater;
import com.app.common.page.Pagination;

public interface CmsTaskDao {
	public Pagination getPage(Integer siteId,int pageNo, int pageSize);
	
	public List<CmsTask> getList();

	public CmsTask findById(Integer id);

	public CmsTask save(CmsTask bean);

	public CmsTask updateByUpdater(Updater<CmsTask> updater);

	public CmsTask deleteById(Integer id);
}