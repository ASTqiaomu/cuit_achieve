package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @version 1.0.0
 * @class Result_type
 * @date 2021/5/10 15:15
 * @description t_result_type
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Result_type {
    Integer typeId;
    String typeName;
    String typeDesc;
    /**成果类型绩效分*/
    Integer typeScore;
}
