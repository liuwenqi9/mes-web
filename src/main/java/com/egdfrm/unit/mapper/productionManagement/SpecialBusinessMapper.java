package com.egdfrm.unit.mapper.productionManagement;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 特殊业务处理Mapper
 * Created by tyq on 17/2/10.
 */
public interface SpecialBusinessMapper {

    /**
     * 产品条码重置
     * @param map
     */
    void productNumReset(@Param("map") Map<String, String> map);

    /**
     * 获取包装类型数据
     * @return 包装类型数据集
     */
    List<Map<String,Object>> getPackageTypes();

    /**
     * 包装条码重置
     * @param map
     */
    void packageNumReset(@Param("map") Map<String, String> map);

    /**
     * 包装尾数重置
     * @param map
     */
    void packageMantissaReset(@Param("map") Map<String, String> map);

    /**
     * 额外新增工单包装箱
     * @param map
     */
    void additional(@Param("map") Map<String, Object> map);

    /**
     * 工单转产
     * @param map
     */
    void theSingleTurn(@Param("map") Map<String, Object> map);
    
    /**
     *  验证包装箱的线别
     * @date 2017-3-23
     */
    List<String> verifyPackBarcodeIsPlanLine(@Param("map")Map<String, Object> map); 
    
    /**
     *  包装箱线别重置
     * @date 2017-3-23
     */
    void planLineResetHeaders(@Param("map")Map<String, Object> map);
    
    /**
     *  产品线别重置
     * @date 2017-3-23
     */
    void planLineResetBarcodes(@Param("map")Map<String, Object> map);


    /**
     *  验证工单是否存在
     */
    List<String> verifyWipName(@Param("map")Map<String, Object> map);

    /**
     *  修改工单数量
     */
    void wipNumReset(@Param("map")Map<String, Object> map);

}
