package com.example.ecomm.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class test {
    @GetMapping("/test")
    public String test() {
        return "Backend Running";
    }
}