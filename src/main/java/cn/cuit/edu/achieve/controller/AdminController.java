package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.tools.Tools;
import cn.cuit.edu.achieve.bean.Admin;
import cn.cuit.edu.achieve.services.AdminServices;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 管理员控制层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class AdminController
 * @date 2021/5/21 10:47
 */
@CrossOrigin
@RestController
public class AdminController {
    @Resource
    AdminServices adminServices;

    HashMap<String, Object> map = null;
    @RequestMapping("/adminChangePw")
    @ResponseBody
    public boolean adminChangePw(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        boolean changeSuccess = false;
        map = Tools.JsonToHashMap.getHashMap(data);
        String oldPassword = map.get("oldPassword").toString();
        String newPassword = map.get("newPassword").toString();
        HttpSession session = request.getSession();
        // 获取session中的admin对象
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin.getAdminPassword().equals(oldPassword)){
            admin.setAdminPassword(newPassword);
            if (adminServices.update(admin)==1){
                //如果修改数据库行数为1，则修改成功，往session中设置新的admin对象
                changeSuccess = true;
                session.setAttribute("admin",admin);
            }
        }
        return changeSuccess;
    }
}
