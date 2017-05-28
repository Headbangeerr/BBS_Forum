package com.bbsforum.test;


import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.bbsforum.biz.BoardBiz;
import com.bbsforum.biz.PostBiz;
import com.bbsforum.biz.UserBiz;
import com.bbsforum.bizimpl.BoardBizImpl;
import com.bbsforum.bizimpl.UserBizImpl;
import com.bbsforum.dao.BoardDao;
import com.bbsforum.dao.MessageDao;
import com.bbsforum.dao.PostDao;
import com.bbsforum.dao.UserDao;
import com.bbsforum.daoimpl.BoardDaoImpl;
import com.bbsforum.entity.Board;
import com.bbsforum.entity.Childboard;
import com.bbsforum.entity.Message;
import com.bbsforum.entity.Post;
import com.bbsforum.entity.Reply;
import com.bbsforum.entity.User;

public class test {

	private static Logger logger = Logger.getLogger(test.class);  
	public static void main(String[] args) throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		
//		 TODO Auto-generated method stub
//		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		BoardDao boardDAO=(BoardDao) context.getBean("boardDao");
//		Set<Childboard> childBoard=boardDAO.getChildBoardList(2);
//		for (Childboard temp : childBoard) {
//			System.out.println(temp.getParentBoard().getChildBoard());
//		}
	//	boardDAO.deleteChildBoard(3);
//		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		MessageDao messageDao= (MessageDao) context.getBean("messageDao");
//		List<Message> list=messageDao.getMessageByReceiverMail("1111");
//		for (Message message : list) {
//			logger.info(message.getPublisherMail().getUsername());
//		}
//		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		UserDao userDao=(UserDao) context.getBean("userDao");
//		User user=userDao.findUserByMailAddress("0000");
//		logger.info("0000的好友个数："+user.getFriends().size());
		
//		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		PostDao userDao=(PostDao) context.getBean("postDao");
//		Timestamp d = new Timestamp(System.currentTimeMillis());
//		Reply reply=new Reply();
//		reply.setId(21321);
//		reply.setContent("sfdsfsa");
//		reply.setPostId("sdrw");
//		reply.setSenderMail("wer");
//		reply.setSendtime(d);
//		userDao.addReply(reply);

	}

	
}
