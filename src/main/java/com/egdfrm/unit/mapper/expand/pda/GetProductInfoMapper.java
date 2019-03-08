package com.egdfrm.unit.mapper.expand.pda;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.model.expand.pda.StorageTransfer;

/**
 * @author 兰继明
 * @date 2017年01月19日 
 * PDA库存
 */
public interface GetProductInfoMapper {


	/**
	 * @author 兰继明
	 * @date 2017年01月19日 据包装条码Id获取包装箱信息，包括： //产品编码： //料号描述： //产品型号： //工 单： //数 量：//货 位：
	 * @param packBarcodeId
	 */
	public Map<String, String> getPackInfoByBarCode(@Param("barCode") String barCode,@Param("organizationId") String organizationId);
	
	
}