package com.BoomBook.Controller;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
import com.BoomBook.DAO.*;
import com.BoomBook.Exceptions.SortedBy;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("searchpage")
public class SearchPageController {

    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;
    private SubcategoryDAO subcategoryDAO;/*
    private int clickedSubcategoryId;
    private int clickedCategoryId;
    private String clickedGeneralCategory="no";
    private String clickedCampaignCategory="no";*/
    private SearchForm searchForm;
    private SortedBy sortedBy;

    @Autowired
    public SearchPageController(BookDAO bookDAO, CategoryDAO categoryDAO, SubcategoryDAO subcategoryDAO) {
        this.bookDAO = bookDAO;
        this.categoryDAO = categoryDAO;
        this.subcategoryDAO = subcategoryDAO;
    }

    @GetMapping("/listbooks")
    public String searchBookPage2(@RequestParam(defaultValue = "title") String sortedBy,
                                @RequestParam(defaultValue = "0") int clickedGeneralCategory,
                                @RequestParam(defaultValue = "0") int clickedCampaignCategory,
                                @RequestParam(defaultValue = "0") int clickedCategoryId,
                                 @RequestParam(defaultValue = "0") int clickedSubcategoryId,
                                 @RequestParam(defaultValue = "0") int page, HttpSession session,Model theModel){
        // int totalPages=0;
        System.out.println("Clicked is: "+subcategoryDAO.findById(clickedSubcategoryId));
        System.out.println("Clicked is: "+searchForm);
        theModel.addAttribute("theCategories", categoryDAO.findAll());

        // to get books sorted

        String[] arrOfStr = sortedBy.split("-");
        System.out.println(arrOfStr[0]);
        if(arrOfStr.length >1 ){ // if sorted by price is wanted
                if(arrOfStr[1].compareTo("low_to_high") == 0){ // if price is low to high
                    if(clickedGeneralCategory==1){ // General category is a subcategory so its searched as subcategory
                        theModel.addAttribute("thebooks",  bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0])) ));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]))).getContent().size());
                    }
                    else if(clickedCampaignCategory==1){ // Campaign category is a subcategory so its searched as subcategory
                        theModel.addAttribute("thebooks",  bookDAO.findCampaign(PageRequest.of(page, 12, Sort.by(arrOfStr[0] ))) );
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findCampaign(PageRequest.of(page, 12, Sort.by(arrOfStr[0] ))).getContent().size());
                    }
                    else if(clickedCategoryId!=0){
                        theModel.addAttribute("thebooks", bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]))) );
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]))).getContent().size());
                    }
                    else if(clickedSubcategoryId!=0){
                        theModel.addAttribute("thebooks", bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]))));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]))).getContent().size());
                    }
                    else{
                        theModel.addAttribute("thebooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]))));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]))).getContent().size());

                    }

                }
                else{  // if price is high to low
                    if(clickedGeneralCategory==1){ // General category is a subcategory so its searched as subcategory
                        theModel.addAttribute("thebooks",  bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending() ) ));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending() ) ).getContent().size());
                    }
                    else if(clickedCampaignCategory==1){ // Campaign category is a subcategory so its searched as subcategory
                        theModel.addAttribute("thebooks",  bookDAO.findCampaign(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending() ) ));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findCampaign(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending() ) ).getContent().size());
                    }
                    else if(clickedCategoryId!=0){
                        theModel.addAttribute("thebooks",  bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())).getContent().size());
                    }
                    else if(clickedSubcategoryId!=0){
                        theModel.addAttribute("thebooks",  bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())).getContent().size());
                    }
                    else{
                        theModel.addAttribute("thebooks",  bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())));
                        theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(arrOfStr[0]).descending())).getContent().size());
                    }

                }


        }
        else {                  // if sorted by price is not wanted

                if(clickedGeneralCategory==1){ // General category is a subcategory so its searched as subcategory
                    System.out.println("hel");
                    theModel.addAttribute("thebooks",  bookDAO.findAll(PageRequest.of(page, 12, Sort.by(sortedBy)) ));
                    theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(sortedBy)) ).getContent().size());
                }
                else if(clickedCampaignCategory==1){ // Campaign category is a subcategory so its searched as subcategory
                    System.out.println("case1: "+ bookDAO.findCampaign(PageRequest.of(page, 12 ) ).getContent());
                    theModel.addAttribute("thebooks",  bookDAO.findCampaign(PageRequest.of(page, 12 ) ));
                    theModel.addAttribute("sizeOfTheBooks", bookDAO.findCampaign(PageRequest.of(page, 12 ) ).getContent().size());
                }
                else if(clickedCategoryId!=0){ // category is clicked
                    System.out.println("hello");
                    theModel.addAttribute("thebooks",  bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(sortedBy)) ));
                    theModel.addAttribute("sizeOfTheBooks", bookDAO.findByCategory(clickedCategoryId, PageRequest.of(page, 12, Sort.by(sortedBy)) ).getContent().size());
                }
                else if(clickedSubcategoryId!=0){  // subcategory is clicked
                    System.out.println("hello2");
                    theModel.addAttribute("thebooks",  bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(sortedBy)) ));
                    theModel.addAttribute("sizeOfTheBooks", bookDAO.findBySubcategory(clickedSubcategoryId, PageRequest.of(page, 12, Sort.by(sortedBy)) ).getContent().size());
                }
                else{  // nor subcategory neither category is clicked, all books are sorted ascending order
                    System.out.println("hello3");
                    theModel.addAttribute("thebooks",  bookDAO.findAll(PageRequest.of(page, 12, Sort.by(sortedBy)) ));
                    theModel.addAttribute("sizeOfTheBooks", bookDAO.findAll(PageRequest.of(page, 12, Sort.by(sortedBy)) ).getContent().size());
                }


        }

        theModel.addAttribute("searchForm", new SearchForm());
        theModel.addAttribute("sortedBy", new SortedBy());

        searchForm = null;
        sortedBy = null;
        clickedSubcategoryId=0;
        clickedCategoryId=0;
        clickedGeneralCategory=0;
        clickedCampaignCategory=0;
        // count of books
        /*BookForm totalCount = new BookForm();
        List<Book> books = bookDAO.findAll();
        int count = 0;
        for(Book book: books){
            count += book.getCount();
        }
        totalCount.setCount(count);*/
        // theModel.addAttribute("totalCount",totalCount);

        return "user/search-page";
    }


    @PostMapping("/searchbutton")
    private String searchButton(@ModelAttribute("searchForm") SearchForm searchForm){
        sortedBy = new SortedBy();
        sortedBy.setName("title");

        if(searchForm.getSearchKey().replaceAll("\\s+","").isEmpty()){
            return "redirect:/searchpage/listbooks/?clickedGeneralCategory=1";
        }
        return "redirect:/searchpage/searchkeyword/"+searchForm.getSearchKey();
    }

    @GetMapping("/searchkeyword/{searchForm}")
    private String searchButton(Model theModel, @RequestParam(defaultValue = "0") int page, @PathVariable String searchForm){
        theModel.addAttribute("theCategories", categoryDAO.findAll());
        theModel.addAttribute("searchForm", new SearchForm());
        theModel.addAttribute("sortedBy", new SortedBy());
        System.out.println("search form: "+searchForm);


        Pattern integer = Pattern.compile("[1-9][0-9]*");//. represents single character
        Pattern floatType = Pattern.compile("[0-9]+.[0-9]+");//. represents single character


        if(integer.matcher(searchForm).matches()){
            theModel.addAttribute("thebooks", bookDAO.findByTitleFloat(Float.parseFloat(searchForm), PageRequest.of(page, 12, Sort.by("title")) ));
            theModel.addAttribute("sizeOfTheBooks", bookDAO.findByTitleFloat(Float.parseFloat(searchForm), PageRequest.of(page, 12, Sort.by("title")) ).getContent().size());
        }

        else{
            theModel.addAttribute("thebooks", bookDAO.findByTitle(searchForm, PageRequest.of(page, 12, Sort.by("title")) ));
            theModel.addAttribute("sizeOfTheBooks", bookDAO.findByTitle(searchForm, PageRequest.of(page, 12, Sort.by("title")) ).getContent().size());
        }
        System.out.println("size: "+bookDAO.findByTitle(searchForm, PageRequest.of(page, 12, Sort.by("title")) ).getContent().size());
        return "user/search-page";
    }

    @PostMapping("/sortbutton")
    private String sortButton(@ModelAttribute("sortedBy") SortedBy sortedBy){
        return "redirect:/searchpage/listbooks/";
    }
}





/*

        //Search criteria
        if(clickedSubcategoryId==0 && clickedCategoryId==0 && searchForm!=null){
            for(Book b: sortedBooksList){
                if((searchForm.getSearchKey()==null) ? true : searchForm.getSearchKey().isEmpty() ){
                    searchCriteriaList = new ArrayList<>(sortedBooksList);
                    break;
                }
                else if(b.getAuthorName().compareTo(searchForm.getSearchKey()) == 0){
                    searchCriteriaList.add(b);
                }
                else if(b.getTitle().compareTo(searchForm.getSearchKey()) == 0){
                    searchCriteriaList.add(b);
                }
                else if(b.getPublisherName().compareTo(searchForm.getSearchKey()) == 0){
                    searchCriteriaList.add(b);
                }
                else if(b.getSubject().compareTo(searchForm.getSearchKey()) == 0){
                    searchCriteriaList.add(b);
                }
                else if(b.getSubcategory().getTitle().compareTo(searchForm.getSearchKey()) == 0){
                    searchCriteriaList.add(b);
                }
                else if(b.getSubcategory().getCategory().getTitle().compareTo(searchForm.getSearchKey()) == 0){
                    searchCriteriaList.add(b);
                }
                else if(b.getIsbn().compareTo(searchForm.getSearchKey()) == 0){
                    searchCriteriaList.add(b);
                }
                else if(Integer.class.isInstance(b)
                        ? String.valueOf(b.getId()).compareTo(searchForm.getSearchKey()) == 0
                        : false){
                    searchCriteriaList.add(b);
                }

            }
        }
        else if(clickedSubcategoryId==0 && clickedCategoryId==0 && searchForm == null){
            System.out.println("Yes");
            searchCriteriaList = new ArrayList<>(sortedBooksList);
        }
        if(clickedSubcategoryId==1 || clickedCategoryId==1){
            if(clickedSubcategoryId==1){
                for(Book b: sortedBooksList){
                    if(b.getSubcategory().getTitle().compareTo(subcategoryDAO.findById(clickedSubcategoryId).getTitle()) == 0){
                        searchCriteriaList.add(b);
                    }
                }
            }
            else{
                for(Book b: sortedBooksList){
                    if(b.getSubcategory().getCategory().getTitle().compareTo(categoryDAO.findById(clickedCategoryId).getTitle()) == 0){
                        searchCriteriaList.add(b);
                    }
                }
            }
        }

        theModel.addAttribute("totalPages", totalPages);
        theModel.addAttribute("thebooks", new PageImpl<>(searchCriteriaList) );

 */