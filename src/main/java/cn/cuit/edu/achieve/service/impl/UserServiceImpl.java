package cn.cuit.edu.achieve.service.impl;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.User;
import cn.cuit.edu.achieve.bean.UserVO;
import cn.cuit.edu.achieve.dao.UserDao;
import cn.cuit.edu.achieve.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * User业务层接口实现
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class UserServiceImpl
 * @date 2021/5/22 21:37
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public List<User> selectAll(User user, PageBean pageBean) {
        return userDao.selectAll(user, pageBean);
    }

    @Override
    public Integer update(User user) {
        return userDao.update(user);
    }

    @Override
    public List<UserVO> selectUserAndCollege(User user, PageBean pageBean) {
        return userDao.selectUserAndCollege(user, pageBean);
    }

    @Override
    public Integer insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public Integer resetUserPassword(Integer userId) {
        return userDao.resetUserPassword(userId);
    }

    @Override
    public Integer deleteUser(Integer userId) {
        return userDao.deleteUser(userId);
    }
}