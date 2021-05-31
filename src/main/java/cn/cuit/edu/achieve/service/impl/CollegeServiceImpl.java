package cn.cuit.edu.achieve.service.impl;

import cn.cuit.edu.achieve.bean.College;
import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.dao.CollegeDao;
import cn.cuit.edu.achieve.service.CollegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * College业务层接口实现
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class CollegeServiceImpl
 * @date 2021/5/30 16:01
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CollegeServiceImpl implements CollegeService {
    @Resource
    CollegeDao collegeDao;

    @Override
    public List<College> selectAll(College college, PageBean pageBean){
        return collegeDao.selectAll(college,pageBean);
    }

    @Override
    public Integer insertCollege(College college) {
        return collegeDao.insertCollege(college);
    }

    @Override
    public Integer updateCollege(College college) {
        return collegeDao.updateCollege(college);
    }
}
