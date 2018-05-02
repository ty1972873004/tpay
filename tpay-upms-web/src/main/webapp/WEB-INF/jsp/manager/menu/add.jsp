<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
    <form class="form-horizontal m-t" id="form" name="form" method="post" action="${pageContext.request.contextPath}/menu/add" >
        <div class="form-group">
            <label class="col-sm-3 control-label">菜单名称：</label>
            <div class="col-sm-8">
                <input id="menuName" name="menuName" class="form-control" type="text" required="" aria-required="true">
               <%-- <span class="help-block m-b-none"><i class="fa fa-info-circle"></i>菜单名称</span>--%>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">菜单类型：</label>
            <div class="col-sm-8">
                <select class="form-control m-b" name="menuType" id="menuType" style="font-size: 12px">
                    <option value="1">目录</option>
                    <option value="2">菜单</option>
                    <option value="3">按钮</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">上级菜单：</label>
            <div class="col-sm-8">
                <select class="form-control m-b" name="parentId" id="parentId" style="font-size: 13px">
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">权限标识：</label>
            <div class="col-sm-8">
                <input id="menuKey" name="menuKey" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">请求地址：</label>
            <div class="col-sm-8">
                <input id="request" name="request" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">排序号：</label>
            <div class="col-sm-8">
                <input id="sort" name="sort" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">是否隐藏：</label>
            <div class="col-sm-8">
                <label class="radio-inline">
                    <input type="radio"  value="1" id="isHide" name="isHide">是</label>
                <label class="radio-inline">
                    <input type="radio" checked="checked" value="0" id="Hide" name="isHide">否</label>
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
<script src="${basePath}/resources/js/manager/menu/add.js"></script>
