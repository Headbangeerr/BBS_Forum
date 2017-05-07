package com.bbsforum.dao;

import java.util.List;

import com.bbsforum.entity.Message;
import com.bbsforum.entity.User;

public interface MessageDao {

	/**
	 * 根据接收者的邮箱地址获取该用户的所有留言
	 * @param ReceiverMail
	 * @return 留言列表
	 */
	public List<Message> getMessageByReceiverMail(String ReceiverMail);
	
	/**
	 * 
	 * @param message 
	 * @return 如果为true表示返回成功，在action中通过flag通知前台是否已发布成功
	 */
	public boolean addMessage(Message message);
	
	/**
	 * 利用分页查询获取消息列表
	 * @param pageIndex 起始页标 
	 * @param pageSize 一个页面中记录条数
	 * @return 分页后的单页留言列表
	 */
	public List<Message> getMessagesForPage(int offset,int pageSize,String receiveral);
	
	
}
