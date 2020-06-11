package com.BoomBook.Service;


import com.BoomBook.DAO.MailDAO;
import com.BoomBook.Model.Mail;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MailDAOImp implements MailDAO {

    private EntityManager entityManager;

    @Autowired
    public MailDAOImp(EntityManager theEntityManager){ entityManager = theEntityManager; }

    /*
    @Override
    @Transactional
    public List<Mail> findAll() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Mail> theQuery = currentSession.createQuery("from Mail", Mail.class);

        List<Mail> mail = theQuery.getResultList();

        return mail;

    }
*/
    @Transactional
    @Override
    public void save(Mail mail) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(mail);
    }

    @Transactional
    @Override
    public void deleteById(int customerID,int pageAdminID) {

        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery(  "delete from Mail " +
                "where page_admin_id=:PageAdmin  and customer_id=:customer ");

        theQuery.setParameter("PageAdmin", pageAdminID);
        theQuery.setParameter("customer", customerID);
        theQuery.executeUpdate();

    }

    @Override
    public List<Mail> findAll() {
        return null;
    }

    @Transactional
    @Override
    public Mail findById(int customerID,int pageAdminID) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Mail> theQuery = currentSession.createQuery("from Mail where page_admin_id=:PageAdmin and customer_id=:customer ", Mail.class);
        theQuery.setParameter("pageAdmin", pageAdminID);
        theQuery.setParameter("customer", customerID);
        Mail mail = theQuery.getSingleResult();

        return mail;

    }



}
