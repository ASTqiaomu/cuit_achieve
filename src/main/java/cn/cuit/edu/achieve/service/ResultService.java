package cn.cuit.edu.achieve.service;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.Result;

import java.util.List;

/**
 * Result业务层接口
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class ResultService
 * @date 2021/6/2 12:28
 */
public interface ResultService {
    /**
     * 多条件查询
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/2 12:29
     * @param result cn.cuit.edu.achieve.bean.Result
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.Result>
     */
    List<Result> selectAll(Result result, PageBean pageBean);

    /**
     * 新增结果
     * @method insertResult
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/2 23:09
     * @param result cn.cuit.edu.achieve.bean.Result
     * @return java.lang.Integer
     */
    Integer insertResult(Result result);

    /**
     * 更新结果信息
     * @method updateResult
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 10:21
     * @param result cn.cuit.edu.achieve.bean.Result
     * @return java.lang.Integer
     */
    Integer updateResult(Result result);

    /**
     * 删除成果
     * @method deleteResult
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 10:47
     * @param resId java.lang.Integer
     * @return java.lang.Integer
     */
    Integer deleteResult(Integer resId);

    /**
     * 更新成果文件
     * @method updateResFile
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 13:22
     * @param result cn.cuit.edu.achieve.bean.Result
     * @return java.lang.Integer
     */
    Integer updateResFile(Result result);

    /**
     * 更新成果审核状态
     * @method updateResultStatus
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/4 14:52
     * @param result cn.cuit.edu.achieve.bean.Result
     * @return java.lang.Integer
     */
    Integer updateResultStatus(Result result);

    /**
     * 普通用户改新名字时，成果表中的申请人会不匹配，所以需要更新
     * @method updateUserTrueName
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/4 15:40
     * @param oldName java.lang.String
     * @param newName java.lang.String
     * @return java.lang.Integer
     */
    Integer updateUserTrueName(String oldName,String newName);

    /**
     * 时间范围查询
     * @method selectResultByDateRange
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/4 19:18
     * @param result cn.cuit.edu.achieve.bean.Result
     * @param startDate java.lang.String
     * @param endDate java.lang.String
     * @return java.util.List<cn.cuit.edu.achieve.bean.Result>
     */
    List<Result> selectResultByDateRange(Result result, String startDate, String endDate);
}
