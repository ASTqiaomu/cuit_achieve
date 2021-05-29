package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.User;
import cn.cuit.edu.achieve.bean.UserVO;
import cn.cuit.edu.achieve.service.UserService;
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

import static cn.cuit.edu.achieve.util.CharacterEncoding.setEncoding;

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
     * 消除魔法值
     */
    final String FEMALE = "0";
    final String MAN = "1";

    /**
     * 查找用户，根据传入的Id、用户名、真实姓名、性别和学院Id
     * @method getUsers
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/28 18:00
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     */
    @RequestMapping("/getUsers")
    @ResponseBody
    public void getUsers(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setEncoding(request,response,"UTF-8");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer rows = Integer.parseInt(request.getParameter("rows"));
        User user = new User();
        user.setUserId(userId);
        PageBean pageBean = new PageBean(page, rows);
        List<UserVO> list = userService.selectUserAndCollege(user,pageBean);
        System.out.println(list);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
        JSONObject result = new JSONObject();
        result.put("rows", jsonArray);
        result.put("total", list.size());
        Response.write(response, result);
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
        setEncoding(request,response,"UTF-8");
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
                //如果修改数据库行数为1，则修改成功，往session中设置新的user对象
                changeSuccess = true;
                session.setAttribute("user",user);
            }
        }
        return changeSuccess;
    }

    /**
     * 更新用户信息
     * @method updateUserInfo
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/29 18:46
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public boolean updateUserInfo(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setEncoding(request,response,"UTF-8");
        boolean updateSuccess = false;
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        String userTrueName = request.getParameter("userTrueName");
        String userPhone = request.getParameter("userPhone");
        String userSex = request.getParameter("userSex");
        if (FEMALE.equals(userSex)){
            userSex = "女";
        }else if (MAN.equals(userSex)){
            userSex = "男";
        }else {
            // 当前端未选择性别时，设置为null值，方便后续处理
            userSex = null;
        }
        // 获取session中的user对象
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user.getUserId().equals(userId)){
            user.setUserTrueName(userTrueName);
            user.setUserPhone(userPhone);
            if (userSex!=null){
                user.setUserSex(userSex);
            }
            if (userService.update(user)==1){
                //如果修改数据库行数为1，则修改成功，往session中设置新的user对象
                updateSuccess = true;
                session.setAttribute("user",user);
            }
        }
        return updateSuccess;
    }
}
