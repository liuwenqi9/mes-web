package com.egdfrm.unit.mapper.expand.pda;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *  借机归还
 */
public interface OpportunityMapper {

    public List<Map<String,Object>> getOpportunity(@Param("parameter") String parameter);

    /**
     *  验证产品条码
     * @param paramMap
     */
    public void pdaCheckLend(Map<String, Object> paramMap);

    public List<Map<String,Object>> getBarcodeTextInfo(@Param("parameter") String parameter);

    /**
     *  借机归还
     * @param paramMap
     */
    public void callMesBarcodesTransferLend(Map<String, Object> paramMap);


}
