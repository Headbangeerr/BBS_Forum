package com.bbsforum.intercept;

import java.util.Map;

import org.apache.log4j.Logger;




import com.bbsforum.entity.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {
	private Logger logger=Logger.getLogger(LoginInterceptor.class);
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext context=arg0.getInvocationContext();
		Map<String, Object> session=context.getSession();
		User user=(User)session.get("user");
		logger.info("成功调用拦截器");
		if(user==null){
			return arg0.invoke();
		}else {
			return Action.LOGIN;
		}
	}

}
