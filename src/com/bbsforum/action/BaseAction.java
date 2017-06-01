package com.bbsforum.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware, RequestAware {
 
	
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private Map<String, Object> request;
	
	@Override//重写自父接口SessionAware的方法,有容器自行调用并给session对象赋值
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	@Override//重写自父接口RequestAware的方法,有容器自行调用并给request对象赋值
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	//用于在子类中获取session对象
	public Map<String, Object> getSession(){
		return session;
	}
	
	//用于在子类中获取request对象
	public Map<String, Object> getRequest(){
		return request;
	}
	


}
