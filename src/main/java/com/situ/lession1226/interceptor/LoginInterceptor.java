package com.situ.lession1226.interceptor;

import com.situ.lession1226.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/2
 * <p>
 * created by 千堆雪 on 2024/1/2, last modified by 千堆雪 on 2024/1/2
 */
public class LoginInterceptor implements HandlerInterceptor {
    public static final String LOGIN_USER_KEY = "@#current_login_user_key";

    /**
     * 前置拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();//获取会话
        //从会话中取出当前登录用户
        User user = (User) session.getAttribute(LOGIN_USER_KEY);

        if (user == null) {//未登录过
            response.sendRedirect("/admin/login");//重定向到登录页
            return false;
        } else {//登录过
            return true;
        }
    }
}
