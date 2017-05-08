package com.bbsforum.bizimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.dao.MessageDao;
import com.bbsforum.dao.PostDao;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.Post;

public class PageViewBizImpl implements PageViewBiz {
	private static Logger logger=Logger.getLogger(PageViewBizImpl.class);
	@Autowired
	MessageDao messageDao;
	public MessageDao getMessageDao() {
		return messageDao;
	}
	
	@Autowired
	PostDao postDao;
	public PostDao getPostDao() {
		return postDao;
	}
	
	@Override
	public PageBean showMessageBypage(int pageIndex, int pageSize,
			String receiverMail) {
		int itemSum=messageDao.getMessageByReceiverMail(receiverMail).size();
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		
		logger.info("pageIndex:"+pageIndex+"offset:"+offset);
		List<Message> messages=messageDao.getMessagesForPage(offset, pageSize, receiverMail);
		logger.info("message size:"+messages.size());
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(messages);
		pageBean.init();
		return pageBean;
	}
	@Override
	public PageBean showPostBypage(int pageIndex, int pageSize,
			String publisherMail,int itemSum) {
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<Post> posts=postDao.getPostListForPage(offset, pageSize, publisherMail);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(posts);
		pageBean.init();
		return pageBean;
	}

}
