package com.egdfrm.unit.service.board;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.board.TobeTest;

import java.util.List;

/**
 * 待检验接口
 * Created by tyq on 17/1/17.
 */
public interface ITobeTestService {

    /**
     * 待检验分页查询
     * @param pagination 分页数据
     * @param orgId 组织ID
     * @return
     */
    Pagination findPage(Pagination pagination,int orgId);

    /**
     * 待检验分页查询 报表分页
     * @param pagination 分页数据
     * @param orgId 组织ID
     * @return
     */
    Pagination findPage2(Pagination pagination,int orgId);
    
    /**
     * 查询所有待检验数据
     * @param orgId 组织ID
     * @return
     */
    List<TobeTest> finAll(int orgId);
}
