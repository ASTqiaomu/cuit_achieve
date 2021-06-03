package cn.cuit.edu.achieve.service.impl;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.Result;
import cn.cuit.edu.achieve.dao.ResultDao;
import cn.cuit.edu.achieve.service.ResultService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Result业务层接口实现
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class ResultServiceImpl
 * @date 2021/6/2 12:29
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ResultServiceImpl implements ResultService {
    @Resource
    ResultDao resultDao;

    @Override
    public List<Result> selectAll(Result result, PageBean pageBean) {
        return resultDao.selectAll(result, pageBean);
    }

    @Override
    public Integer insertResult(Result result) {
        return resultDao.insertResult(result);
    }

    @Override
    public Integer updateResult(Result result) {
        return resultDao.updateResult(result);
    }

    @Override
    public Integer deleteResult(Integer resId) {
        return resultDao.deleteResult(resId);
    }

    @Override
    public Integer updateResFile(Result result) {
        return resultDao.updateResFile(result);
    }
}
