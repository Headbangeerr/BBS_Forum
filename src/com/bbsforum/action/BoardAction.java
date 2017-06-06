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
import com.bbsforum.entity.Board;
import com.bbsforum.entity.Childboard;
import com.mysql.jdbc.log.Log;

@ParentPackage("json-default")//要使用json必须要依赖于该包
public class BoardAction extends BaseAction {
    private Board board;
    private Childboard childboard;
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
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Childboard getChildboard() {
		return childboard;
	}
	public void setChildboard(Childboard childboard) {
		this.childboard = childboard;
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
	@Action(value="addBoard",results={
			@Result(name="success",location="/addBoardSuccess.jsp")
	}) 
	public String  addBoard(){
		if(childboard.getName()!=null){

       Board Board=new Board();
       Board.setName(board.getName());
       Board.setInfo(board.getInfo());
	   Childboard childBoard=new Childboard();
	   childBoard.setName(childboard.getName());
	   childBoard.setParentBoard(Board);
       boardBiz.addChildBoard(childBoard);
		return SUCCESS;
		}else{
			 Board Board=new Board();
			 Board.setName(board.getName());
			 Board.setInfo(board.getInfo());	
		       boardBiz.addBoard(Board);
		return SUCCESS;
		}
	}
	@Action(value="addChildBoard",results={
			@Result(name="success",location="/addBoardSuccess.jsp")
	}) 
	public String  addChildBoard(){
       Board Board=boardBiz.getBoardById(board.getId());
       System.out.println(board.getId());
       Childboard childBoard=new Childboard();
       childBoard.setName(childboard.getName());
       childBoard.setParentBoard(Board);
       boardBiz.addChildBoard(childBoard);
		return SUCCESS;

	}
	@Action(value="updateBoard",results={
			@Result(name="success",location="/updateBoardSuccess.jsp")
	}) 
	public String  updateBoard(){
        Board Board=boardBiz.getBoardById(board.getId());
        Board.setName(board.getName());
        Board.setInfo(board.getInfo());
        boardBiz.updateBoard(Board);
		return SUCCESS;

	}
	@Action(value="updateChildBoard",results={
			@Result(name="success",location="/updateBoardSuccess.jsp")
	}) 
	public String  updateChildBoard(){
       Board Board=boardBiz.getBoardById(board.getId());
       System.out.println(board.getId());
       Childboard ChildBoard=boardBiz.getChildBoardById(childboard.getId());   
       ChildBoard.setName(childboard.getName());
       ChildBoard.setParentBoard(Board);
       boardBiz.updateChildBoard(ChildBoard);
		return SUCCESS;

	}
	
	@Action(value="showBoardList1",results={
			@Result(name="success",location="/addchildBoard.jsp")
	}) 
	public String  showBoardList1(){
		BoardList=boardBiz.getBoardList();
		System.out.println(BoardList);
		getSession().put("BoardList1", BoardList);
		return SUCCESS;

	}
	@Action(value="showBoardList2",results={
			@Result(name="success",location="/BoardList.jsp")
	}) 
	public String  showBoardList2(){
		BoardList=boardBiz.getBoardList();
		System.out.println(BoardList);
		getRequest().put("BoardList2", BoardList);
		return SUCCESS;

	}
	
	@Action(value="deleteBoard",results={
			@Result(name="success",location="/deleteBoardSuccess.jsp")
	}) 
	public String deleteBoard(){
	
        boardBiz.deleteBoard(board.getId());
        System.out.println(board.getId());
		return SUCCESS;
	}
	
}
