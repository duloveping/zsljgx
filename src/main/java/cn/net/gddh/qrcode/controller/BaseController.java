package cn.net.gddh.qrcode.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @GetMapping("error")
    public ModelAndView handleError(HttpServletRequest request){
        // 获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode.equals(401)){
            return new ModelAndView("error/401");
        }else if(statusCode.equals(404)){
            return new ModelAndView("error/404");
        }else if(statusCode.equals(403)){
            return new ModelAndView("error/403");
        }else{
            return new ModelAndView("error/500");
        }
    }
    public JSONObject result(Boolean status, String info) {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("info", StringUtils.trimToEmpty(info));
        return json;
    }

    public JSONObject resultSuccess() {
        return resultSuccess("成功");
    }

    public JSONObject resultSuccess(String info) {
        return result(Boolean.TRUE, info);
    }

    public JSONObject resultError() {
        return resultError("失败");
    }

    public JSONObject resultError(String info) {
        return result(Boolean.FALSE, info);
    }
}
