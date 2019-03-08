<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="form-inline" style="margin-top: 10px;margin-bottom: 10px;">
                <div class="form-group">
                    <label for="jj_number">借机单号</label>
                    <input type="text" class="form-control" id="jj_number" placeholder="借机单号" value="${seaoty.number}" readonly/>
                </div>
                <div class="form-group">
                    <label for="jj_dept">借机部门</label>
                    <select class="form-control" id="jj_dept" disabled>
                        <option value="">---请选择---</option>
                        <c:forEach items="${depts}" var="dept">
                            <option value='${dept.LOOKUP_CODE}' <c:if test="${seaoty.dept==dept.LOOKUP_CODE}">selected="selected"</c:if>>${dept.DESCRIPTION}</option>
                        </c:forEach>
                    </select>
                </div>
                <div style="width: 100%;margin-top: 10px;">
                    <div class="form-group">
                        <label for="jj_person">借机人员</label>
                        <input type="text" class="form-control" id="jj_person" placeholder="借机人员" value="${seaoty.person}" readonly/>
                    </div>
                    <div class="form-group">
                        <label for="jj_purpose">借机用途</label>
                        <select class="form-control" id="jj_purpose" disabled>
                            <option value="" selected="selected">---请选择---</option>
                            <c:forEach items="${purposes}" var="purpose">
                                <option value='${purpose.LOOKUP_CODE}' <c:if test="${seaoty.purpose==purpose.LOOKUP_CODE}">selected="selected"</c:if>>${purpose.DESCRIPTION}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div style="width: 100%;margin-top: 10px;">
                    <div class="form-group">
                        <label for="describe" style="padding-left: 2em;">备注</label>
                        <textarea class="form-control" rows="2" id="describe" placeholder="备注" style="width: 345px;" readonly>${seaoty.remark}</textarea>
                    </div>
                </div>
                <div style="width: 100%;margin-top: 10px;">
                    <div style="width: 70%;float: left;">
                        <shiro:hasPermission name="seizeAnOpportunity:sale">
                            <button type="button" class="btn btn-info" id="sale">销售</button>
                        </shiro:hasPermission>
                        &nbsp;
                        <shiro:hasPermission name="seizeAnOpportunity:deleteLines">
                            <button type="button" class="btn btn-danger" id="delete_line">
							 <i class="glyphicon glyphicon-trash"></i>&nbsp;删除&nbsp;
							</button>
                        </shiro:hasPermission>
                    </div>
                    <div style="width: 30%;float: left;" align="right">
                        <button type="button" class="btn btn-info" id="detailed_printer">打印</button>
                    </div>
                </div>
            </div> 
            <table id="seizeAnOpportunityTable"></table>
        </div>
    </div>
</div>
<!-- 模态框（Modal） 新增 -->
<div class="modal fade bs-example-modal-lg" id="saleModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" id="saleClose">
                    &times;
                </button>
                <h4 class="modal-title">销售</h4>
            </div>
            <div class="modal-body">
                <div class="form-inline">
                    <div class="form-group">
                        <label for="evaluation" style="padding-left: 2em;">销售订单</label>
                        <textarea class="form-control" rows="3" placeholder="销售订单" id="evaluation" style="width: 280px;"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saleSave">保存</button>
            </div>
        </div>
    </div>
</div>