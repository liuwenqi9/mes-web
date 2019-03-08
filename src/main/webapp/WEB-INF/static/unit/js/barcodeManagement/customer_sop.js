//发运清单条码管理

$(function () {
    createMoTable();
    //MO要求
    var $mo_btn = $("#mo_btn");
    //订单内容
    var $order_btn = $("#order_btn");

    var $btns = [$mo_btn, $order_btn];

    /**
     * 列表选择
     */
    for(var j = 0;j < $btns.length;j++){
        $btns[j].on("click",function () {
            for(var i = 0;i < $btns.length;i++){
                var $btn = $btns[i];
                $btn.removeClass("btn-success");
                $btn.addClass("btn-info");
                if($btn.attr("selected") == 'selected'){
                    var btn_id = $btn.attr("id");
                    var index = btn_id.indexOf("_btn");
                    //得到当前选择table的id
                    var tableId = btn_id.substring(0,index)+"Table";
                    var $table = $("#"+tableId);
                    $table.bootstrapTable("destroy");
                }
                $btn.removeAttr("selected");
            }
            var $this = $(this);
            $this.removeClass("btn-info");
            $this.addClass("btn-success");
            $this.attr("selected","true");
            var btn_id = $this.attr("id");
            var index = btn_id.indexOf("_btn");
            //得到当前选择table的id
            var tableId = btn_id.substring(0,index)+"Table";
            var $table = $("#"+tableId);
            $table.show();
            switch (tableId){
                case 'moTable':
                    createMoTable();
                    break;
                case 'orderTable':
                    createOrderTable();
                    break;
            }
            $("#search_btn").click();
        })
    }
//查询
    $("#down_btn").on("click", function () {
        var cust_po_number =  $('#cust_po_number').val().trim();
        if(cust_po_number ==""){
            showInfoMsg("MO单号不能为空");
            $('#cust_po_number').focus();
            return;
        }
        $.ajax({
            cache:false,
            async:false,
            type : 'post',
            url : basePath + 'customerSopController/cusHeader?tm='+new Date(),
            data : {
                cust_po_number : $('#cust_po_number').val().trim()
            },
            success : function(msg){
                msg = $.parseJSON(msg);
                var message = msg.message;
                if(msg.success){
                    var cusHeader = msg.result;

                }else {
                    showInfoMsg(msg.message);
                    return;
                }
            }
        });
        window.open(basePath + 'customerSopController/exportExcel?cust_po_number='+cust_po_number);

    });

    //查询
    $("#search_btn").on("click", function () {
        //查询表单table，先异步请求数据头，
        var cust_po_number =  $('#cust_po_number').val().trim();
        if(cust_po_number ==""){
            showInfoMsg("MO单号不能为空");
            $('#cust_po_number').focus();
            return;
        }
        var falg ;
        $.ajax({
            cache:false,
            async:false,
            type : 'post',
            url : basePath + 'customerSopController/cusHeader?tm='+new Date(),
            data : {
                cust_po_number : $('#cust_po_number').val().trim()
            },
            beforeSend:function () {
                $('#party_number').val('');//客户编号
                $('#party_name').val('');//客户名称
                $('#sop_yh').val('');//验货标准
                $('#yh_date').val('');//验货日期
                $('#sop_rh').val('');//要求ROHS
                $('#sop_box').val('');//箱号连接
                $('#your_po').val('');//PO
                $('#our_pi').val('');//PI
                $('#sop_battery').val('');//带电池
                $('#ps_number').val('');//评审单号
                $('#ps_version').val('');//版本号
                $('#vs_date').val('');//版本日期
                $('#country').val('');//销售国家
                $('#creation_date').val('');//下单日期
                $('#remark').val('');//
            },
            success : function(msg){
                msg = $.parseJSON(msg);
                var message = msg.message;
                if(msg.success){
                    var cusHeader = msg.result;
                    $('#party_number').val(cusHeader.party_number);//客户编号
                    $('#party_name').val(cusHeader.party_name);//客户名称
                    $('#sop_yh').val(cusHeader.sop_yh);//验货标准
                    $('#yh_date').val(cusHeader.yh_date);//验货日期
                    $('#sop_rh').val(cusHeader.sop_rh);//要求ROHS
                    $('#sop_box').val(cusHeader.sop_box);//箱号连接
                    $('#your_po').val(cusHeader.your_po);//PO
                    $('#our_pi').val(cusHeader.our_pi);//PI
                    $('#sop_battery').val(cusHeader.sop_battery);//带电池
                    $('#ps_number').val(cusHeader.ps_number);//评审单号
                    $('#ps_version').val(cusHeader.ps_version);//版本号
                    $('#vs_date').val(cusHeader.vs_date);//版本日期
                    $('#country').val(cusHeader.country);//销售国家
                    $('#creation_date').val(cusHeader.creation_date);//下单日期
                    $('#remark').val(cusHeader.remark);//
                    falg = false;
                }else {
                    showInfoMsg(msg.message);
                    falg = true;
                }
            }
        });
        if(falg){
            return;
        }
        for (var i = 0; i < $btns.length; i++) {
            var $btn = $btns[i];
            var btn_id = $btn.attr("id");
            var selected = $btn.attr("selected");
            if (selected == 'selected') {
                var index = btn_id.indexOf("_btn");
                //得到当前选择table的id
                var tableId = btn_id.substring(0, index) + "Table";
                var $table = $("#" + tableId);
                $table.bootstrapTable('refreshOptions', {
                    url: basePath + $table.attr("url"),
                    queryParams: function (params) {
                        return tableParam(params);
                    }
                });
            }
        }
    });

});

//请求参数封装
function tableParam(params) {
    //评审单号
    var cust_po_number = $("#cust_po_number").val().trim();
    return {
        offset: params.offset + 1,
        limit: params.limit,
        cust_po_number: cust_po_number,
    };
}

function openData(file) {
    window.open(basePath + "customerSopController/getFiles?FILE_ID=" + file + "&tm=" + Date.parse(new Date()));
}

//创建MO要求Table
function createMoTable() {
    var $moTable = $('#moTable');
    $moTable.bootstrapTable(
        {
            queryParams: function (params) {
                return tableParam(params);
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
            // showColumns: true,
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
            pageSize: 100,
            //每页显示数据条数
            pageList: [100, 200, 500, 1000],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache: false,
            // 是否显示分页（*）
            pagination: true,
            // 是否启用排序
            sortable: false,
            // 排序方式
            sortOrder: "desc",
            // 初始化加载第一页，默认第一页
            // 我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
            //pageNumber:1,
            columns: [
                /*{
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                },*/
                {
                    title: '序号',
                    field: 'line_num',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: 'MO',
                    field: 'cust_po_number',
                    align: 'left',
                    valign: 'middle'
                }, {
                    title: '类型',
                    field: 'type',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '需要类型',
                    field: 'others_type',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '详细内容',
                    field: 'comments',
                    align: 'left',
                    valign: 'middle'
                }, {
                    title: '附件',
                    field: 'file_name',
                    align: 'left',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        if (value != null && value != "undefined") {
                            return "<a href='javascript:;' onclick='openData(" + row.file_id + ")'>" + value + "</a>";
                        }
                        else {
                            return "";
                        }


                    }
                }
            ]
        });

}
//创建订单内容Table
function createOrderTable() {
    var $orderTable = $('#orderTable');
    $orderTable.bootstrapTable(
        {
            queryParams: function (params) {
                return tableParam(params);
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
            // showColumns: true,
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
            pageSize: 100,
            //每页显示数据条数
            pageList: [100, 200, 500, 1000],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache: false,
            // 是否显示分页（*）
            pagination: true,
            // 是否启用排序
            sortable: false,
            // 排序方式
            sortOrder: "desc",
            // 初始化加载第一页，默认第一页
            // 我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
            //pageNumber:1,
            columns: [
               /* {
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                },*/
                {
                    title: 'SO号',
                    field: 'order_number',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '行号',
                    field: 'line_num',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '编码',
                    field: 'item',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '数量',
                    field: 'ordered_qty',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '计划发运日期',
                    field: 'schedule_ship_date',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '型号',
                    field: 'item_model',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '客户机型',
                    field: 'customer_model',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: 'LOGO',
                    field: 'logo',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '状态',
                    field: 'status',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '自制/外购',
                    field: 'makeorbuy',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '描述',
                    field: 'description',
                    align: 'left',
                    valign: 'middle'
                },
            ]
        });

}