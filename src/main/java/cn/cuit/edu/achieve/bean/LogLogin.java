package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * t_log_login
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogLogin
 * @date 2021/5/20 17:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LogLogin {
    Integer logId;
    String loginName;
    String loginIp;
    Date loginDate;
}
