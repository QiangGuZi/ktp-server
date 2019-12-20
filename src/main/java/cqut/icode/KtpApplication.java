package cqut.icode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tq
 * @date 2019/12/18
 */
@SpringBootApplication
@MapperScan(value = "cqut.icode.system.mapper")
public class KtpApplication {
    public static void main(String[] args) {
        SpringApplication.run(KtpApplication.class, args);
    }
}
