package com.bbsforum.bizimpl;

import java.util.List;

import com.bbsforum.biz.PostBiz;
import com.bbsforum.dao.PostDao;
import com.bbsforum.entity.Post;

public class PostBizImpl implements PostBiz {

	PostDao postDao;
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@Override
	public List<Post> getLastestPost(int pageIndex, int pageSize) {
		List postList=postDao.getLastestPostList(pageIndex, pageSize);
		return postList;
	}

}
