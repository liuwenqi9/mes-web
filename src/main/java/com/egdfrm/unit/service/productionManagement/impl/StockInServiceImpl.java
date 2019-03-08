package com.egdfrm.unit.service.productionManagement.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.productionManagement.StockInMapper;
import com.egdfrm.unit.model.barcodeManagement.StockInPrintRow;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.model.expand.PageResult;
import com.egdfrm.core.service.BaseService; 
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.excelmodel.StockInExcel;
import com.egdfrm.unit.mapper.standard.InspectionMapper;
import com.egdfrm.unit.service.productionManagement.StockInServiceI;

@Service
public class StockInServiceImpl  extends BaseService implements StockInServiceI{

	@Autowired
	private InspectionMapper inspectionMapper;
	@Autowired
	private TtApplicationUserMapper ttUserMapper;
	@Autowired
	private StockInMapper stockInMapper;
	
	@Override
	public List<Map<String, Object>> getStockInPrinter(String productionLine,
			String workOrderNumber, String workOrderNumberSubpool,
			String stockInIdentify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getStockInPrinters(Integer page, Integer rows,
			String productionLine, String workOrderNumber,
			String workOrderNumberSubpool, String stockInIdentify) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(); 
        sql.append("  SELECT V.* FROM APPS.MES_PACKING_HEADERS_INV_V V ");
        sql.append(" WHERE 1=1 ");
        if(!StringUtils.isEmpty(productionLine)&& !productionLine.equals("0")){
        	sql.append(" AND V.PLAN_LINE = '"+productionLine+"'");
        }
        if(!StringUtils.isEmpty(workOrderNumber)){
        	sql.append(" AND V.WIP_ENTITY_NAME LIKE '%"+workOrderNumber+"%'");
        }
        if(!StringUtils.isEmpty(workOrderNumberSubpool)){ 
        	sql.append(" AND V.SUBINVENTORY_CODE LIKE '%"+workOrderNumberSubpool+"%'");
        }
        if(!StringUtils.isEmpty(stockInIdentify) && !"0".equals(stockInIdentify)){
        	if(stockInIdentify.equals("S_INV")){//已入库
        		sql.append(" AND V.STATUS_CODE = '"+stockInIdentify+"' "); 
        	}else{
        		sql.append(" AND (V.STATUS_CODE IS NULL OR V.STATUS_CODE<>'') "); 
        	}
        }
		Map<String, Object> rv = new HashMap<String, Object>(30);
        try {
			PageResult pr = super.getPrs().pageQuery(sql.toString(), rows, page);
			rv.put("total", pr.getTotalRecords());
			rv.put("rows", pr.getRecords());
		} catch (Exception e) {
			super.log.info("xx");
			e.printStackTrace();
		}
        return rv; 
	}

	@Override
	public List<Map<String, Object>> getStockInSearch(String productionLine,
			String workOrderNumber, String stockInNumber, String stockInIdentify) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getStockInSearchs(Integer page, Integer rows,
			String productionLine, String workOrderNumber,
			String stockInNumber, String stockInIdentify) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer(); 
        sql.append("  SELECT V.* FROM APPS.MES_PACKING_HEADERS_INV_V V ");
        sql.append(" WHERE 1=1 ");
        if(!StringUtils.isEmpty(productionLine) && ! productionLine.equals("0")){
        	sql.append(" AND V.PLAN_LINE = '"+productionLine+"'");
        }
        if(!StringUtils.isEmpty(workOrderNumber)){
        	sql.append(" AND V.WIP_ENTITY_NAME LIKE '%"+workOrderNumber+"%'");
        }
        if(!StringUtils.isEmpty(stockInNumber)){
        	sql.append(" AND V.INV_NUMBER LIKE '%"+stockInNumber+"%'");
        }
        if(!StringUtils.isEmpty(stockInIdentify) && !"0".equals(stockInIdentify)){
        	if(stockInIdentify.equals("S_INV")){//已入库
        		sql.append(" AND V.STATUS_CODE = '"+stockInIdentify+"' "); 
        	}else{
        		sql.append(" AND (V.STATUS_CODE IS NULL OR V.STATUS_CODE<>'') "); 
        	}
        } 
        
		Map<String, Object> rv = new HashMap<String, Object>(30); 
        try {
			PageResult pr = super.getPrs().pageQuery(sql.toString(), rows, page);
			rv.put("total", pr.getTotalRecords());
			rv.put("rows", pr.getRecords());
		} catch (Exception e) {
			super.log.info("xx");
			e.printStackTrace();
		}
        return rv;
		 
	}

	 

	@Override
	public List<Map<String, Object>> getInvNumberList(String loginName, String[] packingBarcodeIds) {
	 
		//生成入库单号
		Map<String, Object> paramsOut = new HashMap<String, Object>(0);
		paramsOut.put("invNumberOut", null);
		inspectionMapper.callGenerateInvNumber(paramsOut);
		Object o = paramsOut.get("invNumberOut");
		String invNumber = o.toString(); 
		
		//update 入库单数据
		for (int i = 0; i < packingBarcodeIds.length; i++) {
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> params = new HashMap<String, Object>(0);
			params.put("userId", ttUserMapper.selectByPrimaryKey(loginName)
					.getUserId());
			params.put("packingBarcodeId", packingBarcodeIds[i]);
			params.put("invNumber", invNumber);
			params.put(MesConstants.DBRETURN, dbreturn);
			inspectionMapper.callUpdateInvNumber(params);
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				log.debug("xxxxxxxx"); 
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
			} 
		} 
		
		//获取入库单数据
		List<Map<String, Object>> lm  = inspectionMapper.getInvNumberList(invNumber);
		System.out.println(lm.toString());
		return lm;
	}

	@Override
	public List<Map<String, Object>> getInvNumberByInvNumber(String invNumber) { 
		return inspectionMapper.getInvNumberList(invNumber);
	}

	@Override
	public Boolean isInvNumber(String[] packingBarcodeIds) {
		boolean falg = true;
		for (int i = 0; i < packingBarcodeIds.length; i++) { 
			List<String> invNumber=inspectionMapper.isInvNumber(String.valueOf(packingBarcodeIds[i]));
		    if(invNumber!=null && !invNumber.isEmpty()){//存在
		    	String m=invNumber.get(0);
		    	if(m!=null){
		    		falg = false;   
		    		break;
		    		}
			 }
		} 
		return falg; 
	}

	/**
	 * 根据包装箱号ID判断是否已生成入库单号
	 * @param pbIDs 包装箱号ID
	 * @return
	 */
	@Override
	public String isPackingBarCodeByNo(String pbIDs) {
		return stockInMapper.isPackingBarCodeByNo(pbIDs);
	}

	/**
	 * 生成入库单
	 * @param map
	 */
	@Override
	public void generateStorageOrder(Map<String, Object> map) {
		stockInMapper.generateStorageOrder(map);
	}

	/**
	 * 修改入库单
	 * @param map
	 */
	@Override
	public void updateDataInv(Map<String, Object> map) {
		stockInMapper.updateDataInv(map);
	}

	/**
	 * 获取打印table数据
	 * @param number 入库单号
	 * @return
	 */
	@Override
	public List<StockInPrintRow> getTableRows(String number) {
		return stockInMapper.getTableRows(number);
	}


	/**
	 * 查询汇总数据
	 * @param number 入库单号
	 * @return
	 */
	@Override
	public Map<String, Object> getTableHZ(String number) {
		return stockInMapper.getTableHZ(number);
	}

	/**
	 * 入库单打印_分页查询
	 * @param pagination 分页添加
	 * @param map 查询条件
	 * @return
	 */
	@Override
	public Pagination getStockInPrinters(Pagination pagination, Map<String, Object> map) {
		long count = stockInMapper.getStockInPrintersByCount(map);
		List<Map<String,Object>> list = stockInMapper.getStockInPrinters(pagination,map);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}

	@Override
	public List<StockInExcel> exportExcel(Map<String, Object> map) { 
		return stockInMapper.stockInExportExcel(map);
	}
}
