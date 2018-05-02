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
    <title>MQ消息管理</title>
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
                        <h4 class="example-title">MQ消息列表</h4>
                        <div id="toolbar">
                        </div>
                        <div class="example">
                            <table id="notifyList" >
                            </table>  <!-- 留意-->
                        </div>
                    </div>
                    <!-- End Example Pagination -->
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${basePath}/resources/js/manager/notify/list.js"></script>
<script>
    function operateFormatter(value, row, index) {
        return [
            '<shiro:hasPermission name="manager:notify:resend"><button id="btn_detail" type="button" class="RoleOfA btn btn-primary btn-rounded" onclick="reSendAction()">消息重发</button></shiro:hasPermission>',
        ].join('');
    }

    window.operateEvents1 = {
        'click .RoleOfA': function(e, value, row, index) {
        }
    };
</script>
</body>
</html>
