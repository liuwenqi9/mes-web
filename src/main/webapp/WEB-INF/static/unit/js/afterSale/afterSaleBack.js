 function initDate(){
	 $('#DELIVERY_OPERATION_TIME').val(new Date().Format('yyyy-MM-dd'));
	    //日期控件初始化
	 $('#DELIVERY_OPERATION_TIME').datetimepicker({
	        language: 'zh-CN',
	        weekStart: 1,
	        todayBtn: 1,
	        autoclose: 1,
	        todayHighlight: 1,
	        startView: 2,
	        minView: 2,
	        forceParse: 0,
	        //语言选择中文
	        format: "yyyy-mm-dd"
	  });
 }

$(function() {
	$('#backTable').bootstrapTable();
	//初始化日期控件
	initDate(); 
	
	/******************工单选中相关代码 start******************/
	//客户选择
	$("#choose").on("click",function(){
		//打开模态框
		$("#myModal").modal();
		getCustomName();
	});

	/**
	 * 客户搜索
	 */
	$("#search").on("click",function(){
		getCustomName("refresh");
	});

	/**
	 * 客户 确认选择
	 */
	$("#choose_ok").on("click",function(){
		var selectRows = $('#customNameTable').bootstrapTable('getSelections');
		if(selectRows.length == 0){
			alert("请选择数据!");
			return;
		}
		//选中的工单
		var CUSTOM_NAME = selectRows[0].CUSTOMER_NAME;
		var CONTACT_NAME = selectRows[0].CONTACT_NAME;
		var PHONE = selectRows[0].PHONE;
		var ADDRESS = selectRows[0].ADDRESS;
		$("#CUSTOM_NAME").val(CUSTOM_NAME);
		$('#CONTACT_NAME').val(CONTACT_NAME);
        $('#PHONE').val(PHONE);
        $('#ADDRESS').val(ADDRESS);
		//关闭模态框
		$("#myModal").modal("hide");
	});
	/******************工单选中相关代码 end******************/
	
	 /**
	  * CUSTOM_NAME失去焦点
	  */ 
	/* $('#CUSTOM_NAME').blur(function(){
		 var CUSTOM_NAME=  $('#CUSTOM_NAME').val().trim();
		 if(CUSTOM_NAME==''){alert('客户不能为空！'); return;}
		 $.ajax({
         	type : 'post',
				url : basePath + 'afterSaleBackController/getCustomerInfo',
				data : {
					customer: CUSTOM_NAME
				},
				success : function (msg) {
                 var $msg = $.parseJSON(msg);
                 if ($msg.success) { 
                     var datas = $msg.result; 
                     $('#CONTACT_NAME').val(datas.CONTACT_NAME);
                     $('#PHONE').val(datas.PHONE);
                     $('#ADDRESS').val(datas.ADDRESS);
                      return;
                 }
                 alert($msg.message);
             }
			});
	 });*/
	 
	/**
	 * 输入产品条码 回车
	 */
    $('#PRODUCT_BARCODE_ALL').keydown(function (e) {
        if (e.keyCode == 13) {
        	var barcodeText  = $('#PRODUCT_BARCODE_ALL').val().trim();
        	if(barcodeText==''){ alert("请输入产品条码！"); return;}
            $.ajax({
            	type : 'post',
				url : basePath + 'afterSaleBackController/getBarcodeInfo',
				data : {
                    barcodeText: barcodeText
				},
				success : function (msg) {
                    var $msg = $.parseJSON(msg);
                    if ($msg.success) { 
                        var datas = $msg.result;
                        
                        /*
            			 * 校验，如果已经录入，则不可重复录入
            			 */
            			 var rows = $('#backTable').bootstrapTable('getOptions').data;
            		     if (rows.length > 0) {
            		         for (var i = 0; i < rows.length; i++) {
            		             var row = rows[i];
            		             if(row.WIP_BARCODE_ID ==datas.WIP_BARCODE_ID ){ 
            		            		 alert('该条码已处在列表中');
            		         			//产品条码清空
            		            		 $('#PRODUCT_BARCODE_ALL').focus();
            		            		 return;  
            		             }
            		         }
            		     }
            		     //加入table中
            		     $('#backTable').bootstrapTable('prepend', datas); 
            		     $('#PRODUCT_BARCODE_ALL').val('');
                         $('#PRODUCT_BARCODE_ALL').focus();
                         return;
                    }
                    alert($msg.message);
                }
			});
        }
    });
    /**
     * 删除按钮
     */
    $('#delete').on('click',function(){
    	var rows = $('#backTable').bootstrapTable('getSelections');
		if (rows.length > 0) {
	       var selects = $('#backTable').bootstrapTable('getSelections');  
	       var wipBarcodeIdArray = $.map(selects, function (row) {  
	           return row.WIP_BARCODE_ID;  
	       }); 
	       $('#backTable').bootstrapTable('remove', {  
	           field: 'WIP_BARCODE_ID',  
	           values: wipBarcodeIdArray  
	       }); 
		} else {
			alert("请选择需要删除的数据");
		}	
    });
    
    /**
     * 清空 按钮resetAll
     */
    $('#resetAll').on('click',function(){
    	$('#CUSTOM_NAME').val('');
  		 $('#RE_LOGI_COM').val('');
  		 $('#RE_EXP_NO').val('');
  		 $('#RETURN_QTY').val('');
  		 $('#CONTACT_NAME').val('');
  		 $('#PHONE').val('');
  		 $('#ADDRESS').val('');
  		 $('#OTHER_CUSTOM_NAME').val('');
  		 $('#OUTER_CONTACT').val('');
  		 $('#INTER_NOTE').val('');
  		 $('#REMAKR').val('');
    	 $('#backTable').bootstrapTable('removeAll');	
    });
     
    /**
     * 保存 按钮commit
     */
    $('#commit').on('click',function(){
    	var rows = $('#backTable').bootstrapTable('getOptions').data;	
    	 if (rows.length > 0) {
    		 var WIP_BARCODE_IDs = [];
    		 var MATERIAL_IDs =[];
    		 var PRODUCT_BARCODEs = [];
    		 var SEGMENT1s=[];
    		 var MODELs = [];
    		 var DESCRIPTIONs = [];
    		 
    		 //值
    		 var CUSTOM_NAME = $('#CUSTOM_NAME').val().trim();
    		 //退货物流公司
    		 var RE_LOGI_COM = $('#RE_LOGI_COM').val().trim();
    		 if(RE_LOGI_COM==''){alert('退货物流公司不能为空！'); $('#RE_LOGI_COM').focus(); return;}
    		 //退货物流单号
    		 var RE_EXP_NO = $('#RE_EXP_NO').val().trim();
    		 if(RE_EXP_NO==''){alert('退货物流单号不能为空！'); $('#RE_EXP_NO').focus(); return;}
    		 //退货件数
    		 var RETURN_QTY = $('#RETURN_QTY').val().trim();
    		 if(RETURN_QTY==''){alert('退货件数不能为空！'); $('#RETURN_QTY').focus(); return;}
    		 var CONTACT_NAME = $('#CONTACT_NAME').val().trim();
    		 var PHONE = $('#PHONE').val().trim();
    		 var ADDRESS = $('#ADDRESS').val().trim();
    		 var DELIVERY_OPERATION_TIME = $('#DELIVERY_OPERATION_TIME').val().trim();
    		 //其他客户
    		 var OTHER_CUSTOM_NAME = $('#OTHER_CUSTOM_NAME').val().trim();
    		 if(CUSTOM_NAME==""&&OTHER_CUSTOM_NAME==""){
    			 alert('各客户不能为空！'); $('#OTHER_CUSTOM_NAME').focus(); return;
    		 }
    		 var OUTER_CONTACT = $('#OUTER_CONTACT').val().trim(); //外箱是否有联系方式
    		 var INTER_NOTE = $('#INTER_NOTE').val().trim();//内箱是否有送货单
    		 var REMAKR = $('#REMAKR').val().trim();//REMAKR=备注
    		 
    		 for (var i = 0; i < rows.length; i++) {
                 var row = rows[i]; 
                 WIP_BARCODE_IDs.push(row.WIP_BARCODE_ID);
                 MATERIAL_IDs.push(row.MATERIAL_ID);
                 PRODUCT_BARCODEs.push(row.PRODUCT_BARCODE);
                 SEGMENT1s.push(row.SEGMENT1);
                 MODELs.push(row.MODEL);
                 DESCRIPTIONs.push(row.DESCRIPTION); 
             }
    		 $.ajax({
    	            type : 'post',
    	            url : basePath + 'afterSaleBackController/saveAfterSaleBarcode',
    	            data : {
    	            	BARCODEID : WIP_BARCODE_IDs.join(','),
    	            	MATERIAL_ID : MATERIAL_IDs.join(','),
    	            	PRODUCT_BARCODE : PRODUCT_BARCODEs.join(','),
    	            	SEGMENT1 : SEGMENT1s.join(','),
    	            	MODEL : MODELs.join(','),
    	            	DESCRIPTION : DESCRIPTIONs.join(','),
    	            	
    	            	CUSTOM_NAME : CUSTOM_NAME,
    	            	RE_LOGI_COM : RE_LOGI_COM,
    	            	RE_EXP_NO : RE_EXP_NO,
    	            	RETURN_QTY : RETURN_QTY,
    	            	CONTACT_NAME :CONTACT_NAME,
    	            	PHONE :PHONE,
    	            	ADDRESS : ADDRESS, 
    	            	DELIVERY_OPERATION_TIME:DELIVERY_OPERATION_TIME,
    	            	OTHER_CUSTOM_NAME:OTHER_CUSTOM_NAME,
    	            	OUTER_CONTACT:OUTER_CONTACT,
    	            	INTER_NOTE:INTER_NOTE,
    	            	REMAKR:REMAKR
    	            },
    	            success : function (msg) {
    	                var $msg = $.parseJSON(msg);
    	                if($msg.success){
    	                    $.messager.alert("提示","保存成功","info",function () {
    	                     $('#CUSTOM_NAME').val('');
    	               		 $('#RE_LOGI_COM').val('');
    	               		 $('#RE_EXP_NO').val('');
    	               		 $('#RETURN_QTY').val('');
    	               		 $('#CONTACT_NAME').val('');
    	               		 $('#PHONE').val('');
    	               		 $('#ADDRESS').val('');
    	               		 $('#OTHER_CUSTOM_NAME').val('');
    	               		 $('#OUTER_CONTACT').val('');
    	               		 $('#INTER_NOTE').val('');
    	               		 $('#REMAKR').val('');
    	               		 $('#backTable').bootstrapTable('removeAll');
    	               		 initDate();
    	                    });
    	                }else {
    	                    $.messager.alert("提示",$msg.massage,"info");
    	                }
    	            }
    	        });
    		 
    		 
    	 }else{alert('没有数据可以保存');}
    });
    
    /**
     * RETURN_QTY 退货件数
     */
    $('#RETURN_QTY').on('keyup',function(){
    	var v = $('#RETURN_QTY').val();
    	  if(!/^[0-9]*$/.test(v)){
    		  alert('退货件数必须为整数！'); 
    		  $('#RETURN_QTY').val('');
    	  }
    });
    
});

//工单查询
function getCustomName(refresh) {
	var url = basePath + 'afterSaleBackController/getCustomerInfo';
	var $table = $('#customNameTable');
	if(typeof refresh == 'undefined'){
		$table.bootstrapTable({
			url : url,
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					customer : $("#CUSTOM_NAME_model").val().trim()
				};
			},
			//分页方式：client客户端分页，server服务端分页（*）
			sidePagination : "server",
			//是否分页
			pagination : true,
			//请求方法 post方式会自动把参数封装为json格式
			method : 'get',
			//服务器返回的数据类型
			//dataType : "json",
			//是否显示行间隔色
			striped : true,
			//是否显示所有的列
			showColumns : false,
			// 是否显示刷新按钮
			showRefresh : false,
			//行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			//height: 30,
			//是否只能选择单行
			singleSelect:true,
			//是否启用点击选中行
			clickToSelect: true,
			//中文支持
			dataLocale:"zh-US",
			//默认加载条数
			pageSize : 10,
			//每页显示数据条数
			pageList : [10,20,50,100],
			// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			cache : false,
			// 是否启用排序
			sortable : false,
			// 排序方式
			sortOrder : "desc",
			columns: [
				{
					checkbox: true,
					align: 'center',
					valign: 'middle'
				}, {
					title: '客户',
					field: 'CUSTOMER_NAME',
					align: 'center',
					valign: 'middle'
				}, {
					title: '联系人',
					field: 'CONTACT_NAME',
					align: 'center',
					valign: 'middle'
				}, {
					title: '联系电话',
					field: 'PHONE',
					align: 'center',
					valign: 'middle'
				}, {
					title: '地址',
					field: 'ADDRESS',
					align: 'left',
					valign: 'middle'
				} 
			]
		});
	}else {
		$table.bootstrapTable('refreshOptions',{
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					customer : $("#CUSTOM_NAME_model").val().trim()
				};
			}
		});
	}
}
