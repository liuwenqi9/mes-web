package com.egdfrm.unit.ws;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Utils;
import com.egdfrm.unit.service.crm.CRMSynchronousInfoService;

/**
 * @author sjf
 * @date 2017年01月16日 
 * 接收CRM客户端发送过来的webservice请求
 */
@WebService
public class CRMServiceImpl implements CRMService {
    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass()); 
    
    @Autowired
    CRMSynchronousInfoService crmsis;

	@Override
	public String[] synchronousCuxCustomer() { 
		Utils.printWebServiceLog();
		try {
			return crmsis.synchronousCuxCustomer();
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousCuxCustomerReceivingParty() {
		Utils.printWebServiceLog();
		try {
			return crmsis.synchronousCuxCustomerReceivingParty();
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousCuxPricList() {
		Utils.printWebServiceLog();
		try {
			return crmsis.synchronousCuxPricList();
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousCuxSalesreps() {
		Utils.printWebServiceLog();
		try {
			return crmsis.synchronousCuxSalesreps();
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousCuxItem() {
		Utils.printWebServiceLog();
		try {
			return crmsis.synchronousCuxItem();
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousCuxItemExclusively() {
		Utils.printWebServiceLog();
		try {
			return crmsis.synchronousCuxItemExclusively();
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousCuxInventory(String itemId) {
		Utils.printWebServiceLog(itemId);
		try {
			return crmsis.synchronousCuxInventory(itemId);
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousCuxOrder(String orderJson) {
		Utils.printWebServiceLog(orderJson);
		try {
			return crmsis.synchronousCuxOrder(orderJson);
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
	    	this.log.error(e.getMessage()); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousShipmentBarcode() {
		Utils.printWebServiceLog();
		try {
			return crmsis.synchronousShipmentBarcode();
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousCuxItemsaleRule() {
		Utils.printWebServiceLog();
		try {
			return crmsis.synchronousCuxItemsaleRule(); 
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
		
	}

	@Override
	public String[] chageOrCancelCuxOrder() { 
		Utils.printWebServiceLog();
		try {
			return crmsis.chageOrCancelCuxOrder(); 
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousPrimaryFlag() { 
		return crmsis.synchronousPrimaryFlag();
	}

	@Override
	public String[] updateERPCustomerStatus(String dealerJson) {
		Utils.printWebServiceLog(dealerJson);
		try {
			return crmsis.updateERPCustomerStatus(dealerJson);
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
	    	this.log.error(e.getMessage()); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}

	@Override
	public String[] synchronousShoppingLogisticsInfo() {
		Utils.printWebServiceLog();
		try {
			return crmsis.synchronousShoppingLogisticsInfo(); 
		} catch (Exception e) { 
	    	this.log.error(Utils.printWebServiceLog(e.getMessage())); 
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "未知异常"; 
			return retVal;
		}
	}


}
