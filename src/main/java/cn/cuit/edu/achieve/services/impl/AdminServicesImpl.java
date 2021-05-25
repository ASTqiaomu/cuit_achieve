package cn.cuit.edu.achieve.services.impl;

import cn.cuit.edu.achieve.bean.Admin;
import cn.cuit.edu.achieve.dao.AdminDao;
import cn.cuit.edu.achieve.services.AdminServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Admin业务层接口实现
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class AdminServicesImpl
 * @date 2021/5/21 14:01
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AdminServicesImpl implements AdminServices {
    @Resource
    private AdminDao adminDao;

    @Override
    public List<Admin> selectAll(Admin admin) {
        return adminDao.selectAll(admin);
    }
}
