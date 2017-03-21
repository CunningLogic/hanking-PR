package com.hanking.pr.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanId){
        ApplicationContext applicationContext = getApplicationContext();
        return (T)applicationContext.getBean(beanId);
    }
    
}
