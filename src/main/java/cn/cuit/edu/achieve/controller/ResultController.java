package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.*;
import cn.cuit.edu.achieve.service.CollegeService;
import cn.cuit.edu.achieve.service.ResultService;
import cn.cuit.edu.achieve.service.ResultTypeService;
import cn.cuit.edu.achieve.service.UserService;
import cn.cuit.edu.achieve.util.CharacterEncoding;
import cn.cuit.edu.achieve.util.Response;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Result控制层
 *
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class ResultController
 * @date 2021/6/1 16:07
 */
@CrossOrigin
@RestController
public class ResultController {
    @Resource
    ResultService resultService;
    @Resource
    UserService userService;
    @Resource
    CollegeService collegeService;
    @Resource
    ResultTypeService resultTypeService;

    final String adminMainStr = "adminMain";
    final String userMainStr = "userMain";

    /**
     * 获取成果类型
     *
     * @param data     java.lang.String
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return void
     * @method getResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 16:45
     */
    @RequestMapping("/getResult")
    @ResponseBody
    public void getResult(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        String type = request.getParameter("type");
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer rows = Integer.parseInt(request.getParameter("rows"));
        PageBean pageBean = new PageBean(page, rows);
        String resName = request.getParameter("resName");
        String typeId = request.getParameter("typeId");
        if (type.equals(userMainStr)) {
            Integer userId = Integer.parseInt(request.getParameter("userId"));
            Result res = new Result();
            res.setUserId(userId);
            if (!"".equals(resName)) {
                res.setResName(resName);
            }
            if (typeId != null && !"".equals(typeId)) {
                res.setTypeId(Integer.parseInt(typeId));
            }
            List<Result> list = resultService.selectAll(res, pageBean);
            List<Result> listAll = resultService.selectAll(res, null);
            Integer total = listAll.size();
            JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
            JSONObject result = new JSONObject();
            result.put("rows", jsonArray);
            result.put("total", total);
            Response.write(response, result);
        }
    }

    @RequestMapping("/addResult")
    @ResponseBody
    public boolean addResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean addSuccess = false;
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        String type = request.getParameter("type");
        String resName = request.getParameter("resName");
        String resDesc = request.getParameter("resDesc");
        String typeId = request.getParameter("typeId");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        System.out.println("userId:"+userId);
        Result res = new Result();
        // 封装申请人
        res.setUserId(userId);
        // 获取用户真实姓名并封装
        User user = new User();
        user.setUserId(userId);
        List<User> list = userService.selectAll(user, null);
        user = list.get(0);
        res.setUserTrueName(user.getUserTrueName());
        // 封装成果类型Id
        if (!"".equals(typeId)) {
            res.setTypeId(Integer.parseInt(typeId));
        }
        // 获取成果类型名并封装
        ResultType resultType = new ResultType();
        resultType.setTypeId(Integer.parseInt(typeId));
        List<ResultType> list1 = resultTypeService.selectAll(resultType, null);
        resultType = list1.get(0);
        res.setTypeName(resultType.getTypeName());
        // 封装成果名称
        if (!"".equals(resName)) {
            res.setResName(resName);
        }
        // 封装成果说明
        if (!"".equals(resDesc)) {
            res.setResDesc(resDesc);
        }
        // 获取用户所属学院并封装
        Integer collegeId = user.getCollegeId();
        College college = new College();
        college.setCollegeId(collegeId);
        List<College> list2 = collegeService.selectAll(college, null);
        college = list2.get(0);
        res.setCollegeName(college.getCollegeName());
        // 封装成果状态，0为申请
        res.setResStatus(0);
        if (resultService.insertResult(res) == 1) {
            addSuccess = true;
        }
        System.out.println("addSuccess:"+addSuccess);
        return addSuccess;
    }
}
