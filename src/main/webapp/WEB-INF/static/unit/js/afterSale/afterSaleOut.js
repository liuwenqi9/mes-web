 

$(function() {
	createDataTable();
	
	/**
	 * 查询
	 */
	$('#search_btn').on('click',function(){
		 createDataTable("refresh");
	});
	
	/**
	 * 出货详情信息 out_info_btn
	 */
	$('#out_info_btn').on('click',function(){
		var rows = $('#outTable').bootstrapTable('getSelections'); 
		if(rows.length>0){ 
			var ids =[];
			var cUSTOM_NAME = rows[0].cUSTOM_NAME;
            for(var i=0;i<rows.length;i++){
                var row = rows[i];
                if(cUSTOM_NAME != row.cUSTOM_NAME ){
                    alert("请选择相同的客户！");
                    return;
                }
                /*if(typeof(row.dELIVERY_OPERATION_TIME) !='undefined'){//表示 已出货
                	alert("只能选择未出货的数据！");
                    return;
                } */
                ids.push(row.iD);
            } 
            $('#SH_EXP_NO_MODEL').val(rows[0].sH_EXP_NO);
            $('#SH_LOGI_COM_MODEL').val(rows[0].sH_LOGI_COM);
            $('#CONTACT_NAME_MODEL').val(rows[0].cONTACT_NAME);
            $('#PHONE_MODEL').val(rows[0].pHONE);
            $('#ADDRESS_MODEL').val(rows[0].aDDRESS);
            $('#SUPPLIER_DELIVERY_INFO_MODEL').val(rows[0].sUPPLIER_DELIVERY_INFO);
            $('#IDS_MODEL').val(ids.join(','));
			$('#updateModal').modal("show");
		}else{
			alert("请选择需要的数据！");
		} 
		// $('#sh').serializeObject()
		//$(':input', '#sh').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
	});
	
	/**
	 * 保存按钮 save
	 */
	$('#save').on('click',function(){
		var param = $('#sh').serializeObject();
		$.ajax({
            type : 'post',
            url : basePath + 'afterSaleOutController/updateToSave',
            data : param,
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                if($msg.success){  
                    $(':input', '#sh').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
               		createDataTable("refresh"); 
               		$('#updateModal').modal("hide");
                }else {
                    $.messager.alert("提示",$msg.massage,"info");
                }
            }
        }); 
	});
	
	/**
	 * 确认发货 comfirm
	 */
	$('#comfirm').on('click',function(){
		var param = $('#sh').serializeObject();
		$.ajax({
            type : 'post',
            url : basePath + 'afterSaleOutController/upateToShip',
            data : param,
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                if($msg.success){  
                    $(':input', '#sh').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
               		createDataTable("refresh");
               		$('#updateModal').modal("hide");
                }else {
                    $.messager.alert("提示",$msg.massage,"info");
                }
            }
        }); 
	});
	
});    
     
//创建dataTable
function createDataTable(mark){
	var url = basePath + 'afterSaleOutController/getAfterSaleOutList';
	var $table = $('#outTable');
	if(typeof mark == 'undefined'){
		$table.bootstrapTable({
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit, 
					REP_WORK_ORDER : $("#REP_WORK_ORDER").val().trim(), 
					CUSTOM_NAME : $("#CUSTOM_NAME").val().trim(),
					PRODUCT_BARCODE : $("#PRODUCT_BARCODE").val().trim(), 
					STATUS :  $("#STATUS").val().trim(),
					MODEL :  $("#MODEL").val().trim(),
					CONTACT_NAME :  $("#CONTACT_NAME").val().trim()
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
				},  {
                    title: '产品条码',
                    field: 'pRODUCT_BARCODE',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '物料编码',
                    field: 'sEGMENT1',
                    align: 'center',
                    valign: 'middle'
                },{
					title: '客户',
					field: 'cUSTOM_NAME',
					align: 'center',
					valign: 'middle'
				},{
                    title: '处理类别',
                    field: 'rEP_TYPE',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '补货SO',
                    field: 'sO_NO',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '状态',
                    field: 'sTATUS',
                    align: 'center',
                    valign: 'middle'
                },{
					title: '出货日期',
					field: 'dELIVERY_OPERATION_TIME',
					align: 'center',
					valign: 'middle'
				}, {
					title: '物流公司',
					field: 'sH_LOGI_COM',
					align: 'center',
					valign: 'middle'
				}, {
					title: '物流单号',
					field: 'sH_EXP_NO',
					align: 'center',
					valign: 'middle'
				},{
					title: '联系人',
					field: 'cONTACT_NAME',
					align: 'center',
					valign: 'middle'
				},{
					title: '联系电话',
					field: 'pHONE',
					align: 'center',
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
					STATUS :  $("#STATUS").val().trim(),
					MODEL :  $("#MODEL").val().trim(),
					CONTACT_NAME :  $("#CONTACT_NAME").val().trim()
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
