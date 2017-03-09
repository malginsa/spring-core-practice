package ua.epam.spring.core.finaltest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppFinalTest {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {

        context = new ClassPathXmlApplicationContext("finaltest.xml");

        B b = (B) context.getBean("b");
        System.out.println(b.getStr());
    }

    private static void q2() {
        System.out.println("1");
//        B b1 = ((A) context.getBean("a")).getB();
        System.out.println("2");
        B b2 = (B) context.getBean("b");
        System.out.println("3");
//        B b3 = ((A) context.getBean("a")).getB();
        System.out.println("4");
        B b4 = (B) context.getBean("b");
        System.out.println("5");

//        System.out.println(b1);
        System.out.println(b2);
//        System.out.println(b3);
        System.out.println(b4);
    }
}
