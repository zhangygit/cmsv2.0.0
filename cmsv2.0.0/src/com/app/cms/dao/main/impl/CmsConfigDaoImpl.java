package com.app.cms.dao.main.impl;

import org.springframework.stereotype.Repository;

import com.app.cms.dao.main.CmsConfigDao;
import com.app.cms.entity.main.CmsConfig;
import com.app.common.hibernate3.HibernateBaseDao;

@Repository
public class CmsConfigDaoImpl extends HibernateBaseDao<CmsConfig, Integer>
		implements CmsConfigDao {
	public CmsConfig findById(Integer id) {
		CmsConfig entity = get(id);
		return entity;
	}

	@Override
	protected Class<CmsConfig> getEntityClass() {
		return CmsConfig.class;
	}
}