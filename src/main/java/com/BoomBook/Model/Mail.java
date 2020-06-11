package com.BoomBook.Model;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Mail")
public class Mail implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn( name="customer_id")
    private Customer customer;

    @Id
    @ManyToOne
    @JoinColumn( name="page_admin_id")
    private PageAdmin pageAdmin;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    // CONSTRUCTORS

    public Mail() {
    }

    public Mail(Customer customer, PageAdmin pageAdmin, String title, String content) {
        this.customer = customer;
        this.pageAdmin = pageAdmin;
        this.title = title;
        this.content = content;
    }

    // GETTERS AND SETTERS

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }

    public PageAdmin getPageAdmin() { return pageAdmin; }

    public void setPageAdmin(PageAdmin pageAdmin) { this.pageAdmin = pageAdmin; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }




    @Override
    public String toString() {
        return "Mail{" +
                "customer=" + customer +
                ", pageAdmin=" + pageAdmin +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
