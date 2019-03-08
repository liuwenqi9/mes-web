package com.egdfrm.unit.mapper.board;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.board.TobeTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by tyq on 17/1/17.
 */
public interface TobeTestMapper {

    /**
     * 查询待检验数据总数
     * @return
     */
    long getCount(@Param("orgId") int orgId);


    /**
     * 待检验分页查询
     * @param pagination 分页数据
     * @return
     */
    List<TobeTest> findPage(@Param("page")Pagination pagination,@Param("orgId")int orgId);

    TobeTest sum_findPage(@Param("orgId")int orgId);
    /**
     * 查询待检验数据总数 （报表）
     * @return
     */
    long getCount2(@Param("orgId") int orgId);


    /**
     * 待检验分页查询（报表）
     * @param pagination 分页数据
     * @return
     */
    List<TobeTest> findPage2(@Param("page")Pagination pagination,@Param("orgId")int orgId);
    
    /**
     * 查询所有待检验数据
     * @param orgId 组织ID
     * @return
     */
    List<TobeTest> finAll(@Param("orgId") int orgId);

}
