package com.situ.lession1226.service.impl;

import com.situ.lession1226.dao.ModDAO;
import com.situ.lession1226.model.Mod;
import com.situ.lession1226.service.ModService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class ModServiceImpl implements ModService {
    private ModDAO modDAO;

    @Autowired
    public void setModDAO(ModDAO modDAO) {
        this.modDAO = modDAO;
    }

    @Override
    public List<Mod> findAllByUsername(String username) {
        return modDAO.findAllByUsername(username);
    }
}
