package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.ResultType;

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
}
