package com.egdfrm.unit.ws;

import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Utils;
import com.egdfrm.unit.service.overseas.ODService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

/**
 * 海外经销商
 * Created by hgb on 2018/7/30
 * Email xhy18650@sina.com
 **/
@WebService
public class OverseasDistributorServiceImpl implements OverseasDistributorService{
    private static Logger logger = LoggerFactory.getLogger(OverseasDistributorServiceImpl.class);

    @Autowired
    ODService odService;

    @Override
    public String[] overseasAllInterface() {
        Utils.printWebServiceLog();
        try {
            return odService.overseasAllInterface();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] overseasRefresh(String falg) {
        Utils.printWebServiceLog();
        try {
            return odService.overseasRefresh(falg);
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] overseasCustomer() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasCustomer();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] overseasCustomerSite() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasCustomerSite();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] overseasPriceList() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasPriceList();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] overseasSaleSreps() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasSaleSreps();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] overseasItems() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasItems();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] overseasItemCustomerRule() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasItemCustomerRule();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] OverseasPaymentTerms() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasPaymentTerms();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    /**
     *  分为两个service层的方法，
     *  因为AOP会自动把一个service方法作为事物，
     *  方法一 为插入接口表，不管后面的操作如何，都会insert 成功
     *  方法二 为创建销售订单，
     *  方法一和方法二单独作为事务，
     * @param orderJson 订单json格式
     * @return
     */
    @Override
    public String[] createOverseasOrder(String orderJson) {
        Utils.printWebServiceLog(orderJson);
        logger.info("orderJson:==========>" + orderJson);
        try {
            if(!odService.insertOsOrderInterface(orderJson)){
                return new String[]{"E","插入接口表出错了！"};
            }
            return odService.createOverseasOrder(orderJson);
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            this.logger.error(e.getMessage());
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] OverseasShipmentDetail() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasShipmentDetail();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] OverseasInvoiceHeader() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasInvoiceHeader();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] OverseasInvoiceLine() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasInvoiceLine();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }

    @Override
    public String[] OverseasOrderChange() {
        Utils.printWebServiceLog();
        try {
            return odService.getOverseasOrderChange();
        } catch (Exception e) {
            this.logger.error(Utils.printWebServiceLog(e.getMessage()));
            String[] retVal = new String[2];
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "未知异常";
            return retVal;
        }
    }
}
