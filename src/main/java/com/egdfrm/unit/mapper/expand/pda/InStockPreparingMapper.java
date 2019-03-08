package com.egdfrm.unit.mapper.expand.pda;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.unit.model.expand.pda.StorageTransfer;

/**
 * @author 兰继明
 * @date 2017年02月16日 
 * PDA检查某个箱子是否属于某个入库单
 */
public interface InStockPreparingMapper {


	/**
	 * @author 兰继明
	 * @date 2017年02月16日 据包装条码Id获取包装箱信息，包括： //产品编码： //数 量：
	 * @param packBarcodeId
	 */
	public List<Map<String, String>> checkPackIsBelongInStockBill(@Param("stockNumber") String stockNumber,@Param("pack_num") String pack_num,@Param("organizationId") String organizationId);
	
	
}