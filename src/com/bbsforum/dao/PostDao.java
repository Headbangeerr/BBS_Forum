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
	 * @param post一个帖子类
	 * @return 返回true或false
	 */
	public boolean addPost(Post post);
	/**
	 * @param offset 要查看那一页的第一条记录下标
	 * @param PageSize 页面大小
	 * @param PublisherMail 发布人的邮箱地址
	 * @return 返回一个页面的帖子列表
	 */
	public List<Post> getPostListForPage(int offset,int PageSize,String PublisherMail);
	/**
	 * @param offset 要查看那一页的第一条记录下标
	 * @param PageSize 页面大小
	 * @param bid所属父板块id
	 * @return 返回一个页面的帖子列表
	 */
	public List<Post> getChoosePostListForPage(int offset, int PageSize,int bid);
	/**
	 * @param bid所属父板块id
	 * @return 返回所需帖子的数量
	 */
	public List<Post> getChoosePostListForPage1(int bid);
	/**
	 * @param id帖子的id
	 * @return 返回一个帖子
	 */
	public Post getPost(String id);
	/**
	 * @param offset 要查看那一页的第一条记录下标
	 * @param PageSize 页面大小
	 * @return 返回一个页面的帖子
	 */
	public List<Post> getAllPostListForPage(int offset,int PageSize);
	/**
	 * @param id所属父板块id
	 * @return 返回所有帖子的数集合
	 */
	public List getAllPostList(int i);
	/**
	 * @param offset 要查看那一页的第一条记录下标
	 * @param PageSize 页面大小
	 * @return 返回一个页面的帖子
	 */
	public List<Post> getViePostListForPage(int offset,int PageSize);
	/**
	 * @return 返回所需帖子的集合
	 */
	public List getViePostList(int i);
	/**
	 * @return 返回所需帖子的集合
	 */
	public List<Post> getJHPost(int i);
	/**
	 * @param post一个帖子类
	 * @return 返回true或false
	 */
	public boolean updaPost(Post post);
	/**
	 * @param offset 要查看那一页的第一条记录下标
	 * @param PageSize 页面大小
	 * @return 返回一个页面的帖子
	 */
	public List<Post> getZiPostListForPage(int offset, int PageSize,int cid);
	/**
	 * @return 返回所需帖子数量的集合
	 */
	public List<Post> getZiPostListForPage1(int cid);
	/**
	 * @param id所要删除的帖子的id
	 * @return 返回true或false
	 */
	public boolean deletePost(String id);
}
