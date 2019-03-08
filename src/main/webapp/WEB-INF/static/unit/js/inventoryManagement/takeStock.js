//全局变量
var path = "";
var name = "";

function getpdTable(refresh) {
    var $pdTable = $('#pdTable');
    if (typeof refresh == 'undefined') {
        $pdTable.bootstrapTable("destroy");
        $pdTable.bootstrapTable({
            url: basePath + 'stocktakingController/getpdHeadersList',
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    beginDate: $("#begin_date").val().trim(),
                    endDate: $("#end_date").val().trim(),
                    status: $("#status").val().trim()
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
            singleSelect: false,
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
            pageSize: 20,
            //每页显示数据条数
            pageList: [20, 50, 100],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache: false,
            columns: [ {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            },{
                title: '盘点序列号',
                field: 'PD_NUMBER',
                align: 'center',
                valign: 'middle'
            }, {
                title: '子库',
                field: 'SUBINVENTORY_CODE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '开始货位',
                field: 'LOCATTION_CODE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '结束货位',
                field: 'LOCATTION_T',
                align: 'center',
                valign: 'middle'
            }, {
                title: '物料编码',
                field: 'ITEM',
                align: 'center',
                valign: 'middle'
            }, {
                title: '盘点时间',
                field: 'PD_DATE',
                align: 'center',
                valign: 'middle'
            }, {
                title: '状态',
                field: 'STATUS_FLAG',
                align: 'center',
                valign: 'middle'
            }, {
                title: '盘点汇总信息',
                field: 'HEADER_ID',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    var url = basePath + 'stocktakingController/toIterationSummary?headerId=' + row.HEADER_ID + '&organizationId=' + row.ORGANIZATION_ID;
                    var e = '<a href="' + url + '" >汇总信息</a>';
                    return e;
                }
            }, {
                title: '盘点明细信息',
                field: 'HEADER_ID',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    var url = basePath + 'stocktakingController/toDetailList?headerId=' + row.HEADER_ID + '&organizationId=' + row.ORGANIZATION_ID;
                    var e = '<a href="' + url + '">明细信息</a> ';
                    return e;
                }
            }
            ]
        });
    } else {
        $pdTable.bootstrapTable('refreshOptions', {
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    beginDate: $("#begin_date").val().trim(),
                    endDate: $("#end_date").val().trim(),
                    status: $("#status").val().trim()
                };
            }
        });
    }
}

$(function () {
    //时间控件初始化
    $('#begin_date,#end_date').datetimepicker({
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
    getpdTable();

    //盘点历史记录查询
    $('#search_btn').on('click', function () {
        getpdTable('refresh');

    });

    //生成盘点报表
    $('#generate_btn').on('click', function () { 
        $.ajax({
            type: 'post',
            url: basePath + 'stocktakingController/callGeneratePD',
            //async: false, //同步  默认是true异步
            data: {
                subinventoryCode: $('#subpool').val(),
                locationCodeStart: $('#location_start').val(),
                locationCodeEnd: $('#location_end').val(),
                item: $('#item').val()
            },
            success: function (msg) {
                var $msg = $.parseJSON(msg);

                if ($msg.xStatus == 'S') {
                    alert('生成成功！');
                    getpdTable("refresh");
                } else {
                    alert($msg.xMessage);
                }
            }
        });


    });
    //更新 选择的盘点号到最新的盘点号下面
    $('#pd_number_udpate').on('click', function () { 
    	var pdNumbers =[];
    	var rows = $('#pdTable').bootstrapTable('getSelections');
    	if(rows.length<1){
    		alert('请选择数据！');
    		return;
    	}
    	for(var i=0;i<rows.length;i++){
    		pdNumbers.push(rows[i].PD_NUMBER);
    	}
    	
    	 $.ajax({
             type: 'post',
             url: basePath + 'stocktakingController/updatePdNumberToNewPdNumber', 
             data: {
                 pdNumbers:pdNumbers.join(',')
             },
             success: function (msg) {
                 var $msg = $.parseJSON(msg); 
                 if ($msg.success) {
                     alert($msg.message);
                     getpdTable("refresh");
                 } else {
                	 alert($msg.message);
                 }
             }
         });
    	
    }); 
    
    
    
    /******************选中相关代码 start******************/
    //选择列表
    $("#subpool_btn,#location_start_btn,#location_end_btn,#item_btn").on("click", function (e) {
    			$("#subpoolSearch").val('');
				var $myModalLabel = $("#myModalLabel");
				var subpool1 = $('#subpool').val().trim();
				switch (e.target.id) {
				case 'subpool_btn':
					path = basePath + 'stocktakingController/getSubpoolList';
					name = "子库";
					$myModalLabel.html("子库查询");
					break;
				case 'location_start_btn': 
					path = basePath
							+ 'stocktakingController/getLocationList?subpool1='
							+ subpool1;
					name = "开始货位";
					$myModalLabel.html("货位查询"); 
					break;
				case 'location_end_btn': 
					path = basePath
							+ 'stocktakingController/getLocationList?subpool1='
							+ subpool1;
					name = "结束货位";
					$myModalLabel.html("货位查询"); 
					break;
				case 'item_btn': 
					path = basePath + 'stocktakingController/getItemList';
					name = "物料编码";
					$myModalLabel.html("物料查询"); 
					break;
				}

				$("#subpoolSearch").attr("placeholder", name);
				// 打开模态框
				$("#myModal").modal();
				getSubpool();// 子库查询

			});

    /**
	 * 列表搜索
	 */
    $("#search").on("click", function () {
        getSubpool("refresh");
    });

    /**
     * 确认选择
     */
    $("#choose_ok").on("click", function () {
        var selectRows = $('#chooseTable').bootstrapTable('getSelections');
        if (selectRows.length == 0) {
            alert("请选择数据!");
            return;
        }
        var value = selectRows[0].DATA1;
        if (name == '子库') {
            $("#subpool").val(value);
        }
        if (name == "开始货位") {
            $("#location_start").val(value);
        }
        if (name == "结束货位") {
            $("#location_end").val(value);
        }
        if (name == "物料编码") {
            $("#item").val(value);
        }
        //关闭模态框
        $("#myModal").modal("hide");
        $("#subpoolSearch").val('');
    });
    /******************选中相关代码 end******************/
});


//选择框列表 查询
function getSubpool(refresh) {
    var $chooseTable = $('#chooseTable');
    if (typeof refresh == 'undefined') {
        $chooseTable.bootstrapTable("destroy");
        $chooseTable.bootstrapTable({
            url: path,
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    subpool: $("#subpoolSearch").val().trim()
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
            pageList: [10, 20],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache: false,
            columns: [
                {
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: name,
                    field: 'DATA1',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '描述',
                    field: 'DATA2',
                    align: 'left',
                    valign: 'middle'
                }
            ]
        });
    } else {
        $chooseTable.bootstrapTable('refreshOptions', {
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    subpool: $("#subpoolSearch").val().trim()
                };
            }
        });
    }
}