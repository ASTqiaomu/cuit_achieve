package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.bean.Result;
import cn.cuit.edu.achieve.bean.ResultType;
import cn.cuit.edu.achieve.service.ResultService;
import cn.cuit.edu.achieve.service.ResultTypeService;
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
import java.util.HashMap;
import java.util.List;

import static cn.cuit.edu.achieve.util.CharacterEncoding.setEncoding;

/**
 * ResultType控制层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class ResultTypeController
 * @date 2021/6/1 16:07
 */
@CrossOrigin
@RestController
public class ResultTypeController {
    @Resource
    ResultTypeService resultTypeService;
    @Resource
    ResultService resultService;

    final String comboboxStr = "combobox";

    /**
     * 获取成果类型
     * @method getResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 16:45
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return void
     */
    @RequestMapping("/getResultType")
    @ResponseBody
    public void getResultType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        String type = request.getParameter("type");
        String typeName = request.getParameter("typeName");
        PageBean pageBean = null;
        List<ResultType> list;
        int total;
        if (!comboboxStr.equals(type)){
            Integer page = Integer.parseInt(request.getParameter("page"));
            Integer rows = Integer.parseInt(request.getParameter("rows"));
            pageBean = new PageBean(page, rows);
        }
        if (comboboxStr.equals(type)) {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("typeId", "");
            jsonObject.put("typeName", "请选择...");
            jsonArray.add(jsonObject);
            // 往json数组中添加刚刚的json对象，以及查出的成果类型名称和Id
            jsonArray.addAll(resultTypeService.selectAll(null, null));
            Response.write(response, jsonArray);
        }else {
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

    /**
     * 检查成果类型名是否存在
     * @method haveResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 17:03
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     */
    @RequestMapping("/haveResultType")
    @ResponseBody
    public boolean haveResultType(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        HashMap<String, Object> map =  JsonToHashMap.getHashMap(data);
        String typeName = map.get("typeName").toString();
        ResultType resultType = new ResultType();
        resultType.setTypeName(typeName);
        List<ResultType> list = resultTypeService.selectAll(resultType, null);;
        return list.size() != 0;
    }

    /**
     * 新增成果类型
     * @method addUserResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 17:35
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     */
    @RequestMapping("/addUserResultType")
    @ResponseBody
    public boolean addUserResultType(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setEncoding(request,response,"UTF-8");
        boolean addSuccess = false;
        String typeName = request.getParameter("typeName");
        String typeDesc = request.getParameter("typeDesc");
        Integer typeScore = Integer.parseInt(request.getParameter("typeScore"));
        ResultType resultType = new ResultType();
        resultType.setTypeName(typeName);
        if (!"".equals(typeDesc)){
            resultType.setTypeDesc(typeDesc);
        }
        resultType.setTypeScore(typeScore);
        Integer updates = resultTypeService.insertResultType(resultType);
        if (updates==1){
            // 新增一行数据代表插入成功
            addSuccess = true;
        }
        return addSuccess;
    }

    /**
     * 更新成果类型
     * @method updateResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 18:21
     * @param data java.lang.String
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return boolean
     */
    @RequestMapping("/updateResultType")
    @ResponseBody
    public boolean updateResultType(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setEncoding(request,response,"UTF-8");
        boolean updateSuccess = false;
        String typeId = request.getParameter("typeId");
        String typeDesc = request.getParameter("typeDesc");
        Integer typeScore = Integer.parseInt(request.getParameter("typeScore"));
        ResultType resultType = new ResultType();
        if (!"".equals(typeId) && typeId!=null){
            resultType.setTypeId(Integer.parseInt(typeId));
        }
        if (!"".equals(typeDesc)){
            resultType.setTypeDesc(typeDesc);
        }
        resultType.setTypeScore(typeScore);
        Integer updates = resultTypeService.updateResultType(resultType);
        if (updates==1){
            // 新增一行数据代表插入成功
            updateSuccess = true;
        }
        return updateSuccess;
    }

    /**
     * 删除成果类型
     * @method deleteResultType
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/6/1 18:22
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @return void
     */
    @RequestMapping("/deleteResultType")
    @ResponseBody
    public void deleteResultType(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        String ids = request.getParameter("ids");
        String[] str = ids.split(",");
        boolean canDel = true;
        String delTypeName = null;
        Result res = new Result();
        for (String value : str) {
            // 将要删除的成果类型Id获取到并封装到res对象中，随后查找该成果类型下是否有成果
            res.setTypeId(Integer.parseInt(value));
            List<Result> list = resultService.selectAll(res, null);
            // 如果成果不为0，即该成果类型下是有成果的，则并记录不删除的状态以及成果类型的名字
            if (list.size() != 0) {
                canDel = false;
                delTypeName = list.get(0).getTypeName();
                break;
            }
        }
        if (canDel){
            // 如果删除
            Integer num = 0;
            for (String s : str) {
                num += resultTypeService.deleteResultType(Integer.parseInt(s));
            }
            result.put("success", "true");
            result.put("num", num);
        }else {
            // 如果不删除
            result.put("success", "false");
            result.put("delTypeName", delTypeName);
        }
        Response.write(response, result);
    }
}
