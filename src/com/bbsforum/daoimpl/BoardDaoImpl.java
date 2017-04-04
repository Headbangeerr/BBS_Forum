package com.bbsforum.daoimpl;

import java.util.List;
import java.util.Set;






import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.bbsforum.dao.BoardDao;
import com.bbsforum.entity.Board;
import com.bbsforum.entity.Childboard;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class BoardDaoImpl implements BoardDao {
	SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	@Transactional
	public List getBoardList() {
		Session  session=sessionFactory.openSession();
		String hql="from Board";
		Query query=session.createQuery(hql);
		List boardList=query.list();
		session.flush();
		session.close();
		return boardList;
		
	}

	@Override
	public Set getChildBoardList(int ParentBoardId) {
		Session session=sessionFactory.openSession();
		Board board=(Board) session.get(Board.class, ParentBoardId);
		Set childBoard=board.getChildBoard();
		session.flush();
		session.close();
		return childBoard;
	}
	@Override
	public boolean deleteChildBoard(int ChildBoardId) {
		Session session=sessionFactory.openSession();
		Transaction tr=(Transaction) session.beginTransaction();
		Childboard childboard=(Childboard) session.get(Childboard.class, ChildBoardId);
		session.delete(childboard);
		session.flush();
		try{
			tr.commit();
			session.close();
			return true;
		}
		catch(Exception e){
			tr.rollback();
			session.close();
			return false;
		}
	}
	

}
