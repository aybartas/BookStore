package com.BoomBook.Model;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="book_comment")
public class BookComment implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn( name="book_id")
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "rate")
    private Float rate;

    @Column(name = "user_comment")
    private String user_comment;

    // CONSTRUCTORS

    public BookComment(Book book, Customer customer, Float rate, String user_comment) {
        this.book = book;
        this.customer = customer;
        this.rate = rate;
        this.user_comment = user_comment;
    }

    public BookComment() {

    }
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    // GETTERS AND SETTERS


    @Override
    public String toString() {
        return "BookComment{" +
                "book=" + book +
                ", customer=" + customer +
                ", rate=" + rate +
                ", user_comment='" + user_comment + '\'' +
                '}';
    }
}
