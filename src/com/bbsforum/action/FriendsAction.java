
package com.bbsforum.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.FriendsBiz;
import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.biz.UserBiz;
import com.bbsforum.entity.PageBean;
@ParentPackage("json-default")//要使用json必须要依赖于该包
public class FriendsAction extends BaseAction {

	private static Logger logger=Logger.getLogger(FriendsAction.class);
	private String userMail;
	private String friendMail;

	@JSON(serialize=false)
	public String getFriendMail() {
		return friendMail;
	}
	public void setFriendMail(String friendMail) {
		this.friendMail = friendMail;
	}
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
	@Autowired
	private UserBiz userBiz;
	public UserBiz getUserBiz() {
		return userBiz;
	}
	@Autowired
	private FriendsBiz friendsBiz;
	public FriendsBiz getFriendsBiz() {
		return friendsBiz;
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
							+"pageBean.list\\[\\d+\\]\\.friends,"
							+"pageBean.list\\[\\d+\\]\\.replys"})
			})
	public String getFriendsList(){
		pageBean=pageViewBiz.showFridensByPage(page, 5, userMail);
		return SUCCESS;
	}
	boolean flag;
	
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	@Action(value="addFriends",results={
			@Result(name="success",type="json",params={
					"includeProperties","flag"
			})
	})
	public  String addFriends(){
		flag=friendsBiz.addFriends(userMail, friendMail);
		return SUCCESS;
	}
	
	private int friendSum;
	public int getFriendSum() {
		return friendSum;
	}
	public void setFriendSum(int friendSum) {
		this.friendSum = friendSum;
	}
	@Action(value="deleFriends",results={
			@Result(name="success",type="json",params={
					"includeProperties","flag,friendSum"
			})
	})
	public  String deleteFriends(){
		flag=friendsBiz.deleteFriend(userMail, friendMail);
		friendSum=userBiz.getUserByMailAddress(userMail).getFriends().size();
		logger.info("正在删除好友…… 用户id："+userMail+"  好友ID："+friendMail+"    friendSum:"+friendSum);
		return SUCCESS;
	}
	
	
}
