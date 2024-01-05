package com.situ.lession1226.controller;

import com.situ.lession1226.interceptor.LoginInterceptor;
import com.situ.lession1226.model.Mod;
import com.situ.lession1226.model.User;
import com.situ.lession1226.service.ModService;
import com.situ.lession1226.service.UserService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/2
 * <p>
 * created by 千堆雪 on 2024/1/2, last modified by 千堆雪 on 2024/1/2
 */
@Controller
public class AdminController {
    //从配置文件中取值，是否开启使用验证码
    @Value("${login.use-captcha:true}")
    private boolean useCaptcha;

    private UserService userService;
    private ModService modService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModService(ModService modService) {
        this.modService = modService;
    }

        /**
         * 跳转到后台管理系统
         */
        @GetMapping("/admin")
        public String admin(Map<String, Object> map, HttpSession session) {
            //查出当前登录用户所有的权限
            //当前用户所可访问的功能模块
            //一定要获取到用户
            User user = (User) session.getAttribute(LoginInterceptor.LOGIN_USER_KEY);

            List<Mod> mods = modService.findAllByUsername(user.getUsername());

        //放到请求域中
        map.put("mods", mods);
        return "admin/admin";
    }

    /**
     * 跳转到欢迎页
     */
    @GetMapping("/welcome")
    public String welcome() {
        return "admin/welcome";
    }

    /**
     * 跳转到登录页
     */
    @GetMapping("/admin/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 后台登录验证
     */
    @PostMapping(value = "/admin/login", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String, Object> login(User user, String captcha, HttpSession session) {
        if (this.useCaptcha) {
            String sessionCaptcha = (String) session.getAttribute("captcha");
            if (!captcha.equalsIgnoreCase(sessionCaptcha)) {
                return Map.of("success", false, "error", "验证码不正确");
            }
        }

        //到数据库中查询用户
        User dbUser = userService.findByUsername(user.getUsername());
        if (dbUser == null) {
            return Map.of("success", false, "error", "无此用户");
        }


        //对用户输出的用户名进行md5加密
        String str = user.getPassword() + "{" + user.getUsername() + "}";
        System.out.println(str);
        String encrypted = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));

        if (dbUser.getPassword().equals(encrypted)) {//密码匹配
            //登录成功，则将用户实体放入到会话域中
            session.setAttribute(LoginInterceptor.LOGIN_USER_KEY, user);
            return Map.of("success", true);
        } else {
            return Map.of("success", false, "error", "用户名或密码不正确");
        }
    }

    /**
     * 退出登录，注销功能
     */
    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        //移除session中存储的认证数据
        session.removeAttribute(LoginInterceptor.LOGIN_USER_KEY);
        //返回值以redirect:为前缀，则表示重定向
        return "redirect:/admin";
    }

    /**
     * 生成验证码图片
     */
    @GetMapping("/admin/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SpecCaptcha captcha = new SpecCaptcha(130, 48, 5);

        //清除缓存
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);

        request.getSession().setAttribute("captcha", captcha.text().toLowerCase());
        captcha.out(response.getOutputStream());
    }
}
