package com.app.cms.manager.assist;

import com.app.cms.entity.assist.CmsVoteReply;
import com.app.common.page.Pagination;

public interface CmsVoteReplyMng {

	public Pagination getPage(Integer  subTopicId, int pageNo, int pageSize);
	
	public CmsVoteReply findById(Integer id);

	public CmsVoteReply save(CmsVoteReply bean);

	public CmsVoteReply update(CmsVoteReply bean);

	public CmsVoteReply deleteById(Integer id);

	public CmsVoteReply[] deleteByIds(Integer[] ids);
}