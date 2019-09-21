package cn.net.gddh.qrcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(
		exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@MapperScan("cn.net.gddh.qrcode.dao")
@EnableAsync
@EnableTransactionManagement
public class QrcodeApplication {
	public static void main(String[] args) {
		SpringApplication.run(QrcodeApplication.class, args);
	}

}
