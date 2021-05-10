package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @version 1.0.0
 * @class Result
 * @date 2021/5/10 15:17
 * @description t_result
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Result {
    Integer resId;
    /**提交人*/
    Integer userId;
    String resName;
    String resDesc;
    /**成果提交日期*/
    Date resDate;
    /**成果状态，0为提交，1为通过，2为拒绝*/
    Integer resStatus;
    Integer typeId;
    String typeName;
    /**成果图片*/
    String resImg;
    /**成果材料*/
    String resFile;
}



