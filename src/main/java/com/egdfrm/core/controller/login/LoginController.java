/**
 * LoginController.java
 * Created at 2013-11-30
 * Created by wangkang
 * Copyright (C) egdfrm.
 */
package com.egdfrm.core.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egdfrm.core.common.Constants;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.core.security.annotation.CurrentUser;
import com.egdfrm.core.service.user.UserService;

/**
 * <p>
 * ClassName: LoginController
 * </p>
 * <p>
 * Description: 登陆
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年11月30日
 * </p>
 */
@Controller
public class LoginController extends BaseController {
	
	
	/**
	 * 用户服务
	 * @author hgb
	 * @date 201-12-30
	 */
    @Autowired
    private UserService us;

    /**
     * <p>
     * Description: 跳转到mainPage
     * </p>
     * 
     * @return main页面
     */
    @RequestMapping("/")
    public String toMainPage(@CurrentUser String loginName, HttpServletRequest request) {
       TtApplicationUser user =  us.getUserByLoginName(loginName);
       request.setAttribute("userName", user.getUserName());
        return "egdfrm/main";
    }

    /**
     * <p>
     * Description: shiro自动验证,验证失败后,调用此方法,返回失败原因到界面
     * </p>
     * 
     * @param req 请求对象
     * @return 登录页面
     */
    @RequestMapping(value = "login")
    public String login(HttpServletRequest req) {
    	Object objL=req.getSession().getAttribute(Constants.IS_LOGIN);
        if (!StringUtils.isEmpty(objL)){
            return "egdfrm/main";
        }
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String rv = "";
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            rv = "用户不存在";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            rv = "用户被锁定";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            rv = "用户名或者密码错误";
        } else if (exceptionClassName != null) {
            rv = "未知错误,请联系管理员";
            this.log.info(exceptionClassName);
        }
        req.setAttribute("rv", rv);
        return "egdfrm/login";
    }
}
