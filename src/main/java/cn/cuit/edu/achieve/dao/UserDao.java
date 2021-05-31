package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.User;
import cn.cuit.edu.achieve.bean.UserVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

    /**
     * user多表模糊查询，见src/main/resources/mapper/UserMapper.xml
     * @method selectUserAndCollege
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/29 10:18
     * @param user cn.cuit.edu.achieve.bean.User
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.UserVO>
     */
    List<UserVO> selectUserAndCollege(User user, PageBean pageBean);

    /**
     * 新增用户
     * @method insertUser
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 18:41
     * @param user cn.cuit.edu.achieve.bean.User
     * @return java.lang.Integer
     */
    @Insert("insert into t_user(userName,userPassword," +
        "userTrueName,userScore,userSex,userPhone,collegeId) " +
        "values(#{userName},#{userPassword}," +
        "#{userTrueName},#{userScore},#{userSex}," +
        "#{userPhone},#{collegeId})")
    Integer insertUser(User user);

    /**
     * 重置用户密码为123456
     * @method resetUserPassword
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 20:06
     * @param userId java.lang.Integer
     * @return java.lang.Integer
     */
    @Update("update t_user set userPassword='123456' where userId=#{userId}")
    Integer resetUserPassword(Integer userId);

    /**
     * 删除用户
     * @method deleteUser
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 20:18
     * @param userId java.lang.Integer
     * @return java.lang.Integer
     */
    @Delete("delete from t_user where userId=#{userId}")
    Integer deleteUser(Integer userId);
}
