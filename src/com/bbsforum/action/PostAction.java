package com.bbsforum.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.biz.PostBiz;
import com.bbsforum.biz.ReplyBiz;
import com.bbsforum.biz.UserBiz;
import com.bbsforum.dao.BoardDao;
import com.bbsforum.dao.PostDao;
import com.bbsforum.dao.ReplyDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.daoimpl.PostDaoImlp;
import com.bbsforum.entity.Childboard;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.Post;
import com.bbsforum.entity.Reply;
import com.bbsforum.entity.User;

@ParentPackage("json-default")//要使用json必须要依赖于该包
public class PostAction extends BaseAction {
	Logger logger=Logger.getLogger(PostAction.class);
	private List<Post> lastestPostList;
	public List<Post> getLastestPostList() {
		return lastestPostList;
	}
	public void setLastestPostList(List<Post> lastestPostList) {
		this.lastestPostList = lastestPostList;
	}
	
	boolean flag;
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	private PageBean pageBean;
		
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
	private PageBean postBean;
	
	public PageBean getPostBean() {
		return postBean;
	}
	public void setPostBean(PageBean postBean) {
		this.postBean = postBean;
	}
	
	private PageBean replyBean;
	
	public PageBean getReplyBean() {
		return replyBean;
	}
	public void setReplyBean(PageBean replyBean) {
		this.replyBean = replyBean;
	}
	
	private Post post;
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}

	private String publisherMail;
	
	@JSON(serialize=false)
	public String getPublisherMail() {
		return publisherMail;
	}
	public void setPublisherMail(String publisherMail) {
		this.publisherMail = publisherMail;
	}
	
	private Integer childboardId;
	@JSON(serialize=false)
	public Integer getChildboardId() {
		return childboardId;
	}
	public void setChildboardId(Integer childboardId) {
		this.childboardId = childboardId;
	}


	private String title;
	@JSON(serialize=false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	private String content;
	@JSON(serialize=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	private int bid;
	@JSON(serialize=false)
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	private String pid;
	@JSON(serialize=false)
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	private int page;
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	private int cid;
	@JSON(serialize=false)
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	@Autowired
	PostBiz postBiz;
	public void setPostBiz(PostBiz postBiz) {
		this.postBiz = postBiz;
	}
	@Autowired
	UserBiz userBiz;
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}
	@Autowired
	BoardDao boardDao;
	public void setBoardDao(BoardDao boardDao){
		this.boardDao=boardDao;
	}
	@Autowired
	PostDao postdDao;
	public void setPostDao(PostDao postdDao){
		this.postdDao=postdDao;
	}
	
	@Action(value="showLastestPostOnIndexPage",results={
			@Result(name="success",type="json",params={
					"excludeProperties","lastestPostList\\[\\d+\\]\\.childboardId.parentBoard,"
							+ "lastestPostList\\[\\d+\\]\\.childboardId.posts,"
							+ "lastestPostList\\[\\d+\\]\\.publisherMail.posts,"
							+ "lastestPostList\\[\\d+\\]\\.publisherMail.friends,"
							+ "lastestPostList\\[\\d+\\]\\.publisherMail.replys"})
			//这里必须使用正则表达式，具体说明请见错误说明文档
	})
	public String  showLastestPostOnIndexPage(){
		lastestPostList=new ArrayList<Post>();
		lastestPostList=postBiz.getLastestPost(1, 10);//显示前十条最新发帖
		return SUCCESS;
	}
	
	@Autowired
	private PageViewBiz pageViewBiz;
	public PageViewBiz getPageViewBiz() {
		return pageViewBiz;
	}
	public void setPageViewBiz(){
		this.pageViewBiz=pageViewBiz;
	}
	
	@Action(value="showPostByPage",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "pageBean.list\\[\\d+\\]\\.publisherMail.posts,"
							+ "pageBean.list\\[\\d+\\]\\.childboardId.posts,"
							+ "pageBean.list\\[\\d+\\]\\.publisherMail.friends,"
							+"pageBean.list\\[\\d+\\]\\.childboardId.parentBoard,"
							+ "pageBean.list\\[\\d+\\]\\.publisherMail.replys"})
	}) 
	public String showPostByPage(){
		User publisher=userBiz.getUserByMailAddress(publisherMail);
		pageBean=pageViewBiz.showPostBypage(page, 5, publisherMail, publisher.getPosts().size());
		logger.info("成功获取到帖子页面…… 页面中的帖子条数为："+pageBean.getList().size());
		return SUCCESS;
	}
	
	@Action(value="checkPostByUrl",results={
			@Result(name="self",location="/pages/postshow.jsp")
	})
	public String checkPostByUrl(){
		getSession().put("bid", bid);
		postBean=pageViewBiz.showChoosePostBypage(1, 5, bid);
		getRequest().put("pageBean", pageBean);
		return "self";
	}
	
	@Action(value="showChoosePostByPage",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "pageBean.list\\[\\d+\\]\\.publisherMail.posts,"
							+ "pageBean.list\\[\\d+\\]\\.childboardId.posts,"
							+ "pageBean.list\\[\\d+\\]\\.publisherMail.friends,"
							+"pageBean.list\\[\\d+\\]\\.childboardId.parentBoard,"
							+"pageBean.list\\[\\d+\\]\\.publisherMail.replys"})
	}) 
	public String showChoosePostByPage(){
		pageBean=pageViewBiz.showChoosePostBypage(page, 5, bid);
		logger.info("成功获取到帖子页面…… 页面中的帖子条数为："+pageBean.getList().size());
		return SUCCESS;
	}

	@Action(value="addPost",results={
			@Result(name="success",type="json")
	})
	public String addPost(){
		int x=(int)(Math.random()*100);
		String a="a"+x;
		User publishser=(User)getSession().get("user");
		Timestamp d = new Timestamp(System.currentTimeMillis());
		Post post=new Post();
		Childboard childboard=boardDao.getChildboard(childboardId);
		post.setId(a);
		post.setTitle(title);
		post.setContent(content);
		post.setPublisherMail(publishser);
		logger.info("发帖者"+publishser.getMailAddress());
		//System.out.println("werwerwerwerwerw"+d);
		post.setPublishTime(d);
		post.setChildboardId(childboard);
		post.setPageView(0);
		if(postBiz.addPost(post)){
			flag=true;
		}
		else{
			flag=false;
		}
		logger.info("flag:"+flag);
		return SUCCESS;
	}
	
	@Action(value="serchPost",results={
			@Result(name="self",location="/reply.jsp")
	})
	public String serchPost(){
		post=postBiz.getPost(pid);
		int vie=post.getPageView()+1;
		post.setPageView(vie);
		postdDao.updaPost(post);
		getSession().put("pidshow", pid);
		post.getPublishTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateString = formatter.format(post.getPublishTime());
		getSession().put("dateString", dateString);
		replyBean=pageViewBiz.showReplyBypage(1, 5, pid);
		getRequest().put("pageBean", pageBean);
		return "self";
	}
	
	@Action(value="showAllPostByPage",results={
			@Result(name="success",type="json",params={
					"excludeProperties",  "pageBean.list\\[\\d+\\]\\.publisherMail.posts,"
							+ "pageBean.list\\[\\d+\\]\\.childboardId.posts,"
							+ "pageBean.list\\[\\d+\\]\\.publisherMail.friends,"
							+"pageBean.list\\[\\d+\\]\\.childboardId.parentBoard,"
							+"pageBean.list\\[\\d+\\]\\.publisherMail.replys"})
	}) 
	public String showAllPostByPage(){
		pageBean=pageViewBiz.showAllPostBypage(page, 5);
		logger.info("成功获取到帖子页面…… 页面中的帖子条数为："+pageBean.getList().size());
		return SUCCESS;
	}
	
	@Action(value="checkAllPostByUrl",results={
			@Result(name="self",location="/pages/postAllshow.jsp")
	})
	public String checkAllPostByUrl(){
		postBean=pageViewBiz.showAllPostBypage(1, 5);
		getRequest().put("pageBean", pageBean);
		return "self";
	}


	@Action(value="showViePostByPage",results={
			@Result(name="success",type="json",params={
					"excludeProperties",  "pageBean.list\\[\\d+\\]\\.publisherMail.posts,"
							+ "pageBean.list\\[\\d+\\]\\.childboardId.posts,"
							+ "pageBean.list\\[\\d+\\]\\.publisherMail.friends,"
							+"pageBean.list\\[\\d+\\]\\.childboardId.parentBoard,"
							+"pageBean.list\\[\\d+\\]\\.publisherMail.replys"})
	}) 
	public String showViePostByPage(){
		pageBean=pageViewBiz.showViePostBypage(page, 5);
		logger.info("成功获取到帖子页面…… 页面中的帖子条数为："+pageBean.getList().size());
		return SUCCESS;
	}
	
	@Action(value="checkViePostByUrl",results={
			@Result(name="self",location="/pages/postVieshow.jsp")
	})
	public String checkViePostByUrl(){
		postBean=pageViewBiz.showViePostBypage(1, 5);
		getRequest().put("pageBean", pageBean);
		return "self";
	}
	
	@Action(value="checkJHPostByUrl",results={
			@Result(name="self",location="/pages/postJHshow.jsp")
	})
	public String checkJHPostByUrl(){
		List<Post> postJH=new ArrayList<Post>();
		int i=7;
		postJH=postBiz.getJHPost(i);
		getRequest().put("postJH", postJH);
		return "self";
	}

	@Action(value="checkZiPostByUrl",results={
			@Result(name="self",location="/pages/postZishow.jsp")
	})
	public String checkZiPostByUrl(){
		getSession().put("cidtt", cid);
		postBean=pageViewBiz.showZiPostBypage(1, 5, cid);
		getRequest().put("pageBean", pageBean);
		return "self";
	}
	
	@Action(value="showZiPostByPage",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "pageBean.list\\[\\d+\\]\\.publisherMail.posts,"
							+ "pageBean.list\\[\\d+\\]\\.childboardId.posts,"
							+ "pageBean.list\\[\\d+\\]\\.publisherMail.friends,"
							+"pageBean.list\\[\\d+\\]\\.childboardId.parentBoard,"
							+"pageBean.list\\[\\d+\\]\\.publisherMail.replys"})
	}) 
	public String showZiPostByPage(){
		pageBean=pageViewBiz.showZiPostBypage(page, 5, cid);
		logger.info("成功获取到帖子页面…… 页面中的帖子条数为："+pageBean.getList().size());
		return SUCCESS;
	}
}
