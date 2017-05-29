package com.bbsforum.dao;

import java.util.List;

import com.bbsforum.entity.Reply;

public interface ReplyDao {
	
	public boolean addReply(Reply reply);
	
	public List<Reply> getReplyListForPage(int offset, int PageSize,String pid);
	
	public List<Reply> getReplyListForPage1(String pid);
}
