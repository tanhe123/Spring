package net.xiayule.spring;


import net.xiayule.spring.core.io.ClassPathResource;
import org.junit.Assert;

import java.io.*;
import java.net.URI;
import java.net.URL;

/**
 * Created by tan on 14-12-15.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("spring-config.xml");

        BufferedReader reader = new BufferedReader(new InputStreamReader(classPathResource.getInputStream()));

        String t;
        while ((t = reader.readLine()) != null) {
            System.out.println(t);
        }

    }
}
