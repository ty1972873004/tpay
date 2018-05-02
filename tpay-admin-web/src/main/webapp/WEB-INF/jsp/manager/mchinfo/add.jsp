<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
    <form class="form-horizontal m-t" id="form" name="form" method="post" action="${pageContext.request.contextPath}/mchinfo/add" >
        <input type="hidden"  name="mchStatus" id="mchStatus">
        <div class="form-group">
            <label class="col-sm-3 control-label">商户名称：</label>
            <div class="col-sm-8">
                <input id="mchName" name="mchName" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户类型：</label>
            <div class="col-sm-8">
                <select class="form-control m-b" name="mchType" id="mchType" style="font-size: 12px">
                    <option value="00">平台商户</option>
                    <option value="01">个体商户</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">是否启用：</label>
            <div class="col-sm-8">
                    <input type="checkbox" class="js-switch" checked name="status" id="status"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户私钥：</label>
            <div class="col-sm-8">
                <input id="mchPrivateKey" name="mchPrivateKey" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户公钥：</label>
            <div class="col-sm-8">
                <input id="mchPublicKey" name="mchPublicKey" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">平台私钥：</label>
            <div class="col-sm-8">
                <input id="tpPrivateKey" name="tpPrivateKey" class="form-control" type="text" required="" aria-required="true">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">平台公钥：</label>
            <div class="col-sm-8">
                <input id="tpPublicKey" name="tpPublicKey" class="form-control" type="text" required="" aria-required="true">
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
<script src="${basePath}/resources/js/manager/mchinfo/add.js"></script>