package cn.cuit.edu.achieve.services;

import cn.cuit.edu.achieve.bean.User;

import java.util.List;

/**
 * User业务层接口
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class UserServices
 * @date 2021/5/22 21:35
 */
public interface UserServices {
    /**
     * 多条件查询，对象user中的非空参数都将作为筛选条件
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/22 21:36
     * @param user cn.cuit.edu.achieve.bean.User
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    List<User> selectAll(User user);
}
