package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.LastestSenderJSON;
import com.bbsforum.entity.News;
import com.bbsforum.entity.User;

public interface NewsBiz {

	public boolean sendNews(String senderMail,String receiverMail,String content,int type);
	
	public boolean handleFriendRequest(String newsId,boolean operate);

	public boolean checkFriRequestExist(String senderMail,String receiverMail);
	
	public List<News> getFriRequestListByReceiverMail(String reciverMail);
	
	public List<LastestSenderJSON> getLastestSenders(String userMail);
	
	public int checkReadNews(String userMail, String friendMail);
	public News getLastestNewsBySender(String userMail);
	public boolean checkFriendInUserLastestSender(String friendMail,String userMail);
}
