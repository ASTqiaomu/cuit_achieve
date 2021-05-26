package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 分页实体
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class PageBean
 * @date 2021/5/26 17:27
 */

 @Data
 @NoArgsConstructor
 @AllArgsConstructor
 @Component
public class PageBean {
    Integer page;
    Integer rows;
}
