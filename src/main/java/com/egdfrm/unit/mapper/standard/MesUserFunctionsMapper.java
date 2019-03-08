package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesUserFunctions;
import com.egdfrm.unit.model.standard.MesUserFunctionsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesUserFunctionsMapper {
    int countByExample(MesUserFunctionsCriteria example);

    int deleteByExample(MesUserFunctionsCriteria example);

    int insert(MesUserFunctions record);

    int insertSelective(MesUserFunctions record);

    List<MesUserFunctions> selectByExample(MesUserFunctionsCriteria example);

    int updateByExampleSelective(@Param("record") MesUserFunctions record, @Param("example") MesUserFunctionsCriteria example);

    int updateByExample(@Param("record") MesUserFunctions record, @Param("example") MesUserFunctionsCriteria example);
}