package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesItemLocattions;
import com.egdfrm.unit.model.standard.MesItemLocattionsCriteria;
import com.egdfrm.unit.service.pagequery.CustomPageQueryInterface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MesItemLocattionsMapper <Object> extends CustomPageQueryInterface {
    int countByExample(MesItemLocattionsCriteria example);

    int deleteByExample(MesItemLocattionsCriteria example);

    int insert(MesItemLocattions record);

    int insertSelective(MesItemLocattions record);

    List<MesItemLocattions> selectByExampleWithBLOBs(MesItemLocattionsCriteria example);

    List<MesItemLocattions> selectByExample(MesItemLocattionsCriteria example);

    int updateByExampleSelective(@Param("record") MesItemLocattions record, @Param("example") MesItemLocattionsCriteria example);

    int updateByExampleWithBLOBs(@Param("record") MesItemLocattions record, @Param("example") MesItemLocattionsCriteria example);

    int updateByExample(@Param("record") MesItemLocattions record, @Param("example") MesItemLocattionsCriteria example);
    
}