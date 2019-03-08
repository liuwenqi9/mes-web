//配件包装 JS
$(function () {
    var $table = $('#partsPackingTable');
    var $check_parts = $("#check_parts");

    getDataTable();
    getDataTablePacking();

    //查询
    $("#partsPacking_btn").on("click",function () {
        var workOrderNumber = $("#workOrderNumber").val();
        if(workOrderNumber == null || workOrderNumber == ''){
            alert("工单号不能为空!");
            return;
        }
        getDataTable("refresh");
        getDataTablePacking("refresh");
        $check_parts.empty();
    });

    //监听dataTable的选中事件
    $table.on("check.bs.table",function (e,row) {
        //工单ID
        var wip_entity_id = row.WIP_ENTITY_ID;
        //物料ID
        var inventory_item_id = row.INVENTORY_ITEM_ID;
        //物料编码
        var segment1 = row.SEGMENT1;
        //需求数量
        var required_quantity = row.REQUIRED_QUANTITY;
        //已包装箱数
        var pack_quantity = row.PACK_QUANTITY;
        var html = getRow(wip_entity_id,inventory_item_id,segment1,required_quantity,pack_quantity);
        $check_parts.append(html);
        listerInputWriteNum($check_parts);
    });
    //监听全部选中事件
    $table.on("check-all.bs.table",function (e,row) {
        $.each(row,function (index, obj) {
            //工单ID
            var wip_entity_id = obj.WIP_ENTITY_ID;
            //物料ID
            var inventory_item_id = obj.INVENTORY_ITEM_ID;
            var $div = $check_parts.find("#"+inventory_item_id);
            if($div.length > 0){
                return true;
            }
            //物料编码
            var segment1 = obj.SEGMENT1;
            //需求数量
            var required_quantity = obj.REQUIRED_QUANTITY;
            //已包装箱数
            var pack_quantity = obj.PACK_QUANTITY;
            var html = getRow(wip_entity_id,inventory_item_id,segment1,required_quantity,pack_quantity);
            $check_parts.append(html);
        });
        listerInputWriteNum($check_parts);
    });
    //监听dataTable的取消选中事件
    $table.on("uncheck.bs.table",function (e,row) {
        //物料ID
        var inventory_item_id = row.INVENTORY_ITEM_ID;
        var $div = $check_parts.find("#"+inventory_item_id);
        $div.remove();
    });
    //监听全部取消选中事件
    $table.on("uncheck-all.bs.table",function (e,row) {
        $check_parts.empty();
    });

    /**
     * 确认
     */
    $("#partsPacking_btn_ok").on("click",function (e) {
        //工单ID
        var $wip_entity_ids = $check_parts.find("input[name='wip_entity_id']");
        //物料ID
        var $inventory_item_id = $check_parts.find("input[name='inventory_item_id']");
        //包装数量
        var $pacgingNum = $check_parts.find("input[name='pacgingNum']");
        var wip_entity_ids = [];
        var inventory_item_ids = [];
        var pacgingNums = [];
        for(var i = 0;i<$wip_entity_ids.length;i++){
            wip_entity_ids.push($wip_entity_ids[i].value);
            inventory_item_ids.push($inventory_item_id[i].value);
            var pacgingNum = $pacgingNum[i].value;
            if(pacgingNum == null || pacgingNum == ''){
                alert("请输入包装数量!");
                return;
            }
            pacgingNums.push(pacgingNum);
        }
        //请求后台，保存数据
        $.ajax({
            type : 'post',
            url : basePath + 'parts/packing/savePackingNumber',
            data : {
                wipEntityIds : wip_entity_ids.join(","),
                inventoryItemIds : inventory_item_ids.join(","),
                pacgingNums : pacgingNums.join(",")
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                alert($msg.message);
                if($msg.success){
                    $("#partsPacking_btn").click();
                }
            }
        });
    });
    
    //打印 
    $("#print_btn").on("click",function () {
        //获取选中的行
        var selections = $('#partsTable').bootstrapTable('getSelections');
        if(selections.length == 0){
            alert("请选择数据");
            return;
        }
        var packing_barcode_ids = [];
        for(var i = 0;i < selections.length;i++){
            packing_barcode_ids.push(selections[i].PACKING_BARCODE_ID);
        }
        $.ajax({
            type : 'post',
            url : basePath + 'parts/packing/printerPacking',
            data : {
                packingBarcodeIds : packing_barcode_ids.join(",")
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                if(!$msg.success){
                    alert($msg.message);
                    return;
                }
                window.open($msg.message);
            }
        });
    });
});

/**
 *
 * @param wip_entity_id 工单ID
 * @param inventory_item_id 物料ID
 * @param segment1 物料编码
 * @param required_quantity 需求数量
 * @param pack_quantity 已包装箱数
 */
function getRow(wip_entity_id,inventory_item_id,segment1,required_quantity,pack_quantity) {
    var html = '<div id="'+inventory_item_id+'" class="form-inline" style="margin-top: 10px;">';
        if(segment1.length == 12){
            segment1 += "&nbsp;&nbsp;"
        }
        html += '<span style="margin-left: 7%;">'+segment1+'</span>';
        var packingNum = (Number(required_quantity)-Number(pack_quantity))+"";
        if(packingNum.length == 4){
            packingNum += "&nbsp;";
        }
        if(packingNum.length == 3){
            packingNum += "&nbsp;&nbsp;";
        }
        if(packingNum.length == 2){
            packingNum += "&nbsp;&nbsp;&nbsp;&nbsp;";
        }
        if(packingNum.length == 1){
            packingNum += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        }
        html += '<span style="margin-left: 17%;">'+packingNum+'</span>';
        html += '<input type="hidden" name="wip_entity_id" value="'+wip_entity_id+'" />';
        html += '<input type="hidden" name="inventory_item_id" value="'+inventory_item_id+'" />';
        html += '<input type="hidden" name="required_quantity" value="'+required_quantity+'" />';
        html += '<input type="hidden" name="pack_quantity" value="'+pack_quantity+'" />';
        html += '<input class="form-control" name="pacgingNum" placeholder="包装数量" style="margin-left: 25%;width: 80px;" />';
        html += '</div>';
    return html;
}

/**
 * 监听包装数量input输入框只能输入数字
 * @param $div
 */
function listerInputWriteNum($div) {
    $div.find("input").off("keyup");
    $div.find("input").on("keyup",function () {
        var $this = $(this);
        //需求数量
        var required_quantity = $this.prev().prev().val();
        //已包装箱数
        var pack_quantity = $this.prev().val();
        //当前输入数据
        var val = $this.val();
        if(Number(val) == 0){
            alert("包装数量不能为0!");
            return;
        }
        if(((Number(pack_quantity)+Number(val))-Number(required_quantity)) > 0){
            alert("包装数量+已包装数量必须≦需求数量!");
            this.value=val.substring(0,val.length-1);
            return;
        }
        this.value=this.value.replace(/\D/g,'');
    });
}

/**
 * 创建dataTable
 */
function getDataTable(refresh) {
    var url = basePath + 'parts/packing/queryAll';
    var $table = $('#partsPackingTable');
    if(eGdUtil.isEmpty(refresh)){
        $table.bootstrapTable({
            queryParams : function(params) {
                return {
                    workOrderNumber:$('#workOrderNumber').val()
                };
            },
            //是否分页
            pagination : false,
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
            height: 350,
            //是否只能选择单行
            singleSelect:false,
            //是否启用点击选中行
            clickToSelect: true,
            //中文支持
            dataLocale:"zh-US",
            // 是否启用排序
            sortable : true,
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            // 是否启用排序
            sortable : false,
            // 排序方式
            sortOrder : "desc",
            columns: [
                {
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '物料编码',
                    field: 'SEGMENT1',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '需求数量',
                    field: 'REQUIRED_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '已包装数量',
                    field: 'PACK_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '描述',
                    field: 'DESCRIPTION',
                    align: 'left',
                    valign: 'middle'
                }
            ]
        });
    }else {
        $table.bootstrapTable('refreshOptions',{
            url : url,
            queryParams : function(params) {
                return {
                    workOrderNumber:$('#workOrderNumber').val()
                };
            }
        });
    }
}

//包装箱查询
function getDataTablePacking(refresh) {
    var url = basePath + 'parts/packing/queryPackingAll';
    var $table = $('#partsTable');
    if(eGdUtil.isEmpty(refresh)){
        $table.bootstrapTable({
            queryParams : function(params) {
                return {
                    workOrderNumber:$('#workOrderNumber').val()
                };
            },
            //是否分页
            pagination : false,
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
            height: 245,
            //是否只能选择单行
            singleSelect:false,
            //是否启用点击选中行
            clickToSelect: true,
            //中文支持
            dataLocale:"zh-US",
            // 是否启用排序
            sortable : true,
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            // 是否启用排序
            sortable : false,
            // 排序方式
            sortOrder : "desc",
            columns: [
                {
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '工单号',
                    field: 'WIP_ENTITY_NAME',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '包装号',
                    field: 'BARCODE_TEXT',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '包装数量',
                    field: 'PACK_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '打印状态',
                    field: 'PRINT_FLAG',
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
                    workOrderNumber:$('#workOrderNumber').val()
                };
            }
        });
    }
}