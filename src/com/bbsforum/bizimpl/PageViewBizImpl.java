package com.bbsforum.bizimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.dao.FriendsDao;
import com.bbsforum.dao.MessageDao;
import com.bbsforum.dao.PostDao;
import com.bbsforum.dao.ReplyDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.Post;
import com.bbsforum.entity.Reply;
import com.bbsforum.entity.User;

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
	@Autowired
	ReplyDao replyDao;
	public ReplyDao getReplyDao(){
		return replyDao;
	}
	@Autowired
	FriendsDao friendsDao;
	public FriendsDao getFriendsDao() {
		return friendsDao;
	}
	@Autowired
	UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
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
		logger.info("总页数"+totalPage);
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

	@Override
	public PageBean showFridensByPage(int pageIndex, int pageSize,
			String userMail){
		int itemSum=userDao.findUserByMailAddress(userMail).getFriends().size();
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标
		logger.info("好友列表长度："+itemSum+"offset:"+offset);
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<User> friends=friendsDao.getFriendList(userMail, offset, pageSize);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(friends);
		pageBean.init();
		return pageBean;
	}
	
	@Override
	public PageBean showChoosePostBypage(int pageIndex, int pageSize, int bid) {
		// TODO Auto-generated method stub
		int itemSum=postDao.getChoosePostListForPage1(bid).size();
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<Post> posts=postDao.getChoosePostListForPage(offset, pageSize, bid);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(posts);
		pageBean.init();
		return pageBean;
	}

	@Override
	public PageBean showReplyBypage(int pageIndex, int pageSize, String pid) {
		// TODO Auto-generated method stub
		int itemSum=replyDao.getReplyListForPage1(pid).size();
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<Reply> replys=replyDao.getReplyListForPage1(pid);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(replys);
		pageBean.init();
		return pageBean;
	}

	@Override
	public PageBean showAllPostBypage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		int i = 4;
		int itemSum=postDao.getAllPostList(i).size();
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<Post> posts=postDao.getAllPostListForPage(offset, pageSize);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(posts);
		pageBean.init();
		return pageBean;

	}

	@Override
	public PageBean showViePostBypage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		int i = 4;
		int itemSum=postDao.getViePostList(i).size();
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<Post> posts=postDao.getViePostListForPage(offset, pageSize);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(posts);
		pageBean.init();
		return pageBean;
	}

	@Override
	public PageBean showZiPostBypage(int pageIndex, int pageSize, int cid) {
		// TODO Auto-generated method stub
		int itemSum=postDao.getZiPostListForPage1(cid).size();
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<Post> posts=postDao.getZiPostListForPage(offset, pageSize, cid);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(posts);
		pageBean.init();
		return pageBean;
	}

	@Override
	public PageBean showUserBypage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		int i = 4;
		int itemSum=userDao.getAllUserList1(i).size();
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(pageSize, pageIndex);//获取本页第一条记录的下标
		final int length=pageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<User> users=userDao.getAllUserList(offset, pageSize);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(users);
		pageBean.init();
		return pageBean;
	}



}

