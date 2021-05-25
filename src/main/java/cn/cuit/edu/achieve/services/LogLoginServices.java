package cn.cuit.edu.achieve.services;

import cn.cuit.edu.achieve.bean.LogLogin;

import java.util.List;

/**
 * LogLogin业务层接口
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogLoginServices
 * @date 2021/5/23 16:00
 */
public interface LogLoginServices {
    /**
     * 多条件查询，对象logLogin中的非空参数都将作为筛选条件
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/23 16:01
     * @param logLogin cn.cuit.edu.achieve.bean.LogLogin
     * @return java.util.List<cn.cuit.edu.achieve.bean.LogLogin>
     */
    List<LogLogin> selectAll(LogLogin logLogin);

    /**
     * 插入登录日志
     * @method insertLogLogin
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/24 13:20
     * @param logLogin cn.cuit.edu.achieve.bean.LogLogin
     * @return java.lang.Integer
     */
    Integer insertLogLogin(LogLogin logLogin);
}
