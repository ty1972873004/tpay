<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<form class="form-horizontal m-t" id="form" name="form" method="post" action="${pageContext.request.contextPath}/dict/update" >
    <input type="hidden" value="${sysDic.id}" name="id">

    <div class="form-group">
        <label class="col-sm-3 control-label">code：</label>
        <div class="col-sm-8">
            <input id="code" name="code" class="form-control" type="text" required="" aria-required="true" value="${sysDic.code}">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label">code名称：</label>
        <div class="col-sm-8">
            <input id="codeText" name="codeText" class="form-control" type="text" required="" aria-required="true"  value="${sysDic.codeText}">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label">排序号：</label>
        <div class="col-sm-8">
            <input id="sortNo" name="sortNo" class="form-control" type="number" required="" aria-required="true" value="${sysDic.sortNo}">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label">备注：</label>
        <div class="col-sm-8">
            <input id="remark" name="remark" class="form-control" type="text" required="" aria-required="true" value="${sysDic.remark}">
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-3 control-label">是否可编辑：</label>
        <div class="col-sm-8">
            <label class="radio-inline">
                <input type="radio"  value="1"  name="editable" <c:if test="${sysDic.editable eq '1'}">checked="checked"</c:if>>是</label>
            <label class="radio-inline">
                <input type="radio"  value="0" name="editable" <c:if test="${sysDic.editable eq '0'}">checked="checked"</c:if>>否</label>
        </div>
    </div>





    <div class="form-group">
        <div class="col-sm-8 col-sm-offset-3">
            <button class="btn btn-primary" type="submit" <c:if test="${sysDic.editable eq '0'}">disabled="true"</c:if>>提交</button>
            <button class="btn btn-white" type="button" onclick="updateDialog.close();">取消</button>
        </div>
    </div>
</form>
<script src="${basePath}/resources/js/manager/dict/edit.js"></script>
