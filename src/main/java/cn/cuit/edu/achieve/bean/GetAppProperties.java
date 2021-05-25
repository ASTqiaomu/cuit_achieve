package cn.cuit.edu.achieve.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 获取配置文件值
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class GetAppProperties
 * @date 2021/5/10 15:31:41
 */
@Configuration
public class GetAppProperties {
    /**获取配置文件application.properties中的端口号port*/
    @Bean
    public String port(@Value("${server.port}") String port) {
        return port;
    }

    /**获取配置文件application.properties中的上下文路径path*/
    @Bean
    public String path(@Value("${server.servlet.context-path}") String path) {
        return path;
    }
}
