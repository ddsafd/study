package com.jjmm.study.controller.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
没有调用过，是启动的时候自动执行的。 没有spring是生成构造对象就运行……
这说明启动过程中调用了addInterceptors

 */
@Configuration//定义此类为配置类
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns拦截的路径  如果一个网址，即拦截又放行，结论是放行。
        String[] addPathPatterns = {"/**"};
        //excludePathPatterns排除的路径
        String[] excludePathPatterns = {
                "/index","/login","/js/**","/image/**","/asserts/css/**"
        };
        //创建用户拦截器对象并指定其拦截的路径和排除的路径
        registry.addInterceptor(new
                LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}