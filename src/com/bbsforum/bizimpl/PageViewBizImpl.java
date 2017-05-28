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
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//������ҳ��
		final int offset=PageBean.countOffset(pageSize, pageIndex);//��ȡ��ҳ��һ����¼���±�
		final int length=pageSize;//ÿҳ�ļ�¼��
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
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//������ҳ��
		final int offset=PageBean.countOffset(pageSize, pageIndex);//��ȡ��ҳ��һ����¼���±�
		final int length=pageSize;//ÿҳ�ļ�¼��
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
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//������ҳ��
		final int offset=PageBean.countOffset(pageSize, pageIndex);//��ȡ��ҳ��һ����¼���±�
		logger.info("�����б��ȣ�"+itemSum+"offset:"+offset);
		final int length=pageSize;//ÿҳ�ļ�¼��
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
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//������ҳ��
		final int offset=PageBean.countOffset(pageSize, pageIndex);//��ȡ��ҳ��һ����¼���±�
		final int length=pageSize;//ÿҳ�ļ�¼��
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
		int totalPage=PageBean.countTotalPage(pageSize, itemSum);//������ҳ��
		final int offset=PageBean.countOffset(pageSize, pageIndex);//��ȡ��ҳ��һ����¼���±�
		final int length=pageSize;//ÿҳ�ļ�¼��
		final int currentPage=PageBean.countCurrentPage(pageIndex);
		List<Reply> replys=replyDao.getReplyListForPage(offset, pageSize, pid);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(pageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(replys);
		pageBean.init();
		return pageBean;
	}

}
