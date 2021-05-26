package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.Admin;
import cn.cuit.edu.achieve.bean.LogLogin;
import cn.cuit.edu.achieve.bean.User;
import cn.cuit.edu.achieve.service.AdminServices;
import cn.cuit.edu.achieve.service.LogLoginServices;
import cn.cuit.edu.achieve.service.UserServices;
import cn.cuit.edu.achieve.util.CharacterEncoding;
import cn.cuit.edu.achieve.util.JsonToHashMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录控制层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LoginController
 * @date 2021/5/21 13:17
 */
@CrossOrigin
@RestController
public class LoginController {
    @Resource
    private AdminServices adminServices;
    @Resource
    private UserServices userServices;
    @Resource
    private LogLoginServices logLoginServices;

    /**
     * 消除魔法值
     */
    final String adminMainStr = "adminMain";
    final String userMainStr = "userMain";
    final String adminStr = "admin";
    final String userStr = "user";
    final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
    final String LOCALHOST_IPV4 = "127.0.0.1";

    HashMap<String, Object> map = null;

    /**
     * 登录
     * @method login
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/21 13:25
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     */
    @RequestMapping("/login")
    @ResponseBody
    public boolean login(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request,response,"UTF-8");
        map = JsonToHashMap.getHashMap(data);
        String userName = map.get("userName").toString();
        String password = map.get("password").toString();
        String loginType = map.get("loginType").toString();
        String ip = request.getRemoteAddr();
        if (ip.equals(LOCALHOST_IPV6)) {
            ip = LOCALHOST_IPV4;
        }
        boolean loginSuccess = false;

        if (loginType.equals(adminStr)) {
            // 管理员登录
            Admin admin = new Admin();
            admin.setAdminName(userName);
            admin.setAdminPassword(password);
            // 查找用户名和密码都匹配的管理员
            List<Admin> list = adminServices.selectAll(admin, null);
            if (list.size() == 1) {
                HttpSession session = request.getSession();
                // 获取到查询出的admin对象，随后往会话中设置属性
                admin = list.get(0);
                session.setAttribute("admin", admin);
                loginSuccess = true;
            }
        } else if (loginType.equals(userStr)) {
            // 用户登录
            User user = new User();
            user.setUserName(userName);
            user.setUserPassword(password);
            // 查找用户名和密码都匹配的用户
            List<User> list = userServices.selectAll(user, null);
            if (list.size() == 1) {
                HttpSession session = request.getSession();
                // 获取到查询出的user对象，随后往会话中设置属性
                user = list.get(0);
                session.setAttribute("user", user);
                loginSuccess = true;
            }
        }

        if (loginSuccess) {
            // 插入登录日志
            LogLogin logLogin = new LogLogin();
            logLogin.setLoginName(userName);
            logLogin.setLoginIp(ip);
            Integer updates = logLoginServices.insertLogLogin(logLogin);
            if (updates != 1) {
                System.out.println("插入登录日志失败！");
            }
        }
        return loginSuccess;
    }

    /**
     * 获取登录的管理员或用户
     * @method getLoginAdmin
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/23 0:04
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    @RequestMapping("/getLoginStatus")
    @ResponseBody
    public Map<String, Object> getLoginAdmin(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request,response,"UTF-8");
        HttpSession session = request.getSession();
        Map<String, Object> map = null;
        if (data.equals(adminMainStr)) {
            // 如果请求的页面是管理员页
            Admin admin = (Admin) session.getAttribute("admin");
            map = new HashMap<>(3);
            map.put("code", 0);
            map.put("Id", admin.getAdminId());
            map.put("Name", admin.getAdminName());
        } else if (data.equals(userMainStr)) {
            // 如果请求的页面是普通页
            User user = (User) session.getAttribute("user");
            map = new HashMap<>(3);
            map.put("code", 0);
            map.put("Id", user.getUserId());
            map.put("Name", user.getUserTrueName());
        }
        return map;
    }

    /**
     * 退出
     * @method logout
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/22 23:45
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return java.lang.Integer
     */
    @RequestMapping("/logout")
    @ResponseBody
    public Integer logout(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request,response,"UTF-8");
        HttpSession session = request.getSession();
        if (data.equals(adminMainStr)) {
            // 如果请求的页面是管理员页
            Admin admin = (Admin) session.getAttribute("admin");
            if (admin != null) {
                session.removeAttribute("admin");
            }
            return 0;
        } else if (data.equals(userMainStr)) {
            // 如果请求的页面是用户页
            User user = (User) session.getAttribute("user");
            if (user != null) {
                session.removeAttribute("user");
            }
            return 0;
        }
        return 1;
    }
}
