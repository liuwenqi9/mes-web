<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath}/static/unit/js/common/commonSelectModal.js"></script>
	
	<!-- 子库选择器 -->
	<div class="modal fade" id="subinventorySelectModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" >选择子库</h4>
				</div>
				<div id="subinventory_select_toolbar" class="btn-group"> 
					<div >   
						<label for="line1" class="col-sm-6 control-label">
							<input type="text" class="form-control"  id="sublibraryName_id" > 
						</label>
						<label for="line1" class="col-sm-2 control-label">
							<button type="button" class="btn btn-info btn-sm" id='sublibrary_search_btn'
								data-backdrop="static"  />
								<i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
							</button> 
						</label> 
						<input type="text" id="subCodeId" hidden>
						<input type="text" id="subDesrId" hidden>
					</div>  
				</div> 	 
				<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
						<table id="sublibrarySelectTable" data-pagination="true" data-page-size="10"
						data-page-list="[10,15,20]" data-pagination-first-text="First"
						data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
						data-pagination-last-text="Last" data-locale="zh-CN"
						data-show-columns="false" data-toolbar="#subinventory_select_toolbar"  data-click-to-select="true"
      					data-single-select="true">
						<thead>
							<tr> 
							<th  data-checkbox="true"></th>
								<th data-field="SECONDARY_INVENTORY_NAME">ERP子库</th>
								<th data-field="DESCRIPTION">子库描述</th>
								<th data-field="FMT_DISABLE_DATE">失效日期</th>  
							</tr>
						</thead>
					</table> 
						</div> </div> </div> </section>	  
			</div>
		</div>
	</div>
	
	
	<!-- 货位选择器 -->
	<div class="modal fade" id="cargospaceSelectModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" >选择货位</h4>
				</div>
				<div id="cargospace_select_toolbar" class="btn-group"> 
					<label for="line1" class="col-sm-6 control-label">
						<input type="text" class="form-control"  id="cargospaceName_sid" > 
					</label>
					<label for="line1" class="col-sm-2 control-label">
						<button type="button" class="btn btn-info btn-sm" id='cargospace_search_btn'
							data-backdrop="static"  />
							<i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
						</button> 
					</label>
					<input type="text" id="cargospaceId" hidden>
					<input type="text" id="sublibraryName" hidden>
				</div> 	 
				<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
						<table id="cargospaceSelectTable" data-pagination="true" data-page-size="10"
						data-page-list="[10,15,20]" data-pagination-first-text="First"
						data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
						data-pagination-last-text="Last" data-locale="zh-CN"
						data-show-columns="false" data-toolbar="#cargospace_select_toolbar"  data-click-to-select="true"
      					data-single-select="true">
						<thead>
							<tr> 
							<th  data-checkbox="true"></th>
								<th data-field="SUBINVENTORY_CODE">ERP子库</th>
								<th data-field="LOCATTION_CODE">货位</th>
								<th data-field="DESCRIPTION">子库描述</th>
								<th data-field="FMT_DISABLE_DATE">失效日期</th>  
							</tr>
						</thead>
					</table> 
						</div> </div> </div> </section>	  
			</div>
		</div>
	</div>
	
	
	<!-- 来源选择器 -->
	<div class="modal fade" id="sourceSelectModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" >选择来源</h4>
				</div>
				<div id="source_select_toolbar" class="btn-group"> 
					<label for="line1" class="col-sm-6 control-label">
						<input type="text" class="form-control"  id="sourceName_sid" > 
					</label>
					<label for="line1" class="col-sm-2 control-label">
						<button type="button" class="btn btn-info btn-sm" id='source_search_btn'
							data-backdrop="static"  />
							<i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查&nbsp;&nbsp;询&nbsp;&nbsp;
						</button> 
					</label>
					<input type="text" id="sourceId" hidden>
				</div> 	 
				<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
						<table id="sourceTable" data-pagination="true" data-page-size="10"
						data-page-list="[10,15,20]" data-pagination-first-text="First"
						data-pagination-pre-text="上一页" data-pagination-next-text="下一页"
						data-pagination-last-text="Last" data-locale="zh-CN"
						data-show-columns="false" data-toolbar="#source_select_toolbar"  data-click-to-select="true"
      					data-single-select="true">
						<thead>
							<tr> 
							<th  data-checkbox="true"></th>
								<th data-field="SEGMENT1">来源</th> 
								<th data-field="DESCRIPTION">来源描述</th>
								<th data-field="DISABLE_DATE">失效日期</th>  
							</tr>
						</thead>
					</table> 
						</div> </div> </div> </section>	  
			</div>
		</div>
	</div>