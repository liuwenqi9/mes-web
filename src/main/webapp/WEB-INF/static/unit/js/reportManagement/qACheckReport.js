 //初始化控件
function init() { 
    //日期控件初始化
    $('#check_date_start,#check_date_end').datetimepicker({
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
		  var line_code = $('#line_code').val().trim();
		  var inspect_number = $('#inspect_number').val().trim();
          var check_date_start = $('#check_date_start').val().trim();
          var check_date_end = $('#check_date_end').val().trim();
	        window.open( basePath + 'qACheckReportController/exportExcel?inspect_number='
	        		+inspect_number+'&check_date_start='+check_date_start+'&check_date_end='+check_date_end+'&line_code='+line_code);
	    }); 
    
});

//创建DataTable fa货
function dataTable(refresh) {
    var url = basePath + 'qACheckReportController/listByPage';
    var $finishedTable = $('#table');
    if(typeof refresh == 'undefined'){
    	 
        $finishedTable.bootstrapTable({ 
        	//url : url,
       	 	queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    line_code:$('#line_code').val().trim(),
                    inspect_number:$('#inspect_number').val().trim(),
                    check_date_start:$('#check_date_start').val().trim(),
                    check_date_end:$('#check_date_end').val().trim()
                    
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
                title: '检验日期',
                field: 'check_date',
                align: 'center',
                valign: 'middle', 
            	},{
                    title: '送检单',
                    field: 'inspect_number',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '线别',
                    field: 'line_code',
                    align: 'center',
                    valign: 'middle', 
                }, {
                    title: '检验员',
                    field: 'user_name',
                    align: 'center',
                    valign: 'middle',  
                }, {
                    title: '机型',
                    field: 'model',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '成品编码',
                    field: 'segment1',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: 'MO',
                    field: 'mo',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '送检</br>数量',
                    field: 'pack_qty',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '抽样</br>数量',
                    field: 'aql',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: 'AQL允收数量</br>MAJ(0.4)',
                    field: 'maj_qty',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: 'AQL允收数量</br>MIN(1.0)',
                    field: 'min_qty',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '检验不良</br>严重',
                    field: 'spe_qty',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '检验不良</br>主要',
                    field: 'major_qty',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '检验不良</br>次要',
                    field: 'sec_qty',
                    align: 'center',
                    valign: 'middle',  
                } ,{
                    title: '检查结果',
                    field: 'check_status',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '不良现象',
                    field: 'check_msg',
                    align: 'left',
                    valign: 'middle', 
                },{
                    title: '备注',
                    field: 'check_remark',
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
                     line_code:$('#line_code').val().trim(),
                     inspect_number:$('#inspect_number').val().trim(),
                     check_date_start:$('#check_date_start').val().trim(),
                     check_date_end:$('#check_date_end').val().trim()
                 };
             },
        });
    } 
}

function getPlanLines() {
    var url = basePath + 'productionStatisticsDaliyReportController/getPlanLines';
    var suc = function (data) {
        var datas = strToJson(data);
        $('#line_code').empty();
        $('#line_code').append("<option value=''>--请选择生产线--</option>");
        for (var i = 0; i < datas.length; i++) {
            if (datas[i].FLEX_VALUE) {
                $('#line_code').append("<option value='" + datas[i].FLEX_VALUE + "'>" + datas[i].PLANLINEDESC + "</option>");
            }
        }
    }
    eGdUtil.requestJsonAjax(url, null, suc);
};
