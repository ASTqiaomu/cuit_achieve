package cn.cuit.edu.achieve.service.impl;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.ResultType;
import cn.cuit.edu.achieve.dao.ResultTypeDao;
import cn.cuit.edu.achieve.service.ResultTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * ResultType业务层接口实现
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class ResultTypeServiceImpl
 * @date 2021/6/1 16:06
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ResultTypeServiceImpl implements ResultTypeService {
    @Resource
    ResultTypeDao resultTypeDao;

    @Override
    public List<ResultType> selectAll(ResultType resultType, PageBean pageBean) {
        return resultTypeDao.selectAll(resultType,pageBean);
    }

    @Override
    public Integer insertResultType(ResultType resultType) {
        return resultTypeDao.insertResultType(resultType);
    }

    @Override
    public Integer updateResultType(ResultType resultType) {
        return resultTypeDao.updateResultType(resultType);
    }

    @Override
    public Integer deleteResultType(Integer typeId) {
        return resultTypeDao.deleteResultType(typeId);
    }
}
