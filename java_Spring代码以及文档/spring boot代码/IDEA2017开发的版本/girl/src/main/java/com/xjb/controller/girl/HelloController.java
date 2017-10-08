package com.xjb.controller.girl;

import com.xjb.entity.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@RestController//相当于@Controller 和 @ResponseBody的组合注解
//@Controller
@RestController
public class HelloController {

    //获得配置文件中的属性
//    @Value("${spring.datasource.url}")
//    private String urls;

    @Autowired
    private Properties properties;


    @RequestMapping(value={"/hello","/hi"},method= RequestMethod.GET,produces="text/html")
    public String say(){
        //System.out.print(properties.toString());
       // return "Hello Spring Boot!";
        return "index";
    }


}