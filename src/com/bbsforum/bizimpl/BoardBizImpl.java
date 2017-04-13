package com.bbsforum.bizimpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bbsforum.biz.BoardBiz;
import com.bbsforum.dao.BoardDao;
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

}
