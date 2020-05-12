package com.BoomBook.Model;

import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {

    // define fields

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;


    // define constructors

    public Category() {

    }

    public Category(int id, String Title) {
        this.id = id;
        this.title = Title;

    }


    public Category(String Title) {
        this.title = Title;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", title=" + title +"]";
    }


    // define getter and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


