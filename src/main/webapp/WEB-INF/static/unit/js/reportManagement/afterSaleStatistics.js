 //初始化控件
function init() { 
    //日期控件初始化
    $('#start_time,#end_time,#ship_date_start,#ship_date_end').datetimepicker({
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
	init();
    //初始化DataTable
	dataTable();
	$('#search_btn').on('click',function(){
		dataTable('refresh');
	}); 
	
		//导出excel
	  $("#exportExcel").on("click",function () { 
		  var status = $('#status').val().trim(); 
          var start_time = $('#start_time').val().trim();
          var end_time = $('#end_time').val().trim();
          var custom_name = $('#custom_name').val().trim();
          var model = $('#model').val().trim();
          var re_exp_no = $('#re_exp_no').val().trim();
          var product_barcode = $('#product_barcode').val().trim();
          var rep_work_order = $('#rep_work_order').val().trim();
          var sh_exp_no = $('#sh_exp_no').val().trim();
          var rep_people = $('#rep_people').val().trim();
          var phone = $('#phone').val().trim();
          var ship_date_start = $('#ship_date_start').val().trim();
          var ship_date_end = $('#ship_date_end').val().trim();
          var contact_name = $('#contact_name').val().trim();
	        window.open( basePath + 'afterSaleStatisticsController/exportExcel?status='
	        		+status+'&start_time='+start_time+'&end_time='+end_time
	        		+'&custom_name='+custom_name+'&model='+model
	        		+'&re_exp_no='+re_exp_no+'&product_barcode='+product_barcode
	        		+'&rep_work_order='+rep_work_order+'&sh_exp_no='+sh_exp_no
	        		+'&rep_people='+rep_people+'&phone='+phone
	        		+'&ship_date_start='+ship_date_start+'&ship_date_end='+ship_date_end
	        		+'&contact_name='+contact_name
	        );
	    }); 
    
});

//创建DataTable fa货
function dataTable(refresh) {
    var url = basePath + 'afterSaleStatisticsController/listByPage';
    var $finishedTable = $('#table');
    if(typeof refresh == 'undefined'){
    	 
        $finishedTable.bootstrapTable({ 
        	//url : url,
       	 	queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    status:$('#status').val(),
                    start_time:$('#start_time').val().trim(),
                    end_time:$('#end_time').val().trim(),
                    custom_name:$('#custom_name').val().trim(),
                    model:$('#model').val().trim(),
                    re_exp_no:$('#re_exp_no').val().trim(), 
                    product_barcode:$('#product_barcode').val().trim(),
                    rep_work_order : $('#rep_work_order').val().trim(),
                    sh_exp_no : $('#sh_exp_no').val().trim(),
                    rep_people : $('#rep_people').val().trim(),
                    phone : $('#phone').val().trim(),
                    ship_date_start:$('#ship_date_start').val().trim(),
                    ship_date_end:$('#ship_date_end').val().trim(),
                    contact_name:$('#contact_name').val().trim()
                    
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
            pageSize : 20,
            //每页显示数据条数
            pageList : [20,50,100,200,500],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            columns: [ {
                title: '维修单',
                field: 'rep_work_order',
                align: 'center',
                valign: 'middle', 
            	},{
                    title: '外箱联</br>系方式',
                    field: 'outer_contact',
                    align: 'center',
                    valign: 'middle', 
               },{
                   title: '内箱</br>送货单',
                   field: 'inter_note',
                   align: 'center',
                   valign: 'middle', 
              },{
                  title: '退货时间',
                  field: 'return_operation_time',
                  align: 'center',
                  valign: 'middle', 
              },{
                    title: '客户',
                    field: 'custom_name',
                    align: 'center',
                    valign: 'middle', 
               },{
                   title: '联系人',
                   field: 'contact_name',
                   align: 'center',
                   valign: 'middle', 
              },{
                  title: '联系电话',
                  field: 'phone',
                  align: 'center',
                  valign: 'middle', 
             },{
                 title: '客户地址 ',
                 field: 'address',
                 align: 'left',
                 valign: 'middle', 
            },{
                    title: '型号',
                    field: 'model',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '料号',
                    field: 'segment1',
                    align: 'center',
                    valign: 'middle', 
                }, {
                    title: '机身码',
                    field: 'product_barcode',
                    align: 'center',
                    valign: 'middle',  
                }, {
                    title: '退货件数',
                    field: 'return_qty',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '维修时间',
                    field: 'maintain_date',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '维修员',
                    field: 'rep_people',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '出货状态',
                    field: 'status',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '出货时间',
                    field: 'delivery_operation_time',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '出货物流公司',
                    field: 'sh_logi_com',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '出货物流单号',
                    field: 'sh_exp_no',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '退货物流单号',
                    field: 're_exp_no',
                    align: 'center',
                    valign: 'middle',  
                },{
                    title: '客户反馈',
                    field: 'customer_feedback',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '故障描述',
                    field: 'issue_description',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '故障原因',
                    field: 'rep_reason',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '备注',
                    field: 'remakr',
                    align: 'center',
                    valign: 'middle',  
                },{
                    title: '生产工单',
                    field: 'wip_name',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '生产日期',
                    field: 'prod_date',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '生产线别',
                    field: 'line_code',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '发运日期',
                    field: 'ship_date',
                    align: 'center',
                    valign: 'middle', 
                } ,{
                    title: '发运客户',
                    field: 'cus_name',
                    align: 'center',
                    valign: 'middle',  
                } ,{
                    title: '供应商快递信息',
                    field: 'supplier_delivery_info',
                    align: 'center',
                    valign: 'middle',  
                } ,{
                    title: '交仓库日期',
                    field: 'hand_over_date',
                    align: 'center',
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
                     status:$('#status').val(),
                     start_time:$('#start_time').val().trim(),
                     end_time:$('#end_time').val().trim(),
                     custom_name:$('#custom_name').val().trim(),
                     model:$('#model').val().trim(),
                     re_exp_no:$('#re_exp_no').val().trim(), 
                     product_barcode:$('#product_barcode').val().trim(),
                     rep_work_order : $('#rep_work_order').val().trim(),
                     sh_exp_no : $('#sh_exp_no').val().trim(),
                     rep_people : $('#rep_people').val().trim(),
                     phone : $('#phone').val().trim(),
                     ship_date_start:$('#ship_date_start').val().trim(),
                     ship_date_end:$('#ship_date_end').val().trim(),
                     contact_name:$('#contact_name').val().trim()
                 };
             },
        });
    } 
} 