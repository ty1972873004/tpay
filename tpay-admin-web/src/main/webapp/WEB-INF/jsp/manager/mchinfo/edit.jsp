<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
    <form class="form-horizontal m-t" id="form" name="form" method="post" action="${pageContext.request.contextPath}/mchinfo/update" >
        <input type="hidden" value="${mchInfo.id}" name="id">
        <input type="hidden"  name="mchStatus" id="mchStatus">
        <div class="form-group">
            <label class="col-sm-3 control-label">商户ID：</label>
            <div class="col-sm-8">
                <input id="mchId" name="mchId" class="form-control" type="text" required="" aria-required="true" value="${mchInfo.mchId}" disabled="disabled">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户名称：</label>
            <div class="col-sm-8">
                <input id="mchName" name="mchName" class="form-control" type="text" required="" aria-required="true" value="${mchInfo.mchName}">
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">商户类型：</label>
            <div class="col-sm-8">
                <select class="form-control m-b" name="mchType" id="mchType" style="font-size: 12px">
                    <option value="00" <c:if test="${mchInfo.mchType == '00'}">selected="selected"</c:if>>平台商户</option>
                    <option value="01" <c:if test="${mchInfo.mchType == '01'}">selected="selected"</c:if>>个体商户</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">是否启用：</label>
            <div class="col-sm-8">
                <input type="checkbox" class="js-switch"  name="status" id="status"  <c:if test="${mchInfo.mchStatus == 1}">checked</c:if>/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户私钥：</label>
            <div class="col-sm-8">
                <textarea id="mchPrivateKey" name="mchPrivateKey" class="form-control" required="">${mchInfo.mchPrivateKey}</textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户公钥：</label>
            <div class="col-sm-8">
                <textarea id="mchPublicKey" name="mchPublicKey" class="form-control" required="">${mchInfo.mchPublicKey}</textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">平台私钥：</label>
            <div class="col-sm-8">
                <textarea id="tpPrivateKey" name="tpPrivateKey" class="form-control" required="">${mchInfo.tpPrivateKey}</textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">平台公钥：</label>
            <div class="col-sm-8">
                <textarea id="tpPublicKey" name="tpPublicKey" class="form-control" required="">${mchInfo.tpPublicKey}</textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-8 col-sm-offset-3">
                <button class="btn btn-primary" type="submit">提交</button>
                <button class="btn btn-white" type="button" onclick="updateDialog.close();">取消</button>
            </div>
        </div>
    </form>

<script src="${basePath}/resources/js/manager/mchinfo/edit.js"></script>