package com.egdfrm.unit.mapper.expand.overseas;

import com.egdfrm.unit.model.ws.OsLine;
import com.egdfrm.unit.model.ws.OsOrders;

import java.util.List;
import java.util.Map;

/**
 * Created by hgb on 2018/7/30
 * Email xhy18650@sina.com
 **/
public interface OverseasDistributorMapper {

    /**
     * 手动刷新所有接口
     */
    void callOsAllInterface();

    /**
     *  刷新 客户信息
     */
    void callGetCustomer(Map<String, Object> paramMap);

    /**
     *  刷新 客户地点
     */
    void callGetCustomerSite(Map<String, Object> paramMap);

    /**
     *  刷新 价目表
     */
    void callGetPriceList(Map<String, Object> paramMap);

    /**
     *  刷新 销售人员
     */
    void callGetSalesreps(Map<String, Object> paramMap);

    /**
     *  刷新 物料信息
     */
    void callGetItem(Map<String, Object> paramMap);

    /**
     *  刷新 销售规则
     */
    void callGetItemRule(Map<String, Object> paramMap);

    /**
     *  刷新 付款条件
     */
    void callGetPayment(Map<String, Object> paramMap);

    /**
     *  刷新 发运明细
     */
    void callGetShipment(Map<String, Object> paramMap);

    /**
     *  刷新 发票头
     */
    void callGetCustomerArHeader(Map<String, Object> paramMap);

    /**
     *  刷新 发票行
     */
    void callGetCustomerArLine(Map<String, Object> paramMap);

    /**
     *  刷新 订单修改
     */
    void callGetOverseasOrderChange(Map<String, Object> paramMap);

    /**
     * 获取客户
     */
    List<Map<String, Object>> getCustomer();

    void updateCustomer();


    /**
     * 获取客户地点
     */
    List<Map<String, Object>> getCustomerSite();

    void updateCustomerSite();


    /**
     * 获取海外价目表
     */
    List<Map<String, Object>> getPriceList();

    void updatePriceList();

    /**
     * 获取销售员
     */
    List<Map<String, Object>> getSaleSreps();

    void updateSaleSreps();

    /**
     * 获取物料
     */
    List<Map<String, Object>> getItems();

    void updateItems();

    /**
     * 获取销售规则表
     */
    List<Map<String, Object>> getItemCustomerRule();

    void updateItemCustomerRule();

    /**
     * 获取付款条件
     */
    List<Map<String, Object>> getPaymentTerms();

    void updatePaymentTerms();

    /**
     * 海外 订单 头 行
     */
    void saveOrderHeader(OsOrders osOrders);


    void saveOrderLine(OsLine osLine);
    List<Map<String,Object>> getOrderHeader(OsOrders osOrders);
    void callOsSetOrder(Map<String,Object> map);
    void deleteOrderHeader(OsOrders osOrders);
    void deleteOrderLine(OsLine osLine);



    /**
     *  海外发运明细
     */
    List<Map<String, Object>> getShipmentDetail();

    /**
     * 回写海外发运明细 状态
     */
    void updateShipmentDetail();

    /**
     *  海外发票头
     */
    List<Map<String, Object>> getOverseasInvoiceHeader();

    /**
     * 回写
     */
    void updateOverseasInvoiceHeaderStatus();

    /**
     *  海外发票行
     */
    List<Map<String, Object>> getOverseasInvoiceLine();

    /**
     * 回写
     */
    void updateOverseasInvoiceLineStatus();

    /**
     *  海外 订单修改，取消
     */
    List<Map<String, Object>> getOverseasOrderChange();

    /**
     * 回写
     */
    void updateOverseasOrderChangeStatus();

}
