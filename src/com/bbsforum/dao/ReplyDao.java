package com.bbsforum.dao;

import java.util.List;

import com.bbsforum.entity.Reply;

public interface ReplyDao {
	/**
	 * @param reply一个帖子类
	 * @return 返回true或false
	 */
	public boolean addReply(Reply reply);
	/**
	 * @param pid对应的贴子的id
	 * @param offset 要查看那一页的第一条记录下标
	 * @param PageSize 页面大小
	 * @return 返回该帖所有回复贴
	 */
	public List<Reply> getReplyListForPage(int offset, int PageSize,String pid);
	/**
	 * @param pid对应的贴子的id
	 * @return 返回该帖所有回复贴
	 */
	public List<Reply> getReplyListForPage1(String pid);
	/**
	 * @param postid回复中帖子的id
	 * @return 返回true或false
	 */
	public boolean deleteReply(String postid);
}
