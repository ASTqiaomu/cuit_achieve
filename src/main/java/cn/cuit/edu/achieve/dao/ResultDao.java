package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.Result;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 更新结果信息
     * @method updateResult
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 10:20
     * @param result cn.cuit.edu.achieve.bean.Result
     * @return java.lang.Integer
     */
    @Update("update t_result set " +
        "resName=#{resName},resDesc=#{resDesc},typeId=#{typeId}," +
        "typeName=#{typeName},resDate=NOW() " +
        "where resId=#{resId}")
    Integer updateResult(Result result);

    /**
     * 删除成果
     * @method deleteResult
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 10:46
     * @param resId java.lang.Integer
     * @return java.lang.Integer
     */
    @Delete("delete from t_result where resId=#{resId}")
    Integer deleteResult(Integer resId);

    /**
     * 更新成果文件
     * @method updateResFile
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 13:22
     * @param result cn.cuit.edu.achieve.bean.Result
     * @return java.lang.Integer
     */
    @Update("update t_result set resFile=#{resFile} where resId=#{resId}")
    Integer updateResFile(Result result);
}
