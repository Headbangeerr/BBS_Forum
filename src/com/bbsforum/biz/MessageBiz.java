package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.User;

public interface MessageBiz {

	public List<Message> getMessageByReceiverMail(String ReceiverMail);
	
	public boolean addMessage(Message message);

}
