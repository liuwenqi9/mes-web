package com.egdfrm.unit.controller.board;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.service.board.IToStockingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 待备货_控制器
 * Created by tyq on 17/1/18.
 */
@Controller
@RequestMapping("board/stocking")
public class ToStockingController {

    @Autowired
    private IToStockingService iToStockingService;

    /**
     * 跳转到待备货待发货页面
     * @return
     */
    @RequestMapping("init")
    public String init(){
        return "unit/board/stocking_manage";
    }

    /**
     * 待备货分页查询
     * @param page 分页条件
     * @return 数据集
     */
    @RequestMapping("findPage")
    @ResponseBody
    public String findPage(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, Pagination page){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iToStockingService.findPage(pagination,user.getOrgId().intValue());
        return JSON.toJSONString(page);
    }
    
    /**
     * 待备货分页查询
     * @param page 分页条件
     * @return 数据集
     */
    @RequestMapping("findPage1")  
    @ResponseBody
    public String findPage1(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, Pagination page){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iToStockingService.findPage1(pagination,user.getOrgId().intValue());
        return JSON.toJSONString(page);
    }
}
