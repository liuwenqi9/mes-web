package com.egdfrm.unit.service.pda;

import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.mapper.standard.TtUserFunctionMapper;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.core.service.BaseService;
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.expand.CommonMapper;
import com.egdfrm.unit.mapper.expand.pda.InventoryMapper;
import com.egdfrm.unit.mapper.expand.pda.OpportunityMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.model.expand.pda.Opportunity;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.ws.PDAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  借机归还
 */
@Service
public class OpportunityService extends BaseService {

    @Autowired
    private TtApplicationUserMapper taum;

    @Autowired
    private TtUserFunctionMapper tufm;

    @Autowired
    private PDACommonService pdaCommonService;

    @Autowired
    private OpportunityMapper opportunityMapper;

    @Autowired
    private CommonMapper comm;

    @Autowired
    private InventoryMapper im;

    @Autowired
    private PDACommonMapper pdam;

    /**
     *  （借机归还）验证借机单号
     * @param parameter 借机单号
     * @return
     */
    public String[] opportunityNumber(String parameter){
        String[] retVal = new String[2];
        retVal[0]="S";
        retVal[1]="请求成功！";
        List<Map<String,Object>> list = opportunityMapper.getOpportunity(parameter);
        if(list.size() == 0 || list == null){
            retVal[0]="E";
            retVal[1]="借机单号不存在！";
        }
        return  retVal;
    }
    /**
     *  （借机归还）验证新货位
     * @param parameter  新货位
     * @return
     */
    public String[] opportunityNewPosition(String parameter){
        String[] retVal = new String[2];
        retVal[0]="S";
        retVal[1]="请求成功！";
       /*
		 * 获取原货位信息
		 */
        Map<String, Object> olocMap=new HashMap<String, Object>();
        olocMap=pdaCommonService.getMesItemLocattions(parameter, "101");
        String olocFlag=(String) olocMap.get(MesConstants.RESULT);
        //如果出错，则直接返回
        if(MesConstants.ERROR.equals(olocFlag)){
            retVal=(String[]) olocMap.get(MesConstants.RETVAL);
            return retVal;
        }

        return  retVal;

    }

    /**
     * （借机归还）扫描产品条码
     * @param parameters
     * @return
     */
    public String[] opportunityBarcodeText(String[] parameters) {
        String[] retVal = new String[7];
        String barcodeText = parameters[0];
        String locattionCode = parameters[1];
        Map<String, Object> paramMap = new HashMap<String, Object>();
        DbReturnParameter dbreturn = new DbReturnParameter();
        paramMap = new HashMap<String, Object>();
        paramMap.put("barcodeText", barcodeText);
        paramMap.put(MesConstants.DBRETURN, dbreturn);
        opportunityMapper.pdaCheckLend(paramMap);
        if(MesConstants.ERROR.equals(dbreturn.getxStatus())){
            retVal[0] = dbreturn.getxStatus();
            retVal[1] = dbreturn.getxMessage();
            return retVal;
        }
        List<Map<String,Object>> list = opportunityMapper.getBarcodeTextInfo(barcodeText);
        if(list!=null&&!list.isEmpty()){
            Map<String,Object> map = list.get(0);
            retVal[2] = map.get("BARCODE_TEXT")==null?"":map.get("BARCODE_TEXT").toString();//产品条码
            retVal[3] = map.get("OLD_LOCATTION_CODE")==null?"":map.get("OLD_LOCATTION_CODE").toString();//原货位
            retVal[4] = locattionCode;//新货位
            retVal[5] = map.get("SEGMENT1")==null?"":map.get("SEGMENT1").toString();//料号
            retVal[6] = map.get("DESCRIPTION")==null?"":map.get("DESCRIPTION").toString();//描述
        }else {
            retVal[0] = "E";
            retVal[1] = "产品条码不存在！";
            return retVal;
        }
        retVal[0] = "S";
        retVal[1] = "请求成功！";
        //取 产品条码，原货位，新货位,物料编码

        return retVal;
    }

    /**
     * （借机归还） 提交
     * @param parameter json数据
     * @return
     */
    public String[] opportunityCommit(String parameter) {
        // 返回值
        String[] retVal = new String[2];
        if (StringUtils.isEmpty(parameter)) {
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "数据异常";
            return retVal;
        }
        // 接收转换json数据
         Opportunity opportunity = JsonObjectConverTools.jsonToObject(parameter, Opportunity.class);
        // 登录用户
        String loginName = opportunity.getUserid();
        TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
        if (user == null) {
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "登录用户不存在，请联系管理员";
            return retVal;
        }
        // 组织ID
        String orgId = opportunity.getWarehouse();
        if (StringUtils.isEmpty(orgId)) {
            retVal[0] = MesConstants.ERROR;
            retVal[1] = "登录组织不存在，请联系管理员";
            return retVal;
        }
        List<Opportunity> list = opportunity.getOpportunityList();
        //取得唯一键
        BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
        for (Opportunity op: list) {
            //旧货位
            String oldLocation=op.getOldWareLoca();
            //新货位
            String newLocation=op.getNewWareLoca();
            //产品条码
            String packBarcode=op.getSnNo();
            if(oldLocation.equals(newLocation)){
                retVal[0] = MesConstants.ERROR;
                retVal[1] = "新旧货位"+newLocation+"一致，请重输";
                return retVal;
            }
            /*
			 * 获取原货位信息
			 */
            Map<String, Object> olocMap=new HashMap<String, Object>();
            olocMap=pdaCommonService.getMesItemLocattions(oldLocation, orgId);
            String olocFlag=(String) olocMap.get(MesConstants.RESULT);
            //如果出错，则直接返回
            if(MesConstants.ERROR.equals(olocFlag)){
                retVal=(String[]) olocMap.get(MesConstants.RETVAL);
                return retVal;
            }
            /*
			 * 获取包装箱信息
			 */
            Map<String, Object> pwMap = new HashMap<String, Object>();
            pwMap = pdaCommonService.getPackOrWip(packBarcode, orgId);
            String pwFlag = (String) pwMap.get(MesConstants.RESULT);
            // 如果出错，则直接返回
            if (MesConstants.ERROR.equals(pwFlag)) {
                retVal = (String[]) pwMap.get(MesConstants.RETVAL);
                return retVal;
            }
            // 包装箱/产品ID
            BigDecimal wipBarcodeId = null;
            // 取得是包装还是产品
            int type = (int) pwMap.get(MesConstants.TYPE);
            if (type == MesConstants.PACK) {
                MesPackingHeaders mph = (MesPackingHeaders) pwMap
                        .get(MesConstants.OBJECT);
                wipBarcodeId = mph.getPackingBarcodeId();
            } else if (type == MesConstants.WIP) {
                MesWipBarcodes mwb = (MesWipBarcodes) pwMap
                        .get(MesConstants.OBJECT);
                wipBarcodeId = mwb.getWipBarcodeId();
            }
			/*
			 * 获取新货位信息
			 */
            Map<String, Object> nlocMap=new HashMap<String, Object>();
            nlocMap=pdaCommonService.getMesItemLocattions(newLocation, orgId);
            String nlocFlag=(String) nlocMap.get(MesConstants.RESULT);
            //如果出错，则直接返回
            if(MesConstants.ERROR.equals(nlocFlag)){
                retVal=(String[]) nlocMap.get(MesConstants.RETVAL);
                return retVal;
            }
            /**
             * 写入MES临时接口表
             */
            DbReturnParameter dbreturn = new DbReturnParameter();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userId", user.getUserId());
            paramMap.put("processId", processId);
            paramMap.put("packingBarcodeId", wipBarcodeId);
            paramMap.put("oldLocation", oldLocation);
            paramMap.put("newLocation", newLocation);
            paramMap.put(MesConstants.DBRETURN, dbreturn);
            im.callTranInsertTransactionTemp(paramMap);
            // 返回错误信息
            if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
                retVal[0] = dbreturn.getxStatus();
                retVal[1] = dbreturn.getxMessage();
                TransactionAspectSupport.currentTransactionStatus()
                        .setRollbackOnly();
                System.out.println("1");
                return retVal;
            }
        }
        /**
         * 写入MES_TRANSACTION
         */
        DbReturnParameter dbreturn = new DbReturnParameter();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("processId", processId);
        paramMap.put(MesConstants.TYPE, "TRANSFER");
        paramMap.put(MesConstants.DBRETURN, dbreturn);
        pdam.callProcessBarcode(paramMap);

        // 返回错误信息
        if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
            retVal[0] = dbreturn.getxStatus();
            retVal[1] = dbreturn.getxMessage();
            TransactionAspectSupport.currentTransactionStatus()
                    .setRollbackOnly();
            System.out.println("2");
            return retVal;
        }
        //调用借机归还 包
        opportunityMapper.callMesBarcodesTransferLend(paramMap);

        // 返回错误信息
        if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
            retVal[0] = dbreturn.getxStatus();
            retVal[1] = dbreturn.getxMessage();
            System.out.println("3");
            return retVal;
        }else{
            retVal[0] = MesConstants.SUCCESS;
            return retVal;
        }

    }
}
