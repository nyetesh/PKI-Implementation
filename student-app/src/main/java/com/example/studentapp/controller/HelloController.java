package com.example.studentapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Nitesh Poudel
 */
@RestController
@RequestMapping(value = "hello")
public class HelloController {

    public String hello() {
        return "hello world";
    }
}
