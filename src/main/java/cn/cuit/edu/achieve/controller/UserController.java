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
 *
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
    final String adminMainStr = "adminMain";
    final String userMainStr = "userMain";
    final String adminStr = "admin";
    final String userStr = "user";
    final String comboboxStr = "combobox";

    /**
     * 查找用户，根据传入的Id、用户名、真实姓名、性别和学院Id
     *
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     * @method getUsers
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/28 18:00
     */
    @RequestMapping("/getUsers")
    @ResponseBody
    public void getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        setEncoding(request, response, "UTF-8");
        String type = request.getParameter("type");
        User user = new User();
        if (comboboxStr.equals(type)) {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", "");
            jsonObject.put("userTrueName", "请选择...");
            jsonArray.add(jsonObject);
            // 往json数组中添加刚刚的json对象，以及查出的用户真实姓名和Id
            jsonArray.addAll(userService.selectAll(null, null));
            Response.write(response, jsonArray);
        } else {
            if (userMainStr.equals(type)) {
                Integer userId = Integer.parseInt(request.getParameter("userId"));
                user.setUserId(userId);
            }
            Integer page = Integer.parseInt(request.getParameter("page"));
            Integer rows = Integer.parseInt(request.getParameter("rows"));
            PageBean pageBean = new PageBean(page, rows);
            List<UserVO> list = userService.selectUserAndCollege(user, pageBean);
            JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
            JSONObject result = new JSONObject();
            result.put("rows", jsonArray);
            result.put("total", list.size());
            Response.write(response, result);
        }
    }

    /**
     * 用户修改密码
     * @param data     java.lang.String
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     * @method userChangePw
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/28 17:59
     */
    @RequestMapping("/userChangePw")
    @ResponseBody
    public boolean userChangePw(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setEncoding(request, response, "UTF-8");
        boolean changeSuccess = false;
        map = JsonToHashMap.getHashMap(data);
        String oldPassword = map.get("oldPassword").toString();
        String newPassword = map.get("newPassword").toString();
        HttpSession session = request.getSession();
        // 获取session中的user对象
        User user = (User) session.getAttribute("user");
        if (user.getUserPassword().equals(oldPassword)) {
            user.setUserPassword(newPassword);
            if (userService.update(user) == 1) {
                //如果修改数据库行数为1，则修改成功，往session中设置新的user对象
                changeSuccess = true;
                session.setAttribute("user", user);
            }
        }
        return changeSuccess;
    }

    /**
     * 更新用户信息
     *
     * @param data     java.lang.String
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     * @method updateUserInfo
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/29 18:46
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public boolean updateUserInfo(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setEncoding(request, response, "UTF-8");
        boolean updateSuccess = false;
        String type = request.getParameter("type");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        String userTrueName = request.getParameter("userTrueName");
        String userPhone = request.getParameter("userPhone");
        String userSex = request.getParameter("userSex");
        if (FEMALE.equals(userSex)) {
            userSex = "女";
        } else if (MAN.equals(userSex)) {
            userSex = "男";
        } else {
            // 当前端未选择性别时，设置为null值，方便后续处理
            userSex = null;
        }
        User user = new User();
        user.setUserId(userId);
        List<User> list = userService.selectAll(user, null);
        if (list.size() == 1) {
            user = list.get(0);
            user.setUserTrueName(userTrueName);
            user.setUserPhone(userPhone);
            if (userSex != null) {
                user.setUserSex(userSex);
            }
            // 如果是管理员，可编辑的信息会多出绩效分和学院
            if (adminMainStr.equals(type)) {
                Integer userScore = Integer.parseInt(request.getParameter("userScore"));
                Integer collegeId = Integer.parseInt(request.getParameter("collegeId"));
                user.setUserScore(userScore);
                user.setCollegeId(collegeId);
            }
            if (userService.update(user) == 1) {
                // 如果修改数据库行数为1，则修改成功
                updateSuccess = true;
                // 如果是用户发起的修改，则往session中设置新的user对象
                if (userMainStr.equals(type)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                }
            }
        }
        return updateSuccess;
    }

    /**
     * 检查用户名是否存在
     *
     * @param data     java.lang.String
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     * @method hasUser
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 20:19
     */
    @RequestMapping("/haveUser")
    @ResponseBody
    public boolean haveUser(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setEncoding(request, response, "UTF-8");
        HashMap<String, Object> map = JsonToHashMap.getHashMap(data);
        String userName = map.get("userName").toString();
        User user = new User();
        user.setUserName(userName);
        List<User> list = userService.selectAll(user, null);
        return list.size() != 0;
    }

    /**
     * 添加用户
     *
     * @param data     java.lang.String
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     * @method addUser
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 20:20
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public boolean addUser(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setEncoding(request, response, "UTF-8");
        boolean addSuccess = false;
        String userName = request.getParameter("userName");
        String userPassword = "123456";
        String userTrueName = request.getParameter("userTrueName");
        String userPhone = request.getParameter("userPhone");
        Integer userScore = Integer.parseInt(request.getParameter("userScore"));
        Integer collegeId = Integer.parseInt(request.getParameter("collegeId"));
        String userSex = request.getParameter("userSex");
        if (FEMALE.equals(userSex)) {
            userSex = "女";
        } else if (MAN.equals(userSex)) {
            userSex = "男";
        } else {
            // 当前端未选择性别时，设置为null值，方便后续处理
            userSex = null;
        }
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(userPassword);
        user.setUserTrueName(userTrueName);
        user.setUserPhone(userPhone);
        user.setUserScore(userScore);
        user.setCollegeId(collegeId);
        user.setUserSex(userSex);
        Integer updates = userService.insertUser(user);
        if (updates == 1) {
            // 新增一行数据代表插入成功
            addSuccess = true;
        }
        return addSuccess;
    }

    /**
     * 重置用户密码
     *
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return void
     * @method resetUserPassword
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 20:21
     */
    @RequestMapping("/resetUserPassword")
    @ResponseBody
    public void resetUserPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");
        String[] str = ids.split(",");
        Integer num = 0;
        for (String s : str) {
            num += userService.resetUserPassword(Integer.parseInt(s));
        }
        result.put("success", "true");
        result.put("num", num);
        Response.write(response, result);
    }

    /**
     * 删除用户
     *
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return void
     * @method deleteUser
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/31 20:22
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");
        String[] str = ids.split(",");
        Integer num = 0;
        for (String s : str) {
            num += userService.deleteUser(Integer.parseInt(s));
        }
        result.put("success", "true");
        result.put("num", num);
        Response.write(response, result);
    }
}
