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
                <input id="mchId" name="mchId" class="form-control" type="text" required="" aria-required="true" value="${payChannel.mchId}" disabled="disabled">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">渠道ID：</label>
            <div class="col-sm-8">
                <input id="channelId" name="channelId" class="form-control" type="text" required="" aria-required="true" value="${payChannel.channelId}" disabled="disabled">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">渠道名称：</label>
            <div class="col-sm-8">
                <input id="channelName" name="channelName" class="form-control" type="text" required="" aria-required="true" value="${payChannelNameMap[payChannel.channelName]}" disabled="disabled">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">渠道商户ID：</label>
            <div class="col-sm-8">
                <input id="channelMchId" name="channelMchId" class="form-control" type="text" required="" aria-required="true" value="${payChannel.channelMchId}" disabled="disabled">
            </div>
        </div>



        <div class="form-group">
            <label class="col-sm-3 control-label">渠道状态：</label>
            <div class="col-sm-8">
                <c:if test="${payChannel.channelStatus == 1}">
                    <input id="channelStatus" name="channelStatus" class="form-control" type="text" required="" aria-required="true" value="正常" disabled="disabled">
                </c:if>
                <c:if test="${payChannel.channelStatus == 0}">
                    <input id="channelStatus" name="channelStatus" class="form-control" type="text" required="" aria-required="true" value="停用" disabled="disabled">
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">渠道参数：</label>
            <div class="col-sm-8">
                <textarea id="param" name="param" class="form-control" required="" aria-required="true"  disabled="disabled">${payChannel.param}</textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">创建时间：</label>
            <div class="col-sm-8">
                <input id="createTime" name="createTime" class="form-control" type="text" required="" aria-required="true" value="<fmt:formatDate value="${payChannel .createTime}" pattern="yyyy/MM/dd HH:mm:ss"/>" disabled="disabled">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">更新时间：</label>
            <div class="col-sm-8">
                <input id="updateTime" name="updateTime" class="form-control" type="text" required="" aria-required="true" value="<fmt:formatDate value="${payChannel.updateTime}" pattern="yyyy/MM/dd HH:mm:ss"/>" disabled="disabled">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-8 col-sm-offset-3">
            </div>
        </div>
    </form>
