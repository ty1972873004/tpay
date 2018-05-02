<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://hi.csdn.net/tjcyjd/tags" prefix="myTag" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
    <form class="form-horizontal m-t" id="form" name="form" method="post" >
        <div class="form-group">
            <label class="col-sm-3 control-label">商户ID：</label>
            <div class="col-sm-8">
                <input id="mchId" name="mchId" class="form-control" type="text" required="" aria-required="true" value="${mchInfo.mchId}" disabled="disabled">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户名称：</label>
            <div class="col-sm-8">
                <input id="mchName" name="mchId" class="form-control" type="text" required="" aria-required="true" value="${mchInfo.mchName}" disabled="disabled">
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-3 control-label">商户类型：</label>
            <div class="col-sm-8">
                <input id="mchType" name="mchType" class="form-control" type="text" required="" aria-required="true" value="${mchTypeMap[mchInfo.mchType]}" disabled="disabled">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户状态：</label>
            <div class="col-sm-8">
                <c:if test="${mchInfo.mchStatus == 1}">
                    <input id="mchStatus" name="mchStatus" class="form-control" type="text" required="" aria-required="true" value="正常" disabled="disabled">
                </c:if>
                <c:if test="${mchInfo.mchStatus == 0}">
                    <input id="mchStatus" name="mchStatus" class="form-control" type="text" required="" aria-required="true" value="停用" disabled="disabled">
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">商户私钥：</label>
            <div class="col-sm-8">
                <textarea id="mchPrivateKey" name="mchPrivateKey" class="form-control" required="" aria-required="true"  disabled="disabled">${mchInfo.mchPrivateKey}</textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户公钥：</label>
            <div class="col-sm-8">
                <textarea id="mchPublicKey" name="mchPublicKey" class="form-control" required="" aria-required="true"  disabled="disabled">${mchInfo.mchPublicKey}</textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">平台私钥：</label>
            <div class="col-sm-8">
                <textarea id="tpPrivateKey" name="tpPrivateKey" class="form-control" required="" aria-required="true"  disabled="disabled">${mchInfo.tpPrivateKey}</textarea>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">平台公钥：</label>
            <div class="col-sm-8">
                <textarea id="tpPublicKey" name="tpPublicKey" class="form-control" required="" aria-required="true"  disabled="disabled">${mchInfo.tpPublicKey}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">创建时间：</label>
            <div class="col-sm-8">
                <input id="createTime" name="createTime" class="form-control" type="text" required="" aria-required="true" value="<fmt:formatDate value="${mchInfo.createTime}" pattern="yyyy/MM/dd HH:mm:ss"/>" disabled="disabled">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">更新时间：</label>
            <div class="col-sm-8">
                <input id="updateTime" name="updateTime" class="form-control" type="text" required="" aria-required="true" value="<fmt:formatDate value="${mchInfo.updateTime}" pattern="yyyy/MM/dd HH:mm:ss"/>" disabled="disabled">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-8 col-sm-offset-3">
            </div>
        </div>
    </form>
