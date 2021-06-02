package cn.cuit.edu.achieve.controller;

import cn.cuit.edu.achieve.bean.College;
import cn.cuit.edu.achieve.bean.PageBean;
import cn.cuit.edu.achieve.service.CollegeService;
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

/**
 * 学院控制层
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class CollegeController
 * @date 2021/5/30 14:59
 */
@CrossOrigin
@RestController
public class CollegeController {
    @Resource
    CollegeService collegeService;

    final String comboboxStr = "combobox";

    @RequestMapping("/getColleges")
    @ResponseBody
    public void getColleges(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        String type = request.getParameter("type");
        PageBean pageBean = null;
        if (!comboboxStr.equals(type)){
            Integer page = Integer.parseInt(request.getParameter("page"));
            Integer rows = Integer.parseInt(request.getParameter("rows"));
            pageBean = new PageBean(page, rows);
        }

        if (comboboxStr.equals(type)) {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("collegeId", "");
            jsonObject.put("collegeName", "请选择...");
            jsonArray.add(jsonObject);
            // 往json数组中添加刚刚的json对象，以及查出的学院名称和Id
            jsonArray.addAll(collegeService.selectAll(null, null));
            Response.write(response, jsonArray);
        }else {
            List<College> list = collegeService.selectAll(null, pageBean);
            Integer total = collegeService.selectAll(null, null).size();
            JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
            JSONObject result = new JSONObject();
            result.put("rows", jsonArray);
            result.put("total", total);
            Response.write(response, result);
        }
    }

    @RequestMapping("/haveCollege")
    @ResponseBody
    public boolean haveCollege(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        HashMap<String,Object> map = JsonToHashMap.getHashMap(data);
        String collegeName = map.get("collegeName").toString();
        College college = new College();
        college.setCollegeName(collegeName);
        List<College> list = collegeService.selectAll(college,null);
        return list.size() != 0;
    }

    @RequestMapping("/addCollege")
    @ResponseBody
    public boolean addCollege(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        boolean addSuccess = false;
        String collegeName = request.getParameter("collegeName");
        String collegeDesc = request.getParameter("collegeDesc");
        College college = new College();
        college.setCollegeName(collegeName);
        if (!"".equals(collegeDesc)){
            college.setCollegeDesc(collegeDesc);
        }
        if (collegeService.insertCollege(college)==1){
            addSuccess = true;
        }
        return addSuccess;
    }

    @RequestMapping("/updateCollegeInfo")
    @ResponseBody
    public boolean updateCollegeInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CharacterEncoding.setEncoding(request, response, "UTF-8");
        boolean updateSuccess = false;
        Integer collegeId = Integer.parseInt(request.getParameter("collegeId"));
        String collegeName = request.getParameter("collegeName");
        String collegeDesc = request.getParameter("collegeDesc");
        College college = new College();
        college.setCollegeId(collegeId);
        college.setCollegeName(collegeName);
        if (!"".equals(collegeDesc)){
            college.setCollegeDesc(collegeDesc);
        }
        if (collegeService.updateCollege(college)==1){
            updateSuccess = true;
        }
        return updateSuccess;
    }
}
