package com.situ.lession1226.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2024/1/3
 * <p>
 * created by 千堆雪 on 2024/1/3, last modified by 千堆雪 on 2024/1/3
 */
@Getter
@Setter
@JsonIgnoreProperties("handler")
public class ClassEntity {
    private Integer id;
    private String name;
    private LocalDate beginTime;
    private LocalDate endTime;
    private String description;//备注
    private int total;//人数
}
