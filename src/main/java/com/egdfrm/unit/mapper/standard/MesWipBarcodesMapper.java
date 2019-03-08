package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.model.standard.MesWipBarcodesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesWipBarcodesMapper {
    int countByExample(MesWipBarcodesCriteria example);

    int deleteByExample(MesWipBarcodesCriteria example);

    int insert(MesWipBarcodes record);

    int insertSelective(MesWipBarcodes record);

    List<MesWipBarcodes> selectByExampleWithBLOBs(MesWipBarcodesCriteria example);

    List<MesWipBarcodes> selectByExample(MesWipBarcodesCriteria example);

    int updateByExampleSelective(@Param("record") MesWipBarcodes record, @Param("example") MesWipBarcodesCriteria example);

    int updateByExampleWithBLOBs(@Param("record") MesWipBarcodes record, @Param("example") MesWipBarcodesCriteria example);

    int updateByExample(@Param("record") MesWipBarcodes record, @Param("example") MesWipBarcodesCriteria example);
}