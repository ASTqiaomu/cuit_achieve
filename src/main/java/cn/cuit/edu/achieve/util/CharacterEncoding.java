package cn.cuit.edu.achieve.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class CharacterEncoding
 * @date 2021/5/26 21:16
 */
public class CharacterEncoding {
    /**
     * 设置request和response的编码格式
     * @method setEncoding
     * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
     * @date 2021/5/26 21:23
     * @param request javax.servlet.http.HttpServletRequest
     * @param response javax.servlet.http.HttpServletResponse
     * @param encoding java.lang.String
     * @return void
     */
    public static void setEncoding(HttpServletRequest request, HttpServletResponse response, String encoding) throws UnsupportedEncodingException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
    }
}
