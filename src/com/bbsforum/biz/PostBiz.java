package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.Post;
import com.bbsforum.entity.Reply;

public interface PostBiz {

	/**
	 * 分页获取最新帖子列表
	 * @param pageIndex  页码，从1开始
	 * @param pageSize	页面大小，即每一页的数据条数
	 * @return 帖子列表
	 */
	public boolean addPost(Post post);
	
	public List<Post> getLastestPost(int pageIndex, int pageSize);
	
	public List<Post> getChoosePostListForPage(int offset, int PageSize,int bid);
	
	public Post getPost(String id);
	
	public List<Post> getAllPostListForPage(int offset,int PageSize);

	public List<Post> getViePostListForPage(int offset,int PageSize);
	
	public List<Post> getJHPost(int i);
	
	public List<Post> getZiPostListForPage(int offset, int PageSize,int cid);
	
	public PageBean SearchSensitivePost(int PageIndex, int PageSize);
	public List ShowPostOnIndexExcludeShiled(String username);
}
