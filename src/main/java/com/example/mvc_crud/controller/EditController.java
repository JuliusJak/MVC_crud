package com.example.mvc_crud.controller;

import com.example.mvc_crud.EditDetails;
import com.example.mvc_crud.PaymentDetails;
import com.example.mvc_crud.repository.UserRepository;
import com.example.mvc_crud.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/*")
public class EditController {

    private UserRepository userRepository;

    @Autowired
    public EditController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/editPage")
    public String showEditPage() {
        return "EditPage";
    }

    @PostMapping("editInvoice")
    public String editInvoice(@RequestParam("id") int id,
                              @RequestParam("name") String name,
                              @RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("category") String category,
                              @RequestParam("price") String price) {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setId(id);
        paymentDetails.setUsername(name);
        paymentDetails.setTitle(title);
        paymentDetails.setBeskrivning(description);
        paymentDetails.setKategori(category);
        paymentDetails.setPris(Integer.parseInt(price));
        userRepository.editInvoice(paymentDetails);
        return "redirect:/invoicePage";
    }

}
