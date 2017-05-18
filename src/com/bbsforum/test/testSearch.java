package com.bbsforum.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;
import org.apache.struts2.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bbsforum.biz.SearchBiz;
import com.bbsforum.dao.SearchDao;
import com.bbsforum.entity.PageBean;
import com.bbsforum.entity.Post;

public class testSearch {
	private static Logger logger=Logger.getLogger(testSearch.class);
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		SearchBiz searchBiz=(SearchBiz) context.getBean("searchBiz");
		PageBean pagebean=searchBiz.SearchPostByChildboardId("test", 4, 1, 6);
		List<Post> posts=new ArrayList<Post>();
		posts=pagebean.getList();
		for(Post post : posts){
			logger.info(post.getTitle());
		}
	}
	

}
