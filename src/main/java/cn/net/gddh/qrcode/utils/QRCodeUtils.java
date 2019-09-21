package cn.net.gddh.qrcode.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码图片流
 * @author 广东鼎和科技有限公司 - 杜永生
 * @version 2019年9月20日
 */
public class QRCodeUtils {
    private static final Logger LOG = LoggerFactory.getLogger(QRCodeUtils.class);

    public static BitMatrix generateQRCodeStream(String content, HttpServletResponse response) {
        //给相应添加头部信息，主要告诉浏览器返回的是图片流
        response.setHeader("Cache-Control", "no-store");
        // 不设置缓存
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        //设置图片的文字编码以及内边框
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        //编码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //边框距
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix;
        try {
            //参数分别为：编码内容、编码类型、图片宽度、图片高度，设置参数
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 250, 250, hints);
        } catch (WriterException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
        return bitMatrix;

    }
}
