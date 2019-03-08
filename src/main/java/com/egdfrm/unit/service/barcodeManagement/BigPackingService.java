package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.barcodeManagement.BigPackingMapper;
import com.egdfrm.unit.model.barcodeManagement.BigPacking;
import com.egdfrm.unit.model.barcodeManagement.BigPackingPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BigPackingService {

    @Autowired
    private BigPackingMapper bigPackingMapper;

    /**
     * 大包装清单 分页查询
     * @param pagination 分页条件
     * @param bigPacking 查询条件
     * @return 数据集
     */
    public Pagination findPage(Pagination pagination, BigPacking bigPacking){
        long count = bigPackingMapper.getCount(bigPacking);
        List<BigPacking> list = bigPackingMapper.findPage(pagination, bigPacking);
        pagination.setTotal(count);
        pagination.setRows(list);
        return  pagination;
    }

    public  List<BigPackingPrint> findDetail(String id){
        return  bigPackingMapper.findDetail(id);
    }

    public  List<BigPackingPrint> findPrint(String[] ids){
        List<BigPackingPrint> list = new ArrayList<BigPackingPrint>();
        for (int i = 0; i <ids.length ; i++) {
            list.addAll(bigPackingMapper.findDetail(ids[i]));
        }
          return  list;
    }

}
