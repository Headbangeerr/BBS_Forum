package com.bbsforum.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.SearchBiz;
import com.bbsforum.entity.PageBean;

public class SearchAction extends BaseAction {
	private static Logger logger=Logger.getLogger(SearchAction.class);
	@Autowired
	SearchBiz searchBiz;
	public SearchBiz getSearchBiz() {
		return searchBiz;
	}
	private String search_username;
	private String username;
	private String keyword;
	private int searchFlag;
	private String search_keyword1;
	private String search_keyword2;
	private int search_childboardlist;//要搜索的子版块id
	private PageBean resultBean;
	private int page;
	
	@Action(value="search",results={
			@Result(name="success",location="/searchresult.jsp")
	})
	public String Search(){		
		resultBean=new PageBean();
		if(page==0){
			page=1;
		}
		logger.info("正在进行搜索…… 搜索标志位："+searchFlag);
		switch (searchFlag) {//为1代表普通搜索帖子，为2代表普通搜索用户,为3代表通过子版块名与关键字搜索帖子，为4代表通过用户名与关键字搜索帖子
		case 1:
			resultBean=searchBiz.SearchPostByKeyword(keyword, page, 10);			
			break;
		case 2:
			resultBean=searchBiz.SearchUserByUsername(username, page, 10);			
			break;
		case 3:
			resultBean=searchBiz.SearchPostByChildboardId(search_keyword1, search_childboardlist, page, 10);
			break;
		case 4:
			resultBean=searchBiz.SearchPostByUsername(search_username,search_keyword2 , page, 10);
		default:
			break;
		}		
		logger.info("search_keyword1:"+search_keyword1+"   search_keyword2:"+search_keyword2+"   search_childboardlist:"+search_childboardlist +"page"+page);
		return SUCCESS;
	}
	

	
	public PageBean getResultBean() {
		return resultBean;
	}
	public void setResultBean(PageBean resultBean) {
		this.resultBean = resultBean;
	}
	public String getSearch_keyword1() {
		return search_keyword1;
	}
	public void setSearch_keyword1(String search_keyword1) {
		this.search_keyword1 = search_keyword1;
	}
	public String getSearch_keyword2() {
		return search_keyword2;
	}
	public void setSearch_keyword2(String search_keyword2) {
		this.search_keyword2 = search_keyword2;
	}
	public int getSearch_childboardlist() {
		return search_childboardlist;
	}
	public void setSearch_childboardlist(int search_childboardlist) {
		this.search_childboardlist = search_childboardlist;
	}
	public int getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(int searchFlag) {
		this.searchFlag = searchFlag;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSearch_username() {
		return search_username;
	}
	public void setSearch_username(String search_username) {
		this.search_username = search_username;
	}



	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
	}
	
	
}
