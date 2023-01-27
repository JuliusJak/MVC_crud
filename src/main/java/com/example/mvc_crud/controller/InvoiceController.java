package com.example.mvc_crud.controller;

import com.example.mvc_crud.model.InvoiceList;
import com.example.mvc_crud.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/*")
public class InvoiceController {


    private UserRepository userListRepository;

    public InvoiceController() {
        userListRepository = new UserRepository();
    }

    @GetMapping
    protected String showShoppingList(Model model, HttpSession session) throws SQLException {
        String username = (String) session.getAttribute("username");

        InvoiceList userShoppingList = userListRepository.getInvoiceList(username);

        model.addAttribute("items", userShoppingList.getItemList());

        return "invoicePage";
    }


}

