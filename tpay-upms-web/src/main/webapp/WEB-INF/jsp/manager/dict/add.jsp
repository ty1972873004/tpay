<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
    <form class="form-horizontal m-t" id="form" name="form" method="post" action="${pageContext.request.contextPath}/dict/add" >
        <input id="indexId" name="indexId" value="${indexId}" type="hidden">
        <div class="form-group">
            <label class="col-sm-3 control-label">code：</label>
            <div class="col-sm-8">
                <input id="code" name="code" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">code名称：</label>
            <div class="col-sm-8">
                <input id="codeText" name="codeText" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">排序号：</label>
            <div class="col-sm-8">
                <input id="sortNo" name="sortNo" class="form-control" type="number" required="" aria-required="true">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">备注：</label>
            <div class="col-sm-8">
                <input id="remark" name="remark" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">是否可编辑：</label>
            <div class="col-sm-8">
                <label class="radio-inline">
                    <input type="radio"  value="1"  name="editable" checked="checked">是</label>
                <label class="radio-inline">
                    <input type="radio"  value="0" name="editable">否</label>
            </div>
        </div>



        <div class="form-group">
            <div class="col-sm-8 col-sm-offset-3">
                <button class="btn btn-primary" type="submit">提交</button>
                <%--<button class="btn btn-primary" type="button" onclick="createSubmit();">提交</button>--%>
                <button class="btn btn-white" type="button" onclick="createDialog.close();">取消</button>
            </div>
        </div>
    </form>
<script src="${basePath}/resources/js/manager/dict/add.js"></script>
