package cn.cuit.edu.achieve.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogVerifyVO
 * @date 2021/6/3 19:45
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LogVerifyVO extends LogVerify {
    String adminName;
    String resName;
    Integer userId;
    String userTrueName;
    String collegeName;
    Integer typeId;
    String typeName;

    @Override
    public String getVerifyDate() {
        return super.getVerifyDate();
    }

    @Override
    public String toString() {
        return "LogVerifyVO{" +
            "verifyId=" + verifyId +
            ", resId=" + resId +
            ", verifyType=" + verifyType +
            ", verifyDesc='" + verifyDesc + '\'' +
            ", adminId=" + adminId +
            ", verifyDate=" + verifyDate +
            ", adminName='" + adminName + '\'' +
            ", resName='" + resName + '\'' +
            ", userId=" + userId +
            ", userTrueName='" + userTrueName + '\'' +
            ", collegeName='" + collegeName + '\'' +
            ", typeId=" + typeId +
            ", typeName='" + typeName + '\'' +
            '}';
    }
}
