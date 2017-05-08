package com.bbsforum.dao;

import java.util.List;
import java.util.Set;

import org.jboss.logging.Param;

import com.bbsforum.entity.Post;

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
	public List<Post> getPostListForPage(int offset,int PageSize,String PublisherMail);
	
	
}
