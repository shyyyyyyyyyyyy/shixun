package com.situ.lession1226.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能：用户
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/2
 * <p>
 * created by 千堆雪 on 2024/1/2, last modified by 千堆雪 on 2024/1/2
 */
@Getter
@Setter
public class User {
    private Integer id;//主键
    private String username;//用户名
    private String password;//密码
}
