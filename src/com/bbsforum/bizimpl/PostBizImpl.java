package com.bbsforum.bizimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.PostBiz;
import com.bbsforum.dao.PostDao;
import com.bbsforum.entity.PageBean;
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
	public List<Post> getAllPostListForPage(int offset, int PageSize) {
		// TODO Auto-generated method stub
		List postList=postDao.getAllPostListForPage(offset, PageSize);
		return postList;

	}

	@Override
	public List<Post> getViePostListForPage(int offset, int PageSize) {
		// TODO Auto-generated method stub
		List  postList=postDao.getViePostListForPage(offset, PageSize);
		return postList;
	}

	@Override
	public List<Post> getJHPost(int i) {
		// TODO Auto-generated method stub
		List  postList=postDao.getJHPost(i);
		return postList;
	}

	@Override
	public List<Post> getZiPostListForPage(int offset, int PageSize, int cid) {
		// TODO Auto-generated method stub
		List  postList=postDao.getZiPostListForPage(offset, PageSize, cid);
		return postList;
	}


	@Override
	public PageBean SearchSensitivePost(int PageIndex, int PageSize) {
		int itemSum=postDao.SearchSensitiveSumPost();
		int totalPage=PageBean.countTotalPage(PageSize, itemSum);//计算总页数
		final int offset=PageBean.countOffset(PageSize, PageIndex);//获取本页第一条记录的下标		
		final int length=PageSize;//每页的记录数
		final int currentPage=PageBean.countCurrentPage(PageIndex);
		List<Post> posts=postDao.SearchSensitivePostForPage(offset, PageSize);
		PageBean pageBean=new PageBean();
		pageBean.setPageSize(PageSize);
		pageBean.setCurrentPage(PageIndex);
		pageBean.setAllRow(itemSum);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(posts);
		pageBean.init();
		return pageBean;

	}

}
