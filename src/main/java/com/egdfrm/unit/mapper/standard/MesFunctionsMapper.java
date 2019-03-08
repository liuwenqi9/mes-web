package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesFunctions;
import com.egdfrm.unit.model.standard.MesFunctionsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesFunctionsMapper {
    int countByExample(MesFunctionsCriteria example);

    int deleteByExample(MesFunctionsCriteria example);

    int insert(MesFunctions record);

    int insertSelective(MesFunctions record);

    List<MesFunctions> selectByExample(MesFunctionsCriteria example);

    int updateByExampleSelective(@Param("record") MesFunctions record, @Param("example") MesFunctionsCriteria example);

    int updateByExample(@Param("record") MesFunctions record, @Param("example") MesFunctionsCriteria example);
}