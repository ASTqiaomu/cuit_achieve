package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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
}
