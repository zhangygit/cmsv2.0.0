package com.app.cms.manager.main;

import com.app.cms.entity.main.Channel;
import com.app.cms.entity.main.ChannelExt;

public interface ChannelExtMng {
	public ChannelExt save(ChannelExt ext, Channel channel);

	public ChannelExt update(ChannelExt ext);
}