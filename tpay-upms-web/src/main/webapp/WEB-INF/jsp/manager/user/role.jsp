<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />


<div id="roleDialog" class="crudDialog">
    <form id="roleForm" method="post">
        <div class="form-group">
            <select id="roleId" name="roleId" multiple="multiple" style="width: 100%">
                <c:forEach var="sysRole" items="${sysRoles}">
                    <option value="${sysRole.tid}" <c:forEach var="sysUserRole" items="${sysUserRoles}"><c:if test="${sysUserRole.tid==sysRole.tid}">selected="selected"</c:if></c:forEach>>${sysRole.roleName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group text-right dialog-buttons">
            <button class="btn btn-primary" type="button" onclick="roleSubmit();">提交</button>
            <button class="btn btn-white" type="button" onclick="roleDialog.close();">取消</button>
        </div>
    </form>
</div>

<script src="${basePath}/resources/js/manager/user/role.js"></script>
