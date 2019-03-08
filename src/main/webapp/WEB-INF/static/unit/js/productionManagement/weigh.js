 

function getWeighTable(refresh) {
    var $pdTable = $('#weighTable');
    if (typeof refresh == 'undefined') {
        $pdTable.bootstrapTable("destroy");
        $pdTable.bootstrapTable({
            url: basePath + 'weighMaintenanceController/getList',
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    startDate: $("#start_date").val().trim(),
                    endDate: $("#end_date").val().trim(),
                    name: $("#name").val().trim(),
                    segment1: $("#segment1").val().trim()
                };
            },
            //分页方式：client客户端分页，server服务端分页（*）
            sidePagination: "server",
            //是否分页
            pagination: true,
            //请求方法 post方式会自动把参数封装为json格式
            method: 'get',
            //服务器返回的数据类型
            //dataType : "json",
            //是否显示行间隔色
            striped: true,
            //是否显示所有的列
            showColumns: false,
            // 是否显示刷新按钮
            showRefresh: false,
            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            //height: 30,
            //是否只能选择单行
            singleSelect: true,
            //是否启用点击选中行
            clickToSelect: true,
            //中文支持
            dataLocale: "zh-US",
            // 是否启用排序
            sortable: true,
            // 排序方式
            sortOrder: "desc",
            //头信息
            //toolbar: '#productBarcode_toolbar',
            //默认加载条数
            pageSize: 10,
            //每页显示数据条数
            pageList: [10,20, 50, 100],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache: false,
            columns: [{
                title: '工单号',
                field: 'wip_entity_name',
                align: 'center',
                valign: 'middle'
            }, {
                title: '工单状态',
                field: 'status_type',
                align: 'center',
                valign: 'middle'
            }, {
                title: '装备件',
                field: 'segment1',
                align: 'center',
                valign: 'middle'
            }, {
                title: '型号',
                field: 'prod_type',
                align: 'center',
                valign: 'middle'
            }, {
                title: '标准包装数',
                field: 'b_quantity',
                align: 'center',
                valign: 'middle'
            }, {
                title: '标准毛重(KG)',
                field: 's_gross_weight',
                align: 'center',
                valign: 'middle'
            }, {
                title: '实际毛重(KG)',
                field: 'gross_weight',
                align: 'center',
                valign: 'middle'
            }, {
                title: '外箱重量',
                field: 'pack_weight',
                align: 'center',
                valign: 'middle'
            }, {
                title: '维护日期',
                field: 'creation_date',
                align: 'center',
                valign: 'middle'
            }, {
                title: '最后维护日期',
                field: 'last_update_date',
                align: 'center',
                valign: 'middle'
            }  
            ]
        });
    } else {
        $pdTable.bootstrapTable('refreshOptions', {
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    startDate: $("#start_date").val().trim(),
                    endDate: $("#end_date").val().trim(),
                    name: $("#name").val().trim(),
                    segment1: $("#segment1").val().trim()
                };
            }
        });
    }
}

$(function () {
	
    //时间控件初始化
    $('#start_date,#end_date').datetimepicker({
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
    //初始化表格
    getWeighTable();

    //查询
    $('#search_btn2').on('click', function () {
    	getWeighTable('refresh');

    });
 
    //保存
    $('#save_btn').on('click', function () {
    	 var wip_entity_name = $('#wip_entity_name').val().trim();
    	 if (wip_entity_name==null || wip_entity_name=='') {
             alert("请点击确认！");
             return;
         } 
         var s_gross_weight = $("#s_gross_weight").val().trim(); 
         var gross_weight = $("#gross_weight").val().trim();
         var pack_weight = $("#pack_weight").val().trim();
         var wip_entity_id = $("#wip_entity_id").val().trim();
    	 //save 
    	 $.ajax({
             type : 'post',
             url : basePath+'weighMaintenanceController/saveOrUpdate',
             data : {
            	 wip_entity_name:wip_entity_name,
            	 s_gross_weight:s_gross_weight,
            	 gross_weight:gross_weight,
            	 pack_weight:pack_weight,
            	 wip_entity_id:wip_entity_id
             },
             success : function (msg) {
                 var $msg = $.parseJSON(msg);
                 if($msg.success){
	                	showInfoMsg($msg.message);
	                	//清空列表
	                	$("#wip_entity_name1").val('');
	                	$("#wip_entity_name").val('');
	                    $("#s_gross_weight").val(''); 
	                    $("#gross_weight").val('');
	                    $("#pack_weight").val('');
	                    $("#wip_entity_id").val('');
	                }else{
	                	showErrorMsg($msg.message);
	                }
             }
         });

    });

    	//导出excel
	  $("#export").on("click",function () { 
		  var startDate= $("#start_date").val().trim();
          var endDate= $("#end_date").val().trim();
          var name= $("#name").val().trim();
          var segment1 = $("#segment1").val().trim();
	        window.open(basePath+"weighMaintenanceController/exportExcel" +
	        		"?startDate=" +startDate+"&endDate="+endDate+"&name="+name+"&segment1="+segment1);
	    }); 
	  
      //确认
	  $('#comfrm').on('click',function(){
		  var input = $("#wip_entity_name1").val();
		  if(input==null||input==''){
			  alert("请输入工单！");
	             return;
		  } 
		  $.ajax({
	             type : 'get',
	             url : basePath+'weighMaintenanceController/getObjectByWipEntityName?wip_entity_name='+input, 
	             success : function (msg) {
	                 var $msg = $.parseJSON(msg);
	                 if($msg.success){ 
		                	//添加列表
		                	$("#wip_entity_name").val($msg.result.DATA1);
		                    $("#s_gross_weight").val($msg.result.DATA2); 
		                    $("#gross_weight").val($msg.result.DATA3);
		                    $("#wip_entity_id").val($msg.result.DATA4);
		                    $("#pack_weight").val($msg.result.DATA5);
		                }else{
		                	alert($msg.message);
		                }
	             }
	         }); 
	  });
	  
	  //清空
	  $('#clear').on('click',function(){
		//清空列表
		  $("#wip_entity_name1").val('');
      	 $("#wip_entity_name").val('');
          $("#s_gross_weight").val(''); 
          $("#gross_weight").val('');
          $("#pack_weight").val('');
          $("#wip_entity_id").val('');
	  });
    
    
	  /*var value1 = selectRows[0].DATA1;//工单
      var value2 = selectRows[0].DATA2;//标准毛重
      var value3 = selectRows[0].DATA3;//实际毛重 
      var value4 = selectRows[0].DATA4;//实际毛重 
      var value5 = selectRows[0].DATA5;//实际毛重 
      $("#wip_entity_name").val(value1);
      $("#s_gross_weight").val(value2); 
      $("#gross_weight").val(value3);
      $("#wip_entity_id").val(value4);
      $("#pack_weight").val(value5);*/ 
 });
 