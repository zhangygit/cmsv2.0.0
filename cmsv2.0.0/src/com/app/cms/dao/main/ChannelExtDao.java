package com.app.cms.dao.main;

import com.app.cms.entity.main.ChannelExt;
import com.app.common.hibernate3.Updater;

public interface ChannelExtDao {
	public ChannelExt save(ChannelExt bean);

	public ChannelExt updateByUpdater(Updater<ChannelExt> updater);
}