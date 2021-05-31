package cn.cuit.edu.achieve.service;

import cn.cuit.edu.achieve.bean.College;
import cn.cuit.edu.achieve.bean.PageBean;

import java.util.List;

/**
 * College业务层接口
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class CollegeService
 * @date 2021/5/30 15:59
 */
public interface CollegeService {
    /**
     * 多条件查询，对象college中的非空参数都将作为筛选条件
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/30 16:00
     * @param college cn.cuit.edu.achieve.bean.College
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.College>
     */
    List<College> selectAll(College college, PageBean pageBean);

    /**
     * 插入学院，有效参数为college里的collegeName(不能为空)和collegeDesc
     * @method insertCollege
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 23:44
     * @param college cn.cuit.edu.achieve.bean.College
     * @return java.lang.Integer
     */
    Integer insertCollege(College college);

    /**
     * 更新学院信息
     * @method updateCollege
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 0:36
     * @param college cn.cuit.edu.achieve.bean.College
     * @return java.lang.Integer
     */
    Integer updateCollege(College college);
}
