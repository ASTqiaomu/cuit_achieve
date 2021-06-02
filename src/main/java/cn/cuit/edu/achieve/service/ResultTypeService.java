package cn.cuit.edu.achieve.service;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.ResultType;

import java.util.List;

/**
 * ResultType业务层接口
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class ResultTypeService
 * @date 2021/6/1 16:05
 */
public interface ResultTypeService {
    /**
     * 多条件查询，对象resultType中的非空参数都将作为筛选条件
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 16:05
     * @param resultType cn.cuit.edu.achieve.bean.ResultType
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.ResultType>
     */
    List<ResultType> selectAll(ResultType resultType, PageBean pageBean);

    /**
     * 新增成果类型
     * @method insertResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 17:03
     * @param resultType cn.cuit.edu.achieve.bean.ResultType
     * @return java.lang.Integer
     */
    Integer insertResultType(ResultType resultType);

    /**
     * 更新成果类型
     * @method updateResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 17:40
     * @param resultType cn.cuit.edu.achieve.bean.ResultType
     * @return java.lang.Integer
     */
    Integer updateResultType(ResultType resultType);

    /**
     * 删除成果类型
     * @method deleteResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 18:30
     * @param typeId java.lang.Integer
     * @return java.lang.Integer
     */
    Integer deleteResultType(Integer typeId);
}
