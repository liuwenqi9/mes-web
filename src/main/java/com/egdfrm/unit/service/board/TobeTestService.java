package com.egdfrm.unit.service.board;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.board.TobeTestMapper;
import com.egdfrm.unit.model.board.TobeTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 待检验实现
 * Created by tyq on 17/1/17.
 */
@Service
public class TobeTestService implements ITobeTestService {

    @Autowired
    private TobeTestMapper tobeTestMapper;

    /**
     * 待检验分页查询
     * @param pagination 分页数据
     * @param orgId 组织ID
     * @return
     */
    @Override
    public Pagination findPage(Pagination pagination,int orgId) {
        long count = tobeTestMapper.getCount(orgId);
        List<TobeTest> list = tobeTestMapper.findPage(pagination,orgId);
        TobeTest tobeTest = tobeTestMapper.sum_findPage(orgId);
        tobeTest.setInspect_date("汇总");
        tobeTest.setPlan_line("");
        list.add(0, tobeTest);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    /**
     * 查询所有待检验数据
     * @param orgId 组织ID
     * @return
     */
    @Override
    public List<TobeTest> finAll(int orgId) {
        return tobeTestMapper.finAll(orgId);
    }
    
    /**
     * 待检验分页查询
     * @param pagination 分页数据
     * @param orgId 组织ID
     * @return
     */
    @Override
    public Pagination findPage2(Pagination pagination,int orgId) {
        long count = tobeTestMapper.getCount2(orgId);
        List<TobeTest> list = tobeTestMapper.findPage2(pagination,orgId);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }
}
