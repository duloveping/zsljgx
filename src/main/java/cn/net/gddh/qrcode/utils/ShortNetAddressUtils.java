package cn.net.gddh.qrcode.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 长链接转短链接的工具类
 * @author 广东鼎和科技有限公司 - 杜永生
 * @version 2019年9月20日
 */
public class ShortNetAddressUtils {
    public static CloseableHttpClient httpclient;
    static {
        httpclient = HttpClients.createDefault();
    }

    public static String generateShortUrl(String url) {

        try {
            HttpPost httpost = new HttpPost("http://suo.im/api.php");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("format", "utf-8")); // 编码
            params.add(new BasicNameValuePair("url", url)); // 用户名称
            httpost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse response = httpclient.execute(httpost);
            String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }

    }
}
