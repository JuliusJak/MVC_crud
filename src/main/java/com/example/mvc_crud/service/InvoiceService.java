package com.example.mvc_crud.service;

import com.example.mvc_crud.model.Invoice;
import com.example.mvc_crud.model.InvoiceList;
import com.example.mvc_crud.repository.UserRepository;

public class InvoiceService {

    private UserRepository UserListRepository;

    public void UserListService() {
        this.UserListRepository = new UserRepository();
    }

    public InvoiceList getShoppingList(String username) {
        InvoiceList list = InvoiceList.getInvoiceList(username);

        if(list == null) {
            //listRepository.createNew(username);
            list = new InvoiceList(username);
        }

        return list;
    }



}
