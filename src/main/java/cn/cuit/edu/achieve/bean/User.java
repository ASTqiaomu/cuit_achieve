package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @version 1.0.0
 * @class User
 * @date 2021/5/10 15:08
 * @description t_user
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
