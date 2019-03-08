package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesTransactions;
import com.egdfrm.unit.model.standard.MesTransactionsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesTransactionsMapper {
    int countByExample(MesTransactionsCriteria example);

    int deleteByExample(MesTransactionsCriteria example);

    int insert(MesTransactions record);

    int insertSelective(MesTransactions record);

    List<MesTransactions> selectByExample(MesTransactionsCriteria example);

    int updateByExampleSelective(@Param("record") MesTransactions record, @Param("example") MesTransactionsCriteria example);

    int updateByExample(@Param("record") MesTransactions record, @Param("example") MesTransactionsCriteria example);
}