package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesShipBarcodes;
import com.egdfrm.unit.model.standard.MesShipBarcodesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesShipBarcodesMapper {
    int countByExample(MesShipBarcodesCriteria example);

    int deleteByExample(MesShipBarcodesCriteria example);

    int insert(MesShipBarcodes record);

    int insertSelective(MesShipBarcodes record);

    List<MesShipBarcodes> selectByExample(MesShipBarcodesCriteria example);

    int updateByExampleSelective(@Param("record") MesShipBarcodes record, @Param("example") MesShipBarcodesCriteria example);

    int updateByExample(@Param("record") MesShipBarcodes record, @Param("example") MesShipBarcodesCriteria example);
}