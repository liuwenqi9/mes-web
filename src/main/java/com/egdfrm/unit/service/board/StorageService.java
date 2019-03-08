package com.egdfrm.unit.service.board;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.board.StorageMapper;
import com.egdfrm.unit.model.board.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 待入库接口实现
 * Created by tyq on 17/1/17.
 */
@Service
public class StorageService implements IStorageService {

    @Autowired
    private StorageMapper storageMapper;

    /**
     * 待入库分页查询
     * @param pagination 分页信息
     * @param orgId 组织ID
     * @return
     */
    @Override
    public Pagination findPage(Pagination pagination, int orgId) {
        long count = storageMapper.getCount(orgId);
        List<Storage> list = storageMapper.findPage(pagination,orgId);
        Storage storage = storageMapper.sum_findPage(orgId);
        storage.setInv_date("汇总");
        storage.setPlan_line("");
        list.add(0, storage);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    /**
     * 待入库报表数据查询
     * @param orgId 组织ID
     * @return
     */
    @Override
    public List<Storage> finAll(int orgId) {
        return storageMapper.finAll(orgId);
    }
    
    /**
     * 待入库分页查询(报表)
     * @param pagination 分页信息
     * @param orgId 组织ID
     * @return
     */
    @Override
    public Pagination findPage2(Pagination pagination, int orgId) {
        long count = storageMapper.getCount2(orgId);
        List<Storage> list = storageMapper.findPage2(pagination,orgId);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }
}
