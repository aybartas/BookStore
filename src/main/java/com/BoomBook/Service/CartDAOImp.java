package com.BoomBook.Service;


import javax.persistence.EntityManager;

import com.BoomBook.DAO.CartDAO;
import com.BoomBook.Model.Cart;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDAOImp implements CartDAO {

    private EntityManager entityManager;

    @Autowired
    public CartDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Cart> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Cart> theQuery = currentSession.createQuery("from Cart", Cart.class);
        // execute query and get result list
        List<Cart> carts = theQuery.getResultList();
        // return the results
        return carts;
    }

    @Override
    @Transactional
    public Cart findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Cart theCart = currentSession.get(Cart.class, theId);
        return theCart;
    }


    @Override
    @Transactional
    public void save(Cart theCart) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theCart);
    }


    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery( "delete from Cart where id=:cartId");
        theQuery.setParameter("cartId", theId);
        theQuery.executeUpdate();
    }
}
