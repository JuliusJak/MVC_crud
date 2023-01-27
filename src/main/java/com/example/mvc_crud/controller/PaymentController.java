package com.example.mvc_crud.controller;

import com.example.mvc_crud.AuthDetails;
import com.example.mvc_crud.PaymentDetails;
import com.example.mvc_crud.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.html.HTMLLabelElement;

import java.sql.*;

@Controller
@RequestMapping("/*")
public class PaymentController {

    private UserRepository userRepository;

    @Autowired
    public PaymentController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("PaymentPage")
    public String showPaymentPage() {
        return "PaymentPage";
    }

    @PostMapping("addInvoice")
    public String addInvoice(@RequestParam("name") String name,
                             @RequestParam("title") String title,
                             @RequestParam("description") String description,
                             @RequestParam("category") String category,
                             @RequestParam("price") String price,
                             @RequestParam("date") String date) {
        if (name.isEmpty() || title.isEmpty() || description.isEmpty() || category.isEmpty() || price.isEmpty() || date.isEmpty()) {
            return "redirect:/invoicePage";
        } else {

            PaymentDetails paymentDetails = new PaymentDetails();
            paymentDetails.setUsername(name);
            paymentDetails.setTitle(title);
            paymentDetails.setBeskrivning(description);
            paymentDetails.setKategori(category);
            paymentDetails.setPris(Integer.parseInt(price));
            paymentDetails.setDatum(date);
            userRepository.addPayment(paymentDetails);
           // return "PaymentPage";
            return "redirect:/invoicePage";
        }
    }
}






/*
@Controller
@RequestMapping("/*")
public class PaymentController {

    @GetMapping("PaymentPage")
    public String showPaymnetPage() {
        return "PaymentPage";
    }





}

 */
