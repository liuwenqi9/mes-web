/*****************************************************************************
 *
 * 入库统计日报表JS
 *
 *****************************************************************************/


//初始化获取生产线列表
function getPlanLines() {
    var url = basePath + 'productionStatisticsDaliyReportController/getPlanLines';
    var suc = function (data) {
        var datas = strToJson(data);
        $('#productionLine_id').empty();
        $('#productionLine_id').append("<option value=''>--请选择生产线--</option>");
        for (var i = 0; i < datas.length; i++) {
            if (datas[i].FLEX_VALUE) {
                $('#productionLine_id').append("<option value='" + datas[i].FLEX_VALUE + "'>" + datas[i].PLANLINEDESC + "</option>");
            }
        }
    }
    eGdUtil.requestJsonAjax(url, null, suc);
};
//初始化控件
function init() {
    //初始化查询日期
//	var startDate=eGdUtil.changeLongToDate(new Date()-90*24*60*60*1000); 90天前
    $('#date_from_id').val(new Date().Format('yyyy-MM-dd'));
    $('#date_to_id').val(new Date().Format('yyyy-MM-dd'));
    //日期控件初始化
    $('#date_from_id,#date_to_id').datetimepicker({
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

$(function () {
	getPlanLines();
	init();
    //初始化DataTable
	dataTable();
	$('#search_btn').on('click',function(){
		dataTable('refresh');
	}); 
	
		//导出excel
	  $("#exportExcel").on("click",function () { 
		  var plan_line_code = $('#productionLine_id').val().trim();
          var start_date = $('#date_from_id').val().trim();
          var end_date = $('#date_to_id').val().trim();
	        window.open( basePath + 'checkStatisticController/exportExcel?plan_line_code='
	        		+plan_line_code+'&start_date='+start_date+'&end_date='+end_date);
	    }); 
    
});

//创建DataTable fa货
function dataTable(refresh) {
    var url = basePath + 'checkStatisticController/listByPage';
    var $finishedTable = $('#table');
    if(typeof refresh == 'undefined'){
    	 
        $finishedTable.bootstrapTable({ 
        	//url : url,
       	 	queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    plan_line_code:$('#productionLine_id').val().trim(),
                    start_date:$('#date_from_id').val().trim(),
                    end_date:$('#date_to_id').val().trim()
                    
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
            pageSize : 50,
            //每页显示数据条数
            pageList : [50,100,200,500],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            columns: [
                      {
                title: '生产线',
                field: 'plan_line_code',
                align: 'center',
                valign: 'middle', 
            	},{
                    title: '工单号',
                    field: 'wip_entity_name',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '物料编码',
                    field: 'segment1',
                    align: 'center',
                    valign: 'middle', 
                }, {
                    title: '型号',
                    field: 'segment1',
                    align: 'center',
                    valign: 'middle',  
                }, {
                    title: 'MO号',
                    field: 'mo_order',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '工单数量',
                    field: 'start_quantity',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '检验数量',
                    field: 'transaction_quantity',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '工单状态',
                    field: 'status_type',
                    align: 'left',
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
                     plan_line_code:$('#productionLine_id').val().trim(),
                     start_date:$('#date_from_id').val().trim(),
                     end_date:$('#date_to_id').val().trim()
                 };
             },
        });
    } 
}
