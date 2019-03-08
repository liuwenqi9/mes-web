package com.egdfrm.unit.mapper.standard;

import com.egdfrm.unit.model.standard.MesTransactionsBarcode;
import com.egdfrm.unit.model.standard.MesTransactionsBarcodeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MesTransactionsBarcodeMapper {
    int countByExample(MesTransactionsBarcodeCriteria example);

    int deleteByExample(MesTransactionsBarcodeCriteria example);

    int insert(MesTransactionsBarcode record);

    int insertSelective(MesTransactionsBarcode record);

    List<MesTransactionsBarcode> selectByExample(MesTransactionsBarcodeCriteria example);

    int updateByExampleSelective(@Param("record") MesTransactionsBarcode record, @Param("example") MesTransactionsBarcodeCriteria example);

    int updateByExample(@Param("record") MesTransactionsBarcode record, @Param("example") MesTransactionsBarcodeCriteria example);
}