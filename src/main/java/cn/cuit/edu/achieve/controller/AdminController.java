package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.Admin;
import cn.cuit.edu.achieve.service.AdminService;
import cn.cuit.edu.achieve.util.CharacterEncoding;
import cn.cuit.edu.achieve.util.JsonToHashMap;
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
    AdminService adminService;

    HashMap<String, Object> map = null;

    /**
     * 管理员修改密码
     * @method adminChangePw
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 18:05
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     */
    @RequestMapping("/adminChangePw")
    @ResponseBody
    public boolean adminChangePw(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request,response,"UTF-8");
        boolean changeSuccess = false;
        map = JsonToHashMap.getHashMap(data);
        String oldPassword = map.get("oldPassword").toString();
        String newPassword = map.get("newPassword").toString();
        HttpSession session = request.getSession();
        // 获取session中的admin对象
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin.getAdminPassword().equals(oldPassword)){
            admin.setAdminPassword(newPassword);
            if (adminService.update(admin)==1){
                //如果修改数据库行数为1，则修改成功，往session中设置新的admin对象
                changeSuccess = true;
                session.setAttribute("admin",admin);
            }
        }
        return changeSuccess;
    }
}
