package ua.epam.spring.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AwareBean implements ApplicationContextAware {

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
    }

    private void init(){
    }
}
