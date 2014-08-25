package com.app.cms.manager.main;

import com.app.cms.entity.main.Content;
import com.app.cms.entity.main.ContentExt;

public interface ContentExtMng {
	public ContentExt save(ContentExt ext, Content content);

	public ContentExt update(ContentExt ext);
}