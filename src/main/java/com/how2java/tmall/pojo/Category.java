package com.how2java.tmall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
