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
        "collegeName,resName,resDesc,resStatus,typeId,typeName,typeScore) " +
        "values(#{userId},#{userTrueName},#{collegeName},#{resName}," +
        "#{resDesc},#{resStatus},#{typeId},#{typeName},#{typeScore})")
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

    /**
     * 更新成果审核状态
     * @method updateResultStatus
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/4 14:51
     * @param result cn.cuit.edu.achieve.bean.Result
     * @return java.lang.Integer
     */
    @Update("update t_result set resStatus=#{resStatus} where resId=#{resId}")
    Integer updateResultStatus(Result result);

    /**
     * 普通用户改新名字时，成果表中的申请人会不匹配，所以需要更新
     * @method updateUserTrueName
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/4 15:37
     * @param oldName java.lang.String
     * @param newName java.lang.String
     * @return java.lang.Integer
     */
    @Update("update t_result set userTrueName=#{newName} where userTrueName=#{oldName}")
    Integer updateUserTrueName(String oldName,String newName);

    /**
     * 时间范围查询，见src/main/resources/mapper/ResultMapper.xml
     * @method selectResultByDateRange
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/4 19:01
     * @param result cn.cuit.edu.achieve.bean.Result
     * @param startDate java.lang.String
     * @param endDate java.lang.String
     * @return java.util.List<cn.cuit.edu.achieve.bean.Result>
     */
    List<Result> selectResultByDateRange(Result result, String startDate, String endDate);
}
