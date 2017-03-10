package ua.epam.spring.core.finaltest.Q11;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class SomeComponent implements InitializingBean, DisposableBean {

    public void init() {
        System.out.println("e");
    }

    public void close() {
        System.out.println("f");
    }

//    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println("g");
    }

//    @Override
    public void destroy() throws Exception {
//        System.out.println("h");
    }

    public void print() {
        System.out.println("i");
    }
}
