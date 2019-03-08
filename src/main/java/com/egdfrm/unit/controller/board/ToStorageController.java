package com.egdfrm.unit.controller.board;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.service.board.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 待入库_控制器   
 */
@Controller
@RequestMapping("board/storage")
public class ToStorageController {

    @Autowired
    private IStorageService iStorageService;

    /**
     * 待检验分页查询
     * @param page 分页条件
     * @return
     */
    @RequestMapping("findPage")
    @ResponseBody
    public String findPage(@CurrentLoginInfo UserAuthenRealm.ShiroUser user, Pagination page){
        Pagination pagination = new Pagination(page.getOffset(),page.getLimit());
        page = iStorageService.findPage(pagination,user.getOrgId().intValue());
        return JSON.toJSONString(page);
    }
}
