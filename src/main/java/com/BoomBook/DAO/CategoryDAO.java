package com.BoomBook.DAO;


import com.BoomBook.Model.Campaign;
import com.BoomBook.Model.Category;

import java.util.List;

public interface CategoryDAO {
    public List<Category> findAll();

    public Category findById(int theId);

    public void save(Category theCategory);

    public void deleteById(int theId);
}
