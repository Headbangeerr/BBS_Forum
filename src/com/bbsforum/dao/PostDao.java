package com.bbsforum.dao;

import java.util.List;
import java.util.Set;

import org.jboss.logging.Param;

import com.bbsforum.entity.Post;
import com.bbsforum.entity.Reply;

/**
 * 帖子的数据库访问接口
 * @author 颜磊
 * @version 1.0
 */
public interface PostDao {
	
	/**
	 * 分页获取最新帖子列表
	 * @param pageIndex  页码，从1开始
	 * @param pageSize	页面大小，即一页中有几条数据
	 * @return 最新帖子列表
	 */
	public List getLastestPostList(int pageIndex,int pageSize );
	/**
	 * @param offset 要查看那一页的第一条记录下标
	 * @param PageSize 页面大小
	 * @param PublisherMail 发布人的邮箱地址
	 * @return 返回一个页面的帖子列表
	 */
	public boolean addPost(Post post);
	
	public List<Post> getPostListForPage(int offset,int PageSize,String PublisherMail);
	
	public List<Post> getChoosePostListForPage(int offset, int PageSize,int bid);
	
	public List<Post> getChoosePostListForPage1(int bid);
	
	public Post getPost(String id);
	
	public List<Post> getAllPostListForPage(int offset,int PageSize);

	public List getAllPostList(int i);
	
	public List<Post> getViePostListForPage(int offset,int PageSize);
	
	public List getViePostList(int i);
	
	public List<Post> getJHPost(int i);
	
	public boolean updaPost(Post post);

	public List<Post> getZiPostListForPage(int offset, int PageSize,int cid);
	
	public List<Post> getZiPostListForPage1(int cid);
}
