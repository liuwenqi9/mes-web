package com.egdfrm.unit.service.reportManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.excelmodel.PickException;
import com.egdfrm.unit.mapper.reportManagement.PickExceptionMapper;

@Service
public class PickExceptionService {
	
	@Autowired
	private PickExceptionMapper pickExceptionMapper;
	/**
     *  挑库异常 分页查询 
     */ 
    public Pagination findPage(Pagination pagination,PickException pickException) {
        long count = pickExceptionMapper.findPageCount(pickException);
        List<PickException> list = pickExceptionMapper.findPage(pagination,pickException);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    
    public List<PickException> export(PickException pickException){
    	return pickExceptionMapper.export(pickException);
    }
}
