package cn.cuit.edu.achieve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
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
