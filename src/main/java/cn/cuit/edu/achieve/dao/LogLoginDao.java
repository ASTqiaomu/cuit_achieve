package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.LogLogin;
import org.apache.ibatis.annotations.Insert;

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
     * @param logLogin LogLogin
     * @return java.util.List<LogLogin>
     */
    List<LogLogin> selectAll(LogLogin logLogin);

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
}
