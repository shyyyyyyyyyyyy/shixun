package com.situ.lession1226.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class TextController {

    @GetMapping("/text")
    public String list()
    {
        return "/text";
    }
}
