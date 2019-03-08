<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-xs-12">
        <div class="box" style="height: 405px;overflow: auto;">
            <div class="form-inline" style="margin-top: 10px;">
                <div class="form-group">
                    <label for="jj_add_number">借机单号</label>
                    <input type="text" id="jj_add_number" class="form-control" placeholder="借机单号" value="${jjNumber}" readonly/>
                </div>
                <div class="form-group">
                    <label for="jj_add_dept">借机部门</label>
                    <select class="form-control" id="jj_add_dept">
                        <option value="" selected="selected">---请选择---</option>
                        <c:forEach items="${depts}" var="dept">
                            <option value='${dept.LOOKUP_CODE}'>${dept.DESCRIPTION}</option>
                        </c:forEach>
                    </select>
                </div>
                <div style="width: 100%;margin-top: 10px;">
                    <div class="form-group">
                        <label for="jj_add_person">借机人员</label>
                        <input type="text" class="form-control" id="jj_add_person" placeholder="借机人员" />
                    </div>
                    <div class="form-group">
                        <label for="jj_add_purpose">借机用途</label>
                        <select class="form-control" id="jj_add_purpose">
                            <option value="" selected="selected">---请选择---</option>
                            <c:forEach items="${purposes}" var="purpose">
                                <option value='${purpose.LOOKUP_CODE}'>${purpose.DESCRIPTION}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div style="width: 100%;margin-top: 10px;">
                    <div class="form-group">
                        <label for="add_remark" style="padding-left: 2em;">备注</label>
                        <textarea class="form-control" rows="2" id="add_remark" placeholder="备注" style="width: 345px;"></textarea>
                    </div>
                </div>
            </div>
            <table cellspacing="0" style="width: 100%;margin-top: 20px;" id="jj_add_table">
                <thead>
                <tr>
                    <th style="text-align: center;height: 30px;">编码</th>
                    <th style="text-align: center;height: 30px;">型号</th>
                    <th style="text-align: center;height: 30px;">申请数量</th>
                    <th style="text-align: center;height: 30px;">计划归还时间</th>
                    <th style="text-align: center;height: 30px;">来源子库</th>
                    <th style="text-align: center;height: 30px;">目的子库</th>
                    <th style="text-align: center;height: 30px;">目的货位</th>
                    <th style="text-align: center;height: 30px;">物料描述</th>
                    <th style="text-align: center;height: 30px;">操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <input name="code" class="form-control" style="width: 130px;" onblur="codeBlur(this);"/>
                        <input type="hidden" name="codeId">
                    </td>
                    <td>
                        <input name="model" class="form-control" style="width: 130px;" readonly/>
                    </td>
                    <td>
                        <input name="quantity" class="form-control" style="width: 75px;" onkeyup="this.value=this.value.replace(/\D/g,'')"/>
                    </td>
                    <td>
                        <input name="returnTime" class="form-control" style="width: 110px;" placeholder="yyyy-mm-dd"/>
                    </td>
                    <td>
                        <select class="form-control" name="sourceSubLibrary" style="width: 110px;" >
                            <option value="" selected="selected">---请选择---</option>
                            <c:forEach items="${librarys}" var="library">
                                <option value='${library.SECONDARY_INVENTORY_NAME}'>${library.SECONDARY_INVENTORY_NAME}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select class="form-control" name="goalSubLibrary" style="width: 110px;" onchange="goalSubLibraryChange(this)">
                         <%--   <option value="">---请选择---</option>--%>
                            <c:forEach items="${librarys}" var="library">
                                <option value='${library.SECONDARY_INVENTORY_NAME}' <c:if test="${library.SECONDARY_INVENTORY_NAME=='F008'}">selected="selected"</c:if>>${library.SECONDARY_INVENTORY_NAME}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select class="form-control" name="goalLocation" style="width: 110px;">
                           <%-- <option value="">---请选择---</option>--%>
                            <c:forEach items="${maps}" var="map">
                                <option value='${map.LOCATTION_CODE}'>${map.LOCATTION_CODE}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input name="describe" class="form-control" style="width: 180px;" readonly/>
                    </td>
                    <td>
                        <a href="javascript:void(0);" onclick="plus(this)"><i class="glyphicon glyphicon-plus"></i></a>
                        <a href="javascript:void(0);" onclick="minus(this)"><i class="glyphicon glyphicon-minus"></i></a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>