package cn.cuit.edu.achieve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 设置项目启动页
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class MyIndexPage
 * @date 2021/5/20 17:56
 */
@Controller
public class MyIndexPage {
    @RequestMapping("/")
    public String hello(){
        return "forward:login.html";
    }
}
