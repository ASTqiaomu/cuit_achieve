package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.ResultType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * ResultType数据访问层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class ResultTypeDao
 * @date 2021/6/1 15:57
 */
public interface ResultTypeDao {
    /**
     * 多条件查询，见src/main/resources/mapper/ResultTypeMapper.xml
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 16:03
     * @param resultType cn.cuit.edu.achieve.bean.ResultType
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.ResultType>
     */
    List<ResultType> selectAll(ResultType resultType, PageBean pageBean);

    /**
     * 新增成果类型
     * @method inserResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 16:49
     * @param resultType cn.cuit.edu.achieve.bean.ResultType
     * @return java.lang.Integer
     */
    @Insert("insert into t_result_type(typeName,typeDesc,typeScore) values(#{typeName},#{typeDesc},#{typeScore})")
    Integer insertResultType(ResultType resultType);

    /**
     * 更新成果类型
     * @method updateResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 17:39
     * @param resultType cn.cuit.edu.achieve.bean.ResultType
     * @return java.lang.Integer
     */
    @Update("update t_result_type set " +
        "typeDesc=#{typeDesc},typeScore=#{typeScore} " +
        "where typeId=#{typeId}")
    Integer updateResultType(ResultType resultType);

    /**
     * 删除成果类型
     * @method deleteResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 18:29
     * @param typeId java.lang.Integer
     * @return java.lang.Integer
     */
    @Delete("delete from t_result_type where typeId=#{typeId}")
    Integer deleteResultType(Integer typeId);
}
