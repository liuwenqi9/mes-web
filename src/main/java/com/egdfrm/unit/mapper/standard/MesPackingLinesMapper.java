package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesPackingLines;
import com.egdfrm.unit.model.standard.MesPackingLinesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesPackingLinesMapper {
    int countByExample(MesPackingLinesCriteria example);

    int deleteByExample(MesPackingLinesCriteria example);

    int insert(MesPackingLines record);

    int insertSelective(MesPackingLines record);

    List<MesPackingLines> selectByExample(MesPackingLinesCriteria example);

    int updateByExampleSelective(@Param("record") MesPackingLines record, @Param("example") MesPackingLinesCriteria example);

    int updateByExample(@Param("record") MesPackingLines record, @Param("example") MesPackingLinesCriteria example);
}