package org.chdzq.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author chdzq
 * @Date 2024/11/14
 */
@RestController
public class Test {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
