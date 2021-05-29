package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * User展示类
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class UserVO
 * @date 2021/5/29 10:35
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserVO extends User{
    String collegeName;
    String collegeDesc;

    @Override
    public String toString() {
        return "UserVO{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userTrueName='" + userTrueName + '\'' +
            ", userScore=" + userScore +
            ", userSex='" + userSex + '\'' +
            ", userPhone='" + userPhone + '\'' +
            ", collegeId=" + collegeId +
            ", collegeName='" + collegeName + '\'' +
            ", collegeDesc='" + collegeDesc + '\'' +
            '}';
    }
}
