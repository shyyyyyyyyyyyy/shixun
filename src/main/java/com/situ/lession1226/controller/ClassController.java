package com.situ.lession1226.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/3
 * <p>
 * created by 千堆雪 on 2024/1/3, last modified by 千堆雪 on 2024/1/3
 */
@Controller
@RequestMapping("/class")
public class ClassController {

    /**
     * 跳转到班级列表页
     */
    @GetMapping("/list")
    public String list() {
        return "class/list";
    }
}
