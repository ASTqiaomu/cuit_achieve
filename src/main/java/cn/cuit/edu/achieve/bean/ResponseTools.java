package cn.cuit.edu.achieve.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应类，用于封装查询结果list到map中
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class UtilsTools
 * @date 2021/5/22 21:55
 */
public class ResponseTools<T> {
    Map<String,Object> map = new HashMap<>(4);
    public ResponseTools(List<T> list){
        if (list.size()>0){
            map.put("code","0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
        }else {
            map.put("code","1");
            map.put("list", "");
            map.put("count", 0);
            map.put("message", "无相关结果！");
        }
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
