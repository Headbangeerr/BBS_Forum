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
	 * @param publisher 留言发布者，也就是目前session保存的登陆者
	 * @param receiver	留言接收者
	 * @param Content	留言内容
	 * @return 如果为true表示返回成功，在action中通过flag通知前台是否已发布成功
	 */
	public boolean addMessage(User publisher,User receiver,String Content);
}
