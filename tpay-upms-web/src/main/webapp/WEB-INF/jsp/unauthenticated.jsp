<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unauthenticated!</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${basePath}/resources/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${basePath}/resources/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${basePath}/resources/css/animate.css" rel="stylesheet">
    <link href="${basePath}/resources/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">


<div class="middle-box text-center animated fadeInDown">
    <h1>Unauthenticated</h1>
    <h3 class="font-bold">Unauthenticated!</h3>

    <div class="error-desc">
        Unauthenticated!
        <br/>您可以返回主页看看
        <br/><a href="index.jsp" class="btn btn-primary m-t">主页</a>
    </div>
</div>
<!-- 全局js -->
<script src="${basePath}/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/resources/js/bootstrap.min.js?v=3.3.6"></script>

</body>

</html>
