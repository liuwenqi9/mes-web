<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/capacityPacking/capacityPacking.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
<!-- jQuery 2.1.4 -->
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
<!-- Bootstrap 3.3.5 -->
<script
	src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>

<title>包装箱列表</title>
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/morris/morris.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<!-- Date Picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datepicker/datepicker3.css">
<!-- Daterange picker -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/daterangepicker/daterangepicker-bs3.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="dist/js/html5shiv.min.js"></script>
        <script src="dist/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">



	<!-- Content Wrapper. Contains page content -->

	<!-- Main content -->
	<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<!-- /.box-header -->
				<div class="box-body table-responsive">
					<div id="capacityPacking_toolbar" class="btn-group">  
				<div class="row-fluid">
					  <div class="span12">
						      <button type="button" class="btn btn-success btn-sm"
								 data-backdrop="static" id="capacityPacking_add_btn"
								data-target="#addCapacityPackingModal">
								<i class="glyphicon glyphicon-plus" ></i>添加
							</button>
							<button type="button" class="btn btn-warning btn-sm"
								data-backdrop="static" data-target="#editCapacityPackingModal"
								onclick='editCapacityPacking();'>
								<i class="glyphicon glyphicon-edit"></i>修改
							</button>
							<button type="button" onclick='deleteCapacityPacking();' class="btn btn-danger btn-sm"
								data-backdrop="static" data-target="#deleteCapacityPackingModal"
								id="capacityPacking_delete_btn">
								<i class="glyphicon glyphicon-trash"></i>删除
							</button>
							<button type="button" onclick='searchCapacityPacking();' class="btn btn-info btn-sm"
								data-backdrop="static" data-target="#searchCapacityPackingModal" />
								<i class="glyphicon glyphicon-search"></i>查询
							</button>
						  <div class="row-fluid"> 
							  	<div style="padding: 10px 1px 10px;">  
								   <input type="text" class="form-control" placeholder="物料编码" id="segment1_search"
															name="segment1">
								      
								</div>   
						  </div>  
				      </div>
			   </div>
				
				</div>
				
					<table id="capacityPackingTable" data-pagination="true" data-page-size="10"
						data-page-list="[5,10,20]" data-pagination-first-text="First"
						data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
						data-pagination-last-text="Last" data-locale="zh-CN"
						data-show-columns="true" data-toolbar="#capacityPacking_toolbar" data-click-to-select="true"
      					data-single-select="true">
						<thead>
							<tr>
								<th data-field="check" data-checkbox="true"></th>
								<th data-field="SEGMENT1">物料编码</th>
								<th data-field="DESCRIPTION">物料描述</th>
								<th data-field="PROD_TYPE">产品类型</th>
								<th data-field="S_QUANTITY">小包装标准容量</th>
								<th data-field="B_QUANTITY">大包装标准容量</th>
								<th data-field="LAST_UPDATE_DATE">最后更新时间</th>
								<th data-field="LOGIN_NAME">最后更新用户</th> 
							</tr>
						</thead>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row --> </section>
	<!-- /.content -->

	<!-- /.content-wrapper -->

	<!-- edit-modal -->
	<div class="modal fade" id="editCapacityPackingModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">修改包装箱</h4>
				</div>
				 <form  class="form-horizontal" id="editCapacityPackingForm" method="post">
					<div class="modal-body">
						<div class="form-group ">
							<label for="SEGMENT1_edit" class="col-sm-2 control-label">物料编码 </label>
							<label for="SEGMENT1_edit" class="col-sm-1 control-label" id='search_btn_edit'>
							<i class="glyphicon glyphicon-search"></i></label> 
							<div class="col-sm-9 ">
								<input type="text" class="form-control " id="SEGMENT1_edit" readonly> 
							</div>
						</div>
						<div class="form-group">
							<label for="PROD_TYPE_edit" class="col-sm-2 control-label">产品类型</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="PROD_TYPE_edit"  readonly/> 
							</div>
						</div> 
						<div class="form-group">
							<label for="DESCRIPTION_edit" class="col-sm-2 control-label">物料描述</label>
							<div class="col-sm-10">
								<textarea cols="20" rows="2" class="form-control" id="DESCRIPTION_edit" readonly ></textarea> 
							</div>
						</div> 
						<div  class="form-group">
							<label for="sQuantity_edit" class="col-sm-2 control-label">小包装容量</label>
							<div class="col-sm-10">
								<input type="tel" class="form-control" id="sQuantity_edit" name="sQuantity" placeholder="只能填写数字"/> 
							</div>
						</div>
						<div  class="form-group">
							<label for="bQuantity" class="col-sm-2 control-label">大包装容量</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="bQuantity_edit"name='bQuantity' placeholder="只能填写数字"> 
							</div>
							 <input id="organization_id_edit" name="organizationId" type="hidden"  />
							 <input id="inventory_item_id_edit" name="inventoryItemId"  type="hidden"/>
							  <input id="HEADER_ID_edit" name="headerId"  type="hidden"/>
						</div> 
					</div> 	
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="edit_capacityPacking_save_btn">保存</button>
					</div>
				</form> 
			</div>
		</div>
	</div>

<!-- editCapacityPacking 物料选择框 -->
	<div class="modal fade" id="editCapacityPackingSelectModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" >选择物料2</h4>
				</div>
				<div id="editselect_toolbar" class="btn-group"> 
					<div style="padding: 10px 1px 10px;">   
						<input type="text" class="form-control" placeholder="物料编码" id="segment1_editSelect"name="segment1">    
					</div>  
				</div> 	 
				<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
						<table id="editselectTable" data-pagination="true" data-page-size="5"
						data-page-list="[5,10]" data-pagination-first-text="First"
						data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
						data-pagination-last-text="Last" data-locale="zh-CN"
						data-show-columns="false" data-toolbar="#editselect_toolbar"  data-click-to-select="true"
      					data-single-select="true">
						<thead>
							<tr> 
							<th  data-checkbox="true"></th>
								<th data-field="SEGMENT1">物料编码</th>
								<th data-field="DESCRIPTION">物料描述</th>
								<th data-field="PROD_TYPE">产品类型</th>  
							</tr>
						</thead>
					</table> 
						</div> </div> </div> </section>	  
			</div>
		</div>
	</div>


	
	<!-- CapacityPacking add -->
	<div class="modal fade" id="addCapacityPackingModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">添加包装箱</h4>
				</div>
				<form  class="form-horizontal" id="addCapacityPackingForm" method="post">
					<div class="modal-body">
						<div id = 'SEGMENT1_add_a' class="form-group ">
							<label for="SEGMENT1_add" class="col-sm-2 control-label">物料编码 </label>
							<label for="SEGMENT1_add" class="col-sm-1 control-label" id='search_btn_2'>
							<i class="glyphicon glyphicon-search"></i></label> 
							<div class="col-sm-9 ">
								<input type="text" class="form-control " id="SEGMENT1_add" readonly> 
							</div>
						</div>
						<div class="form-group">
							<label for="PROD_TYPE_add" class="col-sm-2 control-label">产品类型</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="PROD_TYPE_add" readonly/> 
							</div>
						</div> 
						<div class="form-group">
							<label for="DESCRIPTION_add" class="col-sm-2 control-label">物料描述</label>
							<div class="col-sm-10">
								<textarea cols="20" rows="2" class="form-control" id="DESCRIPTION_add" readonly ></textarea> 
							</div>
						</div> 
						<div  class="form-group">
							<label for="sQuantity_add" class="col-sm-2 control-label">小包装容量</label>
							<div class="col-sm-10">
								<input type="tel" class="form-control" id="sQuantity_add" name="sQuantity" placeholder="只能填写数字"/> 
							</div>
						</div>
						<div  class="form-group">
							<label for="bQuantity" class="col-sm-2 control-label">大包装容量</label>
							<div class="col-sm-10">
								<input type="email" class="form-control" id="bQuantity_add"name='bQuantity' placeholder="只能填写数字"> 
							</div>
							 <input id="organization_id_add" name="organizationId" type="hidden"  />
							 <input id="inventory_item_id_add" name="inventoryItemId"  type="hidden"/>
						</div> 
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary"
							id="add_capacityPacking_save_btn">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- addCapacityPacking 物料选择框 -->
	<div class="modal fade" id="addCapacityPackingSelectModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" >选择物料</h4>
				</div>
				<div id="addselect_toolbar" class="btn-group"> 
					<div style="padding: 10px 1px 10px;">   
						<input type="text" class="form-control" placeholder="物料编码" id="segment1_addSelect"name="segment1">    
					</div>  
				</div> 	 
				<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
						<table id="addselectTable" data-pagination="true" data-page-size="5"
						data-page-list="[5,10]" data-pagination-first-text="First"
						data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
						data-pagination-last-text="Last" data-locale="zh-CN"
						data-show-columns="false" data-toolbar="#addselect_toolbar"  data-click-to-select="true"
      					data-single-select="true">
						<thead>
							<tr> 
							<th  data-checkbox="true"></th>
								<th data-field="SEGMENT1">物料编码</th>
								<th data-field="DESCRIPTION">物料描述</th>
								<th data-field="PROD_TYPE">产品类型</th>  
							</tr>
						</thead>
					</table> 
						</div> </div> </div> </section>	  
			</div>
		</div>
	</div>
	
	
	<!-- delete CapacityPacking -->
	<div class="modal fade" id="deleteUserModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<form action="" id="deleteUserForm" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="exampleModalLabel">
							请确认是否删除物料编码名为(<a id="del_sen" name="sen"></a>)的用户？
						</h4>
					</div>
					<input type="hidden" id = 'del_HEADER_ID' name='HEADER_ID'/>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-danger" id="del_CapacityPacking_btn">删除</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
	<div class="control-sidebar-bg"></div>
	<!-- ./wrapper -->


	<!-- DataTables -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<!-- SlimScroll -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/fastclick/fastclick.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/js/app.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script
		src="${pageContext.request.contextPath}/static/egdfrm/dist/js/demo.js"></script>
	<!-- page script -->
</body>
</html>