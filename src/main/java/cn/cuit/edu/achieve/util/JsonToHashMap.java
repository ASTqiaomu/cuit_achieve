package cn.cuit.edu.achieve.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class JsonToHashMap
 * @date 2021/5/26 14:01
 */
public class JsonToHashMap {
    /**
     * 将JSON String转成HashMap
     * @method getHashMap
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 15:32
     * @param data java.lang.String
     * @return java.util.HashMap<java.lang.String, java.lang.Object>
     */
    public static HashMap<String, Object> getHashMap(String data){
        return JSON.parseObject(data, new TypeReference<HashMap<String, Object>>() {});
    }
}
