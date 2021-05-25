package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * t_user
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class User
 * @date 2021/5/10 15:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class User {
    Integer userId;
    String userName;
    String userPassword;
    String userTrueName;
    Integer userScore;
    String userSex;
    String userPhone;
    Integer collegeId;
}
