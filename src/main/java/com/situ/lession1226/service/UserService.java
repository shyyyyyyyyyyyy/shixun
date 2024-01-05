package com.situ.lession1226.service;

import com.situ.lession1226.model.User;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/3
 * <p>
 * created by 千堆雪 on 2024/1/3, last modified by 千堆雪 on 2024/1/3
 */
public interface UserService {
    //通过用户名从后台获取用户信息
    User findByUsername(String username);
}
