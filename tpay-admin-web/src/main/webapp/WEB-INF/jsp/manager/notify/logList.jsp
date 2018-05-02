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
    <title>消息管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <%@include file="/resources/common/common.jspf"%>

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <ul id="topli">MQ消息管理-消息日志</ul>
        </div>
        <div class="ibox-content">
            <div class="row row-lg">

                <div class="col-sm-12">
                    <!-- Example Pagination -->
                    <div class="example-wrap">
                        <div id="toolbar" role="group" class="btn-group hidden-xs">
                            <form role="form" method="post" class="form-inline" id="eventqueryform">
                            <div class="my-container">
                                <label class="myLabel-content">订单号：</label>
                                <div class="myText-content">
                                    <input  type="text" class="form-control" placeholder="请输入订单号" id="orderNo" name="orderNo">
                                </div>
                            </div>
                            <div class="my-container">
                                <label class="myLabel-content">用户编号：</label>
                                <div class="myText-content">
                                    <input type="text" class="form-control" placeholder="请输入用户编号" id="custNo" name="custNo">
                                </div>
                            </div>
                            <div class="myText-content">
                                <label class="myLabel-content">开始时间：</label>
                                <input placeholder="开始日期" class="form-control layer-date" id="start" name="startTime">
                            </div>
                            <div class="myText-content">
                                <label class="myLabel-content">结束时间：</label>
                                <input placeholder="结束日期" class="form-control layer-date" id="end" name="endTime">
                            </div>
                            <div class="myBtn-content">
                                <input type="button" class="btn btn-default span1" id="eventquery" value="查询" onclick="doQuery();">
                            </div>
                            </form>
                        </div>


                        <div class="example">
                            <table id="notifyLogList" >
                            </table>  <!-- 留意-->
                        </div>
                    </div>
                    <!-- End Example Pagination -->
                </div>
            </div>
        </div>
    </div>
</div>

<script src="${basePath}/resources/js/manager/notify/logList.js"></script>
<script>

    //日期范围限制
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD hh:mm:ss',
        //min: laydate.now(), //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD hh:mm:ss',
        //min: laydate.now(),
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);
</script>
</body>
</html>
