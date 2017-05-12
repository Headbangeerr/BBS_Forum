package com.bbsforum.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.FriendsBiz;
import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.entity.PageBean;
@ParentPackage("json-default")//要使用json必须要依赖于该包
public class FriendsAction extends BaseAction {

	
	private String userMail;
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@JSON(serialize=false)
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	@Autowired
	private PageViewBiz pageViewBiz;
	public void setPageViewBiz(PageViewBiz pageViewBiz) {
		this.pageViewBiz = pageViewBiz;
	}
	PageBean pageBean;
	
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	@JSON(serialize=false)
	@Action(value="showFriendsList",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "pageBean.list\\[\\d+\\]\\.posts,"
					+"pageBean.list\\[\\d+\\]\\.friends,"})
			})
	public String getFriendsList(){
		pageBean=pageViewBiz.showFridensByPage(page, 5, userMail);
		return SUCCESS;
	}
	public  String addFriends(){
		return SUCCESS;
	}
	public  String deleteFriends(){
		return SUCCESS;
	}
	
	
}
