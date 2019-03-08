package com.egdfrm.unit.service.overseas;

import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.expand.crm.CRMSynchronousInfoMapper;
import com.egdfrm.unit.mapper.expand.overseas.OverseasDistributorMapper;
import com.egdfrm.unit.model.ws.OsLine;
import com.egdfrm.unit.model.ws.OsOrders;
import com.egdfrm.unit.service.crm.CRMSynchronousInfoService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 海外经销商 业务类
 * Created by hgb on 2018/7/30
 * Email xhy18650@sina.com
 **/
@Service
public class ODService {

    private Logger logger = LoggerFactory.getLogger(ODService.class);
    @Autowired
    OverseasDistributorMapper overseasDistributorMapper;

    /**
     *  手动刷新所有接口
     * @return
     */
    public  String[] overseasAllInterface(){
        String[] retVal = new String[2];
        try{
            overseasDistributorMapper.callOsAllInterface();
        }catch (Exception e){
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "请求失败了，请在CUX_OS_CRM_INTERFACE_PKG.all_interface中查找原因！";
            return retVal;
        }finally {

        }
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = "请求完成！";
        return retVal;
    }

    /**
     *
     * @param flag 刷新标志
     * @return
     */
    public  String[] overseasRefresh(String flag){
        String[] retVal = new String[2];
        if (flag==null||"".equals(flag)){
            retVal[0]= MesConstants.ERROR;
            retVal[1] = "flag 不能为空";
            return retVal;
        }
        try{
            DbReturnParameter dbreturn = new DbReturnParameter();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            long currentTimeStart = System.currentTimeMillis();
            long time = 0;
             switch (flag){
                 case "1001":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetCustomer(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 case "1002":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetCustomerSite(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 case "1003":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetPriceList(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 case "1004":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetSalesreps(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 case "1005":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetItem(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 case "1006":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetItemRule(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 case "1007":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetPayment(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 case "1008":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetShipment(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 case "1009":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetCustomerArHeader(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 case "1010":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetCustomerArLine(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 case "1011":
                     dbreturn = new DbReturnParameter();
                     paramMap = new HashMap<String, Object>();
                     paramMap.put("bat_id", null); ;
                     paramMap.put(MesConstants.DBRETURN, dbreturn);
                     overseasDistributorMapper.callGetOverseasOrderChange(paramMap);
                     retVal[0]= dbreturn.getxStatus();
                     retVal[1] = dbreturn.getxMessage()+""+paramMap.get("bat_id").toString();
                     time = System.currentTimeMillis()-currentTimeStart;
                     logger.info("刷新标志："+flag+"\t"+"方法执行时间："+String.valueOf(time)+"ms");
                     break;
                 default:
                     logger.info("刷新标志=====>>"+flag);
                     break;
             }
        }catch (Exception e){
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "请求失败了，请在CUX_OS_CRM_INTERFACE_PKG中查找原因！"+flag;
            return retVal;
        }finally {

        }
        return retVal;
    }

    /**
     * 海外客户
     */
    public String[] getOverseasCustomer() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getCustomer();
        overseasDistributorMapper.updateCustomer();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }

    /**
     * 海外客户地点
     */
    public String[] getOverseasCustomerSite() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getCustomerSite();
        overseasDistributorMapper.updateCustomerSite();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }

    /**
     * 海外价目表
     */
    public String[] getOverseasPriceList() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getPriceList();
        overseasDistributorMapper.updatePriceList();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }

    /**
     * 海外销售员
     */
    public String[] getOverseasSaleSreps() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getSaleSreps();
        overseasDistributorMapper.updateSaleSreps();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }

    /**
     * 海外物料
     */
    public String[] getOverseasItems() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getItems();
        overseasDistributorMapper.updateItems();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }

    /**
     * 海外销售规则表
     */
    public String[] getOverseasItemCustomerRule() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getItemCustomerRule();
        overseasDistributorMapper.updateItemCustomerRule();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }

    /**
     * 海外 付款条件
     */
    public String[] getOverseasPaymentTerms() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getPaymentTerms();
        overseasDistributorMapper.updatePaymentTerms();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }

    public static void main(String[] args) {
        ODService odService = new ODService();
        String orderJson = "{\"crm_order_number\":\"20180808\",\"erp_order_number\":\"\",\"process_status\":\"\",\"err_messages\":\"\",\"org_id\":\"\",\"order_source_id\":\"\",\"order_date\":\"\",\"order_type\":\"\",\"salesrep_id\":\"\",\"sold_to_org_id\":\"\",\"sold_from_org_id\":\"\",\"ship_to_org_id\":\"\",\"transactional_curr_code\":\"\",\"booked_flag\":\"\",\"customer_po\":\"\",\"remark\":\"\",\"price_list_id\":\"\",\"ship_method\":\"\",\"country\":\"\",\"address\":\"\",\"fob\":\"\",\"ship_org\":\"\",\"payment_term\":\"\",\"contacts_name\":\"\",\"phone\":\"\",\"transfer_flag\":\"\",\"osLines\":[{\"crm_order_number\":\"20180808\",\"org_id\":\"\",\"line_number\":\"1\",\"order_source_id\":\"\",\"inventory_item_id\":\"\",\"order_quantity\":\"\",\"order_quantity_uom\":\"\",\"unit_list_pric\":\"\",\"unit_selling_pric\":\"\",\"ship_org\":\"\",\"booked_flag\":\"\",\"line_remarks\":\"\",\"process_status\":\"\",\"transfer_flag\":\"\",\"required_date\":\"\"},{\"crm_order_number\":\"20180808\",\"org_id\":\"\",\"line_number\":\"2\",\"order_source_id\":\"\",\"inventory_item_id\":\"\",\"order_quantity\":\"\",\"order_quantity_uom\":\"\",\"unit_list_pric\":\"\",\"unit_selling_pric\":\"\",\"ship_org\":\"\",\"booked_flag\":\"\",\"line_remarks\":\"\",\"process_status\":\"\",\"transfer_flag\":\"\",\"required_date\":\"\"}]}";
        OsOrders osOrders = JsonObjectConverTools.jsonToObject(orderJson, OsOrders.class);
        List<OsLine> osLines = osOrders.getOsLines();
        System.out.println("测试完成！");
    }

    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;

    /**
     * 创建海外订单
     */
    public String[] createOverseasOrder(String orderJson) {
        String[] retVal = new String[2];
        retVal[0] = MesConstants.ERROR;
        try {
            //解析json订单
            OsOrders osOrders = JsonObjectConverTools.jsonToObject(orderJson, OsOrders.class);
            List<OsLine> osLines = osOrders.getOsLines();
            /**
             * 创建销售订单
             */
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("crmOrderNumber", osOrders.getCrm_order_number());
            // 创建销售订单
            overseasDistributorMapper.callOsSetOrder(paramMap);
            // 返回状态
            String sta = (String) paramMap.get(MesConstants.STATUS);
            // 返回错误信息
            String message = (String) paramMap.get(MesConstants.MESSAGE);
            //如果创建失败返回错误信息
            if (!MesConstants.SUCCESS.equals(sta)) {
                //删除订单头行 （仅仅海外经销商的逻辑，创建ERP订单失败也删除接口表，要看数据可以去历史表看）
                overseasDistributorMapper.deleteOrderHeader(osOrders);
                for (OsLine osLine : osLines) {
                    overseasDistributorMapper.deleteOrderLine(osLine);
                }
                this.logger.info(message);
                retVal[0] = MesConstants.ERROR;
                retVal[1] = message;
                return retVal;
            }
            // 创建订单完成！ 取ERP 销售订单号，删除订单行，订单头
            List<Map<String, Object>> list = overseasDistributorMapper.getOrderHeader(osOrders);
            Map<String, Object> map = list.get(0);
            String erp_order_number = map.get("ERP_ORDER_NUMBER").toString();

            //删除订单头行
            overseasDistributorMapper.deleteOrderHeader(osOrders);
            for (OsLine osLine : osLines) {
                overseasDistributorMapper.deleteOrderLine(osLine);
            }
            retVal[0] = MesConstants.SUCCESS;
            retVal[1] = erp_order_number;
            return retVal;


        } catch (Exception e) {
            e.printStackTrace();
            retVal[0] = MesConstants.ERROR;
            retVal[1] = e.getMessage();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return retVal;
        }
    }

    public Boolean insertOsOrderInterface(String orderJson){
      try {
          //解析json订单
          OsOrders osOrders = JsonObjectConverTools.jsonToObject(orderJson, OsOrders.class);
          List<OsLine> osLines = osOrders.getOsLines();
          //插入订单头
          overseasDistributorMapper.saveOrderHeader(osOrders);
          //插入订单行
          for (OsLine osLine : osLines) {
              overseasDistributorMapper.saveOrderLine(osLine);
          }
          return true;
      }catch (Exception e){
          e.printStackTrace();
          TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
          return false;
      }
    }

    /**
     * 海外 发运
     */
    public String[] getOverseasShipmentDetail() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getShipmentDetail();
        overseasDistributorMapper.updateShipmentDetail();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }

    /**
     * 海外 发票头
     * @return
     */
    public String[] getOverseasInvoiceHeader() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getOverseasInvoiceHeader();
        overseasDistributorMapper.updateOverseasInvoiceHeaderStatus();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }

    /**
     * 海外 发票行
     * @return
     */
    public String[] getOverseasInvoiceLine() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getOverseasInvoiceLine();
        overseasDistributorMapper.updateOverseasInvoiceLineStatus();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }
    /**
     * 海外 订单修改，取消
     * @return
     */
    public String[] getOverseasOrderChange() {
        String[] retVal = new String[2];
        List<Map<String, Object>> list = overseasDistributorMapper.getOverseasOrderChange();
        overseasDistributorMapper.updateOverseasOrderChangeStatus();
        String retJson = JsonObjectConverTools.listToJson(list);
        retVal[0] = MesConstants.SUCCESS;
        retVal[1] = retJson;
        return retVal;
    }
}
