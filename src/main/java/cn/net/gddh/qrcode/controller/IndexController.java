package cn.net.gddh.qrcode.controller;

import cn.net.gddh.qrcode.utils.QRCodeUtils;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("list")
    public ModelAndView list() {
        return new ModelAndView("index");
    }

    @GetMapping("single")
    public ModelAndView single() {
        return new ModelAndView("single");
    }
}
