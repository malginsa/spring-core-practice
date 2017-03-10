package ua.epam.spring.core.finaltest.Q11;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class SomeService implements InitializingBean, DisposableBean {

    private final SomeComponent component;

    public SomeService(SomeComponent component) {
        this.component = component;
    }

    public void init() {
        System.out.println("a");
    }

    public void close() {
        System.out.println("b");
    }

//    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("c");
    }

//    @Override
    public void destroy() throws Exception {
        System.out.println("d");
    }

    public void call() {
        component.print();
    }
}
