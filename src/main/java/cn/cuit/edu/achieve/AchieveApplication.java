package cn.cuit.edu.achieve;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @version 1.0.0
 * @class AchieveApplication
 * @date 2021/5/10 14:40
 * @description 项目启动类
 */

@MapperScan(basePackages = {"cn.cuit.edu.achieve.dao"})
@SpringBootApplication
@ServletComponentScan
public class AchieveApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AchieveApplication.class, args);
        System.out.println("正在启动...");
        /*获取项目本地地址 begin*/
        String port = (String) context.getBean("port");
        String path = (String) context.getBean("path");
        String url = "http://localhost:" + port + path;
        System.out.println(url);
        System.out.println("应用已经准备就绪!");
        /*获取项目本地地址 end*/
    }
}
