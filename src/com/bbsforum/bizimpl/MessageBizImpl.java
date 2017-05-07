package com.bbsforum.bizimpl;

import java.util.List;

import javax.ws.rs.GET;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.MessageBiz;
import com.bbsforum.dao.MessageDao;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.User;

public class MessageBizImpl implements MessageBiz {

	@Autowired
	MessageDao messageDao;
	public MessageDao getMessageDao() {
		return messageDao;
	}
	
	
	@Override
	public List<Message> getMessageByReceiverMail(String ReceiverMail) {
		return messageDao.getMessageByReceiverMail(ReceiverMail);
	}


	@Override
	public boolean addMessage(Message message) {
		return messageDao.addMessage(message);
	}

	
}
