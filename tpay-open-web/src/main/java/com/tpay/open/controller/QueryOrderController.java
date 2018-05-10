package com.tpay.open.controller;

import com.tpay.common.constants.WebConstants;
import com.tpay.common.utils.InstanceUtil;
import com.tpay.common.utils.TpayUtils;
import com.tpay.open.service.OrderService;
import com.tpay.open.service.QueryOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(value = "查询订单", description = "查询订单")
@RequestMapping(value = "/open/v1", method = RequestMethod.POST)
public class QueryOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private QueryOrderService queryOrderService;


    @ApiOperation(value="查询订单")
    @PostMapping("/queryOrder")
    public Object queryOrder(ModelMap modelMap,
                             @ApiParam(required = true, value = "商户ID") @RequestParam(value = "mchId", required =  true) String mchId,
                             @ApiParam(required = true, value = "商户订单号") @RequestParam(value = "mchOrderNo", required =  false) String mchOrderNo,
                             @ApiParam(required = true, value = "支付订单号") @RequestParam(value = "payOrderNo", required =  false) String payOrderNo,
                             @ApiParam(required = true, value = "签名") @RequestParam(value = "sign", required =  true) String sign) throws Exception{

        Map<String,String> signMap = InstanceUtil.newHashMap();
        signMap.put("mchId",mchId);

        /** 查询条件二选一（支付订单号优先） **/
        if(!StringUtils.isEmpty(payOrderNo)){
            signMap.put("payOrderNo",payOrderNo);
        }else if(!StringUtils.isEmpty(mchOrderNo)){
            signMap.put("mchOrderNo",mchOrderNo);
        }


        /** do check sign**/
        boolean verifyFlag = orderService.verifySign(signMap,sign);
        if(!verifyFlag){
            return  TpayUtils.setResultMap(String.valueOf(WebConstants.CHECK_FAIL),null,null);
        }


        Map<String,Object> resultMap = queryOrderService.doQueryOrder(mchId,mchOrderNo,payOrderNo);
        return TpayUtils.setSuccessResultMap(null,resultMap);

    }



}
