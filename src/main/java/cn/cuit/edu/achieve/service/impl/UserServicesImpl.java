package cn.cuit.edu.achieve.service.impl;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.User;
import cn.cuit.edu.achieve.dao.UserDao;
import cn.cuit.edu.achieve.service.UserServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * User业务层接口实现
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class UserServicesImpl
 * @date 2021/5/22 21:37
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServicesImpl implements UserServices {
    @Resource
    private UserDao userDao;

    @Override
    public List<User> selectAll(User user, PageBean pageBean) {
        return userDao.selectAll(user, pageBean);
    }
}