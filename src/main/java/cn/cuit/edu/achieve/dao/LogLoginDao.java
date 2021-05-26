package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.LogLogin;
import cn.cuit.edu.achieve.bean.PageBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * LogLogin数据访问层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogLoginDao
 * @date 2021/5/23 15:24
 */
public interface LogLoginDao {
    /**
     * 多条件查询，见src/main/resources/mapper/LogLoginMapper.xml
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/23 15:30
     * @param logLogin cn.cuit.edu.achieve.bean.LogLogin
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.LogLogin>
     */
    List<LogLogin> selectAll(LogLogin logLogin, PageBean pageBean);

    /**
     * 插入登录日志，只需要登录名和IP，日志id自增，登录时间数据库自动获取，返回成功插入的行数，正常为1行
     * @method insertLogLogin
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/23 21:19
     * @param logLogin cn.cuit.edu.achieve.bean.LogLogin
     * @return java.lang.Integer
     */
    @Insert("insert into t_log_login(loginName,loginIp) values(#{loginName},#{loginIp})")
    Integer insertLogLogin(LogLogin logLogin);

    /**
     * 获取登录日志数量
     * @method selectCounts
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 19:11
     * @return java.lang.Integer
     */
    @Select("select count(logId) from t_log_login")
    Integer selectCounts();

    /**
     * 按照logId删除日志
     * @method deleteById
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 21:07
     * @param logId java.lang.Integer
     * @return java.lang.Integer
     */
    @Delete("delete from t_log_login where logId=#{logId}")
    Integer deleteById(Integer logId);
}
