package com.egdfrm.unit.service.productionManagement;

import java.util.List;
import java.util.Map;

/**
 * 特殊业务处理接口
 * Created by tyq on 17/2/10.
 */
public interface ISpecialBusinessService {

    /**
     * 产品条码重置
     * @param map
     */
    void productNumReset(Map<String, String> map);

    /**
     * 获取包装类型
     * @return 包装类型数据集
     */
    List<Map<String,Object>> getPackageTypes();

    /**
     * 包装条码重置
     * @param map
     */
    void packageNumReset(Map<String, String> map);

    /**
     * 包装尾数重置
     * @param map
     */
    void packageMantissaReset(Map<String, String> map);

    /**
     * 额外新增工单包装箱
     * @param map
     */
    void additional(Map<String, Object> map);

    /**
     * 工单转产
     * @param map
     */
    void theSingleTurn(Map<String, Object> map);
    
    /**
     *  验证包装箱的线别
     * @date 2017-3-23
     */
    boolean verifyPackBarcodeIsPlanLine(Map<String, Object> map);
    
    /**
     *  线别重置
     * @date 2017-3-23
     */
    boolean planLineReset(Map<String, Object> map);

    /**
     *  验证工单是否存在
     */
    boolean verifyWipName(Map<String, Object> map);

    /**
     *  修改工单数量
     */
    boolean wipNumReset(Map<String, Object> map);

}
