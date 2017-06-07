package com.bbsforum.bizimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bbsforum.biz.BoardBiz;
import com.bbsforum.dao.BoardDao;
import com.bbsforum.entity.Board;
import com.bbsforum.entity.Childboard;
@Transactional
public class BoardBizImpl implements BoardBiz {

	@Autowired
	BoardDao boardDao;
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public List getBoardList() {
		List boardlist=boardDao.getBoardList();
		return boardlist;
	}

	@Override
	public Set getChildboardByParentBoardId(int ParentBoardId) {
		
		return boardDao.getChildBoardListByParentBoardId(ParentBoardId);
	}
	@Override
	public void addBoard(Board board) {
		boardDao.addBoard(board);
		
	}

	@Override
	public void addChildBoard(Childboard childBoard) {
		boardDao.addChildBoard(childBoard);
		
	}

	@Override
	public Board getBoardById(int id) {
		// TODO Auto-generated method stub
		return boardDao.getBoardById(id);
	}

	@Override
	public void updateBoard(Board board) {
		boardDao.updateBoard(board);
		
	}

	@Override
	public void deleteBoard(int id) {
		boardDao.deleteBoard(id);
		
	}

	@Override
	public void updateChildBoard(Childboard childBoard) {
		boardDao.updateChildBoard(childBoard);
		
	}

	@Override
	public Childboard getChildBoardById(int id) {
		// TODO Auto-generated method stub
		return boardDao.getChildBoardById(id);
	}

}
