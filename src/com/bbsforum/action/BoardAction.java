package com.bbsforum.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbsforum.biz.BoardBiz;
import com.bbsforum.entity.Childboard;
import com.mysql.jdbc.log.Log;

@ParentPackage("json-default")//要使用json必须要依赖于该包
public class BoardAction extends BaseAction {

	private int parentboardId;//接受前台参数
	@JSON(serialize=false)//通过该注解控制json转换时不转换该属性
	public int getParentboardId() {
		return parentboardId;
	}
	public void setParentboardId(int parentboardId) {
		this.parentboardId = parentboardId;
	}

	@Autowired
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
	
	private Set<Childboard> ChildBoardList=new HashSet<Childboard>();
	public Set getChildBoardList() {
		return ChildBoardList;
	}
	public void setChildBoardList(Set childBoardList) {
		ChildBoardList = childBoardList;
	}
	
	@JSON(serialize=false)
	@Action(value="getBoardList",results={
			@Result(name="success",type="json",params={
					"excludeProperties","boardList\\[\\d+\\]\\.childBoard\\[\\d+\\]\\.posts,"
							})
	})
	public String  getAllBoard(){
		BoardList=boardBiz.getBoardList();
		return SUCCESS;
	}
	
	@JSON(serialize=false)
	@Action(value="getChildBoardListByParentBoardId",results={
			@Result(name="success",type="json",params={
					"excludeProperties","childBoardList\\[\\d+\\]\\.parentBoard,"
					+"childBoardList\\[\\d+\\]\\.posts"
			})
	})
	public String  getChildBoardByParentBoardId(){
		ChildBoardList=boardBiz.getChildboardByParentBoardId(parentboardId);
		//System.out.println("ChildBoardList"+ChildBoardList.size());
		return SUCCESS;
	}
}
