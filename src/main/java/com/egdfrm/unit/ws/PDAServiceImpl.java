package com.egdfrm.unit.ws;

import javax.jws.WebService;

import com.egdfrm.unit.service.pda.*;
import com.egdfrm.unit.service.productionManagement.OutsourcingSplitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Utils;

import java.util.Arrays;

/**
 * @author sjf
 * @date 2016年12月15日 接收PDA客户端发送过来的webservice请求
 */
@WebService
public class PDAServiceImpl implements PDAService {
    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LoginService ls;

	@Autowired
	private PackagingService ps;

	@Autowired
	private CompleteService cs;

	@Autowired
	private InventoryService is;

	@Autowired
	private PickingService pis;

	@Autowired
	private DispatchService ds;

	@Autowired
	private StocktakingService ss;

	@Autowired
	private OnLineRepairService ors;
	
	@Autowired
	private AfterSaleService afterSaleService;

	@Autowired
	private SaleReturnsService srs;
	
	@Autowired
	private GetProductInfoService getProductInfoService;

	@Autowired
	private OpportunityService  opportunityService;

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月21日 
	 * @see com.egdfrm.unit.ws.PDAService#init()
	 * 初始化
	 */
	@Override
	public String[] init() { 
		Utils.printWebServiceLog();
		try {
			return ls.init();
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @author sjf
	 * 
	 * @date 2016年12月15日 用户登录
	 * 
	 * @see com.egdfrm.unit.ws.PDAService#login(java.lang.String[])
	 */
	@Override
	public String[] login(String[] userNamePassWord) { 
		Utils.printWebServiceLog(userNamePassWord.toString());
		try {
			return ls.login(userNamePassWord);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @author sjf
	 * 
	 * @date 2016年12月29日 
	 * 
	 * 获取PDA权限菜单
	 * 
	 * @see com.egdfrm.unit.ws.PDAService#login(java.lang.String[])
	 */
	@Override
	public String[] initPdaMenu(String userName){
		Utils.printWebServiceLog(userName); 
		try {
			return ls.initPdaMenu(userName);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}
	/**
	 * @author sjf
	 * @date 2016年12月15日
	 * @param receiveVal
	 * @return 
	 * PDA二次包装/发运包装-扫描包装条码 
	 * 根据包装条码获取包装箱容量
	 */
	@Override
	public String[] packagingPackBarcode(String[] receiveVal) {
		Utils.printWebServiceLog(receiveVal.toString());  
		try {
			return ps.packagingPackBarcode(receiveVal);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}
	/**
	 * @author sjf
	 * @date 2016年12月15日
	 * @param
	 * @return 
	 * PDA二次包装/发运包装-扫描产品条码  
	 * 根据包装条码，产品条码，及是否解除限制进行校验并获取产品编码，产品现有量，产品类型
	 */
	@Override
	public String[] packagingWIPBarcode(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return ps.packagingWIPBarcode(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}
	/**
	 * @author sjf
	 * @date 2016年12月15日
	 * @param
	 * @return 
	 * PDA二次包装-提交
	 */
	@Override
	public String[] packagingCommit(String jsonString, String status) {
		Utils.printWebServiceLog(jsonString);
		try {
			return ps.packagingCommit(jsonString, status);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}
	/**
	 * @author sjf
	 * @date 2016年12月15日
	 * @param
	 * @return 
	 * PDA发运包装-提交
	 */
	@Override
	public String[] shipPackagingCommit(String jsonString) {
		Utils.printWebServiceLog(jsonString); 
		try {
			return ps.shipPackagingCommit(jsonString);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @author sjf
	 * 
	 * @date 2016年12月19日
	 * 
	 * @see com.egdfrm.unit.ws.PDAService#packagingUnpack(java.lang.String[])
	 * 
	 * PDA拆包-扫描
	 */
	@Override
	public String[] packagingUnpack(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return ps.packagingUnpack(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @author sjf
	 * 
	 * @date 2016年12月19日
	 * 
	 * @see
	 * com.egdfrm.unit.ws.PDAService#packagingUnpackCommit(java.lang.String)
	 * 
	 * PDA拆包-提交
	 */
	public String[] packagingUnpackCommit(String jsonString) {
		Utils.printWebServiceLog(jsonString); 
		try {
			return ps.packagingUnpackCommit(jsonString);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @author sjf
	 * 
	 * @date 2016年12月20日
	 * 
	 * @see
	 * com.egdfrm.unit.ws.PDAService#completeAdviseLocations(java.lang.String)
	 * 
	 * PDA工单完工入库-获取建议货位
	 */
	@Override
	public String[] completeAdviseLocations(String[] receiveVal) {
		Utils.printWebServiceLog(receiveVal.toString()); 
		try {
			return cs.getAdviseLocations(receiveVal);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @author 兰继明
	 * 
	 * @date 2017年01月15日
	 * 
	 * @see
	 * 
	 * 
	 * PDA工单完工入库-较验货位（全部调用pl/sql）
	 */
	@Override
	public String[] completeCheckLocations(String[] input) {
		Utils.printWebServiceLog(input.toString());  
		try {
			return cs.completeCheckLocations(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @author sjf
	 * 
	 * @date 2016年12月20日
	 * 
	 * @see
	 * com.egdfrm.unit.ws.PDAService#completeAdviseLocations(java.lang.String)
	 * 
	 * PDA工单完工入库-扫描货位
	 */
	@Override
	public String[] completeScanLocations(String[] input){ 
		Utils.printWebServiceLog(input.toString());   
		try {
			return cs.completeScanLocations(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}
	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月20日 
	 * @see com.egdfrm.unit.ws.PDAService#completeCommit(java.lang.String[])
	 * PDA工单完工入库-提交
	 */
	@Override
	public String[] completeCommit(String jsonString){
		Utils.printWebServiceLog(jsonString);   
		try {
			return cs.completeCommit(jsonString);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		}
	}
	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月20日 
	 * @see com.egdfrm.unit.ws.PDAService#inventoryTransferScan(java.lang.String[])
	 * PDA子库转移-扫描
	 */
	@Override
	public String[] inventoryTransferScan(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return is.inventoryTransferScan(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
	    	this.log.error(e.getMessage()); 
			return retVal;
		} 
	}
	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月20日 
	 * @see com.egdfrm.unit.ws.PDAService#inventoryTransferCommit(java.lang.String)
	 * PDA子库转移-提交
	 */
	@Override
	public String[] inventoryTransferCommit(String jsonString) {
		Utils.printWebServiceLog(jsonString);   
		try {
			return is.inventoryTransferCommit(jsonString);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月22日 
	 * @see com.egdfrm.unit.ws.PDAService#pickingCheckNumber(java.lang.String[])
	 * PDA挑库-验证挑库单号
	 */
	@Override
	public String[] pickingCheckNumber(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return pis.pickingCheckNumber(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}
	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月22日 
	 * @see com.egdfrm.unit.ws.PDAService#pickingCheckNumber(java.lang.String[])
	 * PDA挑库-扫描条码
	 */
	@Override
	public String[] pickingScanBarcode(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return pis.pickingScanBarcode(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月22日 
	 * @see com.egdfrm.unit.ws.PDAService#pickingCommit(java.lang.String)
	 * PDA挑库-提交
	 */
	@Override
	public String[] pickingCommit(String jsonString) {
		Utils.printWebServiceLog(jsonString);   
		try {
			return pis.pickingCommit(jsonString);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月23日 
	 * @see com.egdfrm.unit.ws.PDAService#dispatchScan(java.lang.String[])
	 * PDA发运确认-扫描
	 */
	@Override
	public String[] dispatchScan(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return ds.dispatchScan(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月23日 
	 * @see com.egdfrm.unit.ws.PDAService#dispatchCommit(java.lang.String)
	 * PDA发运确认-提交
	 */
	@Override
	public String[] dispatchCommit(String jsonString) {
		Utils.printWebServiceLog(jsonString);   
		try {
			return ds.dispatchCommit(jsonString);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}  

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月26日 
	 * @see com.egdfrm.unit.ws.PDAService#stocktakingInitSeq()
	 * PDA库存盘点-初始化获取序列号
	 */
	@Override
	public String[] stocktakingInitSeq(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return ss.stocktakingInitSeq(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月26日 
	 * @see com.egdfrm.unit.ws.PDAService#stocktakingScanLocattion(java.lang.String[])
	 * PDA库存盘点-扫描货位
	 */
	@Override
	public String[] stocktakingScanLocattion(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return ss.stocktakingScanLocattion(input);
		} catch (Exception e) {
			e.printStackTrace();
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月26日 
	 * @see com.egdfrm.unit.ws.PDAService#stocktakingScanWip(java.lang.String[])
	 * PDA库存盘点-扫描包装/产品条码
	 */
	@Override
	public String[] stocktakingScanWip(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return ss.stocktakingScanWip(input);
		} catch (Exception e) {
			e.printStackTrace();
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月26日 
	 * @see com.egdfrm.unit.ws.PDAService#stocktakingCommit(java.lang.String)
	 * PDA库存盘点-提交
	 */
	@Override
	public String[] stocktakingCommit(String jsonString) {
		Utils.printWebServiceLog(jsonString);   
		try {
			return ss.stocktakingCommit(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage()));  
			return retVal;
		} 
	}

	/**
	 * PDA上线返修-扫描领料单
	 *  @date 2018-07-13
	 * @param input {userNo,wareHouse，MaterialRequisitio}
	 * @return
	 */
	@Override
	public String[] onLinerepairMaterialRequisitionScan(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return ors.onLinerepairMaterialRequisitionScan(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常 ";
			this.log.error(Utils.printWebServiceLog(e.getMessage()));
			return retVal;
		}
	}
	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月26日 
	 * @see com.egdfrm.unit.ws.PDAService#repairScan(java.lang.String[])
	 * PDA上线返修-扫描包装/产品条码
	 */
	@Override
	public String onLinerepairScan(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return ors.onLinerepairScan(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return "未知异常";
		} 
	}

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月26日 
	 * @see com.egdfrm.unit.ws.PDAService#repairCommit(java.lang.String)
	 * PDA上线返修-提交
	 */
	@Override
	public String[] onLinerepairCommit(String jsonString) {
		Utils.printWebServiceLog(jsonString);   
		try {
			return ors.onLinerepairCommit(jsonString);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}
   /* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月26日
	 * @see com.egdfrm.unit.ws.PDAService#repairScan(java.lang.String[])
	 * PDA上线返修退回-扫描包装/产品条码
	 */

	@Override
	public String reOnLinerepairScan(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return ors.reOnLinerepairScan(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(Utils.printWebServiceLog(e.getMessage()));
			return "未知异常";
		}
	}


	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月26日
	 * @see com.egdfrm.unit.ws.PDAService#repairCommit(java.lang.String)
	 * PDA上线返修退回-提交
	 */
	@Override
	public String[] reOnLinerepairCommit(String jsonString) {
		Utils.printWebServiceLog(jsonString);
		try {
			return ors.reOnLinerepairCommit(jsonString);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(Utils.printWebServiceLog(e.getMessage()));
			return retVal;
		}
	}

	/**
	 *  销售退货-扫描RMA订单
	 * @param input
	 * @return
	 */
	@Override
	public String[] saleReturnsScanRMAOrder(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return srs.saleReturnsScanRMAOrder(input);
		} catch (Exception e) {
			e.printStackTrace();
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = " 未知异常 ";
			this.log.error(Utils.printWebServiceLog(e.getMessage()));
			return retVal;
		}
	}
	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月27日 
	 * @see com.egdfrm.unit.ws.PDAService#saleReturnsScanLocations(java.lang.String[])
	 * PDA销售退货-扫描货位
	 */
	@Override
	public String[] saleReturnsScanLocations(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return srs.saleReturnsScanLocations(input);
		} catch (Exception e) {
			e.printStackTrace();
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = " 未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月27日 
	 * @see com.egdfrm.unit.ws.PDAService#saleReturnsScanBarCode(java.lang.String[])
	 * PDA销售退货-扫描条码
	 */
	@Override
	public String[] saleReturnsScanBarCode(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return srs.saleReturnsScanBarCode(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2016年12月27日 
	 * @see com.egdfrm.unit.ws.PDAService#saleReturnsCommit(java.lang.String)
	 * PDA销售退货-提交
	 */
	@Override
	public String[] saleReturnsCommit(String jsonString) {
		Utils.printWebServiceLog(jsonString);
		try {
			return srs.saleReturnsCommit(jsonString);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage()));
			return retVal;
		}
	}

	/**
	 *  （销售退货确认）提交
	 * @param input
	 * @return
	 */
	@Override
	public String[] salesReturnConfirm(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return srs.salesReturnConfirm(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(Utils.printWebServiceLog(e.getMessage()));
			return retVal;
		}
	}

	//==========================================================================================//
	
	/* (non-Javadoc)
	 * @author 兰继明
	 * @date 2016年12月20日 
	 * PDA根据产品条码查询产品信息
	 */
	@Override
	public String[] getProductInfoByBarCode(String[] barCodeAndorgId){

		Utils.printWebServiceLog(barCodeAndorgId.toString());
		try {
			return getProductInfoService.getProductInfo(barCodeAndorgId);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}
	/* (non-Javadoc)
	 * @author 兰继明
	 * @date 2017年01月17日 
	 * 	//PDA根据包装箱条码查询包装箱信息

	 */
	@Override
	public String[] getPackInfoByBarCode(String[] barCodeAndorgId){

		Utils.printWebServiceLog(barCodeAndorgId.toString());
		try {
			return getProductInfoService.getPackInfoByBarCode(barCodeAndorgId);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			return retVal;
		} 
	}
	
	/* (non-Javadoc)
	 * @author 兰继明
	 * @date 2016年12月20日 
	 * 售后退货_查询产品信息
	 */
	@Override
	public String[] afterSaleReturnGetProductInfo(String[] barcode) throws Exception {
		return afterSaleService.afterSaleReturnGetProductInfo(barcode);
	}  
	/* (non-Javadoc)
	 * @author 兰继明
	 * @date 2016年12月20日 
	 * 售后退货_退货_提交
	 */
	@Override
	public String[] afterSaleReturnCommit(String[] productInfo) {
		
		return afterSaleService.afterSaleReturnCommit(productInfo);
		
	}  
	/* (non-Javadoc)
	 * @author 兰继明
	 * @date 2016年12月20日 
	 * 售后退货_发货_查询产品信息
	 */
	@Override
	public String[] afterSaleDeliverGetProductInfo(String[] barcode) {
		
		return afterSaleService.afterSaleDeliverGetProductInfo(barcode);
		
	}  
	/* (non-Javadoc)
	 * @author 兰继明
	 * @date 2016年12月20日 
	 * 售后退货_发货_提交
	 */
	@Override
	public String[] afterSaleDeliverCommit(String[] productInfo) {
		
		return afterSaleService.afterSaleDeliverCommit(productInfo);
		
	}
 
	
	//==========================================================================================//
 
	
	@Autowired private CheckStockStatusService checkStockStatusService;
	/**
	 * 入库单检查 
	 * @param stockNumber    
	 * @author	hgb
	 * @date 2017-2-16
	 * */
	@Override
	public String[] checkStockStatus(String[] stockNumber) { 
		return checkStockStatusService.getPackBarcodeByStockNum(stockNumber);
	}
	@Autowired private InStockPreparingService inStockPreparingService;
	/**
	 * //检查入库单是否存在
	 * @author	兰继明
	 * @date 2017-2-16
	 * */
	@Override
	public String[] checkIsHaveInStockBill(String[] stockNumber){
		return inStockPreparingService.checkIsHaveInStockBill(stockNumber);
	}
	/**
	 * //检查某个箱子是否属于某个入库单
	 * @author	兰继明
	 * @date 2017-2-16
	 * */
	@Override
	public String[] checkPackIsBelongInStockBill(String[] stockNumber_and_pack_num) { 
		return inStockPreparingService.checkPackIsBelongInStockBill(stockNumber_and_pack_num);
	}
	@Autowired private BorrowProductService borrowProductService;
	/**
	 * //借机--检查借机单号
	 * @author	兰继明
	 * @date 2017-03-14
	 * */
	@Override
	public String[] borrowProductCheckBorrowProductBill(String[] parameter) {
		Utils.printWebServiceLog(parameter.toString());   
		try {
			return borrowProductService.CheckBorrowProductBill(parameter);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(e.getMessage()); 
			return retVal;
		} 
	}
	/**
	 * 借机--检查产品条码
	 * @author	兰继明
	 * @date 2017-03-14
	 * */
	@Override
	public String[] borrowProductCheckProduct(String[] parameter) {
		Utils.printWebServiceLog(parameter.toString());   
		try {
			return borrowProductService.CheckProduct(parameter);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(e.getMessage()); 
			return retVal;
		} 
	}
	/**
	 * //借机--提交
	 * @author	兰继明
	 * @date 2017-03-14
	 * */
	@Override
	public String[] borrowProductCommit(String parameter) {
		Utils.printWebServiceLog(parameter.toString());   
		try {
			return borrowProductService.commit(parameter);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(e.getMessage()); 
			return retVal;
		} 
	}
	/**
	 * //外购机拼箱--检查包装箱条码
	 * @author	兰继明
	 * @date 2017-03-15
	 * */
	@Override
	public String[] outsourcingPackCheckPack(String[] parameter) {
		Utils.printWebServiceLog(parameter.toString());   
		try {
			return outsourcingPackService.outsourcingPackCheckPack(parameter);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(e.getMessage()); 
			return retVal;
		} 
	}
	/**
	 * //外购机拼箱--检查产品条码
	 * @author	兰继明
	 * @date 2017-03-15
	 * */
	@Override
	public String[] outsourcingPackCheckProduct(String[] parameter) {
		Utils.printWebServiceLog(parameter.toString());   
		try {
			return outsourcingPackService.outsourcingPackCheckProduct(parameter);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(e.getMessage()); 
			return retVal;
		} 
	}
	@Autowired
	OutsourcingPackService outsourcingPackService;
	/**
	 * //外购机拼箱--提交
	 * @author	兰继明
	 * @date 2017-03-15
	 * */
	@Override
	public String[] outsourcingPackCommit(String parameter) {
		Utils.printWebServiceLog(parameter.toString());   
		try {
			return outsourcingPackService.comoutsourcingPackCommit(parameter);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
	    	this.log.error(e.getMessage()); 
			return retVal;
		} 
	}


    @Autowired
    OutsourcingSplitService outsourcingSplitService;
    /**
     * 外购机拆箱-----检查包装条码
     *
     * @author 罗港
     * @param parameter
     * @return
     */
    @Override
    public String[] outsourcingSplitCheckPack(String[] parameter) {
        Utils.printWebServiceLog(parameter.toString());
        try {
            return outsourcingSplitService.outsourcingSplitCheckPack(parameter);
        } catch (Exception e) {
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            this.log.error(e.getMessage());
            return retVal;
        }
    }

    /**
     * 外购机拆箱-----检查产品条码
     *
     * @author 罗港
     * @param parameter
     * @return
     */
    @Override
    public String[] outsourcingSplitCheckProduct(String[] parameter) {
        Utils.printWebServiceLog(parameter.toString());
        try {
            return outsourcingSplitService.outsourcingSplitCheckProduct(parameter);
        } catch (Exception e) {
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            this.log.error(e.getMessage());
            return retVal;
        }
    }

    /**
     * 外购机拆箱-----提交
     *
     * @author 罗港
     * @param parameter
     * @return
     */
    @Override
    public String[] outsourcingSplitCommit(String parameter) {
        Utils.printWebServiceLog(parameter.toString());
        try {
            return outsourcingSplitService.outsourcingSplitCommit(parameter);
        } catch (Exception e) {
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            this.log.error(e.getMessage());
            return retVal;
        }
    }

	
	@Autowired
	ShipConfirmWXService shipConfirmWXService;

	/**
	 * // 外销发货--校验出货通知单
	 * 
	 * @author 兰继明
	 * @date 2017-04-28
	 * */
	@Override
	public String[] shipConfirmWXCheckOutSn(String out_sn) {
		Utils.printWebServiceLog(out_sn.toString());
		try {
			return shipConfirmWXService.checkOutSn(out_sn);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(e.getMessage());
			return retVal;
		}
	}
	
	/**
	 * ////外销发货--校验包装箱
	 * 
	 * @author 兰继明
	 * @date 2017-04-28
	 * */
	@Override
	public String[] shipConfirmWXCheckPackNo(String[] input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return shipConfirmWXService.shipConfirmWXCheckPackNo(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(e.getMessage());
			return retVal;
		}
	}
	
	/**
	 * ////外销发货--提交
	 * 
	 * @author 兰继明
	 * @date 2017-04-28
	 * */
	@Override
	public String[] shipConfirmWXCommit(String input) {
		Utils.printWebServiceLog(input.toString());
		try {
			return shipConfirmWXService.commit(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(e.getMessage());
			return retVal;
		}
	}

	@Override
	public String[] beingBigPack(String parameter) {
		try {
			Utils.printWebServiceLog(parameter.toString());
			return  ps.beingBigPack(parameter);
		}catch (Exception e){
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(e.getMessage());
			return retVal;
		}
	}

	@Override
	public String[] beingSmallPack(String[] parameters) {
		try {
			Utils.printWebServiceLog(Arrays.toString(parameters));
			return ps.beingSmallPack(parameters);
		}catch (Exception e){
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(e.getMessage());
			return retVal;
		}
	}

	@Override
	public String[] bigPackCommit(String input) {
		Utils.printWebServiceLog(input);
		try {
			return ps.bigPackCommit(input);
		} catch (Exception e) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(Utils.printWebServiceLog(e.getMessage()));
			return retVal;
		}
	}

/*	@Override
	public String[] opportunityNumber(String parameter) {
		try {
			Utils.printWebServiceLog(parameter.toString());
			return opportunityService.opportunityNumber(parameter);
		}catch (Exception e){
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(e.getMessage());
			return retVal;
		}
	}*/
	@Override
	public String[] opportunityNewPosition(String parameter) {
		try {
			Utils.printWebServiceLog(parameter.toString());
			return opportunityService.opportunityNewPosition(parameter);
		}catch (Exception e){
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(e.getMessage());
			return retVal;
		}
	}
	@Override
	public String[] opportunityBarcodeText(String[] parameters) {
		try {
			//parameters[3]
			this.log.debug(Arrays.toString(parameters));
			return opportunityService.opportunityBarcodeText(parameters);
		}catch (Exception e){
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(e.getMessage());
			return retVal;
		}
	}

	@Override
	public String[] opportunityCommit(String parameter) {
		try {
			Utils.printWebServiceLog(parameter.toString());
			return opportunityService.opportunityCommit(parameter);
		}catch (Exception e){
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常";
			this.log.error(e.getMessage());
			return retVal;
		}
	}


}
