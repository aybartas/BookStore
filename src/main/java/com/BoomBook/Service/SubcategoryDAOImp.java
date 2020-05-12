package com.BoomBook.Service;

import com.BoomBook.DAO.SubcategoryDAO;
import com.BoomBook.Model.Category;
import com.BoomBook.Model.Subcategory;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SubcategoryDAOImp implements SubcategoryDAO {

    private EntityManager entityManager;

    @Autowired
    public SubcategoryDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Subcategory> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Subcategory> theQuery = currentSession.createQuery("from Subcategory", Subcategory.class);
        // execute query and get result list
        List<Subcategory> subcategories = theQuery.getResultList();
        // return the results
        return subcategories;
    }

    @Override
    @Transactional
    public Subcategory findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Subcategory theSubcategory = currentSession.get(Subcategory.class, theId);

        return theSubcategory;
    }


    @Override
    @Transactional
    public void save(Subcategory theSubcategory) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theSubcategory);
    }


    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery(  "delete from Subcategory where id=:subcategoryId");
        theQuery.setParameter("subcategoryId", theId);

        theQuery.executeUpdate();
    }

    @Override
    public List<Subcategory> findSubcategoriesByCategoryId(int theId) {
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Subcategory> theQuery = currentSession.createQuery("from Subcategory", Subcategory.class);
        // execute query and get result list
        List<Subcategory> subcategories = theQuery.getResultList();
        List<Subcategory> modifiedSubcategories = new ArrayList<Subcategory>();
        // return the results

        for(Subcategory s:subcategories){
            if(s.getCategory()!=null){
                if(s.getCategory().getId()==theId){
                    modifiedSubcategories.add(s);
                }
            }
        }
        // return the subcategory
        return modifiedSubcategories;
    }

}
