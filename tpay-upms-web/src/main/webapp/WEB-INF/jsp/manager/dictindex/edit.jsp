<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<form class="form-horizontal m-t" id="form" name="form" method="post" action="${pageContext.request.contextPath}/dictIndex/update" >
    <input type="hidden" value="${sysDicIndex.id}" name="id">

    <div class="form-group">
        <label class="col-sm-3 control-label">key值：</label>
        <div class="col-sm-8">
            <input id="keyValue" name="keyValue" class="form-control" type="text" required="" aria-required="true" value="${sysDicIndex.keyValue}">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label">key名称：</label>
        <div class="col-sm-8">
            <input id="keyName" name="keyName" class="form-control" type="text" required="" aria-required="true"  value="${sysDicIndex.keyName}">
        </div>
    </div>


    <div class="form-group">
        <div class="col-sm-8 col-sm-offset-3">
            <button class="btn btn-primary" type="submit">提交</button>
            <button class="btn btn-white" type="button" onclick="updateDialog.close();">取消</button>
        </div>
    </div>
</form>
<script src="${basePath}/resources/js/manager/dictindex/edit.js"></script>
