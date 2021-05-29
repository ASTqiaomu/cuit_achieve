package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.LogLogin;
import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.service.LogLoginService;
import cn.cuit.edu.achieve.util.CharacterEncoding;
import cn.cuit.edu.achieve.util.Response;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登录日志控制层
 *
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogLoginController
 * @date 2021/5/26 15:11
 */
@CrossOrigin
@RestController
public class LogLoginController {
    @Resource
    LogLoginService logLoginService;

    /**
     * 获取登录日志
     * @method getLogLogin
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 18:12
     * @param request  javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return void
     */
    @RequestMapping("/getLogLogin")
    @ResponseBody
    public void getLogLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request,response,"UTF-8");
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer rows = Integer.parseInt(request.getParameter("rows"));
        PageBean pageBean = new PageBean(page, rows);
        List<LogLogin> list = logLoginService.selectAll(null, pageBean);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
        JSONObject result = new JSONObject();
        int total = logLoginService.selectCounts();
        result.put("rows", jsonArray);
        result.put("total", total);
        Response.write(response, result);
    }

    /**
     * 删除登录日志
     * @method deleteLogLogin
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/28 18:01
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return void
     */
    @RequestMapping("/deleteLogLogin")
    @ResponseBody
    public void deleteLogLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request,response,"UTF-8");
        String delIds = request.getParameter("delIds");
        JSONObject result = new JSONObject();
        String[] ids = delIds.split(",");
        Integer delNums = 0;
        for (String id : ids) {
            if (logLoginService.deleteById(Integer.parseInt(id)) == 1) {
                delNums++;
            }
        }
        result.put("success", "true");
        result.put("delNums", delNums);
        Response.write(response, result);
    }
}

