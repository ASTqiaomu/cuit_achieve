package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.User;
import org.apache.ibatis.annotations.Update;

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
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.User>
     */
    List<User> selectAll(User user, PageBean pageBean);

    /**
     * 更新user数据
     * @method update
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/28 17:33
     * @param user cn.cuit.edu.achieve.bean.User
     * @return java.lang.Integer
     */
    @Update("Update t_user set " +
        "userId = #{userId}," +
        "userName = #{userName}," +
        "userPassword = #{userPassword}," +
        "userTrueName = #{userTrueName}," +
        "userScore = #{userScore}," +
        "userSex = #{userSex}," +
        "userPhone = #{userPhone}," +
        "collegeId = #{collegeId} " +
        "where userId = #{userId}")
    Integer update(User user);
}
