package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.News;

public interface NewsBiz {

	public boolean sendMessage(String senderMail,String receiverMail,String content,int type);
	
	public boolean handleFriendRequest(String newsId,boolean operate);

	public boolean checkFriRequestExist(String senderMail,String receiverMail);
	
	public List<News> getFriRequestListByReceiverMail(String reciverMail);
}
