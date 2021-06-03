package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * t_result
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class Result
 * @date 2021/5/10 15:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Result {
    Integer resId;
    /**提交人*/
    Integer userId;
    String userTrueName;
    String collegeName;
    String resName;
    String resDesc;
    /**成果提交日期*/
    Date resDate;
    /**成果状态，0为提交，1为通过，2为拒绝*/
    Integer resStatus;
    Integer typeId;
    String typeName;
    /**成果文件*/
    String resFile;

    /**
     * 获取成果提交日期，指定格式为 2016-07-06 19:39:58
     * @method getLoginDate
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/2 16:58
     * @return java.lang.String
     */
    public String getResDate(){
        if (this.resDate==null){
            return null;
        }
        DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dFormat.format(this.resDate);
    }
}



