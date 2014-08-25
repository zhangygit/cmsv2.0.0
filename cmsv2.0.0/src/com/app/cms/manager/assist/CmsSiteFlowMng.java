package com.app.cms.manager.assist;

import net.sf.ehcache.Ehcache;

import com.app.cms.entity.assist.CmsSiteFlow;
import com.app.cms.entity.main.CmsSite;

public interface CmsSiteFlowMng {
	public CmsSiteFlow save(CmsSite site,String ip, String page, String sessionId);
	
	public CmsSiteFlow findUniqueByProperties(Integer siteId, String accessDate, String sessionId, String page);

	public int freshCacheToDB(Ehcache cache);
}
