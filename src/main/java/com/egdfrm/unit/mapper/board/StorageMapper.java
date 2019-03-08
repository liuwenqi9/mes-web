package com.egdfrm.unit.mapper.board;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.board.Storage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 待入库Mapper
 * Created by tyq on 17/1/17.
 */
public interface StorageMapper {

    /**
     * 查询待入库数据总数
     * @return
     */
    long getCount(@Param("orgId") int orgId);


    /**
     * 待入库分页查询
     * @param pagination 分页数据
     * @return
     */
    List<Storage> findPage(@Param("page")Pagination pagination, @Param("orgId")int orgId);

    
    Storage sum_findPage(@Param("orgId")int orgId);
    
    /**
     * 查询待入库数据总数（报表）
     * @return
     */
    long getCount2(@Param("orgId") int orgId);


    /**
     * 待入库分页查询（报表）
     * @param pagination 分页数据
     * @return
     */
    List<Storage> findPage2(@Param("page")Pagination pagination, @Param("orgId")int orgId);
    /**
     * 待入库报表数据查询
     * @param orgId 组织ID
     * @return
     */
    List<Storage> finAll(@Param("orgId")int orgId);

}
