//初始化维修人员 下拉列表 
function repPeopleInit(){
	$.ajax({
        type : 'post',
        url : basePath + 'afterSaleSummaryController/getRepPeopleAll',
        data : { 
        },
        success : function (msg) {
            var $msg = $.parseJSON(msg);
            if($msg.success){
            	var datas = $msg.result;
            	//$('#REP_PEOPLE_MODEL').empty(); 
                for (var i = 0; i < datas.length; i++) { 
                   $('#REP_PEOPLE_MODEL').append("<option value='" + datas[i].REP_PEOPLE + "'>" + datas[i].REP_PEOPLE + "</option>");
                    
                }
            }else {
                $.messager.alert("提示",$msg.massage,"info");
            }
        }
    });  
	 
} 

 
$(function(){
	createDataTable();
	
	//时间控件初始化
	$('#RETURN_OPERATION_TIME_END,#RETURN_OPERATION_TIME_START').datetimepicker({
		language:  'zh-CN',
		weekStart: 1,
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0,
		//语言选择中文
		format:"yyyy-mm-dd"
	});
	
	//查询btn
	$('#search').on('click',function(){
		 createDataTable("refresh");
	});
	
	
	//update_btn2 更新 按钮
	$('#update_btn2').on('click',function(){ 
		var rows = $('#summaryTable').bootstrapTable('getSelections');
		if(rows.length == 1){
			//只能修改一条 
			$('#RE_LOGI_COM_MODEL2').val('');
        	$('#RE_EXP_NO_MODEL2').val(''); 
        	$('#RETURN_QTY_MODEL2').val('');
        	$('#ADDRESS_MODEL2').val('');
        	$('#CUSTOM_NAME_MODEL2').val('');
        	$('#CONTACT_NAME_MODEL2').val('');
        	$('#PHONE_MODEL2').val(''); 
        	$.ajax({
                type : 'post',
                url : basePath + 'afterSaleSummaryController/getUpdateLineInfoById',
                data : {
                	ID : rows[0].iD
                },
                success : function (msg) {
                    var $msg = $.parseJSON(msg);
                    if($msg.success){
                    	var datas = $msg.result;
                    	$('#RE_LOGI_COM_MODEL2').val(datas.RE_LOGI_COM);
                    	$('#RE_EXP_NO_MODEL2').val(datas.RE_EXP_NO); 
                    	$('#RETURN_QTY_MODEL2').val(datas.RETURN_QTY);  
                    	$('#ADDRESS_MODEL2').val(datas.ADDRESS); 
                    	$('#CUSTOM_NAME_MODEL2').val(datas.CUSTOM_NAME);
                    	$('#CONTACT_NAME_MODEL2').val(datas.CONTACT_NAME);
                    	$('#PHONE_MODEL2').val(datas.PHONE); 
                    	$('#OTHER_CUSTOM_NAME_MODEL2').val(datas.OTHER_CUSTOM_NAME);
                    	$('#OUTER_CONTACT_MODEL2').val(datas.OUTER_CONTACT);
                    	$('#INTER_NOTE_MODEL2').val(datas.INTER_NOTE);
                    	$('#REMAKRPHONE_MODEL2').val(datas.REMAKR);
                    	$('#PRODUCT_BARCODE_MODEL2').val(datas.PRODUCT_BARCODE); 
                   	    $('#updateModal2').modal("show");  
                    }else {
                        $.messager.alert("提示",$msg.message,"info");
                    }
                }
            }); 
		}else{
			alert("请选择一条数据！");
		}  
	});
	
	//summary_save2 保存按钮(更新模态)
	$('#summary_save2').on('click',function(){
		var rows = $('#summaryTable').bootstrapTable('getSelections'); 
		var RE_LOGI_COM = $('#RE_LOGI_COM_MODEL2').val().trim();
		var RE_EXP_NO = $('#RE_EXP_NO_MODEL2').val().trim(); 
		var RETURN_QTY = $('#RETURN_QTY_MODEL2').val().trim();
		var ADDRESS = $('#ADDRESS_MODEL2').val().trim();
		var CUSTOM_NAME = $('#CUSTOM_NAME_MODEL2').val().trim();
    	var CONTACT_NAME = $('#CONTACT_NAME_MODEL2').val().trim();
    	var PHONE = $('#PHONE_MODEL2').val().trim();
    	var OTHER_CUSTOM_NAME = $('#OTHER_CUSTOM_NAME_MODEL2').val().trim();
    	var OUTER_CONTACT = $('#OUTER_CONTACT_MODEL2').val().trim();
    	var INTER_NOTE  = $('#INTER_NOTE_MODEL2').val().trim();
    	var REMAKR = $('#REMAKR_MODEL2').val().trim();
    	var PRODUCT_BARCODE = $('#PRODUCT_BARCODE_MODEL2').val().trim();
    	
		var ID=rows[0].iD; 
		$.ajax({
            type : 'post',
            url : basePath + 'afterSaleSummaryController/updateById',
            data : {
            	ID :ID, 
            	RE_LOGI_COM :RE_LOGI_COM,
            	RE_EXP_NO :RE_EXP_NO,
            	RETURN_QTY :RETURN_QTY,
            	ADDRESS :ADDRESS,
            	CUSTOM_NAME :CUSTOM_NAME,
            	CONTACT_NAME :CONTACT_NAME,
            	PHONE :PHONE,
            	OTHER_CUSTOM_NAME : OTHER_CUSTOM_NAME,
            	OUTER_CONTACT : OUTER_CONTACT,
            	INTER_NOTE : INTER_NOTE,
            	REMAKR : REMAKR,
            	PRODUCT_BARCODE :PRODUCT_BARCODE
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                if($msg.success){
                    $.messager.alert("提示","保存成功","info",function () { 
                    $('#RE_LOGI_COM_MODEL2').val('');
                    $('#RE_EXP_NO_MODEL2').val(''); 
                    $('#RETURN_QTY_MODEL2').val('');
                    $('#ADDRESS_MODEL2').val(''); 
                    $('#CUSTOM_NAME_MODEL2').val('');
                	$('#CONTACT_NAME_MODEL2').val('');
                	$('#PHONE_MODEL2').val('');
                	$('#OTHER_CUSTOM_NAME_MODEL2').val('');
                	$('#OUTER_CONTACT_MODEL2').val('');
                	$('#INTER_NOTE_MODEL2').val('');
                	$('#REMAKRPHONE_MODEL2').val('');
                	$('#PRODUCT_BARCODE_MODEL2').val(''); 
               		$('#updateModal2').modal("hide");
               		createDataTable("refresh");
                    });
                }else {
                    $.messager.alert("提示",$msg.message,"info");
                }
            }
        });
	});
	
	//summary_save3 交仓库日期 (更新模态)
	$('#summary_save3').on('click',function(){
		var rows = $('#summaryTable').bootstrapTable('getSelections');  
		var IDs=[];
		for(var i = 0;i<rows.length;i++){
			var row = rows[i];
			IDs.push(row.iD);
		} 
		var HAND_OVER_DATE = $('#hand_over_date_modal').val();
		if(HAND_OVER_DATE==''){alert('请填写交仓库日期！'); return;}
		$.ajax({
            type : 'post',
            url : basePath + 'afterSaleSummaryController/updateHandOverByIds',
            data : {
            	IDs :IDs.join(','), 
            	HAND_OVER_DATE:HAND_OVER_DATE
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                if($msg.success){
                    $.messager.alert("提示","保存成功","info",function () { 
                    $('#id').val('');
                    $('#hand_over_date_modal').val('');
               		createDataTable("refresh");
               		$('#updateModal3').modal("hide");
                    });
                }else {
                    $.messager.alert("提示",$msg.message,"info");
                }
            }
        });
	});
	//hand_over 交仓库日期 按钮
	$('#hand_over').on('click',function(){ 
		var rows = $('#summaryTable').bootstrapTable('getSelections');
		if(rows.length>0){
			var IDs=[];
			for(var i = 0;i<rows.length;i++){
				var row = rows[i];
				IDs.push(row.iD);
			} 
			$('#id').val(IDs.join(','));
			$('#hand_over_date_modal').datetimepicker({
				language:  'zh-CN',
				weekStart: 1,
				todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0,
				//语言选择中文
				format:"yyyy-mm-dd"
			});
			$('#updateModal3').modal("show");   
		}else if(rows.length<1){
			alert("请选择数据");
		}  
	});
	
	//update_btn 售后维修记录 按钮
	// 1、 必须都存在维修单
   // 2、不同的维修单，不能一起维修。
	//3、维修单号只要存在一个为空的，不能进行维修, 提示生成维修单，(2018-07-25 改成未生成维修单，可以维修)
	$('#update_btn').on('click',function(){
		repPeopleInit();
		var rows = $('#summaryTable').bootstrapTable('getSelections');
		if(rows.length>0){
			//多条维修记录时，要判断是否是 已经维修过的，//1、
           /* var b1 = false;
            for(var j=0;j<rows.length;j++){
                var weixiudan = rows[j].rEP_WORK_ORDER;//维修单
                if(typeof(weixiudan)=='undefined'){b1=true;break;}
            }
            if(b1){alert('请生成维修单！'); return;}
*/
			if(rows.length>1){
                var weixiudan0 = rows[0].rEP_WORK_ORDER;
                var b2 = false;
                for(var j=0;j<rows.length;j++){
					if(j>0){
                        var weixiudan = rows[j].rEP_WORK_ORDER;//维修单
                        if(weixiudan0!=weixiudan){b2=true;break;}
					}
                }
                if(b2){alert('请选择相同的维修单！'); return;}
			}
			$('#REP_WORK_ORDER_MODEL').val('');
        	$('#SO_NO_MODEL').val(''); 
        	$('#REP_TYPE_MODEL').val('');
        	$('#REP_REASON_MODEL').val('');
        	$('#ISSUE_DESCRIPTION_MODEL').val(''); 
        	$('#CUSTOMER_FEEDBACK_MODEL').val('');
        	$.ajax({
                type : 'post',
                url : basePath + 'afterSaleSummaryController/getLineInfoById',
                data : {
                	IDs : rows[0].iD
                },
                success : function (msg) {
                    var $msg = $.parseJSON(msg);
                    if($msg.success){
                    	var datas = $msg.result;
                    	$('#REP_WORK_ORDER_MODEL').val(datas.REP_WORK_ORDER);
                    	$('#SO_NO_MODEL').val(datas.SO_NO);
                    	//维修人员
                    	$('#REP_PEOPLE_MODEL').val(datas.REP_PEOPLE); 
                    	
                    	$('#REP_TYPE_MODEL').val(datas.REP_TYPE);
                    	$('#REP_REASON_MODEL').val(datas.REP_REASON);
                    	$('#ISSUE_DESCRIPTION_MODEL').val(datas.ISSUE_DESCRIPTION);
                    	$('#CUSTOMER_FEEDBACK_MODEL').val(datas.CUSTOMER_FEEDBACK);
                   	    $('#updateModal').modal("show");  
                    }else {
                        $.messager.alert("提示",$msg.message,"info");
                    }
                }
            }); 
		}else if(rows.length<1){
			alert("请选择数据");
		}  
	});
	
	
	//summary_save 保存按钮
	$('#summary_save').on('click',function(){
		var rows = $('#summaryTable').bootstrapTable('getSelections'); 
		var REP_WORK_ORDER = $('#REP_WORK_ORDER_MODEL').val().trim();
		var SO_NO = $('#SO_NO_MODEL').val().trim();
		var REP_PEOPLE = $('#REP_PEOPLE_MODEL').val().trim();
		/*if(REP_PEOPLE==''){
			alert('请选择维修人员！');
			return;
		}*/
		var REP_TYPE = $('#REP_TYPE_MODEL').val().trim();
		var REP_REASON = $('#REP_REASON_MODEL').val().trim();
		var ISSUE_DESCRIPTION = $('#ISSUE_DESCRIPTION_MODEL').val().trim();
		var CUSTOMER_FEEDBACK = $('#CUSTOMER_FEEDBACK_MODEL').val().trim();
		var IDs=[];
		for(var i = 0;i<rows.length;i++){
			var row = rows[i];
			IDs.push(row.iD);
		} 
		$.ajax({
            type : 'post',
            url : basePath + 'afterSaleSummaryController/bacthUpdate',
            data : {
            	IDs : IDs.join(','), 
            	REP_WORK_ORDER :REP_WORK_ORDER,
            	SO_NO :SO_NO,
            	REP_PEOPLE :REP_PEOPLE,
            	REP_TYPE :REP_TYPE,
            	REP_REASON :REP_REASON,
            	ISSUE_DESCRIPTION :ISSUE_DESCRIPTION,
            	CUSTOMER_FEEDBACK : CUSTOMER_FEEDBACK
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                if($msg.success){
                    $.messager.alert("提示","保存成功","info",function () { 
                	$('#REP_WORK_ORDER_MODEL').val('');
                	$('#SO_NO_MODEL').val('');
                	$('#REP_PEOPLE_MODEL').val('');
                	$('#REP_TYPE_MODEL').val('');
                	$('#REP_REASON_MODEL').val('');
                	$('#ISSUE_DESCRIPTION_MODEL').val('');
                	$('#CUSTOMER_FEEDBACK_MODEL').val('');
               		$('#updateModal').modal("hide");
               		 createDataTable("refresh");
                    });
                }else {
                    $.messager.alert("提示",$msg.message,"info");
                }
            }
        });
		
		 
	});
	/**
	 * generater 生成维修单， 
	 */
	$('#generater').on('click',function(){
		var rows = $('#summaryTable').bootstrapTable('getSelections'); 
		if(rows.length>0){
			var IDs = [];
			var CUSTOM_NAME =rows[0].cUSTOM_NAME;//客户
			for(var i=0;i<rows.length;i++){
				var rep  =rows[i].rEP_WORK_ORDER;
				if(typeof(rep)!='undefined'){
					alert('选择的数据中存在已经生成维修单，不能多次生成');
					return;
				} 
				//只能选择相同的客户
				if(rows[i].cUSTOM_NAME!=CUSTOM_NAME){
					alert('只能选择相同的客户！');
					return;
				}
				IDs.push(rows[i].iD);
			}
			//qingqiu 
			$.ajax({
	            type : 'post', 
	           // url : basePath + 'afterSaleSummaryController/generaterRepWorkOrderAndPrint',
	            url : basePath + 'afterSaleSummaryController/generaterRepWorkOrder',
	            data : {
	            	IDs : IDs.join(',') 
	            },
	            success : function (msg) {
	                var $msg = $.parseJSON(msg); 
	                if($msg.success){  
	                	$.messager.alert("提示",$msg.message,"info");
	                	createDataTable("refresh");
	                	//var path = $msg.message;
	                	//window.open(path); 
	                }else {
	                    $.messager.alert("提示",$msg.message,"info");
	                }
	            }
	        });
		}else{
			alert("请选择数据");
		}
	});
	/**
	 * 补打维修单 print (先作废)
	 */
	/*
	$('#print').on('click',function(){
		var rows = $('#summaryTable').bootstrapTable('getSelections'); 
		if(rows.length>0){
			var rep_work_orders = [],new_array=[];
			for(var i=0;i<rows.length;i++){
				var rep  =rows[i].rEP_WORK_ORDER;
				if(typeof(rep)=='undefined'){
					alert('选择的数据存在未生成维修单！');
					return;
				}  
				rep_work_orders.push(rep);
			}
			//去重复数组，放入新数组
			var len = rep_work_orders.length;
			for(var index=0;index<len;index++){
				var new_len = new_array.length;
				var fa= false;
				var temp = rep_work_orders[index];
				for(var b=0;b<new_len;b++){ 
					if(temp == new_array[b]){
						fa = true;
						break;
					}
				}
				if(!fa){ 
					new_array.push(temp);
				} 
			}
			var ss= new_array;
			//qingqiu 
			$.ajax({
	            type : 'post',
	            url : basePath + 'afterSaleSummaryController/reprinter',
	            data : {
	            	num : new_array.join(',') 
	            },
	            success : function (msg) {
	                var $msg = $.parseJSON(msg);
	                if($msg.success){  
	                	var path = $msg.message;
	                	window.open(path); 
	                }else {
	                    $.messager.alert("提示",$msg.massage,"info");
	                }
	            }
	        });
		}else{
			alert("请选择数据");
		}
	});*/
	 
	$('#print').on('click',function(){
		var rows = $('#summaryTable').bootstrapTable('getSelections'); 
		var rep_work_order0 = rows[0].rEP_WORK_ORDER;
		if(rows.length>0){
			var rep_work_orders = [],new_array=[],IDs=[]; 
			for(var i=0;i<rows.length;i++){
				var rep  =rows[i].rEP_WORK_ORDER;
				if(typeof(rep)=='undefined'){
					alert('选择的数据存在未生成维修单！');
					return;
				}
				if(rep_work_order0!=rep){//第一个数 和所有的比较
					alert('只能选择相同维修单！');
					return;
				}
				rep_work_orders.push(rep);
				IDs.push(rows[i].iD);
			}
			//去重复数组，放入新数组
			var len = rep_work_orders.length;
			for(var index=0;index<len;index++){
				var new_len = new_array.length;
				var fa= false;
				var temp = rep_work_orders[index];
				for(var b=0;b<new_len;b++){ 
					if(temp == new_array[b]){
						fa = true;
						break;
					}
				}
				if(!fa){ 
					new_array.push(temp);
				} 
			}
			var ss= new_array;
			//qingqiu 
			$.ajax({
	            type : 'post',
//	            url : basePath + 'afterSaleSummaryController/reprinter',
	            url : basePath + 'afterSaleSummaryController/reprinter2',
	            data : {
	            	IDs : IDs.join(',')
	            },
	            success : function (msg) {
	                var $msg = $.parseJSON(msg);
	                if($msg.success){  
	                	var path = $msg.message;
	                	window.open(path); 
	                }else {
	                    $.messager.alert("提示",$msg.massage,"info");
	                }
	            }
	        });
		}else{
			alert("请选择数据");
		}
	});
	
	//售后维修记录(导出excel)
	$('#export_excel').on('click',function(){
		var REP_WORK_ORDER = $("#REP_WORK_ORDER").val().trim();
		var CUSTOM_NAME = $("#CUSTOM_NAME").val().trim();
		var PRODUCT_BARCODE = $("#PRODUCT_BARCODE").val().trim();
		var RETURN_OPERATION_TIME_START = $("#RETURN_OPERATION_TIME_START").val().trim();
		var RETURN_OPERATION_TIME_END = $("#RETURN_OPERATION_TIME_END").val().trim();
		var STATUS =  $("#STATUS").val().trim();
		var RE_EXP_NO = $("#RE_EXP_NO").val().trim();
		var PHONE = $("#PHONE").val().trim();
		var PRINTER_STATUS = $("#PRINTER_STATUS").val().trim();
		 
		window.open( basePath + 'afterSaleSummaryController/exportExcel?REP_WORK_ORDER='+REP_WORK_ORDER
				+'&CUSTOM_NAME='+CUSTOM_NAME+'&PRODUCT_BARCODE='+PRODUCT_BARCODE
				+'&RETURN_OPERATION_TIME_START='+RETURN_OPERATION_TIME_START+'&RETURN_OPERATION_TIME_END='+RETURN_OPERATION_TIME_END
				+'&STATUS='+STATUS+'&RE_EXP_NO='+RE_EXP_NO
				+'&PHONE='+PHONE+'&PRINTER_STATUS='+PRINTER_STATUS
		);
		
	});

});	
	

	

	

//创建dataTable
function createDataTable(mark){
	var url = basePath + 'afterSaleSummaryController/getAfterSaleSummaryList';
	var $table = $('#summaryTable');
	if(typeof mark == 'undefined'){
		$table.bootstrapTable({
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit, 
					REP_WORK_ORDER : $("#REP_WORK_ORDER").val().trim(), 
					CUSTOM_NAME : $("#CUSTOM_NAME").val().trim(),
					PRODUCT_BARCODE : $("#PRODUCT_BARCODE").val().trim(),
					RETURN_OPERATION_TIME_START : $("#RETURN_OPERATION_TIME_START").val().trim(),
					RETURN_OPERATION_TIME_END : $("#RETURN_OPERATION_TIME_END").val().trim(),
					STATUS :  $("#STATUS").val().trim(),
					RE_EXP_NO:$("#RE_EXP_NO").val().trim(),
					PHONE:$("#PHONE").val().trim(),
					PRINTER_STATUS:$("#PRINTER_STATUS").val().trim()
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
			singleSelect:false,
			//是否启用点击选中行
			clickToSelect: true,
			//中文支持
			dataLocale:"zh-US",
			// 是否启用排序
			sortable : true,
			// 排序方式
			sortOrder : "desc",
			//头信息 
			// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			cache : false,
			// 是否显示分页（*）
			pagination : true,
			// 是否启用排序
			sortable : false,
			// 排序方式
			sortOrder : "desc",
			// 初始化加载第一页，默认第一页
			// 我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
			//pageNumber:1,
			//默认加载条数
			pageSize : 10,
			//每页显示数据条数
			pageList : [10,20,50,100,200],
			columns: [
				{
					checkbox: true,
					align: 'center',
					valign: 'middle'
				}, {
					title: '维修单号',
					field: 'rEP_WORK_ORDER',
					align: 'center',
					valign: 'middle'
				}, {
					title: '型号',
					field: 'mODEL',
					align: 'center',
					valign: 'middle'
				}, {
                    title: '物料编码',
                    field: 'sEGMENT1',
                    align: 'left',
                    valign: 'middle'
                }, {
                    title: '机身码',
                    field: 'pRODUCT_BARCODE',
                    align: 'left',
                    valign: 'middle'
                }, {
					title: '客户',
					field: 'cUSTOM_NAME',
					align: 'center',
					valign: 'middle'
				}, {
					title: '退货物流单号',
					field: 'rE_EXP_NO',
					align: 'center',
					valign: 'middle'
				}, {
					title: '退货物流公司',
					field: 'rE_LOGI_COM',
					align: 'center',
					valign: 'middle'
				}, {
					title: '退货</br>件数',
					field: 'rETURN_QTY',
					align: 'center',
					valign: 'middle'
				}, {
					title: '退货接</br>收日期',
					field: 'rETURN_OPERATION_TIME',
					align: 'center',
					valign: 'middle'
				}, {
					title: '客户联系人',
					field: 'cONTACT_NAME',
					align: 'center',
					valign: 'middle'
				}, {
					title: '客户联系电话',
					field: 'pHONE',
					align: 'center',
					valign: 'middle'
				}, {
					title: '客户地址',
					field: 'aDDRESS',
					align: 'left',
					valign: 'middle'
				},{
					title: '客户反馈',
					field: 'cUSTOMER_FEEDBACK',
					align: 'center',
					valign: 'middle'
				}, {
                    title: '故障描述',
                    field: 'iSSUE_DESCRIPTION',
                    align: 'left',
                    valign: 'middle'
                }, {
                    title: '维修分析',
                    field: 'rEP_REASON',
                    align: 'left',
                    valign: 'middle'
                }, {
                    title: '维修人员',
                    field: 'rEP_PEOPLE',
                    align: 'left',
                    valign: 'middle'
                }, {
                    title: '处理类别',
                    field: 'rEP_TYPE',
                    align: 'left',
                    valign: 'middle'
                }, {
                    title: '补货SO',
                    field: 'sO_NO',
                    align: 'left',
                    valign: 'middle'
                }, {
                    title: '发货时间',
                    field: 'dELIVERY_OPERATION_TIME',
                    align: 'left',
                    valign: 'middle'
                }, {
                    title: '打印状态',
                    field: 'pRINTER_STATUS',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '物料描述',
                    field: 'dESCRIPTION',
                    align: 'left',
                    valign: 'middle'
                }
			]
		});
	}else {
		$table.bootstrapTable('refreshOptions',{
            url : url,
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit, 
					REP_WORK_ORDER : $("#REP_WORK_ORDER").val().trim(), 
					CUSTOM_NAME : $("#CUSTOM_NAME").val().trim(),
					PRODUCT_BARCODE : $("#PRODUCT_BARCODE").val().trim(),
					RETURN_OPERATION_TIME_START : $("#RETURN_OPERATION_TIME_START").val().trim(),
					RETURN_OPERATION_TIME_END : $("#RETURN_OPERATION_TIME_END").val().trim(),
					STATUS :  $("#STATUS").val().trim(),
					RE_EXP_NO:$("#RE_EXP_NO").val().trim(),
					PHONE:$("#PHONE").val().trim(),
					PRINTER_STATUS:$("#PRINTER_STATUS").val().trim()
				};
			},
			onLoadSuccess : function (data) {
                if(typeof data.rows == 'undefined'){
                    window.location = window.location;
                }
            }
		});
	}
}

