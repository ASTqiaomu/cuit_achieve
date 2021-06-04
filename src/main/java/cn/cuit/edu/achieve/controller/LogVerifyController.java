package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.LogVerifyVO;
import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.service.LogVerifyService;
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
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LogVerifyController
 * @date 2021/6/3 20:35
 */
@CrossOrigin
@RestController
public class LogVerifyController {
    @Resource
    LogVerifyService logVerifyService;
    final String adminMainStr = "adminMain";
    final String userMainStr = "userMain";

    @RequestMapping("/getLogVerify")
    @ResponseBody
    public void getLogVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        String type = request.getParameter("type");
        String userId = request.getParameter("userId");
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer rows = Integer.parseInt(request.getParameter("rows"));
        PageBean pageBean = new PageBean(page, rows);
        String typeId = request.getParameter("typeId");
        LogVerifyVO logVerifyVO = new LogVerifyVO();
        if (!"".equals(typeId) && typeId != null){
            logVerifyVO.setTypeId(Integer.parseInt(typeId));
        }
        if (!"".equals(userId) && userId != null) {
            logVerifyVO.setUserId(Integer.parseInt(userId));
        }
        List<LogVerifyVO> list = logVerifyService.selectUserAndCollege(logVerifyVO, pageBean);
        int total = logVerifyService.selectUserAndCollege(logVerifyVO, null).size();
        JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
        JSONObject result = new JSONObject();
        result.put("rows", jsonArray);
        result.put("total", total);
        Response.write(response, result);
    }
}
