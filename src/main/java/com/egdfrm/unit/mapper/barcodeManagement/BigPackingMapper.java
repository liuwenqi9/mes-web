package com.egdfrm.unit.mapper.barcodeManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.barcodeManagement.BigPacking;
import com.egdfrm.unit.model.barcodeManagement.BigPackingPrint;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 大包装箱清单Mapper
 * Created by tyq on 17/1/19.
 */
public interface BigPackingMapper {

    /**
     * 查询总数据条数
     * @param bigPacking 查询条件
     * @return 数据总数
     */
    long getCount(@Param("bigPacking") BigPacking bigPacking);

    /**
     * 发运清单条码分页查询
     * @param pagination 分页条件
     * @param bigPacking 查询条件
     * @return 数据集
     */
    List<BigPacking> findPage(@Param("page") Pagination pagination, @Param("bigPacking") BigPacking bigPacking);

    List<BigPackingPrint> findDetail(@Param("id") String id);

}
