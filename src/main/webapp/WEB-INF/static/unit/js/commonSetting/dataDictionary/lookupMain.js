/**
 *
 */
$(function () {

    // 当前选择的类型
    var currTypeName = '';


    // 分页条数
    var lookupTablePageSize = getServerParam(basePath, 'PAGE_SIZE');


    // 构造表格
    $('#lookup_type_table').datagrid({
        title: '数据类型',
        url: basePath + 'lookupController/getLookupTypeList',
        method: 'post',
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        pageSize: lookupTablePageSize / 2,
        pageList: [lookupTablePageSize / 2, lookupTablePageSize],
        toolbar: '#lookup_type_table_param',
        queryParams: {},
        frozenColumns: [[{
            title: '来源系统类型MES/CRM',
            field: 'SOURCE_TYPE',
            align: 'left',
            width: 80
        }, {
            title: '类型',
            field: 'LOOKUP_TYPE',
            align: 'left',
            width: 80
        }]],
        columns: [[{
            title: '说明',
            field: 'DESCRIPTION',
            align: 'left',
            width: 45,

        }, {
            title: '创建人员',
            field: 'CREATED_BY',
            align: 'left',
            width: 100
        }, {
            title: '创建日期',
            field: 'CREATION_DATE',
            align: 'left',
            width: 100,
            formatter: function (value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleDateString();
                }
            }
        }, {
            title: '最后修改人员',
            field: 'LAST_UPDATED_BY',
            align: 'left',
            width: 100
        }, {
            title: '最后修改日期',
            field: 'LAST_UPDATE_DATE',
            align: 'left',
            width: 80,
            formatter: function (value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleDateString();
                }
            }
        }]],
        onClickRow: function (rowIndex, rowData) {
            currTypeName = rowData.LOOKUP_TYPE;
            loadLookupTable(currTypeName);
        },
        onLoadSuccess: function (data) {
//			currUserName = '';
//			currJobCodeList = '';
//			currRoleCodeList = '';
//			loadUserJobTable(currUserName);
//			loadRoleTable(currUserName, currJobCodeList);
//			loadOrgTable(currUserName, currJobCodeList);
//			loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
        },
        onLoadError: function () {
            showErrorWindow('数据加载失败!');
        }
    });


    // 构造表格
    $('#lookup_table').datagrid({
        title: '数据字典',
        method: 'post',
        fit: true,
        rownumbers: true,
        singleSelect: true,
        pagination: true,
        pageSize: lookupTablePageSize / 2,
        pageList: [lookupTablePageSize / 2, lookupTablePageSize],
        toolbar: '#lookup_table_param',
        queryParams: {},
        frozenColumns: [[{
            title: '数据字典类型',
            field: 'LOOKUP_TYPE',
            align: 'left',
            width: 80
        }, {
            title: '数据字典码',
            field: 'LOOKUP_CODE',
            align: 'left',
            width: 80
        }]],
        columns: [[{
            title: '描述',
            field: 'DESCRIPTION',
            align: 'left',
            width: 45
        }, {
            title: '是否有效',
            field: 'ENABLED_FLAG',
            align: 'left',
            width: 100
        }, {
            title: '开始生效日期',
            field: 'START_DATE_ACTIVE',
            align: 'left',
            width: 100,
            formatter: function (value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleDateString();
                }
            }
        }, {
            title: '结束日期',
            field: 'END_DATE_ACTIVE',
            align: 'left',
            width: 100,
            formatter: function (value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleDateString();
                }
            }
        }, {
            title: '创建人员',
            field: 'CREATED_BY',
            align: 'left',
            width: 100
        }, {
            title: '创建日期',
            field: 'CREATION_DATE',
            align: 'left',
            width: 100,
            formatter: function (value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleDateString();
                }
            }
        }, {
            title: '最后修改人员',
            field: 'LAST_UPDATED_BY',
            align: 'left',
            width: 100
        }, {
            title: '最后修改日期',
            field: 'LAST_UPDATE_DATE',
            align: 'left',
            width: 80,
            formatter: function (value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleDateString();
                }
            }
        }]],
        onClickRow: function (rowIndex, rowData) {
//			currUserName = rowData.LOGIN_NAME;
//			currJobCodeList = '';
//			currRoleCodeList = '';
//			loadUserJobTable(currUserName);
//			loadRoleTable(currUserName, currJobCodeList);
//			loadOrgTable(currUserName, currJobCodeList);
//			loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
        },
        onLoadSuccess: function (data) {
//			currUserName = '';
//			currJobCodeList = '';
//			currRoleCodeList = '';
//			loadUserJobTable(currUserName);
//			loadRoleTable(currUserName, currJobCodeList);
//			loadOrgTable(currUserName, currJobCodeList);
//			loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
        },
        onLoadError: function () {
            showErrorWindow('数据加载失败!');
        }
    });

    // 加载数据字典列表
    function loadLookupTable() {
        $('#lookup_table').datagrid({
            url: basePath + 'lookupController/getLookupList?lookupType=' + currTypeName
        });
    }


    // 绑定查询按钮事件
    $('#lookup_type_table_search_btn').click(function () {
        $('#lookup_type_table').datagrid('load', {
            lookupType: $('#lookupTypeSearch').val(),
        });
    });

    // 绑定查询按钮
    $('#lookupTypeSearch').keydown(function (e) {
        if (e.keyCode == 13) {
            $('#lookup_type_table_search_btn').click();
        }
    });

    // 新增按钮
    $('#lookup_type_table_add_btn').click(function () {
        // 弹出新增窗口
        $('#lookup_type_window_add').window({
            title: '新增数据类型',
            collapsible: false,
            minimizable: false,
            maximizable: false,
            resizable: false,
            modal: true,
            width: 600,
            height: 200,
            href: basePath + 'lookupController/toAddLookupType'
        });
    });

    // 修改按钮
    $('#lookup_type_table_edit_btn').click(function () {
        var row = $('#lookup_type_table').datagrid('getSelected');
        if (row) {
            // 弹出新增窗口
            $('#lookup_type_window_edit').window({
                title: '修改用户',
                collapsible: false,
                minimizable: false,
                maximizable: false,
                resizable: false,
                modal: true,
                width: 600,
                height: 200,
                href: basePath + 'lookupController/toEditLookupType?lookupType=' + row.LOOKUP_TYPE
            });
        } else {
            // 登录失败,弹出提示
            showErrorMsg('请选择要修改的数据');
        }
    });


    // 数据字典新增按钮
    $('#lookup_table_add_btn').click(function () {
        var row = $('#lookup_type_table').datagrid('getSelected');
        if (row) {
            // 弹出新增窗口
            $('#lookup_type_window_add').window({
                title: '新增数据字典',
                collapsible: false,
                minimizable: false,
                maximizable: false,
                resizable: false,
                modal: true,
                width: 600,
                height: 200,
                href: basePath + 'lookupController/toAddLookup'
            });
        } else {
            // 弹出提示
            showErrorMsg('请先选择要增加的数据类型');
        }
    });


//	// 删除
//	$('#lookup_type_table_delete_btn').click(function() {
//		var row = $('#lookup_table').datagrid('getSelected');
//		if (row) {
//			$.messager.confirm('警告', '将会删除用户以及用户与岗位的关联,是否要操作?', function(r) {
//				if (r) {
//					$.ajax({
//						type : 'POST',
//						url : basePath + 'userController/userDelete?loginName=' + row.LOGIN_NAME,
//						success : function(data) {
//							// 解析数据
//							var datas = strToJson(data);
//							if (datas.returnCode == '1') {
//								$('#lookup_type_table_search_btn').click();
//								$('#user_job_table').datagrid('reload');
//								showInfoMsg(datas.result);
//							} else {
//								showErrorMsg("删除失败");
//							}
//						}
//					});
//				}
//			});
//		} else {
//			showErrorMsg('请选择要删除的用户');
//		}
//	});
//
//	
//
//	// -------------------------------------------
//
//	// 岗位表格
//	$('#user_job_table').datagrid({
//		title : '岗位列表',
//		method : 'post',
//		fit : true,
//		rownumbers : false,
//		singleSelect : false,
//		pagination : false,
//		queryParams : {},
//		columns : [ [ {
//			title : '岗位代码',
//			field : 'JOB_CODE',
//			align : 'left',
//			width : 80
//		}, {
//			title : '岗位名称',
//			field : 'JOB_NAME',
//			align : 'left',
//			width : 80
//		}, {
//			title : '组织机构',
//			field : 'ORG_NAME',
//			align : 'left',
//			width : 80
//		}, {
//			title : '创建人',
//			field : 'CREATE_BY',
//			align : 'left',
//			width : 80
//		}, {
//			title : '创建日期',
//			field : 'CREATE_DATE',
//			align : 'left',
//			width : 100,
//			formatter : function(value, row, index) {
//				if (value) {
//					var unixTimestamp = new Date(value);
//					return unixTimestamp.toLocaleDateString();
//				}
//			}
//		} ] ],
//		onClickRow : function(rowIndex, rowData) {
//			var selections = $('#user_job_table').datagrid("getSelections");
//			var jobCodes = '';
//			if (selections.length > 0) {
//				for (var i = 0; i < selections.length; i++) {
//					jobCodes += "'" + selections[i].JOB_CODE + "',";
//				}
//				jobCodes = jobCodes.substring(0, jobCodes.length - 1);
//			} else {
//				jobCodes = '';
//			}
//			currJobCodeList = jobCodes;
//			currRoleCodeList = '';
//			loadRoleTable(currUserName, currJobCodeList);
//			loadOrgTable(currUserName, currJobCodeList);
//			loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
//		},
//		onLoadError : function() {
//			showErrorWindow('数据加载失败!');
//		}
//	});


});