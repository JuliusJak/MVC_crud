package com.example.mvc_crud.model;

import java.util.ArrayList;
import java.util.List;

public class InvoiceList {
    private List<Invoice> invoiceList;
    private String owner;

    public InvoiceList(String owner) {
        this.owner = owner;
        this.invoiceList = new ArrayList<>();
    }

    public static InvoiceList getInvoiceList(String username) {
        return null;
    }

    public String getOwner() {
        return owner;
    }

    public List<Invoice> getItemList() {
        return invoiceList;
    }
}
