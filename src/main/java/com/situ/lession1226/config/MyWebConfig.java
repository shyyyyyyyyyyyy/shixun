package com.situ.lession1226.config;

import com.situ.lession1226.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/2
 * <p>
 * created by 千堆雪 on 2024/1/2, last modified by 千堆雪 on 2024/1/2
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                //对哪些请求地址模式进行拦截
                .addPathPatterns("/**")
                //对哪些请求地址模式进行忽略、放行
                .excludePathPatterns("/admin/login/**",
                        "/admin/logout/**", "/admin/captcha/**",
                        "/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.png");

    }
}
