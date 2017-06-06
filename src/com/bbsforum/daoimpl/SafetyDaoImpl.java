package com.bbsforum.daoimpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bbsforum.dao.SafetyDao;
import com.bbsforum.entity.Safety;

public class SafetyDaoImpl implements SafetyDao {
	@Autowired
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	Session session;

	@Override
	public List findSafetyByMail(Safety safety) {
		List list=null;
		Transaction tx=null;	//声明事务
		session=sessionFactory.openSession();		//获取session
		try {
			tx=session.beginTransaction();
			Criteria c=session.createCriteria(Safety.class);
			Example example=Example.create(safety);
			c.add(example);
			list=c.list();
			tx.commit();
		} catch (Exception e) {
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean addSafety(Safety safety) {
		Transaction tx=null;
		//获取session
		session=sessionFactory.openSession();
		try{
			tx=session.beginTransaction();
			session.save(safety);
			tx.commit();
			return true;
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}

}
