package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @version 1.0.0
 * @class College
 * @date 2021/5/10 15:03
 * @description t_college
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class College {
    Integer collegeId;
    String collegeName;
    String collegeDesc;
}
