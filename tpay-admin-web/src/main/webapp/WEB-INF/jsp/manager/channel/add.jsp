<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
    <form class="form-horizontal m-t" id="form" name="form" method="post" action="${pageContext.request.contextPath}/channel/add" >
        <input type="hidden"  name="channelStatus" id="channelStatus">
        <div class="form-group">
            <label class="col-sm-3 control-label">商户ID：</label>
            <div class="col-sm-8">
                <input id="mchId" name="mchId" class="form-control" type="text" required="" aria-required="true"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">渠道ID：</label>
            <div class="col-sm-8">
                <select class="form-control m-b" name="channelId" id="channelId" style="font-size: 12px">
                    <c:forEach items="${payChannelMap}" var="map" >
                        <option value="${map.key}">${map.key}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">渠道名称：</label>
            <div class="col-sm-8">
                <select class="form-control m-b" name="channelName" id="channelName" style="font-size: 12px">
                    <c:forEach items="${payChannelNameMap}" var="map" >
                        <option value="${map.key}">${map.key}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">渠道商户ID：</label>
            <div class="col-sm-8">
                <input id="channelMchId" name="channelMchId" class="form-control" type="text" required="" aria-required="true"/>
            </div>
        </div>



        <div class="form-group">
            <label class="col-sm-3 control-label">是否启用：</label>
            <div class="col-sm-8">
                    <input type="checkbox" class="js-switch" checked name="status" id="status"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">渠道参数：</label>
            <div class="col-sm-8">
                <textarea id="param" name="param" class="form-control" required="" aria-required="true" ></textarea>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-8 col-sm-offset-3">
                <button class="btn btn-primary" type="submit">提交</button>
                <button class="btn btn-white" type="button" onclick="createDialog.close();">取消</button>
            </div>
        </div>
    </form>
<script src="${basePath}/resources/js/manager/channel/add.js"></script>