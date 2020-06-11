package com.BoomBook.DAO;

import com.BoomBook.Model.BookComment;
import com.BoomBook.Model.Campaign;

import java.util.List;

public interface BookCommentDAO {

    public List<BookComment> findAll();

    public BookComment findById(int BookID,int CustomerID);

    public void save(BookComment bookComment);

    public void deleteById(int BookID,int CustomerID);



}
