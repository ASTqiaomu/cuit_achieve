package cn.cuit.edu.achieve.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;

/**
 * 工具类
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class Tools
 * @date 2021/5/26 14:01
 */
public class Tools {
    public static class JsonToHashMap {
        public static HashMap<String, Object> getHashMap(String data){
            return JSON.parseObject(data, new TypeReference<HashMap<String, Object>>() {});
        }
    }

}
