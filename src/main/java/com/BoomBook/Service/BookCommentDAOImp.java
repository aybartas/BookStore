package com.BoomBook.Service;

import com.BoomBook.DAO.BookCommentDAO;
import com.BoomBook.DAO.CategoryDAO;
import com.BoomBook.Model.BookComment;
import com.BoomBook.Model.Category;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BookCommentDAOImp implements BookCommentDAO {

    private EntityManager entityManager;

    @Autowired
    public BookCommentDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }


    @Override
    @Transactional
    public List<BookComment> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query<BookComment> theQuery = currentSession.createQuery("from BookComment", BookComment.class);

        List<BookComment> bookComments = theQuery.getResultList();

        return bookComments;

    }


    @Transactional
    @Override
    public BookComment findById(int BookID, int CustomerID) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<BookComment> theQuery = currentSession.createQuery("from BookComment where book_id =:bookParam and customer_id =:customerParam ", BookComment.class);
        theQuery.setParameter("bookParam", BookID);
        theQuery.setParameter("customerParam", CustomerID);
        BookComment theBookComment = theQuery.getSingleResult();

        return theBookComment;

    }
    @Transactional
    @Override
    public void save(BookComment bookComment) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(bookComment);
    }

    @Transactional
    @Override
    public void deleteById(int BookID, int CustomerID) {

        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery(  "delete from BookComment " +
                "where book_id=:book  and customer_id=:customer ");

        theQuery.setParameter("book", BookID);
        theQuery.setParameter("customer", CustomerID);
        theQuery.executeUpdate();

    }
}
