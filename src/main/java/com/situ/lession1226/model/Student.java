package com.situ.lession1226.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

/**
 * 功能：
 *
 * @author 千堆雪
 * @version 1.0.0
 * @since 2023/12/26
 * <p>
 * created by 千堆雪 on 2023/12/26, last modified by 千堆雪 on 2023/12/26
 */
@Getter
@Setter
@JsonIgnoreProperties("handler")
public class Student {
    private Integer id;//主键
    private String stuId;//学号
    private String name;//姓名
    private String pinyin;//拼音
    private String sex;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String wechat;//微信

    //根据实际业务需要选择在哪一侧建立关系
    private ClassEntity classEntity;//学生所属班级
}
