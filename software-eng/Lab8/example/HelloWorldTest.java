package cpe327.Lab8.example;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HelloWorldTest {
    private HelloWorld hello;

    @Before
    public void setUp() {
        hello = new HelloWorld();
    }

    @Test
    public void testSayHello() {
        assertEquals("failure - strings are not equal", "Hello Khajonpong", hello.sayHello("Khajonpong"));
    }
}
