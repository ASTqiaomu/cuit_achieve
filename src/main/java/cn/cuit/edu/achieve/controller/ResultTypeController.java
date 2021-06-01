package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.ResultType;
import cn.cuit.edu.achieve.service.ResultTypeService;
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
 * ResultType控制层
 *
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class ResultTypeController
 * @date 2021/6/1 16:07
 */
@CrossOrigin
@RestController
public class ResultTypeController {
    @Resource
    ResultTypeService resultTypeService;

    @RequestMapping("/getResultType")
    @ResponseBody
    public void getResultType(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        String type = request.getParameter("type");
        String typeName = request.getParameter("typeName");
        PageBean pageBean = null;
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer rows = Integer.parseInt(request.getParameter("rows"));
        pageBean = new PageBean(page, rows);
        List<ResultType> list = null;
        Integer total = null;
        if (!"".equals(typeName)){
            ResultType resultType = new ResultType();
            resultType.setTypeName(typeName);
            list = resultTypeService.selectAll(resultType, pageBean);
            total = resultTypeService.selectAll(resultType, null).size();
        }else {
            list = resultTypeService.selectAll(null, pageBean);
            total = resultTypeService.selectAll(null, null).size();
        }
        JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
        JSONObject result = new JSONObject();
        result.put("rows", jsonArray);
        result.put("total", total);
        Response.write(response, result);
    }
}
