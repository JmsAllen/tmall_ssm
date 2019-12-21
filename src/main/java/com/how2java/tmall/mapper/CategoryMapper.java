package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    List<Category> list();
}
