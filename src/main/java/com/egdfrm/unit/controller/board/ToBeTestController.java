package com.egdfrm.unit.controller.board;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.service.board.ITobeTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 待检验_控制器
 * Created by tyq on 17/1/17.
 */
@Controller
@RequestMapping("board/tobeTest")
public class ToBeTestController { 

    @Autowired
    private ITobeTestService iTobeTestService;

    /**
     * 跳转到待检验待入库页面
     * @return
     */
    @RequestMapping("init")
    public String init(){
        return "unit/board/tobetest_manage";
    }


    /**
     * 待检验分页查询
     * @param page 分页条件
     * @return
     */
    @RequestMapping("findPage")
    @ResponseBody
    public String findPage(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, Pagination page){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iTobeTestService.findPage(pagination,user.getOrgId().intValue());
        return JSON.toJSONString(page);
    }
}
