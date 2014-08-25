package com.app.cms.dao.assist;

import java.util.List;

import com.app.cms.entity.back.CmsConstraints;
import com.app.cms.entity.back.CmsField;
import com.app.cms.entity.back.CmsTable;

public interface CmsDataDao {
	public List<CmsTable> listTables();

	public List<CmsField> listFields(String tablename);

	public List<CmsConstraints> listConstraints(String tablename);

	public CmsTable findTable(String tablename);

}