package com.xkcoding.task.quartz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Jia Binbin
 * @version 1.0
 * @date 2020/5/25 14:50
 * @describe todo
 */
@Controller
public class IndexController {
    @GetMapping("index")
    public String index(){
        return "job";
    }
}
