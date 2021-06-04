package cn.cuit.edu.achieve.service;

import cn.cuit.edu.achieve.bean.LogVerify;
import cn.cuit.edu.achieve.bean.LogVerifyVO;
import cn.cuit.edu.achieve.bean.PageBean;

import java.util.List;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogVerifyService
 * @date 2021/6/3 20:14
 */
public interface LogVerifyService {
    /**
     * 多条件查询
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 20:14
     * @param logVerify cn.cuit.edu.achieve.bean.LogVerify
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.LogVerify>
     */
    List<LogVerify> selectAll(LogVerify logVerify, PageBean pageBean);

    /**
     * 封装了更多的属性的查询
     * @method selectUserAndCollege
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/4 1:04
     * @param logVerifyVO cn.cuit.edu.achieve.bean.LogVerifyVO
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.LogVerify>
     */
    List<LogVerifyVO> selectUserAndCollege(LogVerifyVO logVerifyVO, PageBean pageBean);

    /**
     * 插入审核日志
     * @method insertLogVerify
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/4 14:31
     * @param logVerify cn.cuit.edu.achieve.bean.LogVerify
     * @return java.lang.Integer
     */
    Integer insertLogVerify(LogVerify logVerify);
}
