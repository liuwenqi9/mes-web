package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesCapacityPacking;
import com.egdfrm.unit.model.standard.MesCapacityPackingCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesCapacityPackingMapper {
    int countByExample(MesCapacityPackingCriteria example);

    int deleteByExample(MesCapacityPackingCriteria example);
    
    int deleteByPrimaryKey(String headerId);
    
    int insert(MesCapacityPacking record);

    int insertSelective(MesCapacityPacking record);

    List<MesCapacityPacking> selectByExample(MesCapacityPackingCriteria example);

    int updateByExampleSelective(@Param("record") MesCapacityPacking record, @Param("example") MesCapacityPackingCriteria example);

    int updateByExample(@Param("record") MesCapacityPacking record, @Param("example") MesCapacityPackingCriteria example);

    int updateByPrimaryKey(MesCapacityPacking record);
}