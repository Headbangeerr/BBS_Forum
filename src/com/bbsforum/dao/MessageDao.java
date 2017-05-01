package com.bbsforum.dao;

import java.util.List;

import com.bbsforum.entity.Message;

public interface MessageDao {

	/**
	 * 根据接收者的邮箱地址获取该用户的所有留言
	 * @param ReceiverMail
	 * @return 留言列表
	 */
	public List<Message> getMessageByReceiverMail(String ReceiverMail);
}
