package cn.cuit.edu.achieve.service;

import cn.cuit.edu.achieve.bean.LogLogin;
import cn.cuit.edu.achieve.bean.PageBean;

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
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.LogLogin>
     */
    List<LogLogin> selectAll(LogLogin logLogin, PageBean pageBean);

    /**
     * 插入登录日志
     * @method insertLogLogin
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/24 13:20
     * @param logLogin cn.cuit.edu.achieve.bean.LogLogin
     * @return java.lang.Integer
     */
    Integer insertLogLogin(LogLogin logLogin);

    /**
     * 获取登录日志数量
     * @method selectCounts
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 19:13
     * @return java.lang.Integer
     */
    Integer selectCounts();

    /**
     * 按照logId删除日志
     * @method deleteById
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 21:08
     * @param logId java.lang.Integer
     * @return java.lang.Integer
     */
    Integer deleteById(Integer logId);
}
