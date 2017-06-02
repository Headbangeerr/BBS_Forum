
package com.bbsforum.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.FriendsBiz;
import com.bbsforum.biz.MessageBiz;
import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.biz.UserBiz;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.Childboard;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.Post;
import com.bbsforum.entity.User;
import com.opensymphony.xwork2.ActionSupport;
@ParentPackage("json-default")
public class UserAction extends BaseAction {
private static final String SUPERMAN = null;
private static Logger logger=Logger.getLogger(UserAction.class);
	private String mailAddress;
	private String password;
	private int errorFlag;//登录时用于标记错误信息，为0时表示用户不存在，为2时表示密码错误
	private boolean friendFlag;
	private int le;
	boolean flag;
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public int isLe() {
		return le;
	}
	public void setLe(int le) {
		this.le = le;
	}
	public boolean getFriendFlag() {
		return friendFlag;
	}
	public void setFriendFlag(boolean friendFlag) {
		this.friendFlag = friendFlag;
	}
	public int getErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(int errorFlag) {
		this.errorFlag = errorFlag;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	private int page;
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	private String userMail;
	
	@JSON(serialize=false)
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	@Autowired
	UserBiz userBiz;
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	
	@Action(value="login",results={
			@Result(name="success",location="/index.jsp"),
			@Result(name="login",location="/login.jsp"),
			@Result(name="superman",location="/superman.jsp")
	})
	public String login(){//登陆
		logger.info("login……   useraddress:"+mailAddress);
		User user=userBiz.getUserByMailAddress(mailAddress);
		getSession().put("mailAddress", mailAddress);
		if(user==null){
			errorFlag=0;
			return LOGIN;
		}else{
			if(user.getPassword().endsWith(password)){
				if(user.getType().equals(2)){
					getSession().put("user", user);
					return "superman";
				}else{
					getSession().put("user", user);
					return SUCCESS;
				}
			}else{
				errorFlag=2;
				return LOGIN;
			}
		}
	}
	
	@Action(value="logout",results={
			@Result(name="success",location="/index.jsp")
	})
	public String logout(){
		getSession().put("user", null);
		return SUCCESS;
	}
	
	@Autowired
	MessageBiz messageBiz;
	public MessageBiz getMessageBiz() {
		return messageBiz;
	}
	private PageBean pageBean;
	public PageBean getPageBean() {
		return pageBean;
	}
	private PageBean postBean;
	public PageBean getPostBean() {
		return postBean;
	}
	private PageBean userBean;
	public PageBean getUserBean() {
		return userBean;
	}
	private User userBean1;
	public User getUserBean1() {
		return userBean1;
	}
	private PageBean friendsBean;
	public PageBean getFriendsBean() {
		return friendsBean;
	}
	@Autowired
	private PageViewBiz pageViewBiz;
	public PageViewBiz getPageViewBiz() {
		return pageViewBiz;
	}
	@Autowired
	private FriendsBiz friendsBiz;
	public FriendsBiz getFriendsBiz() {
		return friendsBiz;
	}
	@Autowired
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Action(value="chaeckUserByUrl",results={
			@Result(name="others",location="/member.jsp"),
			@Result(name="self",location="/personal.jsp")
	})
	public String checkUserByUrl(){
		User user=(User) getSession().get("user");
		//如果要查看的用户与此时已登录的用户是同一个人，则跳转至用户的个人资料修改页面
		User check;
		//List<Message> messageList=messageBiz.getMessageByReceiverMail(mailAddress);
		pageBean=pageViewBiz.showMessageBypage(1, 4, mailAddress);
		getRequest().put("pageBean", pageBean);
		if(null==user||!mailAddress.equals(user.getMailAddress())){
			check=userBiz.getUserByMailAddress(mailAddress);
			logger.info("查看其他用户   被查看的用户账号："+mailAddress+"  用户名"+check.getUsername());
			getRequest().put("checkedUser", check);
			postBean=pageViewBiz.showPostBypage(1, 5, mailAddress, check.getPosts().size());
			friendFlag=false;
			if(null!=user){			
				friendFlag=friendsBiz.checkFriend(user.getMailAddress(), mailAddress);
			}
			logger.info("friendFlag:"+friendFlag);
			return "others";
		}
		else{
			logger.info("被查看的用户是登陆者本人："+user.getUsername());
			getRequest().put("checkedUser", user);
			postBean=pageViewBiz.showPostBypage(1, 5, mailAddress, user.getPosts().size());
			logger.info(user.getPosts().size());
			logger.info("帖子总页数"+postBean.getTotalPage());
			pageBean=pageViewBiz.showMessageBypage(1, 5, mailAddress);
			friendsBean=pageViewBiz.showFridensByPage(1, 5, mailAddress);
			return "self";
		}
	}
	
	@Action(value="showAllUser",results={
			@Result(name="success",type="json",params={
					"excludeProperties",  "pageBean.list\\[\\d+\\]\\.posts,"
							+"pageBean.list\\[\\d+\\]\\.friends,"
							+"pageBean.list\\[\\d+\\]\\.replys,"
							+"pageBean.list\\[\\d+\\]\\.mailAddress.posts"})
	}) 
	public String showAllUser(){
		pageBean=pageViewBiz.showUserBypage(page, 5);
		logger.info("成功获取到帖子页面…… 页面中的帖子条数为："+pageBean.getList().size());
		return SUCCESS;
	}
	
	@Action(value="getAllUser",results={
			@Result(name="self",location="/pages/SuperGetUser.jsp")
	})
	public String getAllUser(){
		int i=7;
		List<User> userBean1=new ArrayList<User>();
		userBean1=userDao.getAllUserList1(i);
		getRequest().put("userBean1", userBean1);
		return "self";
	}
	
	@Action(value="checkQXUserByUrl",results={
			@Result(name="others",location="/pages/QXUser.jsp")
	})
	public String checkQXUserByUrl(){
		User check;
		getSession().put("usermailqx", mailAddress);
		check=userBiz.getUserByMailAddress(mailAddress);
		le=check.getType();
		getRequest().put("checkedUser1", check);
		return "others";
	
	}
	@Action(value="upQX",results={
			@Result(name="success",type="json")
	})
	public String upQX(){
		User userqx=userBiz.getUserByMailAddress(userMail);
		userqx.setType(userqx.getType()+1);
		if(userDao.updaUser(userqx)){
			flag=true;
		}
		else{
			flag=false;
		}
		logger.info("flag:"+flag);
		return SUCCESS;
	}
	@Action(value="downQX",results={
			@Result(name="success",type="json")
	})
	public String downQX(){
		User userqx=userBiz.getUserByMailAddress(userMail);
		userqx.setType(userqx.getType()-1);
		if(userDao.updaUser(userqx)){
			flag=true;
		}
		else{
			flag=false;
		}
		logger.info("flag:"+flag);
		return SUCCESS;
	}
}

