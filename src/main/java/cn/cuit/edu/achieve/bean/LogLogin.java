package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @version 1.0.0
 * @class LogLogin
 * @date 2021/5/20 17:33
 * @description t_log_login
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
