package com.BoomBook.Controller;

import com.BoomBook.DAO.*;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("managecategories")
public class ManageCategoriesController {
    private CategoryDAO categoryDAO;
    private SubcategoryDAO subcategoryDAO;
    private CustomerDAO customerDAO;
    private BookDAO bookDAO;
    private CampaignDAO campaignDAO;

    int deletingId;


    @Autowired
    public ManageCategoriesController(@Qualifier("categoryDAO") CategoryDAO theCategoryDAO, @Qualifier("subcategoryDAO") SubcategoryDAO theSubcategoryDAO, HttpSession session ) {
        categoryDAO = theCategoryDAO;
        subcategoryDAO = theSubcategoryDAO;
    }

    @GetMapping("/listcategories")
    public String manageCategoryMainPage( Model theModel, @RequestParam(defaultValue = "0") int page, HttpSession session){
        CategoryButton categoryButton = new CategoryButton();
        theModel.addAttribute("categoryButton", categoryButton);
        SubcategoryButton subcategoryButton = new SubcategoryButton();
        theModel.addAttribute("subcategoryButton", subcategoryButton);

        Category category = new Category();
        theModel.addAttribute("categoryForSave" , category);

        SubcategoryButton addSubcategoryButton = new SubcategoryButton();
        theModel.addAttribute("addSubcategoryButton",addSubcategoryButton);

        theModel.addAttribute("thecategories",
                categoryDAO.findAll(PageRequest.of(page, 6)));

        List<Category> categories = categoryDAO.findAll();
        int totalCategory = categories.size();
        theModel.addAttribute("totalCategory",totalCategory);

        return "admin/manage-categories";
    }

    @PostMapping("/update-category")
    public String showUpdateCategory(@RequestParam("categoryId") int theId, @ModelAttribute("categoryButton") CategoryButton theCategoryButton, Model theModel, HttpSession session) {

        Category theCategory = categoryDAO.findById(theId);
        System.out.println("Update clicked" + "id is: " + theId);

        theCategory.setTitle(theCategoryButton.getTitle());

        System.out.println(theCategoryButton.getTitle());
        categoryDAO.save(theCategory);

        return "redirect:/managecategories/listcategories";
    }

    @PostMapping("/saveCategory")
    public String submitCategory(@ModelAttribute("categoryForSave") Category theCategory) {

        Category savedCategory = new Category();
        savedCategory.setSubcategoryList(theCategory.getSubcategoryList());
        savedCategory.setTitle(theCategory.getTitle());

        System.out.println(theCategory.getTitle());

        categoryDAO.save(savedCategory);
        return "redirect:/managecategories/listcategories";
    }

    @PostMapping("/saveSubcategory")
    public String submitsubcategory(@RequestParam("categoryId") int theId, @ModelAttribute("addSubcategoryButton") SubcategoryButton addSubcategoryButton) {

        Category theCategory = categoryDAO.findById(theId);
        System.out.println("Add clicked" + " id is: " + theId);

        theCategory.getSubcategoryList().add(new Subcategory(addSubcategoryButton.getTitle(), theCategory));

        System.out.println(theCategory.getTitle());

        categoryDAO.save(theCategory);
        return "redirect:/managecategories/listcategories";
    }

    @PostMapping("/update-subcategory")
    public String showUpdateSubCategory(@RequestParam("subcategoryId") int theId, @ModelAttribute("subcategoryButton") SubcategoryButton theSubcategoryButton, Model theModel, HttpSession session) {

        Subcategory theSubCategory = subcategoryDAO.findById(theId);
        System.out.println("Update clicked" + "id is: " + theId);

        theSubCategory.setTitle(theSubcategoryButton.getTitle());

        System.out.println(theSubcategoryButton.getTitle());
        subcategoryDAO.save(theSubCategory);

        return "redirect:/managecategories/listcategories";
    }


    @PostMapping("/deletecategory")
    public String deletecategory(@RequestParam("categoryId") int theCategoryId, Model theModel, HttpSession session) {

        deletingId = theCategoryId;
        categoryDAO.deleteById(deletingId);
        System.out.println("Deleted");
        System.out.println("id is : " + theCategoryId);
        System.out.println("AAAAAAAAAAAAAAAAAAAAA");
        return "redirect:/managecategories/listcategories";
    }

    @PostMapping("/deletesubcategory")
    public String deletesubcategory(@RequestParam("subcategoryId") int theSubCategoryId) {


        subcategoryDAO.deleteById(theSubCategoryId);
        System.out.println("Deleted");
        System.out.println("id is : " + theSubCategoryId);
        return "redirect:/managecategories/listcategories";
    }




}
