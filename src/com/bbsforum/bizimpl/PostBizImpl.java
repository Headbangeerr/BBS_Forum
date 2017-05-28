package com.bbsforum.bizimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.PostBiz;
import com.bbsforum.dao.PostDao;
import com.bbsforum.entity.Post;
import com.bbsforum.entity.Reply;

public class PostBizImpl implements PostBiz {

	@Autowired
	PostDao postDao;
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@Override
	public List<Post> getLastestPost(int pageIndex, int pageSize) {
		List postList=postDao.getLastestPostList(pageIndex, pageSize);
		return postList;
	}

	public List<Post> getChoosePostListForPage(int offset, int PageSize,int bid){
		List postList1=postDao.getChoosePostListForPage(offset, PageSize, bid);
		return postList1;
	}

	@Override
	public boolean addPost(Post post) {
		return postDao.addPost(post);
	}

	@Override
	public Post getPost(String id) {
		// TODO Auto-generated method stub
		return postDao.getPost(id);
	}

	@Override
	public boolean addReply(Reply reply) {
		// TODO Auto-generated method stub
		return postDao.addReply(reply);
	}

	@Override
	public List<Reply> getReplyListForPage(int offset, int PageSize, String pid) {
		// TODO Auto-generated method stub
		return postDao.getReplyListForPage(offset, PageSize, pid);
	}

	@Override
	public List<Reply> getReplyListForPage1(String pid) {
		// TODO Auto-generated method stub
		return postDao.getReplyListForPage1(pid);
	}
}
