package com.bbsforum.bizimpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bbsforum.biz.BoardBiz;
import com.bbsforum.dao.BoardDao;
@Transactional
public class BoardBizImpl implements BoardBiz {

	BoardDao boardDao;
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public List getBoardList() {
		List boardlist=boardDao.getBoardList();
		return boardlist;
	}

}
