package com.BoomBook.Controller;

import com.BoomBook.DAO.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("viewdeliveries")
public class ViewDeliveriesController {
    private CartDAO cartDAO;
    private CourierCompanyDAO courierCompanyDAO;
    private CustomerDAO customerDAO;
    private InCargoDAO inCargoDAO;
    private PurchaseRequestDAO purchaseRequestDAO;


    @GetMapping("/listdeliveriessituations")
    public String manageDeliveriesMainPage(Model model, @RequestParam(defaultValue = "0") int page, HttpSession session){
//        session.setAttribute();

        return "admin/delivery-situations";
    }

    @GetMapping("order")
    public String ord(){
        return "admin/orders";
    }

}
