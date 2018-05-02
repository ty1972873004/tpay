<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <%@include file="/resources/common/common.jspf"%>
    <link href="${basePath}/resources/css/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="${basePath}/resources/css/jquery-confirm.min.css" rel="stylesheet">
    <script src="${basePath}/resources/js/jquery.json-2.4.js"></script>
    <script src="${basePath}/resources/js/plugins/webuploader/webuploader.min.js"></script>
    <script src="${basePath}/resources/js/plugins/webuploader/webuploader.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <ul id="topli"></ul>
        </div>
        <div class="ibox-content">
            <div class="row row-lg">

                <div class="col-sm-12">
                    <!-- Example Pagination -->
                    <div class="example-wrap">
                        <h4 class="example-title">用户管理列表</h4>
                       <div id="toolbar">
                           <shiro:hasPermission name="manager:user:add"><a class="btn btn-primary btn-rounded" href="javascript:;" onclick="addAction()"> 新增用户</a></shiro:hasPermission>
                           <shiro:hasPermission name="manager:user:edit"><a class="btn btn-info btn-rounded" href="javascript:;" onclick="editAction()"> 编辑用户</a></shiro:hasPermission>
                           <shiro:hasPermission name="manager:user:del"><a class="btn btn-danger btn-rounded" href="javascript:;" onclick="delAction()"> 删除用户</a></shiro:hasPermission>
                           <shiro:hasPermission name="manager:user:role"><a class="btn btn-danger btn-rounded" href="javascript:;" onclick="roleAction()">用户角色</a></shiro:hasPermission>
                        </div>
                        <div class="example">
                            <table id="userList" >
                            </table>  <!-- 留意-->
                        </div>
                    </div>
                    <!-- End Example Pagination -->
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${basePath}/resources/js/manager/user/list.js"></script>
</body>
</html>
