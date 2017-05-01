package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.Message;

public interface MessageBiz {

	public List<Message> getMessageByReceiverMail(String ReceiverMail);
}
