package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.utils.Page;

import java.util.List;

/**
 * @author Allen
 */
public interface CategoryService {
    List<Category> list(Page page);

    int total();

    void add(Category category);

    void delete(int id);

    void update(Category category);

    Category get(int id);
}