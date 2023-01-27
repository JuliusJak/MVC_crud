package com.example.mvc_crud.repository;

import com.example.mvc_crud.AuthDetails;
import com.example.mvc_crud.EditDetails;
import com.example.mvc_crud.PaymentDetails;
import com.example.mvc_crud.db.MysqlDatabase;
import com.example.mvc_crud.model.Invoice;
import com.example.mvc_crud.model.InvoiceList;
import com.example.mvc_crud.model.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.*;

@Repository

public class UserRepository {

    private MysqlDatabase db;

    public boolean loggedIn;


    public UserRepository() {
        db = MysqlDatabase.getInstance();
    }



    public void userCheker(HttpSession session, RedirectAttributes redirect, @ModelAttribute AuthDetails auth){

        Connection conn = db.getConnection();
        try {


            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT username FROM users WHERE username='"+auth.getUsername()+"'");

            if (rs.next()) {
                Users users = new Users();
                String bob = users.getBobPassword();
                String bertil = users.getBertilPassword();
                String Gertrude = users.getGertrudePassword();
                String Linus = users.getLinusPassword();
                String Todd = users.getToddPassword();
                if (bob.equals(auth.getPassword()) && auth.getUsername().equalsIgnoreCase("bob")
                        || bertil.equals(auth.getPassword()) && auth.getUsername().equalsIgnoreCase("bertil")
                        || Gertrude.equals(auth.getPassword()) && auth.getUsername().equalsIgnoreCase("Gertrude")
                        || Linus.equals(auth.getPassword()) && auth.getUsername().equalsIgnoreCase("Linus")
                        || Todd.equals(auth.getPassword())&& auth.getUsername().equalsIgnoreCase("Todd")) {
                    session.setMaxInactiveInterval(60 * 30);
                    session.setAttribute("username", auth.getUsername());
                    loggedIn = true;
                    //System.out.println("Success");
                }
                else {
                    loggedIn = false;
                    //System.out.println("Fail");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public InvoiceList getInvoiceList(String username) {
        InvoiceList list = new InvoiceList(username);
        Connection conn = db.getConnection();
        String sql = "" +
                "SELECT * FROM users "
                + "WHERE username = ? ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if(!rs.next()) { return list; } //guardian clause

            do {
                Invoice item = new Invoice();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("username"));
                item.setTitel(rs.getString("Titel"));
                item.setBeskrivning(rs.getString("Beskrivning"));
                item.setKategori(rs.getString("Kategori"));
                item.setPris(rs.getInt("Pris"));
                item.setDate(rs.getString("created_at"));

                list.getItemList().add(item);
            } while(rs.next());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void addPayment(PaymentDetails paymentDetails) {
        String insertSql = "INSERT INTO users " +
                "(username, Titel, Beskrivning, kategori, Pris,  created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = db.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(insertSql)) {

            String name = paymentDetails.getUsername();
            String title = paymentDetails.getTitle();
            String description =paymentDetails.getBeskrivning();
            String category = paymentDetails.getKategori();
            int price = paymentDetails.getPris();
            String date = paymentDetails.getDatum();

            statement.setString(1, name);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setString(4, category);
            statement.setDouble(5, price);
            statement.setString(6, date);
            statement.executeUpdate();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }


    public void deleteInvoice(EditDetails editDetails) {
        //System.out.println("#########");
        String deleteSql = "DELETE FROM users WHERE id = ?";
        Connection conn = db.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(deleteSql)) {

            int id = editDetails.getId();
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            Connection conn = db.getConnection();
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public void editInvoice(PaymentDetails paymentDetails) {
        Connection conn = db.getConnection();
        //System.out.println("&&&&&&&&&&");
        try (
             PreparedStatement stmt = conn.prepareStatement("" +
                     "UPDATE users SET " +
                     "username = ?, Titel = ?, Beskrivning = ?, " +
                     "Kategori = ?, Pris = ? WHERE id = ?")) {

            String name = paymentDetails.getUsername();
            String Titel = paymentDetails.getTitle();
            String Beskrivning =paymentDetails.getBeskrivning();
            String Kategori = paymentDetails.getKategori();
            int Pris = paymentDetails.getPris();
            String date = paymentDetails.getDatum();
            int id = paymentDetails.getId();

            stmt.setString(1, name);
            stmt.setString(2, Titel);
            stmt.setString(3, Beskrivning);
            stmt.setString(4, Kategori);
            stmt.setDouble(5, Pris);
            stmt.setInt(6, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
