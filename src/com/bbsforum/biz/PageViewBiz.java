
package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;

public interface PageViewBiz {

	public PageBean showMessageBypage(int pageIndex, int pageSize,String receiverMail);
	
	public PageBean showPostBypage(int pageIndex, int pageSize,String publisherMail,int itemSum);
	
	public PageBean showFridensByPage(int pageIndex,int pageSize,String userMail);
	
	public PageBean showChoosePostBypage(int pageIndex, int pageSize, int bid);
	
	public PageBean showReplyBypage(int pageIndex, int pageSize, String pid);

	public PageBean showAllPostBypage(int pageIndex, int pageSize);

	public PageBean showViePostBypage(int pageIndex,int pageSize);
	
	public PageBean showZiPostBypage(int pageIndex, int pageSize, int cid);
	
	public PageBean showUserBypage(int pageIndex,int pageSize);
}

