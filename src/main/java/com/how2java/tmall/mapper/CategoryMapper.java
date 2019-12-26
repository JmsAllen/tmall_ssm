package com.how2java.tmall.mapper;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.utils.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Allen
 */
@Repository
public interface CategoryMapper {
    List<Category> list();

    void add(Category category);

    void delete(int id);

    void update(Category category);

    Category get(int id);
}
