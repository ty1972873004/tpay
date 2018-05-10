<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-04-16
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付回调</title>
</head>
<body>
<form name=\"punchout_form\" method=\"post\" action=\"https://openapi.alipaydev.com/gateway.do?charset=UTF-8&method=alipay.trade.wap.pay&sign=xCivFsTH6mKz4BG6fn%2BMSmYs1LvTswbfhbcoejHe%2FJgV8U1bxZMagiGUYRqxT2ZxhhBSj3aCxjVe72uBvfWo3IKNQsRCvFWHq6%2BSJDEspA6SslewM%2FknhAJKahWQpuLQnxdpuQ8KJTgIr67VBK5z7nM2mgdhlbbdr%2F1NOm9t011z4jhW%2FdxSCc3yMiVZS2aE5bALo5hEQUGJ5tDFMn4qyljVeaJ69ZlZBKyfwf8puR3xlsyySJEEuIF6wjSBbQE5PJ9mN0PnB9986fKuF5bN4kVFGltVeKY534tdR8R6pVPRmplODMKCStJJ5rIIX%2B4uECQRnUIB%2FDyEiwF5jbUxsA%3D%3D&notify_url=http%3A%2F%2Fsys.javaxxw.cn%2Fpay%2FasyRequestForAliPay&version=1.0&app_id=2016080300154678&sign_type=RSA2&timestamp=2018-05-10+15%3A35%3A54&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json\">\n<input type=\"hidden\" name=\"biz_content\" value=\"{&quot;out_trade_no&quot;:&quot;2018051015354210000016&quot;,&quot;total_amount&quot;:&quot;1&quot;,&quot;subject&quot;:&quot;tPay测试交易&quot;,&quot;product_code&quot;:&quot;QUICK_WAP_PAY&quot;}\">\n<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n</form>\n<script>document.forms[0].submit();</script>
</body>
</html>
