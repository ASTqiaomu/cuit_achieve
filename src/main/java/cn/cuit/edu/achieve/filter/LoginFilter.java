package cn.cuit.edu.achieve.filter;

import cn.cuit.edu.achieve.bean.Admin;
import cn.cuit.edu.achieve.bean.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author IceCream - 吃猫的鱼℘, 935478677@qq.com
 * @class LoginFilter
 * @date 2021/6/6 10:02
 */
@WebFilter(filterName = "LoginFilter",
    urlPatterns = "*.html", /*通配符（*）表示对所有的web资源进行拦截*/
    initParams = {
        @WebInitParam(name = "charset", value = "utf-8")    /*这里可以放一些初始化的参数*/
    })
public class LoginFilter implements Filter {
    private String filterName;
    private String charset;

    final String LOGIN_PAGE = "login.html";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /*初始化方法  接收一个FilterConfig类型的参数 该参数是对Filter的一些配置*/
        filterName = filterConfig.getFilterName();
        charset = filterConfig.getInitParameter("charset");
        System.out.println("过滤器初始化：" + filterName);
        System.out.println("字符集编码：" + charset);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        /*过滤方法 主要是对request和response进行一些处理，然后交给下一个过滤器或Servlet处理*/
        req.setCharacterEncoding(charset);
        resp.setCharacterEncoding(charset);

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String url = request.getRequestURI();
        System.out.println("请求的url：" + url);

        /*处理请求页面*/
        int idx = url.lastIndexOf("/");
        String endWith = url.substring(idx + 1);

        /*判断请求页面*/
        if (!LOGIN_PAGE.equals(endWith)) {
            System.out.println("不是登录页面，进行拦截处理");
            if (!isLogin(request)) {
                System.out.println("未登录或者账号密码错误，跳转到登录界面");
                /*转码*/
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.flush();
                out.println("<script>");
                out.println("alert('未登录！');");
                out.println("window.location.href='login.html';");
                out.println("</script>");
                out.close();
            } else {
                System.out.println("已登录");
                chain.doFilter(req, resp);
            }
        } else {
            System.out.println("是登录页面，不进行拦截处理");
            chain.doFilter(req, resp);
        }
    }

    private boolean isLogin(HttpServletRequest request) {
        HttpSession session =  request.getSession();
        boolean login = false;
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin!=null){
            login = true;
        }
        User user = (User) session.getAttribute("user");
        if (user!=null){
            login = true;
        }
        return login;
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁：" + filterName);
    }
}
