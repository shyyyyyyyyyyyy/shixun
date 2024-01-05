package com.situ.lession1226.dao;

import com.situ.lession1226.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/3
 * <p>
 * created by 千堆雪 on 2024/1/3, last modified by 千堆雪 on 2024/1/3
 */
@Mapper
public interface UserDAO {
    User findByUsername(String username);
}
