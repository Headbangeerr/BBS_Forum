package com.bbsforum.biz;

import java.util.List;

import com.bbsforum.entity.PageBean;

public interface SearchBiz {

public PageBean SearchPostByKeyword(String keyword,int PageIndex, int PageSize);
	
public PageBean SearchUserByUsername(String username,int PageIndex, int PageSize);

public PageBean SearchPostByChildboardId(String keyword,int childboardId,int pageIndex,int pageSize);

public PageBean SearchPostByUsername(String username,String  keyword,int pageIndex,int pageSize);
}
