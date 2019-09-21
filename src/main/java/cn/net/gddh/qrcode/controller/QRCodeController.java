package cn.net.gddh.qrcode.controller;

import cn.net.gddh.qrcode.utils.QRCodeUtils;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/qrcode")
public class QRCodeController {
    @RequestMapping(value = "/generateqrcode", method = RequestMethod.GET)
    @ResponseBody
    public void generateQRCode4Product(HttpServletRequest request, HttpServletResponse response) {
//        String longUrl;
        try {
//            longUrl = "https://www.jianshu.com/u/c0aa31157ba5";
            // 转换成短url
//            String shortUrl = ShortNetAddressUtils.generateShortUrl(longUrl);
            // 生成二维码
            BitMatrix qRcodeImg = QRCodeUtils.generateQRCodeStream("http://www.zhyle.com/", response);
            // 将二维码输出到页面中
            MatrixToImageWriter.writeToStream(qRcodeImg, "png", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
