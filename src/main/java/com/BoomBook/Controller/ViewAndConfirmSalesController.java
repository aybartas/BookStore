package com.BoomBook.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("viewandconfirmsales")
public class ViewAndConfirmSalesController {

    @GetMapping("order")
    public String ord(){
        return "admin/orders";
    }
}
