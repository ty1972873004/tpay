package com.tpay.upms.controller.admin;



import com.tpay.base.controller.BaseController;
import com.tpay.common.utils.Request2ModelUtil;
import com.tpay.common.utils.WebUtil;
import com.tpay.dao.plugins.util.Page;
import com.tpay.sys.model.SysDic;
import com.tpay.sys.service.SysDicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tuyong
 * @version 1.0
 * @DESCRIPTION
 * @create 2017-08-11 15:22
 **/

@Controller
@RequestMapping("/dict")
public class SysDictController extends BaseController {

    @Autowired
    private SysDicService sysDicService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String toList(Long id, ModelMap modelMap){
        modelMap.put("id",id);
        return "/manager/dict/list.jsp";
    }

    @ResponseBody
    @RequestMapping("findByPage")
    public Page<SysDic> findByPage(String pageNow,
                                   String pageSize, String column, HttpServletRequest request) throws Exception {
        Map<String, Object> params = WebUtil.getParameterMap(request);
        params.put("pageNum", pageNow);
        params.put("pageSize", pageSize);
        params.put("enable", "1");
        params.put("orderBy", "sort_no");
        Page<SysDic> list=sysDicService.query(params);
        return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(Long indexId,ModelMap modelMap){
        modelMap.put("indexId",indexId);
        return "/manager/dict/add.jsp";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(ModelMap model, HttpServletRequest request, SysDic sysDic){
        sysDic = Request2ModelUtil.covert(SysDic.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (sysDic.getCreateBy() == null) {
            sysDic.setCreateBy(uid == null ? 1 : uid);
        }
        if (sysDic.getUpdateBy() == null) {
            sysDic.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            sysDic.setUpdateBy(uid);
        }
        sysDicService.insertOrUpdate(sysDic);
        return setSuccessModelMap(model);
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap modelMap){
        SysDic sysDic = sysDicService.queryById(id);
        modelMap.put("sysDic",sysDic);
        return "/manager/dict/edit.jsp";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(ModelMap model, HttpServletRequest request, SysDic sysDic){
        sysDic = Request2ModelUtil.covert(SysDic.class,request);
        Long uid = WebUtil.getCurrentUser();
        if (sysDic.getCreateBy() == null) {
            sysDic.setCreateBy(uid == null ? 1 : uid);
        }
        if (sysDic.getUpdateBy() == null) {
            sysDic.setUpdateBy(uid == null ? 1 : uid);
        } else if (uid != null) {
            sysDic.setUpdateBy(uid);
        }
        sysDicService.insertOrUpdate(sysDic);
        return setSuccessModelMap(model);
    }

    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids, ModelMap modelMap) {
        String[] idArray = ids.split("-");
        int count = 0;
        for (String idStr : idArray) {
            Long id = Long.parseLong(idStr);
            SysDic sysDic = new SysDic();
            sysDic.setId(id);
            int result = sysDicService.deleteById(sysDic);
            count += result;
        }
        return setSuccessModelMap(modelMap,count);
    }

}
