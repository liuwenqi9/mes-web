
$(function () {
    //初始化数据列表
    creatDataTable();
    
  //时间控件初始化
    $('#start_date,#end_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format:"yyyy-mm-dd"
    });
 
    $("#search_btn").on("click",function () {
        creatDataTable("refresh");
    }); 
    
    $("#exportExcel").on("click",function () { 
    	var document_num = $('#document_num').val().trim(); 
    	var cust_po_number = $('#cust_po_number').val().trim();
        var start_date = $('#start_date').val().trim();
        var end_date = $('#end_date').val().trim();
        window.open(basePath+"wXOutController/wXOutExportExcel" +
        		"?document_num=" +document_num+'&cust_po_number='+cust_po_number
        		+'&start_date='+start_date+'&end_date='+end_date);
    }); 
    
    
});


/**
 * 创建dataTable
 */
function creatDataTable(refresh) {
    var url = basePath + 'wXOutController/getwXOutpLists';
    var $table = $('#wXOutTable');
    if(eGdUtil.isEmpty(refresh)){
        $table.bootstrapTable({
            queryParams : function(params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    document_num:$('#document_num').val().trim(),
                    cust_po_number:$('#cust_po_number').val().trim(),
                    start_date:$('#start_date').val().trim(),
                    end_date:$('#end_date').val().trim()
                };
            },
            //分页方式：client客户端分页，server服务端分页（*）
            sidePagination : "server",
            //请求方法 post方式会自动把参数封装为json格式
            method : 'get',
            //服务器返回的数据类型
            //dataType : "json",
            //是否显示行间隔色
            striped : true,
            //是否显示所有的列
            showColumns : true,
            // 是否显示刷新按钮
            showRefresh : false,
            //是否只能选择单行
            singleSelect:false,
            //是否启用点击选中行
            clickToSelect: true,
            //中文支持
            dataLocale:"zh-US",
            //默认加载条数
            pageSize : 20,
            //每页显示数据条数
            pageList : [20,200,500,1000],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            // 是否显示分页（*）
            pagination : true,
            paginationPreText:'上一页',
            paginationNextText:'下一页',
            columns: [
                {   
                    title: '出货通</br>知单',
                    field: 'DOCUMENT_NUM',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '销售</br>订单',
                    field: 'ORDER_NUMBER',
                    align: 'center',
                    valign: 'middle'
                }/*,{
                    title: '行号',
                    field: 'LINE_NUMBER',
                    align: 'center',
                    valign: 'middle'
                }*/, {
                    title: 'SO交期',
                    field: 'DATE_SCHEDULED',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: 'MO',
                    field: 'CUST_PO_NUMBER',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '产品</br>编码',
                    field: 'SEGMENT1',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '型号',
                    field: 'PROD_TYPE',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '客户机型',
                    field: 'CUSTOM_MODEL',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '需求</br>数量',
                    field: 'REQUESTED_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '工单号',
                    field: 'WIP_ENTITY_NAME',
                    align: 'center',
                    valign: 'middle'
                },/* {
                    title: 'XX',
                    field: 'TRANSACTION_DATE',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                    	if (row.TRANSACTION_DATE !='')
                    		return row.TRANSACTION_DATE.substr(0,10);
                        return '';
                    }
                },*/ {
                    title: '工单</br>状态',
                    field: 'STATUS_TYPE',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '生产</br>线',
                    field: 'ATTRIBUTE7',
                    align: 'center',
                    valign: 'middle'
                }
                ,{
                    title: '计划生产</br>日期',
                    field: 'SCHEDULED_START_DATE',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '计划出</br>货日期',
                    field: 'SCHEDULED_SHIP_DATE',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '行发运</br>数量',
                    field: 'CUR_SHIPPED_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '工单</br>数量',
                    field: 'START_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '完工</br>数量',
                    field: 'QUANTITY_COMPLETED',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '已发运</br>数量 ',
                    field: 'SHIP_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: 'ERP</br>发运数 ',
                    field: 'SHIPPED_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                } ,{
                    title: '生产</br>数量 ',
                    field: 'PROD_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '实际开工时间',
                    field: 'MIN_TRANSACTION_DATE',
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
                	offset: params.offset + 1,
                    limit: params.limit,
                    document_num:$('#document_num').val().trim(),
                    cust_po_number:$('#cust_po_number').val().trim(),
                    start_date:$('#start_date').val().trim(),
                    end_date:$('#end_date').val().trim()
                };
            }
        });
    }
}