package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.Reply;

public interface ReplyBiz {
	public boolean addReply(Reply reply);
	
	public List<Reply> getReplyListForPage(int offset, int PageSize,String pid);
	
	public List<Reply> getReplyListForPage1(String pid);
}
