package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * t_result_type
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class ResultType
 * @date 2021/5/10 15:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ResultType {
    Integer typeId;
    String typeName;
    String typeDesc;
    /**成果类型绩效分*/
    Integer typeScore;
}
