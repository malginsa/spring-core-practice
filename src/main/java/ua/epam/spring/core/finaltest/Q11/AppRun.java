package ua.epam.spring.core.finaltest.Q11;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppRun {

    public static void main(String[] args) {
        ConfigurableApplicationContext appContext =
                new ClassPathXmlApplicationContext("Q11springContext.xml");

        SomeService service = appContext.getBean(SomeService.class);
        service.call();

        appContext.getBean("someComponent", SomeComponent.class);

        appContext.close();
    }
}
