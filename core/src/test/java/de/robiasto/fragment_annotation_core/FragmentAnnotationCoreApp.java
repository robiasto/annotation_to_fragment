package de.robiasto.fragment_annotation_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FragmentAnnotationCoreApp {

    public static void main(String[] args) {
        System.out.println("test main");
        SpringApplication.run(FragmentAnnotationCoreApp.class);
    }

}
