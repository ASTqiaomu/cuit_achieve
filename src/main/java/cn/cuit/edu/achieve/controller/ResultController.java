package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.*;
import cn.cuit.edu.achieve.service.*;
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
    @Resource
    LogVerifyService logVerifyService;

    final String adminMainStr = "adminMain";
    final String userMainStr = "userMain";
    final String addStr = "add";
    final String updateStr = "update";
    final String verifyStr = "verify";

    /**
     * 获取成果类型
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return void
     * @method getResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 16:45
     */
    @RequestMapping("/getResult")
    @ResponseBody
    public void getResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer rows = Integer.parseInt(request.getParameter("rows"));
        PageBean pageBean = new PageBean(page, rows);
        String resName = request.getParameter("resName");
        String typeId = request.getParameter("typeId");
        String userId = request.getParameter("userId");
        String resStatus = request.getParameter("resStatus");
        Result res = new Result();
        if (!"".equals(resName)) {
            res.setResName(resName);
        }
        if (!"".equals(typeId) && typeId!=null) {
            res.setTypeId(Integer.parseInt(typeId));
        }
        if (!"".equals(userId) && userId!=null){
            res.setUserId(Integer.parseInt(userId));
        }
        if (!"".equals(resStatus) && resStatus!=null){
            res.setResStatus(Integer.parseInt(resStatus));
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

    /**
     * 添加或者更新成果信息
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     * @method addResult
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 9:51
     */
    @RequestMapping("/addResult")
    @ResponseBody
    public boolean addResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean success = false;
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        String type = request.getParameter("type");
        String resName = request.getParameter("resName");
        String resDesc = request.getParameter("resDesc");
        String typeId = request.getParameter("typeId");
        String userId = request.getParameter("userId");
        String resId = request.getParameter("resId");
        String resStatus = request.getParameter("resStatus");
        String verifyDesc = request.getParameter("verifyDesc");
        String adminId = request.getParameter("adminId");
        Result res = new Result();
        if (addStr.equals(type)) {
            // 新增成果信息
            // 封装申请人
            res.setUserId(Integer.parseInt(userId));
            // 获取用户真实姓名并封装
            User user = new User();
            user.setUserId(Integer.parseInt(userId));
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
            // 封装成果绩效分
            res.setTypeScore(resultType.getTypeScore());
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
                success = true;
            }
        } else if (updateStr.equals(type)){
            // 更新成果信息
            res.setResId(Integer.parseInt(resId));
            res.setResName(resName);
            res.setResDesc(resDesc);
            res.setTypeId(Integer.parseInt(typeId));
            ResultType resultType = new ResultType();
            resultType.setTypeId(Integer.parseInt(typeId));
            List<ResultType> list = resultTypeService.selectAll(resultType, null);
            resultType = list.get(0);
            res.setTypeName(resultType.getTypeName());
            // 封装成果状态，0为申请
            res.setResStatus(0);
            Integer update = resultService.updateResult(res);
            if (update == 1) {
                success = true;
            }
        }else if(verifyStr.equals(type)){
            // 审核成果信息
            LogVerify logVerify = new LogVerify();
            if (!"".equals(resId) && resId != null){
                logVerify.setResId(Integer.parseInt(resId));
                res.setResId(Integer.parseInt(resId));
            }
            if (!"".equals(resStatus) && resStatus != null){
                logVerify.setVerifyType(Integer.parseInt(resStatus));
                res.setResStatus(Integer.parseInt(resStatus));

            }
            if (!"".equals(verifyDesc) && verifyDesc != null){
                logVerify.setVerifyDesc(verifyDesc);
            }
            if (!"".equals(adminId) && adminId != null){
                logVerify.setAdminId(Integer.parseInt(adminId));
            }
            // 更新成果状态
            Integer updates = resultService.updateResultStatus(res);
            // 插入审核日志
            Integer updates2 = logVerifyService.insertLogVerify(logVerify);
            if (updates==1&&updates2==1){
                success = true;
            }
        }
        return success;
    }

    /**
     * 删除成果
     * @method deleteResult
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/3 12:22
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return void
     */
    @RequestMapping("/deleteResult")
    @ResponseBody
    public void deleteResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");
        String[] str = ids.split(",");
        Integer num = 0;
        for (String s : str) {
            num += resultService.deleteResult(Integer.parseInt(s));
        }
        result.put("success", "true");
        result.put("num", num);
        Response.write(response, result);
    }
}
