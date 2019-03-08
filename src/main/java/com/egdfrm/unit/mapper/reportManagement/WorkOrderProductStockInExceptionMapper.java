package com.egdfrm.unit.mapper.reportManagement;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.WorkOrderProductStockInException;

public interface WorkOrderProductStockInExceptionMapper {
	
	 
    long findPageCount(@Param("work") WorkOrderProductStockInException workOrderProductStockInException);

    
    List<WorkOrderProductStockInException> findPage(@Param("page") Pagination pagination,@Param("work") WorkOrderProductStockInException workOrderProductStockInException);

    
    List<WorkOrderProductStockInException> export(@Param("work") WorkOrderProductStockInException workOrderProductStockInException);

}
