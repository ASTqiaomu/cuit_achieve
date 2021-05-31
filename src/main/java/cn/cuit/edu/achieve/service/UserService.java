package cn.cuit.edu.achieve.service;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.User;
import cn.cuit.edu.achieve.bean.UserVO;

import java.util.List;

/**
 * User业务层接口
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class UserService
 * @date 2021/5/22 21:35
 */
public interface UserService {
    /**
     * 多条件查询，对象user中的非空参数都将作为筛选条件
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/22 21:36
     * @param user cn.cuit.edu.achieve.bean.User
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.User>
     */
    List<User> selectAll(User user, PageBean pageBean);

    /**
     * 更新数据，对象user中的非空参数都将作为更新后的数据
     * @method update
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/28 17:31
     * @param user cn.cuit.edu.achieve.bean.User
     * @return java.lang.Integer
     */
    Integer update(User user);

    /**
     * 多表模糊查询，连接t_user和t_college，模糊查找支持用户名和真实姓名
     * @method selectUserAndCollege
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/29 10:50
     * @param user cn.cuit.edu.achieve.bean.User
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.User>
     */
    List<UserVO> selectUserAndCollege(User user, PageBean pageBean);

    /**
     * 新增用户
     * @method insertUser
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 19:44
     * @param user cn.cuit.edu.achieve.bean.User
     * @return java.lang.Integer
     */
    Integer insertUser(User user);

    /**
     * 重置用户密码为123456
     * @method resetUserPassword
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 20:07
     * @param userId java.lang.Integer
     * @return java.lang.Integer
     */
    Integer resetUserPassword(Integer userId);

    /**
     * 删除用户
     * @method deleteUser
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 20:17
     * @param userId java.lang.Integer
     * @return java.lang.Integer
     */
    Integer deleteUser(Integer userId);
}
