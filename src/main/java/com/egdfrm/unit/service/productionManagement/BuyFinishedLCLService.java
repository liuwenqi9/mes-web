package com.egdfrm.unit.service.productionManagement;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.service.BaseService;
import com.egdfrm.unit.mapper.productionManagement.BuyFinishedLCLMapper;

/**
 * @author sjf
 * @date 2017年2月15日
 * <p>
 * 采购成品拼箱
 */
@Service
public class BuyFinishedLCLService extends BaseService {


    @Autowired
    private BuyFinishedLCLMapper buyFinishedLCLMapper;


    /**
     * @param packageBarcode
     * @param wipBarcode
     * @return 获取包装箱信息
     * @author sjf
     * @date 2017年2月15日
     */
    public List<Map<String, Object>> getWipBarcode(String packageBarcode,
                                                   String wipBarcode) {
        return buyFinishedLCLMapper.getWipBarcode(packageBarcode, wipBarcode);
    }


    /**
     * @author sjf
     * @date 2017年2月16日
     * 提交
     */
    public void callCreatePoPack(Map<String, Object> paramMap) {
        buyFinishedLCLMapper.callCreatePoPack(paramMap);
    }


    /**
     * 根据包装箱号查询可包装数量
     * @param packageBarcode 包装箱号
     * @return 可包装数量
     */
    public String queryPackageByCount(String packageBarcode) {
        return buyFinishedLCLMapper.queryPackageByCount(packageBarcode);
    }
}
