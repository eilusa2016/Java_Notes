package com.xjb;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *默认的读取同级别以及该级别之下的所有包
 */
@SpringBootApplication
public class GirlApplication {

    public static void main(String[] args) {
        //SpringApplication  app= new SpringApplication(GirlApplication.class);
        //app.setBannerMode(Banner.Mode.OFF); //关闭启动时显示的文字
        //app.run(args);

        //默认的启动方式
        SpringApplication.run(GirlApplication.class, args);


    }
}
