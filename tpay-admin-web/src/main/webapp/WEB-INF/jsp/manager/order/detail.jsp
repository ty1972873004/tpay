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
            <label class="col-sm-3 control-label">平台订单号：</label>
            <div class="col-sm-8">
                <input id="payOrderNo" name="payOrderNo" class="form-control" type="text" required="" aria-required="true" value="${payOrder.payOrderNo}" disabled="disabled"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户号：</label>
            <div class="col-sm-8">
                <input id="mchId" name="mchId" class="form-control" type="text" required="" aria-required="true" value="${payOrder.mchId}" disabled="disabled"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商户订单号：</label>
            <div class="col-sm-8">
                <input id="mchOrderNo" name="mchOrderNo" class="form-control" type="text" required="" aria-required="true" value="${payOrder.mchOrderNo}" disabled="disabled"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">订单金额：</label>
            <div class="col-sm-8">
                <input id="amount" name="amount" class="form-control" type="text" required="" aria-required="true" value="<myTag:money money="${payOrder.amount}" />" disabled="disabled"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">金额类型：</label>
            <div class="col-sm-8">
                <input id="currency" name="currency" class="form-control" type="text" required="" aria-required="true" value="${payOrder.currency}" disabled="disabled"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">订单类型：</label>
            <div class="col-sm-8">
                <input id="payStatus" name="payStatus" class="form-control" type="text" required="" aria-required="true" value="${payStatusMap[payOrder.payStatus]}" disabled="disabled"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">商品标题：</label>
            <div class="col-sm-8">
                <input id="subject" name="subject" class="form-control" type="text" required="" aria-required="true" value="${payOrder.subject}" disabled="disabled"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">商品内存：</label>
            <div class="col-sm-8">
                <input id="body" name="body" class="form-control" type="text" required="" aria-required="true" value="${payOrder.body}" disabled="disabled"/>
            </div>
        </div>

        <div class="form-group">
        <label class="col-sm-3 control-label">渠道商户id：</label>
        <div class="col-sm-8">
            <input id="channelMchId" name="channelMchId" class="form-control" type="text" required="" aria-required="true" value="${payOrder.channelMchId}" disabled="disabled"/>
        </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">渠道支付流水号：</label>
            <div class="col-sm-8">
                <input id="channelOrderNo" name="channelOrderNo" class="form-control" type="text" required="" aria-required="true" value="${payOrder.channelOrderNo}" disabled="disabled"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-3 control-label">错误代码：</label>
            <div class="col-sm-8">
                <input id="errCode" name="errCode" class="form-control" type="text" required="" aria-required="true" value="${payOrder.errCode}" disabled="disabled"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">错误消息：</label>
            <div class="col-sm-8">
                <input id="errMsg" name="errMsg" class="form-control" type="text" required="" aria-required="true" value="${payOrder.errMsg}" disabled="disabled"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">扩展参数：</label>
            <div class="col-sm-8">
                <input id="extra" name="extra" class="form-control" type="text" required="" aria-required="true" value="${payOrder.extra}" disabled="disabled"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">异步通知地址：</label>
            <div class="col-sm-8">
                <input id="notifyUrl" name="notifyUrl" class="form-control" type="text" required="" aria-required="true" value="${payOrder.notifyUrl}" disabled="disabled"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">异步通知次数：</label>
            <div class="col-sm-8">
                <input id="notifyCount" name="notifyCount" class="form-control" type="text" required="" aria-required="true" value="${payOrder.notifyCount}" disabled="disabled"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">支付成功时间：</label>
            <div class="col-sm-8">
                <input id="paySuccessTime" name="paySuccessTime" class="form-control" type="text" required="" aria-required="true" value="<fmt:formatDate value="${payOrder.paySuccessTime}" pattern="yyyy/MM/dd HH:mm:ss"/>" disabled="disabled"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-8 col-sm-offset-3">
            </div>
        </div>
    </form>
