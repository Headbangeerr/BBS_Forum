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
import com.bbsforum.biz.SafetyBiz;
import com.bbsforum.biz.UserBiz;
import com.bbsforum.dao.UserDao;
import com.bbsforum.entity.Childboard;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.Post;
import com.bbsforum.entity.Safety;
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
	private int type;
	private String username;
	private String sex;
	private String signature;
	private String safetyCode;
	private int safetyCodeFlag;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSafetyCode() {
		return safetyCode;
	}
	public void setSafetyCode(String safetyCode) {
		this.safetyCode = safetyCode;
	}
	public int getSafetyCodeFlag() {
		return safetyCodeFlag;
	}
	public void setSafetyCodeFlag(int safetyCodeFlag) {
		this.safetyCodeFlag = safetyCodeFlag;
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
	@Autowired
	SafetyBiz safetyBiz;
	public void setSafetyBiz(SafetyBiz safetyBiz) {
		this.safetyBiz = safetyBiz;
	}
	
	@Action(value="login",results={
			@Result(name="success",location="/index.jsp"),
			@Result(name="login",location="/login.jsp"),
			@Result(name="superman",location="/superman.jsp")
	})
	public String login(){//登陆
		logger.info("login⋯⋯   useraddress:"+mailAddress);
		User user=userBiz.getUserByMailAddress(mailAddress);
		getSession().put("mailAddress", mailAddress);
		if(user==null){
			errorFlag=0;
			return LOGIN;
		}else{
			if((user.getPassword().endsWith(password))&&(user.getType().equals(type))){
				getSession().put("user", user);
				switch (type) {
				case 1:
					return SUCCESS;
				case 2:
					return "superman";
				case 0:
					return SUCCESS;
				default:
					errorFlag=2;
					return LOGIN;
				}
			}else{
				if(!user.getType().equals(type)){
					errorFlag=4;
				}else{
					errorFlag=2;
				}
				return LOGIN;
			}
		}
	}
	//注册
	@Action(value="regist",results={
			@Result(name="success",location="/login.jsp"),
			@Result(name="error",location="/regist.jsp")
	})
	public String regist(){
		User user=new User();
		if(userBiz.getUserByMailAddress(mailAddress)!=null){
			errorFlag=0;
			return ERROR;
		}else{
			user.setMailAddress(mailAddress);
			user.setUsername(username);
			user.setPassword(password);
			user.setSex(sex);
			user.setType(0);
			user.setLevel(1);
			user.setSignature("还未设置个人签名。");
			if(userBiz.addUser(user)){
				errorFlag=3;
				return SUCCESS;
			}else{
				return ERROR;
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
			//安全码设置
			Safety safety=new Safety();
			safety.setMailAddress(user.getMailAddress());
			List list=safetyBiz.findSafetyByMail(safety);
			if(list.size()>0){
				safetyCodeFlag=1;
			}else{
				safetyCodeFlag=0;
			}
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
	@Action(value="updateMessage",results={
			@Result(name="success",location="/index.jsp"),
			@Result(name="error",location="/index.jsp")
	})
	public String updateMessage(){
		User user=userBiz.getUserByMailAddress(((User) getSession().get("user")).getMailAddress());
		user.setUsername(username);
		user.setSex(sex);
		user.setSignature(signature);
		if(userBiz.updateUser(user)){
			getSession().put("user", user);
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	@Action(value="setSafetyCode",results={
			@Result(name="success",location="/index.jsp"),
			@Result(name="error",location="/personal.jsp")
	})
	public String setSafetyCode(){
		User user=(User) getSession().get("user");
		Safety safety=new Safety();
		safety.setMailAddress(user.getMailAddress());
		safety.setSafetyCode(safetyCode);
		if(safetyBiz.addSafety(safety)){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	@Action(value="checkSafetyCode",results={
			@Result(name="success",location="/updatePwd.jsp"),
			@Result(name="error",location="/checkSafetyCode.jsp")
	})
	public String checkSafetyCode(){
		Safety safety=new Safety();
		safety.setMailAddress(mailAddress);
		safety.setSafetyCode(safetyCode);
		List list=safetyBiz.findSafetyByMail(safety);
		if(list.size()>0){
			getSession().put("mailAddress", safety.getMailAddress());
			return SUCCESS;
		}else{
			errorFlag=0;
			return ERROR;
		}
	}
	@Action(value="updatePwd",results={
			@Result(name="success",location="/login.jsp"),
			@Result(name="error",location="/updatePwd.jsp")
	})
	public String updatePwd(){
		User user=userBiz.getUserByMailAddress((String) getSession().get("mailAddress"));
		user.setPassword(password);
		if(userBiz.updateUser(user)){
			errorFlag=1;
			getSession().clear();
			return SUCCESS;
		}else{
			errorFlag=0;
			return ERROR;
		}
	}
}


