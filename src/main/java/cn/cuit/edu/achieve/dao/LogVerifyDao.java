package cn.cuit.edu.achieve.dao;

import cn.cuit.edu.achieve.bean.LogVerify;
import cn.cuit.edu.achieve.bean.LogVerifyVO;
import cn.cuit.edu.achieve.bean.PageBean;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * LogVerifyDao数据访问层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogVerifyDao
 * @date 2021/6/3 20:12
 */
public interface LogVerifyDao {
    /**
     * 多条件查询，见src/main/resources/mapper/LogVerifyDaoMapper.xml
     * @method selectAll
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 20:13
     * @param logVerify cn.cuit.edu.achieve.bean.LogVerify
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.LogVerify>
     */
    List<LogVerify> selectAll(LogVerify logVerify, PageBean pageBean);

    /**
     * 封装了更多属性的查询
     * @method selectUserAndCollege
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/4 1:05
     * @param logVerifyVO cn.cuit.edu.achieve.bean.LogVerifyVO
     * @param pageBean cn.cuit.edu.achieve.bean.PageBean
     * @return java.util.List<cn.cuit.edu.achieve.bean.LogVerifyVO>
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
    @Insert("insert into t_log_verify(resId,verifyType,verifyDesc,adminId) " +
        "values(#{resId},#{verifyType},#{verifyDesc},#{adminId})")
    Integer insertLogVerify(LogVerify logVerify);
}
