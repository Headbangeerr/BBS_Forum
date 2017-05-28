package com.bbsforum.bizimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.ReplyBiz;
import com.bbsforum.dao.ReplyDao;
import com.bbsforum.entity.Reply;

public class ReplyBizImpl implements ReplyBiz {
	@Autowired
	ReplyDao replyDao;
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	@Override
	public boolean addReply(Reply reply) {
		// TODO Auto-generated method stub
		return replyDao.addReply(reply);
	}

	@Override
	public List<Reply> getReplyListForPage(int offset, int PageSize, String pid) {
		// TODO Auto-generated method stub
		return replyDao.getReplyListForPage(offset, PageSize, pid);
	}

	@Override
	public List<Reply> getReplyListForPage1(String pid) {
		// TODO Auto-generated method stub
		return replyDao.getReplyListForPage1(pid);
	}
}
