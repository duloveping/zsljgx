package cn.net.gddh.qrcode;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 修改启动类，继承SpringBootServletInitializer类并重写configure方法
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里的参数要指向原先用main方法执行的Application启动类
        return builder.sources(QrcodeApplication.class);
    }
}
