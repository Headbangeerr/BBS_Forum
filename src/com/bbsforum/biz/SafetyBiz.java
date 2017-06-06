package com.bbsforum.biz;

import java.util.List;
import com.bbsforum.entity.Safety;

public interface SafetyBiz {
	public List findSafetyByMail(Safety safety);
	public boolean addSafety(Safety safety);

}
