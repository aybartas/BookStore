package com.BoomBook.DAO;

import com.BoomBook.Model.Category;
import com.BoomBook.Model.Subcategory;

import java.util.List;

public interface SubcategoryDAO {
    public List<Subcategory> findAll();

    public Subcategory findById(int theId);

    public void save(Subcategory theSubcategory);

    public void deleteById(int theId);

    public List<Subcategory> findSubcategoriesByCategoryId(int theId);
}
