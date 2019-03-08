package com.egdfrm.unit.mapper.barcodeManagement;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.model.barcodeManagement.JJNoproductBarcode;

import java.util.List;
import java.util.Map;

public interface JJNoProductBarcodeMapper {

    /**
     * 验证是否符合更新条件， 符合：list；
     * @return
     */
    List<String> verifyProductBarcode(@Param("productBarcode") String productBarcode);
    
    /**
     * 验证是否符合添加条件， 符合：list；
     * @return
     */
    List<String> verifyProductBarcodeAdd(@Param("productBarcode") String productBarcode);
    
    List<String> verifyProductBarcodeIsReprod(@Param("productBarcode") String productBarcode);
    

    int update(@Param("userId") Integer userId,@Param("model")JJNoproductBarcode model);
    
    int insert(@Param("userId") Integer userId,@Param("model")JJNoproductBarcode model);
    
    int insertToShip(@Param("userId") Integer userId,@Param("model")JJNoproductBarcode model);
    
    int updateToShip(@Param("userId") Integer userId,@Param("model")JJNoproductBarcode model);
     
    List<JJNoproductBarcode> verifyCodeId(@Param("segment1") String segment1);
    
    List<JJNoproductBarcode> getSecondaryInventoryName();
    
    List<JJNoproductBarcode> getLocattionCode(String locattionCode); 
    
    List<String> verifyBarcode(@Param("barcode")String barcode);
    
}
