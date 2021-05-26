package cn.cuit.edu.achieve.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class UtilsTools
 * @date 2021/5/22 21:55
 */
public class Response {

	/**
	 * 将查询结果返回到前端
	 * @method write
	 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
	 * @date 2021/5/26 15:30
	 * @param response javax.servlet.http.HttpServletResponse
	 * @param o java.lang.Object
	 * @return void
	 */
	public static void write(HttpServletResponse response,Object o) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
