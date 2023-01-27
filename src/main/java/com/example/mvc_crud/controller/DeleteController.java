package com.example.mvc_crud.controller;

import com.example.mvc_crud.EditDetails;
import com.example.mvc_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/*")
public class DeleteController {


    private UserRepository userRepository;

    @Autowired
    public DeleteController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/deletePage")
    public String showDeletePage() {
        return "DeletePage";
    }

    @PostMapping("deleteInvoice")
    public String deleteInvoice(@RequestParam("id") int id) {
        EditDetails editDetails = new EditDetails();
        editDetails.setId(id);
        userRepository.deleteInvoice(editDetails);
        return "redirect:/invoicePage";
    }

}
