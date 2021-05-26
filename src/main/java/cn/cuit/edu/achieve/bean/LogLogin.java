package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    /**
     * 获取登录日期，指定格式为 2016-07-06 19:39:58
     * @method getLoginDate
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 20:18
     * @return java.lang.String
     */
    public String getLoginDate(){
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dFormat.format(this.loginDate);
    }
}
