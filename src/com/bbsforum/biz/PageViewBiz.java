package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;

public interface PageViewBiz {

	public PageBean showMessageBypage(int pageIndex, int pageSize,String receiverMail);
	
}
