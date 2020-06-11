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
@RequestMapping("managecustomers")
public class ManageCustomerController {

    private CategoryDAO categoryDAO;
    private SubcategoryDAO subcategoryDAO;
    private CustomerDAO customerDAO;
    private BookDAO bookDAO;
    private PurchaseRequestDAO purchaseRequestDAO;
    private MailDAO mailDAO;
    private PageAdminDAO pageAdminDAO;

    int deletingId;
    Customer theCustomerForSentMail;

    @Autowired
    public ManageCustomerController(@Qualifier("customerDAO") CustomerDAO theCustomerDAO,@Qualifier("purchaseRequestDAO") PurchaseRequestDAO thePurchaseRequestDAO , MailDAO theMailDAO) {
        customerDAO = theCustomerDAO;
        purchaseRequestDAO = thePurchaseRequestDAO;
        mailDAO = theMailDAO;
    }

    @GetMapping("/listcustomers")
    public String manageCustomerMainPage(Model theModel, @RequestParam(defaultValue = "0") int page, HttpSession session){

        theModel.addAttribute("thecustomers",
                customerDAO.findAll(PageRequest.of(page, 6)));


        List<Customer> customers = customerDAO.findAll();
        int totalCustomer = customers.size();
        theModel.addAttribute("totalCustomer",totalCustomer);

        return "admin/manage-customer";
    }


    @PostMapping("/deletecustomer")
    public String deletecustomer(@RequestParam("customerId") int theCustomerId, Model theModel, HttpSession session) {

        deletingId = theCustomerId;
        customerDAO.deleteById(deletingId);
        System.out.println("Deleted");
        System.out.println("id is : " + theCustomerId);
        return "redirect:/managecustomers/listcustomers";
    }

    @PostMapping("/send-mail")
    public String sentMail(@RequestParam("customerId") int theCustomerId, Model theModel, HttpSession session) {

        System.out.println("send-mail clicked");
        System.out.println("id is : " + theCustomerId);

        theCustomerForSentMail = customerDAO.findById(theCustomerId);


        Mail mail = new Mail();
        System.out.println("clicked");

        mail.setTitle(theCustomerForSentMail.getEmail());
        theModel.addAttribute("mailForSave", mail);

        return "admin/send-mail";
    }

    @PostMapping("/saveMail")
    public String sendMail(@ModelAttribute("mailForSave") Mail theMail, Model theModel, HttpSession session) {

        Mail sentMail = new Mail();
        sentMail.setTitle(theMail.getTitle());
        sentMail.setContent(theMail.getContent());
        //sentMail.setPageAdmin();
        if(theCustomerForSentMail != null)
            sentMail.setCustomer(theCustomerForSentMail);


        sentMail.setPageAdmin(new PageAdmin(1,"1234","admin@admin"));
        mailDAO.save(sentMail);
        return "redirect:/managecustomers/listcustomers";
    }





    @PostMapping("/update-customer")
    public String showUpdateCustomer(@RequestParam("customerId") int theId, Model theModel, HttpSession session) {

        Customer theCustomer = customerDAO.findById(theId);
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(theCustomer.getId());
        updatedCustomer.setAddress(theCustomer.getAddress());
        updatedCustomer.setCustomerName(theCustomer.getCustomerName());
        updatedCustomer.setCustomerPassword(theCustomer.getCustomerPassword());
        updatedCustomer.setEmail(theCustomer.getEmail());
        updatedCustomer.setPhone(theCustomer.getPhone());


        theModel.addAttribute("customerForSave",updatedCustomer);

        System.out.println("Update clicked" + "id is: " + theId);
        return "admin/update-customer";
    }

    @PostMapping("/saveCustomer")
    public String submitCustomer(@ModelAttribute("customerForSave") Customer theCustomer, Model theModel, HttpSession session) {

        Customer savedCustomer = new Customer();
        savedCustomer.setPhone(theCustomer.getPhone());
        savedCustomer.setEmail(theCustomer.getEmail());
        savedCustomer.setCustomerPassword(theCustomer.getCustomerPassword());
        savedCustomer.setAddress(theCustomer.getAddress());
        savedCustomer.setCustomerName(theCustomer.getCustomerName());
        savedCustomer.setId(theCustomer.getId());

        customerDAO.save(savedCustomer);
        return "redirect:/managecustomers/listcustomers";
    }


    @GetMapping("/add-customer")
    public String addCustomerButton(Model theModel, HttpSession session){

        Customer customer = new Customer();
        System.out.println("clicked");

        theModel.addAttribute("customerForSave", customer);
        return "admin/add-customer";
    }

    @PostMapping("/customer-detail")
    public String viewDetailsButton( @RequestParam("customerId") int theCustomerId, @RequestParam(defaultValue = "0") int page, Model theModel, HttpSession session){

        theModel.addAttribute("thecustomer",
                customerDAO.findById(theCustomerId));
        System.out.println("detail clicked" + " id is : "+  theCustomerId );

        theModel.addAttribute("customer",
                customerDAO.findCustomerById(theCustomerId,PageRequest.of(page, 6)));

        //List<PurchaseRequest> orders = purchaseRequestDAO.f


        return "admin/customer-detail";
    }



}
