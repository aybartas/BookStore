package com.BoomBook.DAO;


import com.BoomBook.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO  extends JpaRepository<Book,Integer> {
    public List<Book> findAll();

    public Book findById(int theId);

    public void deleteById(int theId);

    public List<Book> findByisbn(String isbn);
}
