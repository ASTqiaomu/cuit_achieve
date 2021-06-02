package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.Result;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * Result数据访问层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class ResultTypeDao
 * @date 2021/6/2 12:25
 */
public interface ResultDao {
    /**
     * 多条件查询，见src/main/resources/mapper/ResultMapper.xml
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/2 12:27
     * @param result cn.cuit.edu.achieve.bean.Result
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.Result>
     */
    List<Result> selectAll(Result result, PageBean pageBean);

    /**
     * 新增结果
     * @method insertResult
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/2 23:08
     * @param result cn.cuit.edu.achieve.bean.Result
     * @return java.lang.Integer
     */
    @Insert("insert into t_result(userId,userTrueName," +
        "collegeName,resName,resDesc,resStatus,typeId,typeName) " +
        "values(#{userId},#{userTrueName},#{collegeName},#{resName}," +
        "#{resDesc},#{resStatus},#{typeId},#{typeName})")
    Integer insertResult(Result result);
}
