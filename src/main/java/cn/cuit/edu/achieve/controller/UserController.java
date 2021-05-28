package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.User;
import cn.cuit.edu.achieve.service.UserService;
import cn.cuit.edu.achieve.util.CharacterEncoding;
import cn.cuit.edu.achieve.util.JsonToHashMap;
import cn.cuit.edu.achieve.util.Response;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * 用户控制层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class UserController
 * @date 2021/5/28 16:35
 */
@CrossOrigin
@RestController
public class UserController {
    @Resource
    UserService userService;

    HashMap<String, Object> map = null;

    /**
     * 获取用户
     * @method getUsers
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/28 18:00
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     */
    @RequestMapping("/getUserById")
    @ResponseBody
    public void getUserById(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request,response,"UTF-8");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        User user = new User();
        user.setUserId(userId);
        List<User> list = userService.selectAll(user,null);
        if (list.size()==1){
            JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
            JSONObject result = new JSONObject();
            result.put("rows", jsonArray);
            result.put("total", 1);
            Response.write(response, result);
        }
    }

    /**
     * 用户修改密码
     * @method userChangePw
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/28 17:59
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     */
    @RequestMapping("/userChangePw")
    @ResponseBody
    public boolean userChangePw(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request,response,"UTF-8");
        boolean changeSuccess = false;
        map = JsonToHashMap.getHashMap(data);
        String oldPassword = map.get("oldPassword").toString();
        String newPassword = map.get("newPassword").toString();
        HttpSession session = request.getSession();
        // 获取session中的user对象
        User user = (User) session.getAttribute("user");
        if (user.getUserPassword().equals(oldPassword)){
            user.setUserPassword(newPassword);
            if (userService.update(user)==1){
                //如果修改数据库行数为1，则修改成功，往session中设置新的admin对象
                changeSuccess = true;
                session.setAttribute("user",user);
            }
        }
        return changeSuccess;
    }
}
