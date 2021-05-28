package cn.cuit.edu.achieve.service.impl;

import cn.cuit.edu.achieve.bean.Admin;
import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.dao.AdminDao;
import cn.cuit.edu.achieve.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Admin业务层接口实现
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class AdminServiceImpl
 * @date 2021/5/21 14:01
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    @Override
    public List<Admin> selectAll(Admin admin, PageBean pageBean) {
        return adminDao.selectAll(admin, pageBean);
    }

    @Override
    public Integer update(Admin admin) {
        return adminDao.update(admin);
    }
}
