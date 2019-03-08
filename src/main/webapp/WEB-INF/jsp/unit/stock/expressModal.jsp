<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="col-xs-12">
        <div class="box" style="height: 200px;overflow: auto;">
            <div class="form-inline" style="margin-top: 10px;">
                <div class="form-group">
                    <label for="express">物流公司</label>
                    <select class="form-control" id="express">
                        <option value="" selected="selected">---请选择---</option>
                        <c:forEach items="${expressList}" var="map">
                            <option value='${map.LOOKUP_CODE}'>${map.DESCRIPTION}</option>
                        </c:forEach>
                    </select>
                </div>
                <div style="width: 100%;margin-top: 10px;">
                    <div class="form-group">
                        <label for="expressNumber">物流单号</label>
                        <input type="text" class="form-control" id="expressNumber" placeholder="物流单号" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>