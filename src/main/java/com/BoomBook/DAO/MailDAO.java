package com.BoomBook.DAO;

import com.BoomBook.Model.Mail;

import java.util.List;

public interface MailDAO {
    public List<Mail> findAll();

    public Mail findById(int customerID,int pageAdminID);

    public void save(Mail mail);

    public void deleteById(int customerID,int pageAdminID);
}
