package com.BoomBook.Controller;


import java.util.ArrayList;

import java.util.Date;

import java.util.List;

import com.BoomBook.DAO.BookDAO;
import com.BoomBook.DAO.CampaignDAO;
import com.BoomBook.DAO.CategoryDAO;
import com.BoomBook.DAO.SubcategoryDAO;
import com.BoomBook.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
@RequestMapping("managebooks")
public class ManageBooksController {

    private CategoryDAO categoryDAO;
    private SubcategoryDAO subcategoryDAO;
    private BookDAO bookDAO;
    private CampaignDAO campaignDAO;
    public ModelISBN isbnBool;


    int deletingId;
    @Autowired
    public ManageBooksController(CategoryDAO theCategoryDAO, @Qualifier("bookDAO") BookDAO theBookDAO, CampaignDAO theCampaignDAO, SubcategoryDAO theSubcategoryDAO ) {
        categoryDAO = theCategoryDAO;
        bookDAO = theBookDAO;
        campaignDAO = theCampaignDAO;
        subcategoryDAO = theSubcategoryDAO;
        isbnBool = new ModelISBN();


    }



 
    @PostMapping( "/add-campaign" )
    public String addCampaign(@RequestParam("abookId") int theBookId, @ModelAttribute("campaignButton") CampaignButton theCampaignButton) {

        List<Campaign> isCampaign = campaignDAO.findBooksCampaign(theBookId);

        if(isCampaign.size()!=0){
            Campaign thereCampaign = isCampaign.get(0);
            thereCampaign.setDiscountPercentage(theCampaignButton.getCampaignPercentage());
            thereCampaign.setImageURL(theCampaignButton.getImageUrl());
            thereCampaign.setNote(theCampaignButton.getNote());
            campaignDAO.save(thereCampaign);
        }

        else{
            Book campaignBook = bookDAO.findById(theBookId);
            Campaign campaign = new Campaign(campaignBook,theCampaignButton.getCampaignPercentage(), theCampaignButton.getNote(), theCampaignButton.getImageUrl()) ;
            campaignDAO.save(campaign);
        }
        return "redirect:/managebooks/listbooks";
    }




    // Added for add-book form

    @GetMapping("/subcategories")
    public @ResponseBody
    Subcategory[] findAllSubcategories(
            @RequestParam(value = "selectedCatedoryId", required = true) int selectedCatedoryId, Model theModel) {
        makeFalseIsbnBool();
        theModel.addAttribute("Currentisbn", isbnBool);
        System.out.println("Selected Category: "+ selectedCatedoryId);
        List<Subcategory> theSubcategories = subcategoryDAO.findSubcategoriesByCategoryId(selectedCatedoryId);
        Subcategory[] myArray = new Subcategory[theSubcategories.size()];
        theSubcategories.toArray(myArray);
        for(Subcategory i:myArray){
            System.out.println(i.getTitle());
        }
        return myArray;
    }



    // Added for add-book form

    @PostMapping( "/deletebook" )
    public String deletebook(@RequestParam("bookId") int theBookId, Model theModel) {
        makeFalseIsbnBool();
        theModel.addAttribute("Currentisbn", isbnBool);
        System.out.println("deleted: "+ theBookId);
        deletingId = theBookId;
        bookDAO.deleteById(deletingId);
        return "redirect:/managebooks/listbooks";
    }


    //// ---SEARCH SCREEN METHOD---- YUSUF ////

    @PostMapping("/searchbook")
    public String searchBook(@ModelAttribute("searchForm") SearchForm searchForm, Model theModel){

        CampaignButton campaignButton = new CampaignButton();
        List<Book> books = bookDAO.findAll();
        List<Book> searchedBook = new ArrayList<Book>();

        for(Book b: books){
            System.out.println(searchForm.getSearchKey()+" "+b.getTitle());
            if(b.getAuthorName().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getTitle().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getPublisherName().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getSubject().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getSubcategory().getTitle().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getSubcategory().getCategory().getTitle().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(b.getIsbn().compareTo(searchForm.getSearchKey()) == 0){
                searchedBook.add(b);
            }
            else if(Integer.class.isInstance(b)
                    ? String.valueOf(b.getId()).compareTo(searchForm.getSearchKey()) == 0
                    : false){
                searchedBook.add(b);
            }
            else if(searchForm.getSearchKey().isEmpty()){
                searchedBook = new ArrayList<>(books);
                break;
            }
        }

        theModel.addAttribute("campaignButton", campaignButton);
        theModel.addAttribute("searchedBook", searchedBook);
        return "admin/search-screen";
    }

    private void makeFalseIsbnBool(){
        isbnBool.setComeFromAddBook(false);
    }


    // Changed

    @PostMapping("/update-book")
    public String showUpdateBook(@RequestParam("bookId") int theId, Model theModel) {
        makeFalseIsbnBool();
        theModel.addAttribute("Currentisbn", isbnBool);
        // get the employee from the service
        Book theBook = bookDAO.findById(theId);

        // Koray
        BookForm bookForm = new BookForm();

        bookForm.setAuthorName(theBook.getAuthorName());
        bookForm.setCount(theBook.getCount());
        bookForm.setId(theBook.getId());
        bookForm.setImageURL(theBook.getImageURL());
        bookForm.setIsbn(theBook.getIsbn());
        bookForm.setPrice(theBook.getPrice());
        bookForm.setPublisherName(theBook.getPublisherName());
        bookForm.setSubcategory(theBook.getSubcategory().getId());
        bookForm.setSubject(theBook.getSubject());
        bookForm.setTitle(theBook.getTitle());
        bookForm.setYear(theBook.getYear());


        // set employee as a model attribute to pre-populate the form
        Subcategory generalSubcategory = subcategoryDAO.findById(1);
        List<Category> categories = categoryDAO.findAll();
        theModel.addAttribute("bookForUpdate", bookForm);
        theModel.addAttribute("theCategories", categories);
        theModel.addAttribute("theGeneralSubcategory", generalSubcategory);
        //Koray

        // set employee as a model attribute to pre-populate the form
        theModel.addAttribute("bookForUpdate", theBook);
        System.out.println("Oktay manager" + theBook.getId());

        // send over to our form
        return "admin/update-book";
    }


    // Changed
    @PostMapping("/saveBook")
    public String submitBook(@ModelAttribute("bookForSave") BookForm theBook, Model theModel) {

        //Aybar
        List<Book> isbnBooks = bookDAO.findByisbn(theBook.getIsbn());
        if(isbnBooks.size() > 0){
            if(theBook.getId()==0){
                isbnBool.setCurrent_isbn(false);
                isbnBool.setComeFromAddBook(true);
                theModel.addAttribute("Currentisbn",isbnBool);
            }
            else{
                Book savedBook = new Book();
                if(theBook.getSubcategory()==0)
                    savedBook.setSubcategory(subcategoryDAO.findById(theBook.getSubcategory()+2));
                else
                    savedBook.setSubcategory(subcategoryDAO.findById(theBook.getSubcategory()));
                savedBook.setId(theBook.getId());
                savedBook.setTitle(theBook.getTitle());
                savedBook.setIsbn(theBook.getIsbn());
                savedBook.setImageURL(theBook.getImageURL());
                savedBook.setPublisherName(theBook.getPublisherName());
                savedBook.setSubject(theBook.getSubject());
                savedBook.setCount(theBook.getCount());
                savedBook.setPrice(theBook.getPrice());
                savedBook.setAuthorName(theBook.getAuthorName());
                savedBook.setYear(theBook.getYear());
                theModel.addAttribute("Currentisbn",isbnBool);
                bookDAO.save(savedBook);
            }
        }
        else{
            Book savedBook = new Book();
            if(theBook.getSubcategory()==0)
                savedBook.setSubcategory(subcategoryDAO.findById(theBook.getSubcategory()+2));
            else
                savedBook.setSubcategory(subcategoryDAO.findById(theBook.getSubcategory()));
            if(theBook.getId()!=0)
                savedBook.setId(theBook.getId());
            else{
                isbnBool.setComeFromAddBook(true);
                isbnBool.setCurrent_isbn(true);
            }
            savedBook.setTitle(theBook.getTitle());
            savedBook.setIsbn(theBook.getIsbn());
            savedBook.setImageURL(theBook.getImageURL());
            savedBook.setPublisherName(theBook.getPublisherName());
            savedBook.setSubject(theBook.getSubject());
            savedBook.setCount(theBook.getCount());
            savedBook.setPrice(theBook.getPrice());
            savedBook.setAuthorName(theBook.getAuthorName());
            savedBook.setYear(theBook.getYear());
            theModel.addAttribute("Currentisbn",isbnBool);
            bookDAO.save(savedBook);
            // use a redirect to prevent duplicate submissions
        }
        return "redirect:/managebooks/listbooks";
    }



    @GetMapping("/listbooks")
    public String manageBooksMainPage(Model theModel, @RequestParam(defaultValue = "0") int page){
        theModel.addAttribute("Currentisbn", isbnBool);
        SearchForm searchForm = new SearchForm();

        CampaignButton campaignButton = new CampaignButton();
        theModel.addAttribute("campaignButton", campaignButton);
        theModel.addAttribute("searchForm", searchForm);

        theModel.addAttribute("thebooks",
                bookDAO.findAll(PageRequest.of(page, 6)));

        //Koray
        BookForm totalCount = new BookForm();
        //Koray

        //Koray
        List<Book> books = bookDAO.findAll();
        int count = 0;
        for(Book book: books){
            count += book.getCount();
        }
        totalCount.setCount(count);
        theModel.addAttribute("totalCount",totalCount);
        //Koray


        System.out.println(books.size());
        return "admin/manage-books";
    }


    @GetMapping("/add-book")
    public String addBookButton(Model theModel){
        makeFalseIsbnBool();
        theModel.addAttribute("Currentisbn", isbnBool);

        Book book = new Book();
        BookForm bookForm = new BookForm();

        Subcategory generalSubcategory = subcategoryDAO.findById(1);
        List<Category> categories = categoryDAO.findAll();
        theModel.addAttribute("bookForSave", bookForm);
        theModel.addAttribute("theCategories", categories);
        theModel.addAttribute("theGeneralSubcategory", generalSubcategory);
        System.out.println("added clicked");
        return "admin/add-book";
    }




}
