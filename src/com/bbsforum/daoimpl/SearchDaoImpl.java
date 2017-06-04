package com.bbsforum.daoimpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbsforum.dao.SearchDao;
import com.bbsforum.entity.Post;

public class SearchDaoImpl implements SearchDao {
	private Logger logger=Logger.getLogger(SearchDaoImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	private Session session;
	@Override
	public List SearchPostByKeywordForPage(String keyword, int offset, int PageSize) {
		session=sessionFactory.openSession();
		List<Post> PostPage=new ArrayList<Post>();
		Query query=session.createQuery("from Post where title like'%"+keyword+"%' or content like'%"+keyword+"%'");
		query.setFirstResult(offset);
		query.setMaxResults(PageSize);
		PostPage=query.list();
		session.close();
		return PostPage;	
	}
	@Override
	public List SearchUserByUsernameForPage(String username, int offset, int PageSize) {
		session=sessionFactory.openSession();
		List<Post> PostPage=new ArrayList<Post>();
		Query query=session.createQuery("from User where username like'%"+username+"%'");
		query.setFirstResult(offset);
		query.setMaxResults(PageSize);
		PostPage=query.list();
		session.close();
		return PostPage;	
	}
	@Override
	public int SearchSumPostByKeyword(String keyword) {
		session=sessionFactory.openSession();
		String hql="select count(*) from Post where title like'%"+keyword+"%' or content like'%"+keyword+"%'";
		Query query=session.createQuery(hql);
		Long sumNum=(Long)query.uniqueResult();
		int sum=sumNum.intValue();
		session.close();
		return sum;
	}
	@Override
	public int SearchSumUserByUsername(String username) {
		session=sessionFactory.openSession();
		String hql="select count(*) from User where username like'%"+username+"%'";
		Query query=session.createQuery(hql);
		Long sumNum=(Long) query.uniqueResult();
		int sum=sumNum.intValue();
		session.close();
		return sum;
	}
	@Override
	public int SearchSumPostByChildboard(String keyword,int childboardId) {
		session=sessionFactory.openSession();
		String hql="select count(*) from Post where childboardId=? and (title like'%"+keyword+"%' or content like'%"+keyword+"%')";
		Query query=session.createQuery(hql);
		query.setInteger(0, childboardId);
		Long sumNum=(Long) query.uniqueResult();
		int sum=sumNum.intValue();
		session.close();
		return sum;
	}
	@Override
	public List SearchPostByChildboard(String keyword,int childboardId, int offset,
			int pageSize) {
		List<Post> posts=new ArrayList<Post>();
		session=sessionFactory.openSession();
		String hql="from Post where childboardId=?  and (title like'%"+keyword+"%' or content like'%"+keyword+"%')";
		Query query=session.createQuery(hql);		
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		query.setInteger(0, childboardId);
		posts=query.list();
		logger.info("list .size"+posts.size());
		session.close();
		return posts;	
	}
	@Override
	public int SearchSumPostByUsername(String username,String keyword) {
		session=sessionFactory.openSession();
		String sql="select count(*) from post where post.publisher_mail in (select mail_address from user where username like'%"+username+"%')"
				+ "and (title like'%"+keyword+"%' or content like'%"+keyword+"%')";
		Query query=session.createSQLQuery(sql);
		BigInteger sumNum=(BigInteger) query.uniqueResult();		
		int sum=sumNum.intValue();
		session.close();
		return sum;
	}
	@Override
	public List SearchPostByUsername(String username,String keyword,int offset, int pageSize) {
		List<Post> posts=new ArrayList<Post>();
		session=sessionFactory.openSession();
		String sql="select * from post where post.publisher_mail in (select mail_address from user where username like'%"+username+"%') and "
				+ "(title like'%"+keyword+"%' or content like'%"+keyword+"%')";
		Query query=session.createSQLQuery(sql).addEntity(Post.class);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		posts=query.list();
		session.close();
		return posts;
	}


}
