package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.College;
import cn.cuit.edu.achieve.bean.PageBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * College数据访问层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class CollegeDao
 * @date 2021/5/30 15:48
 */
public interface CollegeDao {
    /**
     * 多条件查询，见src/main/resources/mapper/CollegeMapper.xml
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/30 15:51
     * @param college College
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<College>
     */
    List<College> selectAll(College college, PageBean pageBean);

    /**
     * 插入学院信息，学院名不能为空
     * @method insertCollege
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 23:43
     * @param college cn.cuit.edu.achieve.bean.College
     * @return java.lang.Integer
     */
    @Insert("insert into t_college(collegeName,collegeDesc) values(#{collegeName},#{collegeDesc})")
    Integer insertCollege(College college);

    /**
     * 更新学院信息
     * @method updateCollege
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 0:35
     * @param college cn.cuit.edu.achieve.bean.College
     * @return java.lang.Integer
     */
    @Update("update t_college set collegeName=#{collegeName},collegeDesc=#{collegeDesc} " +
        "where collegeId=#{collegeId}")
    Integer updateCollege(College college);
}
