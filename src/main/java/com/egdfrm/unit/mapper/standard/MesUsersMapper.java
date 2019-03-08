package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesUsers;
import com.egdfrm.unit.model.standard.MesUsersCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesUsersMapper {
    int countByExample(MesUsersCriteria example);

    int deleteByExample(MesUsersCriteria example);

    int insert(MesUsers record);

    int insertSelective(MesUsers record);

    List<MesUsers> selectByExample(MesUsersCriteria example);

    int updateByExampleSelective(@Param("record") MesUsers record, @Param("example") MesUsersCriteria example);

    int updateByExample(@Param("record") MesUsers record, @Param("example") MesUsersCriteria example);
}