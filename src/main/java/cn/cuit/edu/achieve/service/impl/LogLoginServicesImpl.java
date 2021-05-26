package cn.cuit.edu.achieve.service.impl;

import cn.cuit.edu.achieve.bean.LogLogin;
import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.dao.LogLoginDao;
import cn.cuit.edu.achieve.service.LogLoginServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * LogLogin业务层接口实现
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogLoginServicesImpl
 * @date 2021/5/23 16:02
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class LogLoginServicesImpl implements LogLoginServices {
    @Resource
    LogLoginDao logLoginDao;

    @Override
    public List<LogLogin> selectAll(LogLogin logLogin, PageBean pageBean) {
        return logLoginDao.selectAll(logLogin, pageBean);
    }

    @Override
    public Integer insertLogLogin(LogLogin logLogin) {
        return logLoginDao.insertLogLogin(logLogin);
    }

    @Override
    public Integer selectCounts() {
        return logLoginDao.selectCounts();
    }

    @Override
    public Integer deleteById(Integer logId) {
        return logLoginDao.deleteById(logId);
    }
}
