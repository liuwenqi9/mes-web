//全局变量
var path = "";
var name = "";

$(function(){
	//时间控件初始化
	$('#startTime,#endTime').datetimepicker({
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
//	//时间控件初始化
//	$('#realStartTime,#realEndTime').datetimepicker({
//		language:  'zh-CN',
//		weekStart: 1,
//		todayBtn:  1,
//		autoclose: 1,
//		todayHighlight: 1,
//		startView: 2,
//		minView: 2,
//		forceParse: 0,
//		//语言选择中文
//		format:"yyyy-mm-dd"
//	});
	//时间控件初始化
	$('#MOStartTime,#MOEndTime').datetimepicker({
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
	//初始化表格
	getDataTable();
	//查询btn
	$('#progress_search_btn').on('click',function(){
		getDataTable('refresh');
	});
		//导出excel
	  $("#exportExcel").on("click",function () { 
		  	var workOrderNumber= $("#workOrderNumber").val().trim();
		  	var productionLine=$("#production_line").val();
		  	var parts=$("#parts").val().trim();
		  	var moCode=$("#mo_code").val().trim();
		  	var startTime=$("#startTime").val().trim();
		  	var endTime=$("#endTime").val().trim();
//		  	var realStartTime=$("#realStartTime").val().trim();
//			var realEndTime=$("#realEndTime").val().trim();
			var MOStartTime=$("#MOStartTime").val().trim();
			var MOEndTime=$("#MOEndTime").val().trim();
			var wipStatus=$("#wip_status").val().trim();
	        window.open(basePath+"workOrderProgressController/exportExcel" +
	        		"?workOrderNumber=" +workOrderNumber+"&productionLine="+productionLine+"&parts="+parts
	        		+"&moCode="+moCode
	        		+"&startTime="+startTime+"&endTime="+endTime
//	        		+"&realStartTime="+realStartTime+"&realEndTime="+realEndTime);
	        +"&MOStartTime="+MOStartTime+"&MOEndTime="+MOEndTime+"&wipStatus="+wipStatus);
	    }); 
	

	/******************工单选中相关代码 start******************/
	//工单选择 or 装配件
	$("#choose_won,#choose_line,#choose_parts").on("click",function(e){
		var $myModalLabel = $("#myModalLabel");
		switch(e.target.id){
			case 'choose_won':
				path = basePath + 'productBarcodeController/getWorkOrderByPage';
				name = "工单";
				$myModalLabel.html("工单号查询");
				break;
			case 'choose_parts':
				path = basePath + 'workOrderProgressController/getPartsByPage';
				name = "装配件";
				$myModalLabel.html("装配件查询");
				break;
		}
		var $workNum = $("#workOrderNumSearch");
        $workNum.attr("placeholder",name);
        $workNum.val("");
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
		var value = selectRows[0].WIP_ENTITY_NAME;
		if(name == '工单'){
			$("#workOrderNumber").val(value);
		}
		if(name == "装配件"){
            $("#parts").val(value);
		}
		//关闭模态框
		$("#myModal").modal("hide");
	});
	/******************工单选中相关代码 end******************/
	
	
});

//创建dataTable
function getDataTable(refresh) {
	 var $table = $('#workOrderTable');
	 if (typeof refresh == 'undefined') {
	        $table.bootstrapTable("destroy");
	        $table.bootstrapTable({ 
				queryParams: function (params) { 
					return{
						offset: params.offset + 1,
						limit: params.limit,
						workOrderNumber: $("#workOrderNumber").val().trim(),
						productionLine: $("#production_line").val(),
						parts: $("#parts").val().trim(),
						moCode: $("#mo_code").val().trim(),
						startTime:$("#startTime").val().trim(),
					  	endTime:$("#endTime").val().trim(),
//					  	realStartTime:$("#realStartTime").val().trim(),
//						realEndTime:$("#realEndTime").val().trim(),
						MOStartTime: $("#MOStartTime").val().trim(),
						MOEndTime: $("#MOEndTime").val().trim(),
						wipStatus:$("#wip_status").val().trim()
					}; 
				},
				//分页方式：client客户端分页，server服务端分页（*）
	            sidePagination: "server",
	            //是否分页
	            pagination : true,
	            //请求方法 post方式会自动把参数封装为json格式
	            method: 'get',
	            //服务器返回的数据类型
	            //dataType : "json",
	            //是否显示行间隔色
	            striped: true,
	            //是否显示所有的列
	            showColumns: true,
	            // 是否显示刷新按钮
	            showRefresh: false,
	            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	            //height: 30,
	            //是否只能选择单行
	            singleSelect: false,
	            //是否启用点击选中行
	            clickToSelect: true,
	            //中文支持
	            dataLocale: "zh-US",
	            // 是否启用排序
	            sortable: true,
	            // 排序方式
	            sortOrder: "desc",
	            //默认加载条数
	            pageSize: 10,
	            //每页显示数据条数
	            pageList: [10, 20, 50, 100],
	            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	            cache: false,
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
						title: 'MO单号',
						field: 'MO_ORDER',
						align: 'center',
						valign: 'middle'
					}, {
						title: '生产线',
						field: 'PLAN_LINE_CODE',
						align: 'center',
						valign: 'middle'
					}, {
						title: '装配件',
						field: 'ITEM_NO',
						align: 'center',
						valign: 'middle'
					}, {
						title: '工单</br>数量',
						field: 'START_QUANTITY',
						align: 'center',
						valign: 'middle'
					}, {
						title: '生产</br>数量',
						field: 'PROD_QUANTITY',
						align: 'center',
						valign: 'middle'
					}, {
						title: '已入库</br>数量',
						field: 'INV_QUANTITY',
						align: 'center',
						valign: 'middle'
					},  {
						title: '检验</br>通过数',
						field: 'P_QUANTITY',
						align: 'center',
						valign: 'middle'
					}, {
						title: '检验</br>NG数',
						field: 'E_QUANTITY',
						align: 'center',
						valign: 'middle'
					},{
						title: '工单</br>状态',
						field: 'WIP_STATUS',
						align: 'center',
						valign: 'middle'
					}, {
						title: '型号',
						field: 'PROD_TYPE',
						align: 'center',
						valign: 'middle'
					}, {
						title: 'MO交货日期',
						field: 'ATTRIBUTE4',
						align: 'center',
						valign: 'middle'
					}, {
						title: '计划开工时间',
						field: 'SCHEDULED_START_DATE',
						align: 'center',
						valign: 'middle'
					},{
						title: '实际开工时间',
						field: 'MIN_TRANSACTION_DATE',
						align: 'center',
						valign: 'middle'
					},{
		                title: '描述',
		                field: 'DESCRIPTION',
		                align: 'left',
		                valign: 'middle'
		            }
				]
			});
	 }else{
		 $table.bootstrapTable("refresh",{ 
			 	url: basePath + 'workOrderProgressController/getWorkOrderList',
				queryParams: function (params) {
					return {
						offset: params.offset + 1,
						limit: params.limit,
						workOrderNumber: $("#workOrderNumber").val().trim(),
						productionLine: $("#production_line").val(),
						parts: $("#parts").val().trim(),
						moCode: $("#mo_code").val().trim(),
						startTime:$("#startTime").val().trim(),
					  	endTime:$("#endTime").val().trim(),
//					  	realStartTime:$("#realStartTime").val().trim(),
//						realEndTime:$("#realEndTime").val().trim(),
						MOStartTime: $("#MOStartTime").val().trim(),
						MOEndTime: $("#MOEndTime").val().trim(),
						wipStatus:$("#wip_status").val().trim()
					}; 
				}
			});
		 
	 }
		 
}

//工单查询
function getworkOrderNumber(refresh) {
	var $barcodeTable = $('#barcodeTable');
	if(typeof refresh == 'undefined'){
		$barcodeTable.bootstrapTable("destroy");
		$barcodeTable.bootstrapTable({
			url : path,
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
			pageSize : 10,
			//每页显示数据条数
			pageList : [10,20,50,100],
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
					title: name,
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