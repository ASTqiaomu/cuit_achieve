package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.Admin;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Admin数据访问层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class AdminDao
 * @date 2021/5/21 10:51
 */
public interface AdminDao {
    /**
     * 多条件查询，见src/main/resources/mapper/AdminMapper.xml
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/21 14:54
     * @param admin cn.cuit.edu.achieve.bean.Admin
     * @return java.util.List<cn.cuit.edu.achieve.bean.Admin>
     */
    List<Admin> selectAll(Admin admin);

    @Update("Update t_admin set " +
        "adminId = #{adminId}," +
        "adminName = #{adminName})," +
        "adminPassword = #{adminPassword}," +
        "adminLevel = #{adminLevel}")
    Integer update(Admin admin);
}
