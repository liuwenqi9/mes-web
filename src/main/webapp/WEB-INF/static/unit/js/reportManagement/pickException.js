


$(function () {
    //初始化DataTable
	dataTable();
	$('#search_btn').on('click',function(){
		dataTable('refresh');
	}); 
	
		//导出excel
	  $("#exportExcel").on("click",function () { 
		  var source_header_number = $('#source_header_number').val().trim();
          var request_number = $('#request_number').val().trim(); 
	        window.open( basePath + 'pickExceptionController/exportExcel?source_header_number='
	        		+source_header_number+'&request_number='+request_number );
	    }); 
    
});

//创建DataTable  
function dataTable(refresh) {
    var url = basePath + 'pickExceptionController/listByPage';
    var $finishedTable = $('#table');
    if(typeof refresh == 'undefined'){
    	 
        $finishedTable.bootstrapTable({ 
        	//url : url,
       	 	queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    source_header_number:$('#source_header_number').val().trim(),
                    request_number:$('#request_number').val().trim()
                    
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
                title: '销售订单',
                field: 'source_header_number',
                align: 'center',
                valign: 'middle', 
            	},{
                    title: '客户',
                    field: 'customer_name',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '挑库单号',
                    field: 'request_number',
                    align: 'center',
                    valign: 'middle', 
                }, {
                    title: '物料编码',
                    field: 'segment1',
                    align: 'center',
                    valign: 'middle',  
                }, {
                    title: '型号',
                    field: 'prod_type',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '挑库日期',
                    field: 'pick_date',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '需求数量',
                    field: 'move_order_quantity',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: 'MES</br>挑库数',
                    field: 'mes_pick_quantity',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: 'ERP</br>挑库数',
                    field: 'erp_picked_quantity',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: 'MES</br>发运数',
                    field: 'mes_ship_quantity',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: 'ERP</br>发运数',
                    field: 'erp_ship_quantity',
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
                     source_header_number:$('#source_header_number').val().trim(),
                     request_number:$('#request_number').val().trim()
                 };
             },
        });
    } 
}
