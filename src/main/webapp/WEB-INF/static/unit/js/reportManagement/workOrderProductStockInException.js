
 
$(function () {
    //初始化DataTable
	dataTable();
	$('#search_btn').on('click',function(){
		dataTable('refresh');
	}); 
	
		//导出excel
	  $("#exportExcel").on("click",function () { 
		  var wip_entity_name = $('#wip_entity_name').val().trim(); 
	        window.open( basePath + 'workOrderProductStockInExceptionController/exportExcel?wip_entity_name='
	        		+wip_entity_name  );
	    }); 
    
});

//创建DataTable  
function dataTable(refresh) {
    var url = basePath + 'workOrderProductStockInExceptionController/listByPage';
    var $finishedTable = $('#table');
    if(typeof refresh == 'undefined'){
    	 
        $finishedTable.bootstrapTable({ 
        	//url : url,
       	 	queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    wip_entity_name:$('#wip_entity_name').val().trim() 
                    
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
            showRefresh : true,
            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            //height: 500,
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
            //toolbar: '#productBarcode_toolbar',
            //默认加载条数
            pageSize : 25,
            //每页显示数据条数
            pageList : [25,50,100,200,500],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            columns: [ 
                      {
                title: '工单号',
                field: 'wip_entity_name',
                align: 'center',
                valign: 'middle', 
            	},{
                    title: 'MO单号',
                    field: 'mo_order',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '生产线',
                    field: 'plan_line_code',
                    align: 'center',
                    valign: 'middle', 
                }, {
                    title: '物料编码',
                    field: 'item_no',
                    align: 'center',
                    valign: 'middle',  
                }, {
                    title: '型号',
                    field: 'prod_type',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '工单状态',
                    field: 'wip_status',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '工单</br>数量',
                    field: 'start_quantity',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '条码</br>生产数',
                    field: 'prod_quantity',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '条码</br>完工数',
                    field: 'mes_inv_quantity',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: 'ERP</br>完工数',
                    field: 'erp_inv_quantity',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '计划开工时间',
                    field: 'scheduled_start_date',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '实际开工时间',
                    field: 'min_transaction_date',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '物料描述',
                    field: 'description',
                    align: 'left',
                    valign: 'middle', 
                }  
            ]
        });
    }else {
        $finishedTable.bootstrapTable('refreshOptions',{
        	url:url,
             queryParams : function(params) {
                 return {
                     offset : params.offset + 1,
                     limit : params.limit,
                     wip_entity_name:$('#wip_entity_name').val().trim() 
                 };
             },
        });
    } 
}
