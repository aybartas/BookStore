package com.BoomBook.Service;

import com.BoomBook.DAO.CategoryDAO;
import com.BoomBook.Model.Category;

import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CategoryDAOImp implements CategoryDAO {

    private EntityManager entityManager;

    @Autowired
    public CategoryDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Category> theQuery = currentSession.createQuery("from Category", Category.class);
        // execute query and get result list
        List<Category> categories = theQuery.getResultList();
        // return the results
        return categories;
    }

    @Override
    @Transactional
    public Category findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Category theCategory = currentSession.get(Category.class, theId);

        return theCategory;
    }


    @Override
    @Transactional
    public void save(Category theCategory) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theCategory);
    }


    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery(  "delete from Category where id=:categoryId");

        theQuery.setParameter("categoryId", theId);

        theQuery.executeUpdate();
    }
}
