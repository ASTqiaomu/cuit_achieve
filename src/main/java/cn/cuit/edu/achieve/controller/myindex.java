package cn.cuit.edu.achieve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @version 1.0.0
 * @class myindex
 * @date 2021/5/20 17:56
 * @description 设置项目启动页
 */
@Controller
public class myindex {
    @RequestMapping("/")
    public String hello(){
        return "forward:login.html";
    }
}
