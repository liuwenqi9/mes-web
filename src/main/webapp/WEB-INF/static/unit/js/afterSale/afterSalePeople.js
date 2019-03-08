
$(function(){
	createDataTable();
	
	//时间控件初始化

  /*  $('#activeDate_addModal,#activeDate_editModal').datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        autoclose: 1,
        minView: 0,
        minuteStep:1

    });*/
	$('#activeDate_editModal').datetimepicker({
		language:  'zh-CN',
		weekStart: 1,
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0,
		//语言选择中文
		format:"yyyy-mm-dd "
	});
	
	//查询btn
	$('#search').on('click',function(){
		 createDataTable("refresh");
	});


    //update
    $('#add').on('click',function(){
            //clear
            $('#flexValue_addModal').val('');
            $('#repPeople_addModal').val('');
            // $('#activeDate_addModal').val('');
            $('#addModal').modal("show");
    });

    //save1 add to save
    $('#save1').on('click',function(){
        var flexValue_addModal = $('#flexValue_addModal').val().trim();
        var repPeople_addModal = $('#repPeople_addModal').val().trim();
        // var activeDate_addModal = $('#activeDate_addModal').val().trim();
        if(flexValue_addModal==""){$.messager.alert("提示","请填写序列号","info"); return;}
        if(repPeople_addModal==""){ $.messager.alert("提示","请填写维修人员","info");return;}
        $.ajax({
                type: 'post',
                url: basePath + 'afterSaleSummaryController/peopleAdd',
                data: {
                    flexValue: flexValue_addModal,
                    repPeople: repPeople_addModal
                },
                success: function (msg) {
                    var $msg = $.parseJSON(msg);
                    if ($msg.success) {
                        $.messager.alert("提示", "保存成功", "info", function () {
                            $('#flexValue_addModal').val('');
                            $('#repPeople_addModal').val('');
                            // $('#activeDate_addModal').val('');
                            createDataTable("refresh");
                            $('#addModal').modal("hide");
                        });
                    } else {
                        $.messager.alert("提示", $msg.message, "info");
                    }
                }
            });
    });


	//update
	$('#edit').on('click',function(){
		var rows = $('#table').bootstrapTable('getSelections');
		if(rows.length>0){
            row = rows[0];
			//clear
            $('#id_editModal').val('');
            $('#flexValue_editModal').val('');
            $('#repPeople_editModal').val('');
            $('#activeDate_editModal').val('');
            // into
            $('#id_editModal').val(row.id);
            $('#flexValue_editModal').val(row.flexValue);
            $('#repPeople_editModal').val(row.repPeople);
            $('#activeDate_editModal').val(row.activeDate);

            $('#editModal').modal("show");

		}else {
			alert("请选择数据");
		}  
	});
	//save2 update to save
	$('#save2').on('click',function(){
		var rows = $('#table').bootstrapTable('getSelections');
        var id_editModal = $('#id_editModal').val().trim();
        var flexValue_editModal = $('#flexValue_editModal').val().trim();
		var repPeople_editModal = $('#repPeople_editModal').val().trim();
		var activeDate_editModal = $('#activeDate_editModal').val().trim();
        if(flexValue_editModal==""){ $.messager.alert("提示","请填写序列号","info");return;}
		$.ajax({
            type : 'post',
            url : basePath + 'afterSaleSummaryController/peopleUpdate',
            data : {
            	id :id_editModal,
                flexValue :flexValue_editModal,
                repPeople :repPeople_editModal,
                activeDate :activeDate_editModal
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                if($msg.success){
                    $.messager.alert("提示","保存成功","info",function () {
                        $('#id_editModal').val('');
                        $('#flexValue_editModal').val('');
                        $('#repPeople_editModal').val('');
                        $('#activeDate_editModal').val('');
               		 	createDataTable("refresh");
               		 	$('#editModal').modal("hide");
                    });
                }else {
                    $.messager.alert("提示",$msg.message,"info");
                }
            }
        });
	});
});

function clearNoNum(obj){
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
        obj.value= parseFloat(obj.value);
    }
}

function peopleValidation(obj) {
    $.ajax({
        type : 'post',
        url : basePath + 'afterSaleSummaryController/peopleValidation',
        data : {
                repPeople:obj.value
        },
        success : function (msg) {
            var $msg = $.parseJSON(msg);
            if(!$msg.success){ //存在
                $.messager.alert("提示",$msg.message,"info");
            }else {
            }
        }
    });
}


//创建dataTable
function createDataTable(mark){
	var url = basePath + 'afterSaleSummaryController/getAfterSalePeopleList';
	var $table = $('#table');
	if(typeof mark == 'undefined'){
		$table.bootstrapTable({
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit, 
					repPeople : $("#repPeople").val().trim()
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
			pageSize : 10,
			//每页显示数据条数
			pageList : [10,20,50],
			columns: [
				{
					checkbox: true,
					align: 'center',
					valign: 'middle'
				}, {
                    title: '序列号',
                    field: 'flexValue',
                    align: 'center',
                    valign: 'middle'
                }, {
					title: '维修人员',
					field: 'repPeople',
					align: 'center',
					valign: 'middle'
				},{
						title: '无效时间',
						field: 'activeDate',
						align: 'center',
						valign: 'middle'
				},{
                    title: '创建时间',
                    field: 'createDate',
                    align: 'center',
                    valign: 'middle'
                },{
					title: '创建人',
						field: 'createLoginName',
						align: 'center',
						valign: 'middle'
				},{
                    title: '更新时间',
                    field: 'updateDate',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '更新人',
                    field: 'updateLoginName',
                    align: 'center',
                    valign: 'middle'
                }
			]
		});
	}else {
	    //refresh  refreshOptions
		$table.bootstrapTable('refresh',{
            url : url,
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
                    repPeople : $("#repPeople").val().trim()
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

