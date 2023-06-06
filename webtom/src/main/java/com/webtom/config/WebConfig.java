package com.webtom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration // Web.xml 대체
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override // root-context.xml을 대신하는 클래스 지정하지만 예제는 RootConfig를 사용하므로 다음과 같이 변경
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return null;
    }
}
