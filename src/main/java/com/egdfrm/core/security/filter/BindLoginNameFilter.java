package com.egdfrm.core.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.egdfrm.core.common.Constants;
import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;

/**
 * <p>
 * ClassName: BindLoginNameFilter
 * </p>
 * <p>
 * Description: 登陆后,将当前登陆名放入请求对象中
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2014年4月21日
 * </p>
 */
public class BindLoginNameFilter extends PathMatchingFilter {
	
	@Autowired
	private TtApplicationUserMapper taum;
	
	protected boolean onPreHandle(ServletRequest req, ServletResponse res, Object mappedValue)
	      throws Exception {
		Subject sb = SecurityUtils.getSubject();
		ShiroUser user = (ShiroUser) sb.getPrincipal();
		if (user != null) {
			String username = user.getLoginName();
			req.setAttribute(Constants.CURRENT_LOGIN_NAME, username);
			req.setAttribute("userId", user.getUserId());
			//新增存入组织ID add by sjf 20170105
			req.setAttribute(Constants.CURRENT_ORGANIZATION_ID, user.getOrgId());
			req.setAttribute(Constants.CURRENT_LOGIN_INFO, user);
			
			HttpServletRequest hsreq=(HttpServletRequest) req;
			hsreq.getSession().setAttribute(Constants.IS_LOGIN, true);
			
		}
		return true;
	}
}
