package ua.epam.spring.core.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("client")
public class Client {

    private String id;
    private String fullName;
    private String greeting;

    @Autowired
    public Client(@Value("${id}") String id,
                  @Value("${name}") String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Autowired
    public void setGreeting(@Value("${greeting}")
                                        String greeting) {
        this.greeting = greeting;
    }
}
