package com.situ.lession1226.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Document {
    private Integer documentId;
    private String title;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Integer createdBy;
}
