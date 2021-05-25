package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * t_admin
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class Admin
 * @date 2021/5/10 14:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Admin {
    Integer adminId;
    String adminName;
    String adminPassword;
    /**权限等级，可选(0,1,2)，0最高*/
    Integer adminLevel;
}
