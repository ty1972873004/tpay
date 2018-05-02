package com.tpay.admin.controller.admin;

import com.tpay.base.controller.BaseController;
import com.tpay.cache.dict.CacheUtils;
import com.tpay.common.constants.CacheConstants;
import com.tpay.common.constants.WebConstants;
import com.tpay.common.utils.StringUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.dao.plugins.util.Page;
import com.tpay.notify.enums.NotifyStatusEnum;
import com.tpay.notify.enums.NotifyTypeEnum;
import com.tpay.notify.model.NotifyRecord;
import com.tpay.notify.model.NotifyRecordLog;
import com.tpay.notify.service.NotifyRecordLogService;
import com.tpay.notify.service.NotifyRecordService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;

/**
 * @author tuyong
 * @version 1.0
 * @desc
 * @create 2018-04-27 16:36
 **/
@Controller
@RequestMapping("/notify")
@Api(value = "消息通知管理", description = "消息通知管理")
public class NotifyRecordController extends BaseController{

    @Autowired
    private NotifyRecordService notifyRecordService;

    @Autowired
    private NotifyRecordLogService notifyRecordLogService;

    @RequiresPermissions("manager:notify:read")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList(){
        return "/manager/notify/list.jsp";
    }


    @RequiresPermissions("manager:notify:read")
    @ResponseBody
    @RequestMapping("findByPage")
    public Page<NotifyRecord> findByPage(String pageNow,
                                         String pageSize, String column, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        params.put("pageNum", pageNow);
        params.put("pageSize", pageSize);
        params.put("enable", "1");
        params.put("orderBy", "id_");
        Page<NotifyRecord> list = notifyRecordService.query(params);
        return list;
    }

    @RequiresPermissions("manager:notify:read")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap, Map<String ,String> payStatusMap){

        //缓存中获取
        NotifyRecord notifyRecord = notifyRecordService.queryById(id);
        modelMap.put("notifyRecord",notifyRecord);
        return "/manager/notify/detail.jsp";
    }

    @RequiresPermissions("manager:notify:resend")
    @RequestMapping(value = "/resend/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object resend(ModelMap model, HttpServletRequest request,@PathVariable("id") Long id){
        NotifyRecord record = this.notifyRecordService.queryById(id);
        if(StringUtil.compare(record.getStatus(), NotifyStatusEnum.SUCCESS.getValue())){
            return setModelMap(model, WebConstants.REQUEST_FAIL,"该消息已经发送成功");
        }
        if(StringUtil.compare(record.getLimitNotifyTimes(),record.getNotifyTimes())){
            notifyRecordService.notifySend(record.getUrl(),record.getOrderNo(),record.getCustNo(),record.getNotifyType(), NotifyTypeEnum.getEnum(record.getNotifyType()).toString(),"消息重发--->原消息id"+record.getTid());
        }else{
            return setModelMap(model, WebConstants.REQUEST_FAIL,"未超过最大发送次数");
        }
        return setSuccessModelMap(model);
    }

    @RequestMapping(value = "/logList", method = RequestMethod.GET)
    public String toNotifyList(){
        return "/manager/notify/logList.jsp";
    }

    @RequiresPermissions("manager:notifyLog:read")
    @ResponseBody
    @RequestMapping("findLogByPage")
    public Page<NotifyRecordLog> findLogByPage(String pageNow,
                                               String pageSize, String column, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        params.put("pageNum", pageNow);
        params.put("pageSize", pageSize);
        params.put("enable", "1");
        params.put("orderBy", "id_");
        Page<NotifyRecordLog> list = this.notifyRecordLogService.query(params);
        return list;
    }

}
