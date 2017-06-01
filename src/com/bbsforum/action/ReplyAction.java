package com.bbsforum.action;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.biz.PageViewBiz;
import com.bbsforum.biz.ReplyBiz;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.Reply;
import com.bbsforum.entity.User;
@ParentPackage("json-default")
public class ReplyAction  extends BaseAction{
	Logger logger=Logger.getLogger(PostAction.class);
	private PageBean pageBean;
	
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	boolean flag;
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	private int page;
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	private String content;
	@JSON(serialize=false)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Autowired
	ReplyBiz replyBiz;
	public ReplyBiz getReplyBiz() {
		return replyBiz;
	}
	public void setReplyBiz(ReplyBiz replyBiz) {
		this.replyBiz = replyBiz;
	}
	@Autowired
	private PageViewBiz pageViewBiz;
	public PageViewBiz getPageViewBiz() {
		return pageViewBiz;
	}
	public void setPageViewBiz(){
		this.pageViewBiz=pageViewBiz;
	}
	
	@Action(value="addReply",results={
			@Result(name="success",type="json")
	})
	public String addReply(){
		User publishser=(User)getSession().get("user");
		Timestamp d = new Timestamp(System.currentTimeMillis());
		Reply reply=new Reply();
		//System.out.println("asrfewrwerewrwerewrwerewrwerewrwe"+content+"sdfasfadsfasdfadsf");
		reply.setSenderMail(publishser);
		reply.setSendtime(d);
		reply.setContent(content);
		reply.setPostId(getSession().get("pidshow").toString());
		logger.info("mai:"+publishser.getMailAddress()+"tim："+d+"cont"+content+"pid"+getSession().get("pidshow").toString());
		if(content.equals("请输入回帖内容")){
			flag=false;
		}else{
			
			if(replyBiz.addReply(reply)){
				flag=true;
			}
			else{
				flag=false;
			}
		}

		logger.info("flag:"+flag);
		return SUCCESS;
	}
	@Action(value="showReplyByPage",results={
			@Result(name="success",type="json",params={
					"excludeProperties", "pageBean.list\\[\\d+\\]\\.senderMail.posts,"
							+ "pageBean.list\\[\\d+\\]\\.senderMail.friends,"
							+ "pageBean.list\\[\\d+\\]\\.senderMail.replys"})
	}) 
	public String showReplyByPage(){
		//System.out.println(getSession().get("pidshow").toString());
		pageBean=pageViewBiz.showReplyBypage(page, 5, getSession().get("pidshow").toString());
		logger.info("成功获取到帖子页面…… 页面中的帖子条数为："+pageBean.getList().size());
		return SUCCESS;
	}
		
}
