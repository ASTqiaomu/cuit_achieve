package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * t_log_verify
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogVerify
 * @date 2021/5/10 15:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LogVerify {
    Integer verifyId;
    Integer resId;
    /**审核状态，0申请，1通过，2拒绝*/
    Integer verifyType;
    String verifyDesc;
    Integer adminId;
    Date verifyDate;

    /**
     * 获取审核日期，指定格式为 2016-07-06 19:39:58
     * @method getLoginDate
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 20:18
     * @return java.lang.String
     */
    public String getVerifyDate(){
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dFormat.format(this.verifyDate);
    }
}
