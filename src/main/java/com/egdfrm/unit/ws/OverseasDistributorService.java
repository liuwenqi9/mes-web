package com.egdfrm.unit.ws;

import javax.jws.WebService;

/**
 * 海外经销商
 * Created by hgb on 2018/7/30
 * Email xhy18650@sina.com
 **/
@WebService
public interface OverseasDistributorService {

    /**
     *   该方法运行海外的所有接口，提供给海外CRM
     *   手动刷新所有接口
     */
    public String[] overseasAllInterface();

    public String[] overseasRefresh(String falg);

    /**
     *  海外 客户
     */
    public String[] overseasCustomer();

    /**
     *  海外客户 地点
     */
    public String[] overseasCustomerSite();

    /**
     * 海外价目表
     */
    public String[] overseasPriceList();

    /**
     * 海外销售员
     */
    public String[] overseasSaleSreps();

    /**
     * 海外物料
     */
    public String[] overseasItems();

    /**
     * 海外销售规则表
     */
    public String[] overseasItemCustomerRule();

    /**
     * 海外付款条件
     */
    public String[] OverseasPaymentTerms();

    /**
     *  创建海外订单
     * @param orderJson 订单json格式
     * @return
     */
   public String[] createOverseasOrder(String orderJson);

    /**
     * 海外发运
     */
    public String[] OverseasShipmentDetail();

    /**
     * 海外 发票头
     */
    public String[] OverseasInvoiceHeader();
    /**
     * 海外 发票行
     */
    public String[] OverseasInvoiceLine();


    /**
     * 海外 订单修改，取消
     */
    public String[] OverseasOrderChange();
}
