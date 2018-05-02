<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<form class="form-horizontal m-t" id="permissionForm"  method="post" >
    <div>
        <ul id="ztree" class="ztree"></ul>
    </div>

    <div class="form-group">
        <div class="col-sm-8 col-sm-offset-3">
            <button class="btn btn-primary" type="button" onclick="permissionSubmit();">提交</button>
            <button class="btn btn-white" type="button" onclick="permissionDialog.close();">取消</button>
        </div>
    </div>
</form>
<script src="${basePath}/resources/js/manager/role/permission.js"></script>
