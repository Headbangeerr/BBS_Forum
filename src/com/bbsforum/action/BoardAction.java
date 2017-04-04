package com.bbsforum.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbsforum.biz.BoardBiz;
import com.mysql.jdbc.log.Log;

@ParentPackage("json-default")//要使用json必须要依赖于该包
public class BoardAction extends BaseAction {

	BoardBiz boardBiz;
	public void setBoardBiz(BoardBiz boardBiz) {
		this.boardBiz = boardBiz;
	}
	
	private List BoardList;
	public void setBoardList(List boardList) {
		BoardList = boardList;
	}
	public List getBoardList() {
		return BoardList;
	}


	@Action(value="getBoardList",results={
			@Result(name="success",type="json",params={
					"excludeProperties","boardList\\[\\d+\\]\\.childBoard\\[\\d+\\]\\.posts,"
							})
	})
	public String  getAllBoard(){
		BoardList=boardBiz.getBoardList();
		return SUCCESS;
	}

}
