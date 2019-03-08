package com.egdfrm.unit.service.board;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.board.Storage;

import java.util.List;

/**
 * 待入库接口
 * Created by tyq on 17/1/17.
 */
public interface IStorageService {

    /**
     * 待入库分页查询
     * @param pagination 分页信息
     * @param orgId 组织ID
     * @return
     */
    Pagination findPage(Pagination pagination, int orgId);

    /**
     * 待入库报表数据查询
     * @param orgId 组织ID
     * @return
     */
    List<Storage> finAll(int orgId);
    
    /**
     * 待入库分页查询（报表）
     * @param pagination 分页信息
     * @param orgId 组织ID
     * @return
     */
    Pagination findPage2(Pagination pagination, int orgId);
}
