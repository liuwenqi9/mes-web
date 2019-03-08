//发运清单条码管理

$(function () {
    dataTable();

    //查询
    $("#shipment_search_btn").on("click",function () {
        dataTable("refresh");
    });

    //条码打印
    $("#shipment_print_btn").on("click",function () {
        var selectRows = $('#shipmentTable').bootstrapTable('getSelections');
        if(selectRows.length == 0){
            alert("请选择数据!");
            return;
        }
        var codes = [];
        var citys = [];
        var names = [];
        for(var i = 0;i<selectRows.length;i++){
            codes.push(selectRows[i].barcode_text);
            var city = selectRows[i].city;
            var name = selectRows[i].account_name;
            if(typeof city == 'undefined'){
                city = '-';
            }
            if(typeof name == 'undefined'){
                name = '-';
            }
            citys.push(city);
            names.push(name);
        }
        $.ajax({
            type : 'post',
            url : basePath + 'shipment/printCode',
            data : {
                codes : codes,
                citys : citys,
                names : names
            },
            success : function(msg){
                msg = $.parseJSON(msg);
                var message = msg.message;
                if(msg.success){
                    window.open(message);
                }else {
                    alert(message);
                }
            }
        });
    })
});

//创建dataTable
function dataTable(refresh) {
    var url = basePath + 'shipment/findPage';
    var $shipmentTable = $('#shipmentTable');
    if(typeof refresh == 'undefined'){
        $shipmentTable.bootstrapTable({
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    barcode_text : '',
                    logistics : '',
                    packing_type : $("#packing_type").val(),
                    ship_print_flag : ''
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
            showColumns : true,
            // 是否显示刷新按钮
            showRefresh : false,
            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            //height: 30,
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
            pageSize : 100,
            //每页显示数据条数
            pageList : [100,200,500,1000],
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
                    title: '包装条码',
                    field: 'barcode_text',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '数量',
                    field: 'quantity',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '打印标志',
                    field: 'ship_print_flag',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '物流',
                    field: 'logistics',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '客户名称',
                    field: 'account_name',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '地址',
                    field: 'address1',
                    align: 'left',
                    valign: 'middle'
                }
            ]
        });
    }else {
        $shipmentTable.bootstrapTable('refreshOptions',{
            url : url,
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit,
                    barcode_text : $("#barcode_text").val(),
                    logistics : encodeUnicode($("#logistics").val()),
                    packing_type : $("#packing_type").val(),
                    ship_print_flag : $("#ship_print_flag").val()
                };
            }
        });
    }
}

// 转为unicode 编码
function encodeUnicode(str) {
    var res = [];
    for ( var i=0; i<str.length; i++ ) {
        res[i] = ( "00" + str.charCodeAt(i).toString(16) ).slice(-4);
    }
    return "\\u" + res.join("\\u");
}