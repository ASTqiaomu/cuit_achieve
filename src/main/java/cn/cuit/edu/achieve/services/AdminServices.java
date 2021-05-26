package cn.cuit.edu.achieve.services;

import cn.cuit.edu.achieve.bean.Admin;

import java.util.List;

/**
 * Admin业务层接口
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class AdminServices
 * @date 2021/5/21 13:00
 */
public interface AdminServices {
    /**
     * 多条件查询，对象admin中的非空参数都将作为筛选条件
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/22 22:45
     * @param admin cn.cuit.edu.achieve.bean.Admin
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    List<Admin> selectAll(Admin admin);

    /**
     * 更新数据，对象admin中的非空参数都将作为更新后的数据
     * @method update
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 13:42
     * @param admin cn.cuit.edu.achieve.bean.Admin
     * @return java.lang.Integer
     */
    Integer update(Admin admin);
}
