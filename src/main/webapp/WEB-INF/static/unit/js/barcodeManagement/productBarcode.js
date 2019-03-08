//产品条码管理js

$(function() {
	//初始化表格
	getProductBarcode();
	//查询btn
	$('#productBarcode_search_btn').on('click',function(){
		getProductBarcode("refresh");
	});

	//条码生成
	$('#productBarcode_generate_btn').click(function(){
		//工单
		var workOrderNumber = $("#workOrderNumber").val().trim();
		$.ajax({
			type : 'post',
			url : basePath + 'productBarcodeController/barcodeProduction',
			data : {
				workOrder : workOrderNumber
			},
			success : function(msg){
				var $msg = $.parseJSON(msg);
				alert($msg.message);
				if($msg.success){
					getProductBarcode("refresh");
				}
			}
		});
	});

	/**
	 * 条码作废
	 */
	$("#productBarcode_abolish_btn").on("click",function(){
		var selectRows = $('#productBarcodeTable').bootstrapTable('getSelections');
		if(selectRows.length == 0){
			alert("请选择数据!");
			return;
		}
		$("#confirm").modal();
	});

	//确认作废
	$("#confirm_ok").on("click",function(){
		$("#confirm").modal("hide");
		var selectRows = $('#productBarcodeTable').bootstrapTable('getSelections');
		var codes = [];
		for(var i = 0;i<selectRows.length;i++){
			codes.push(selectRows[i].BARCODE_TEXT);
		}
		//作废原因
		var invalidReason = $("#invalidReason").val();
		$.ajax({
			type : 'post',
			url : basePath + 'productBarcodeController/abolish',
			data : {
				codes : codes.join(","),
                invalidReason: invalidReason
			},
			success : function(msg){
				msg = $.parseJSON(msg);
				var message = msg.message;
				alert(message);
				if(msg.success){
					getProductBarcode("refresh");
				}
			}
		});
	})

	//打印两份
	$("#productBarcode_print_btn").on("click",function(){
		var selectRows = $('#productBarcodeTable').bootstrapTable('getSelections');
		if(selectRows.length == 0){
			alert("请选择数据!");
			return;
		}
		var codes = [];
		for(var i = 0;i<selectRows.length;i++){
			codes.push(selectRows[i].BARCODE_TEXT);
		}
		$.ajax({
			type : 'post',
			url : basePath + 'productBarcodeController/toBarCodeByQRCode',
			data : {
				codes : codes
			},
			success : function(msg){
				msg = $.parseJSON(msg);
				var message = msg.message;
				if(msg.success){
					window.open(message);
				}else {
					alert(message);
				}
			}
		});
	});

	//打印一份
	$("#productBarcode_print_btn1").on("click",function(){
		var selectRows = $('#productBarcodeTable').bootstrapTable('getSelections');
		if(selectRows.length == 0){
			alert("请选择数据!");
			return;
		}
		var codes = [];
		for(var i = 0;i<selectRows.length;i++){
			codes.push(selectRows[i].BARCODE_TEXT);
		}
		$.ajax({
			type : 'post',
			url : basePath + 'productBarcodeController/toBarCodeByQRCode1',
			data : {
				codes : codes
			},
			success : function(msg){
				msg = $.parseJSON(msg);
				var message = msg.message;
				if(msg.success){
					window.open(message);
				}else {
					alert(message);
				}
			}
		});
	});
	/******************工单选中相关代码 start******************/
	//工单选择
	$("#choose").on("click",function(){
		//打开模态框
		$("#myModal").modal();
		getworkOrderNumber();
	});

	/**
	 * 工单搜索
	 */
	$("#search").on("click",function(){
		getworkOrderNumber("refresh");
	});

	/**
	 * 确认选择
	 */
	$("#choose_ok").on("click",function(){
		var selectRows = $('#barcodeTable').bootstrapTable('getSelections');
		if(selectRows.length == 0){
			alert("请选择数据!");
			return;
		}
		//选中的工单
		var worNum = selectRows[0].WIP_ENTITY_NAME;
		$("#workOrderNumber").val(worNum);
		//关闭模态框
		$("#myModal").modal("hide");
	});
	/******************工单选中相关代码 end******************/
});

//创建dataTable
function getProductBarcode(refresh) {
	var url = basePath + 'productBarcodeController/getProductBarcode';//'test.json';
	var $productBarcodeTable = $('#productBarcodeTable');
	if(typeof refresh == 'undefined'){
		$productBarcodeTable.bootstrapTable({
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					workOrderNumber : 'null',
					productBarcode : '',
					workStatus : '',
					printFlag : ''
				};
			}, 
			checkboxHeader:true,//全选按钮
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
			showColumns : true,
			// 是否显示刷新按钮
			showRefresh : true,
			//行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            //height: 500,
			//是否只能选择单行
			singleSelect:false,
			//是否启用点击选中行
			clickToSelect: false,
			//中文支持
			dataLocale:"zh-US",
			//默认加载条数
			pageSize : 1000,
			//每页显示数据条数
			pageList : [100,1000,2000,5000,10000],
			// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			cache : false,
			columns: [
				{
					checkbox: true,
					align: 'center',
					valign: 'middle'
				}, {
					title: '工单',
					field: 'WIP_ENTITY_NAME',
					align: 'center',
					valign: 'middle'
				}, {
                    title: '包装箱',
                    field: 'PACK',
                    align: 'center',
                    valign: 'middle'
                }, {
					title: '装配件',
					field: 'SEGMENT1',
					align: 'center',
					valign: 'middle'
				}, {
					title: '工单数量',
					field: 'START_QUANTITY',
					align: 'center',
					valign: 'middle'
				}, {
					title: '生产线',
					field: 'PLAN_LINE_CODE',
					align: 'center',
					valign: 'middle'
				}, {
					title: 'MO单号',
					field: 'MO_ORDER',
					align: 'center',
					valign: 'middle'
				}, {
					title: '序列号（条码）',
					field: 'BARCODE_TEXT',
					align: 'center',
					valign: 'middle'
				}, {
					title: '条码状态',
					field: 'BARCODE_STATUS',
					align: 'center',
					valign: 'middle'
				}, {
					title: '工单状态',
					field: 'MEANING',
					align: 'center',
					valign: 'middle'
				}, {
					title: '打印状态',
					field: 'PRINT_FLAG',
					align: 'center',
					valign: 'middle'
				}, {
                    title: '描述',
                    field: 'DESCRIPTION',
                    align: 'left',
                    valign: 'middle'
                }, {
                    title: '作废原因',
                    field: 'ATTRIBUTE1',
                    align: 'left',
                    valign: 'middle'
                }
			]
		});
	}else {
		$productBarcodeTable.bootstrapTable('refreshOptions',{
            url : url,
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					workOrderNumber : $("#workOrderNumber").val().trim(),
					productBarcode : $("#productBarcode").val().trim(),
					workStatus : encodeUnicode($("#workStatus").val()),
					printFlag : $("#printStatus").val()
				};
			}
		});
	}
}

//工单查询
function getworkOrderNumber(refresh) {
	var url = basePath + 'productBarcodeController/getWorkOrderByPage';
	var $barcodeTable = $('#barcodeTable');
	if(typeof refresh == 'undefined'){
		$barcodeTable.bootstrapTable({
			url : url,
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					workOrderNumber : $("#workOrderNumSearch").val().trim()
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
					title: '工单',
					field: 'WIP_ENTITY_NAME',
					align: 'center',
					valign: 'middle'
				}
			]
		});
	}else {
		$barcodeTable.bootstrapTable('refreshOptions',{
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					workOrderNumber : $("#workOrderNumSearch").val().trim()
				};
			}
		});
	}
}

// 转为unicode 编码
function encodeUnicode(str) {
	var res = [];
	for ( var i=0; i<str.length; i++ ) {
		res[i] = ( "00" + str.charCodeAt(i).toString(16) ).slice(-4);
	}
	return "\\u" + res.join("\\u");
}