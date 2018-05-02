<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
    <form class="form-horizontal m-t" id="form" name="form" method="post" action="${pageContext.request.contextPath}/role/add" >
        <div class="form-group">
            <label class="col-sm-3 control-label">角色名称：</label>
            <div class="col-sm-8">
                <input id="roleName" name="roleName" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">角色类型：</label>
            <div class="col-sm-8">
                <select class="form-control m-b" name="roleType" id="roleType" style="font-size: 12px">
                    <option value="1">系统管理员</option>
                    <option value="2">普通管理员</option>
                    <option value="3">客服</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">角色key：</label>
            <div class="col-sm-8">
                <input id="roleKey" name="roleKey" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">角色状态：</label>
            <div class="col-sm-8">
                <label class="radio-inline">
                    <input type="radio" checked="checked" value="1" id="status" name="status">正常</label>
                <label class="radio-inline">
                    <input type="radio"  value="0" id="status_" name="status">停用</label>
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
<script src="${basePath}/resources/js/manager/role/add.js"></script>
