package com.bbsforum.daoimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bbsforum.dao.BoardDao;
import com.bbsforum.entity.Board;
import com.bbsforum.entity.Childboard;
import com.bbsforum.entity.Post;
import com.bbsforum.entity.User;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class BoardDaoImpl implements BoardDao {
	@Autowired
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
	public Set getChildBoardListByParentBoardId(int ParentBoardId) {
		Session session=sessionFactory.openSession();
		Board board=(Board) session.get(Board.class, ParentBoardId);
		if(board==null){
			System.out.println("Board null");
			return null;
		}
		Set<Childboard> childBoard=board.getChildBoard();
		for (Childboard childboard2 : childBoard) {
			System.out.println(childboard2.getName());
		}
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
			return true;
		}
		catch(Exception e){
			tr.rollback();
			return false;
		}
		finally{
			session.close();
		}
	}
	@Override
	public Childboard getChildboard(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Childboard chidboard=(Childboard)session.get(Childboard.class, id);
		session.close();
		return chidboard;
	}
	@Override
	public Board getBoard(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();	
		Board board=(Board)session.get(Board.class, id);
		session.close();
		return board;
	}
	
	@Override
	public void addBoard(Board board) {
		Session session=sessionFactory.openSession();
	    Transaction tx=session.beginTransaction();
		session.save(board);
	    tx.commit();
		session.close();
	}
	@Override
	public void addChildBoard(Childboard childBoard) {
		Session session=sessionFactory.openSession();
	    Transaction tx=session.beginTransaction();
		session.save(childBoard);
	    tx.commit();
		session.close();
		
	}
	@Override
	public Board getBoardById(int id) {
		Session session=sessionFactory.openSession();
	    Transaction tx=session.beginTransaction();
	    Board board=(Board) session.get(Board.class, id);
	    tx.commit();
		session.close();
		return board;
	}
	@Override
	public void updateBoard(Board board) {
		Session session=sessionFactory.openSession();
	    Transaction tx=session.beginTransaction();
         session.update(board);
	    tx.commit();
		session.close();
		
	}
	@Override
	public void deleteBoard(int id) {
		Session session=sessionFactory.openSession();
	    Transaction tx=session.beginTransaction();
        Board board=(Board) session.get(Board.class, id);
        session.delete(board);
	    tx.commit();
		session.close();
		
	}
	@Override
	public void updateChildBoard(Childboard childBoard) {
		Session session=sessionFactory.openSession();
	    Transaction tx=session.beginTransaction();
        session.update(childBoard);
	    tx.commit();
		session.close();
		
		
	}
	@Override
	public Childboard getChildBoardById(int id) {
		Session session=sessionFactory.openSession();
	    Transaction tx=session.beginTransaction();
	    Childboard childboard=(Childboard) session.get(Childboard.class, id);
	    tx.commit();
		session.close();
		return childboard;
	}
	


}
