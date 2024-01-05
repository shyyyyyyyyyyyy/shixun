package com.situ.lession1226.service;

import com.situ.lession1226.model.Mod;

import java.util.List;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/4
 * <p>
 * created by 千堆雪 on 2024/1/4, last modified by 千堆雪 on 2024/1/4
 */
public interface ModService {
    List<Mod> findAllByUsername(String username);
}
