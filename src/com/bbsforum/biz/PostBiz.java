package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.Post;

public interface PostBiz {

	/**
	 * 分页获取最新帖子列表
	 * @param pageIndex  页码，从1开始
	 * @param pageSize	页面大小，即每一页的数据条数
	 * @return 帖子列表
	 */
	public List<Post> getLastestPost(int pageIndex, int pageSize);
}
