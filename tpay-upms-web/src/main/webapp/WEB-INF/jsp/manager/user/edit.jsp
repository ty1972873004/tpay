<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />

    <form class="form-horizontal m-t" id="form" name="form" method="post" action="${pageContext.request.contextPath}/user/update" >
        <input type="hidden" value="${sysUser.id}" name="id">
        <div class="form-group">
            <label class="col-sm-3 control-label">登录名：</label>
            <div class="col-sm-8">
                <input disabled class="form-control" type="text" required="" aria-required="true" value="${sysUser.loginName}">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">用户名：</label>
            <div class="col-sm-8">
                <input id="userName" name="userName" class="form-control" type="text" required="" aria-required="true" value="${sysUser.userName}">
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">电话：</label>
            <div class="col-sm-8">
                <input id="phone" name="phone" class="form-control" type="text" required="" aria-required="true" maxlength="20" value="${sysUser.phone}">
            </div>
        </div>

       <div class="form-group">
            <label class="col-sm-3 control-label">邮箱：</label>
            <div class="col-sm-8">
                <input id="email" name="email" class="form-control" type="email" required="" aria-required="true" value="${sysUser.email}">
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">用户类型：</label>
            <div class="col-sm-8">
                <select class="form-control m-b" name="userType" id="userType" style="font-size: 12px">
                    <option value="1">总管理员</option>
                    <option value="2">普通管理员</option>
                    <option value="3">普通用户</option>
                </select>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">头像：</label>
            <div class="col-sm-5">
                <div id="fileList" class="uploader-list" style="width:35px;"></div>
                <div id="upInfo"></div>
            </div>
            <div class="col-lg-4">
                <div id="filePicker">上传头像</div>
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-8 col-sm-offset-3">
                <button class="btn btn-primary" type="submit">提交</button>
                <%--<button class="btn btn-primary" type="button" onclick="createSubmit();">提交</button>--%>
                <button class="btn btn-white" type="button" onclick="updateDialog.close();">取消</button>
            </div>
        </div>
    </form>
<script src="${basePath}/resources/js/getting-started-qiniu.js"></script>
<script src="${basePath}/resources/js/manager/user/edit.js"></script>
