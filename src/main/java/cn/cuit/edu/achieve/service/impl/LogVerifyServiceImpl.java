package cn.cuit.edu.achieve.service.impl;

import cn.cuit.edu.achieve.bean.LogVerify;
import cn.cuit.edu.achieve.bean.LogVerifyVO;
import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.dao.LogVerifyDao;
import cn.cuit.edu.achieve.service.LogVerifyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogVerifyServiceImpl
 * @date 2021/6/3 20:15
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class LogVerifyServiceImpl implements LogVerifyService {
    @Resource
    LogVerifyDao logVerifyDao;

    @Override
    public List<LogVerify> selectAll(LogVerify logVerify, PageBean pageBean) {
        return logVerifyDao.selectAll(logVerify, pageBean);
    }

    @Override
    public List<LogVerifyVO> selectUserAndCollege(LogVerifyVO logVerifyVO, PageBean pageBean) {
        return logVerifyDao.selectUserAndCollege(logVerifyVO,pageBean);
    }

    @Override
    public Integer insertLogVerify(LogVerify logVerify) {
        return logVerifyDao.insertLogVerify(logVerify);
    }
}
