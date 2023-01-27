package com.example.mvc_crud.controller;

import com.example.mvc_crud.AuthDetails;
import com.example.mvc_crud.db.MysqlDatabase;
import com.example.mvc_crud.model.InvoiceList;
import com.example.mvc_crud.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.*;

@Controller
@RequestMapping("/*")

public class AuthController {

    UserRepository userRepository = new UserRepository();

    private MysqlDatabase db;



    @GetMapping("login")
    public String showLoginPage() {
        return "HomePage";
    }



    @PostMapping("login")
    public String login(HttpSession session, RedirectAttributes redirect, @ModelAttribute AuthDetails auth) {
        // Connect to the database
       // Connection conn = null;
        userRepository.userCheker(session, redirect, auth);
        if (userRepository.loggedIn) {
            return "redirect:/invoicePage";
        } else {
            return "redirect:/login";
        }
    }



    @PostMapping("logout")
    public String logout(HttpSession session) throws IOException {
        session.invalidate(); //Invalidate - empty the session
        return "redirect:/index.html";
    }
}


