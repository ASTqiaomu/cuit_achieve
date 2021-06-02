package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.Result;

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
}
