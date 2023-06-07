package com.tom.webtomcat.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages= {"com.tom.webtomcat.controller"})
public class ServletConfig implements WebMvcConfigurer {
    // 기존의 servlet-context.xml 을 클래스로 작성
    
    
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
            bean.setViewClass(JstlView.class);
            bean.setPrefix("/WEB-INF/views/"); // 위치 Prefix 위치
            bean.setSuffix(".jsp");   //형식      suffix  형식
            registry.viewResolver(bean);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

}
