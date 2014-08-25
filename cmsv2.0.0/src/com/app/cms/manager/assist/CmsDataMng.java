package com.app.cms.manager.assist;

import java.util.List;

import com.app.cms.entity.back.CmsConstraints;
import com.app.cms.entity.back.CmsField;
import com.app.cms.entity.back.CmsTable;

public interface CmsDataMng {
	public List<CmsTable> listTabels();

	public CmsTable findTable(String tablename);

	public List<CmsField> listFields(String tablename);

	public List<CmsConstraints> listConstraints(String tablename);
}