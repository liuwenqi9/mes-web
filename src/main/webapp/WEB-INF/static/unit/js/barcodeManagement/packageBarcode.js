//包装条码管理
$(function(){
	//初始化包装条码列表
	createDataTable();

	//查询btn
	$('#packageBarcode_search_btn').on('click',function(){
		//工单号
		var workOrder = $("#workOrder").val();
		$.ajax({
			type : 'post',
			url : basePath + 'packageBarcodeController/getFlagMark',
			data : {
                workOrder : workOrder
			},
			success : function (msg) {
				$("#flagMark").val(msg=="Y"?"是":"否");
            }
		});
        createDataTable("refresh");
	});

	/**
	 * 打印 (两份)
	 */
	$("#packbarcode_print_btn").on("click", function () {
		var selectRows = $('#packageBarcodeTable').bootstrapTable('getSelections');
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
			url : basePath + 'packageBarcodeController/toBarCodeByQRCode',
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

    /**
     * 打印 (一份)
     */
    $("#packbarcode_print_btn1").on("click", function () {
        var selectRows = $('#packageBarcodeTable').bootstrapTable('getSelections');
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
            url : basePath + 'packageBarcodeController/toBarCodeByQRCode1',
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
	//条码生成
	$("#packageBarcode_generate_btn").on("click",function(){
		//工单
		var workOrder = $("#workOrder").val().trim();
		if(workOrder == null || workOrder == ''){
			alert("请输入工单");
			return;
		}
		//条码类型
		var packageType = $("#packageType").val();
		if(packageType == null || packageType == ''){
			alert("请选择条码类型");
			return;
		}
		$.ajax({
			type : 'post',
			url : basePath + 'packageBarcodeController/barcodePackage',
			data : {
				workOrder : workOrder,
				packageType : packageType
			},
			success : function(msg){
				var $msg = $.parseJSON(msg);
				alert($msg.message);
				if($msg.success){
					createDataTable("refresh");
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
		$("#workOrder").val(worNum);
		//关闭模态框
		$("#myModal").modal("hide");
	});
	/******************工单选中相关代码 end******************/
});

//创建dataTable
function createDataTable(mark){
	var url = basePath + 'packageBarcodeController/getPackageBarcode';
	var $packageBarcodeTable = $('#packageBarcodeTable');
	if(typeof mark == 'undefined'){
		$packageBarcodeTable.bootstrapTable({
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					workOrder : 'null',
					packageBarcode : $("#packageBarcode").val(),
					typeCode : $("#packageType").val(),
                    printStatus : $("#printStatus").val()
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
			showColumns : true,
			// 是否显示刷新按钮
			showRefresh : true,
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
			//toolbar: '#packageBarcode_toolbar',
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
			pageSize : 100,
			//每页显示数据条数
			pageList : [100,200,500,1000],
			columns: [
				{
					checkbox: true,
					align: 'center',
					valign: 'middle'
				}, {
					title: '生产线',
					field: 'PLAN_LINE_CODE',
					align: 'center',
					valign: 'middle'
				}, {
					title: '工单号',
					field: 'WIP_ENTITY_NAME',
					align: 'center',
					valign: 'middle'
				}, {
					title: '装配件',
					field: 'ITEM_NO',
					align: 'center',
					valign: 'middle'
				}, {
					title: '工单数量',
					field: 'START_QUANTITY',
					align: 'center',
					valign: 'middle'
				}, {
					title: '条码类型',
					field: 'PACK_TYPE',
					align: 'center',
					valign: 'middle'
				}, {
					title: '序列号（条码）',
					field: 'BARCODE_TEXT',
					align: 'center',
					valign: 'middle'
				}, {
					title: '可盛放数量',
					field: 'QUANTITY',
					align: 'center',
					valign: 'middle'
				}, {
					title: '已包装数量',
					field: 'PACK_QUANTITY',
					align: 'center',
					valign: 'middle'
				}, {
					title: '工单状态',
					field: 'WIP_STATUS',
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
                }
			]
		});
	}else {
		$packageBarcodeTable.bootstrapTable('refreshOptions',{
            url : url,
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					workOrder : $("#workOrder").val(),
					packageBarcode : $("#packageBarcode").val(),
					typeCode : $("#packageType").val(),
                    printStatus : $("#printStatus").val()
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
			// 是否启用排序
			sortable : true,
			// 排序方式
			sortOrder : "desc",
			//头信息
			//toolbar: '#productBarcode_toolbar',
			//默认加载条数
			pageSize : 100,
			//每页显示数据条数
			pageList : [100,200,500,1000],
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
		$barcodeTable.bootstrapTable('refresh',{
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