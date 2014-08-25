package com.app.cms.dao.main;

import java.util.List;

import com.app.cms.entity.main.CmsGroup;
import com.app.common.hibernate3.Updater;

public interface CmsGroupDao {
	public List<CmsGroup> getList();

	public CmsGroup getRegDef();

	public CmsGroup findById(Integer id);

	public CmsGroup save(CmsGroup bean);

	public CmsGroup updateByUpdater(Updater<CmsGroup> updater);

	public CmsGroup deleteById(Integer id);
}