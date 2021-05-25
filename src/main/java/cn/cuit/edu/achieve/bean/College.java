package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * t_college
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class College
 * @date 2021/5/10 15:03
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
