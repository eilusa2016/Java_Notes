package com.xjb.mymvc;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 自定义的springmvc配置
 */
@Configuration//申明这是一个配置
public class MySpringMvcConfig  extends WebMvcConfigurerAdapter{
    //增加自定义的拦截器,d第一种方法
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //自定义第一个拦截器
        //最好封装成类 这里测试教学
        HandlerInterceptor  interceptor_1=new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
                System.out.println("进入拦截器 ");
                return true;
            }

            @Override
            public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

            }
        };
        //添加并且注明拦截的请求
        registry.addInterceptor(interceptor_1).addPathPatterns("/**");
       // super.addInterceptors(registry);
    }




    //自定义消息转换器的第二种方法
    //第一种方法是在 @Configuration中  注明@Bean的方法上返回转换器类型
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter1=new StringHttpMessageConverter(Charset.forName("UTF-8"));
        converters.add(converter1);
       // super.configureMessageConverters(converters);
    }
}
