package com.app.cms.manager.main;

import com.app.cms.entity.main.Content;
import com.app.cms.entity.main.ContentTxt;

public interface ContentTxtMng {
	public ContentTxt save(ContentTxt txt, Content content);

	public ContentTxt update(ContentTxt txt, Content content);
}