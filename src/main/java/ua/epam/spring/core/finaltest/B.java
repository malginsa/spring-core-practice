package ua.epam.spring.core.finaltest;

public class B {

    private C c;

    public B() {}

    public B(C c) {
        this.c = c;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public void init() {
        System.out.println("b is created");
    }
}
