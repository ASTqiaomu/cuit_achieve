package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.User;

import java.util.List;

/**
 * User数据访问层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class UserDao
 * @date 2021/5/22 21:38
 */
public interface UserDao {
    /**
     * 多条件查询，见src/main/resources/mapper/UserMapper.xml
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/22 21:39
     * @param user cn.cuit.edu.achieve.bean.User
     * @return java.util.List<cn.cuit.edu.achieve.bean.User>
     */
    List<User> selectAll(User user);
}
