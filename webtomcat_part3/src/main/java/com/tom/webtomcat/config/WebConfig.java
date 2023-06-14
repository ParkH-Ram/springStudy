package com.tom.webtomcat.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static long MAX_FILE_SIZE = 10 * 1024 * 1024;


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };  // 기본 경로 설정
    }

    //  Multipart-config 설정을 MultipartConfigElement 객체로 등록  servlet 3버전 이상 설정 방법
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration){
        MultipartConfigElement multipartConfig = new MultipartConfigElement("C:\\upload\\tmp", MAX_FILE_SIZE, MAX_FILE_SIZE, 0);
        registration.setMultipartConfig(multipartConfig);
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }


}
