package com.BoomBook.Service;

import javax.persistence.EntityManager;

import com.BoomBook.DAO.CampaignDAO;
import com.BoomBook.Model.Campaign;
import com.BoomBook.Model.Subcategory;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CampaignDAOImp implements CampaignDAO {


    private EntityManager entityManager;

    @Autowired
    public CampaignDAOImp(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Campaign> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Campaign> theQuery = currentSession.createQuery("from Campaign", Campaign.class);
        // execute query and get result list
        List<Campaign> campaigns = theQuery.getResultList();
        // return the results
        return campaigns;
    }


    @Override
    @Transactional
    public List<Campaign> findBooksCampaign(int theBookId){
        Session currentSession = entityManager.unwrap(Session.class);
        // create a query
        Query<Campaign> theQuery = currentSession.createQuery("from Campaign", Campaign.class);
        // execute query and get result list
        List<Campaign> campaigns = theQuery.getResultList();
        List<Campaign> modifiedCampaigns = new ArrayList<Campaign>();
        // return the results

        for(Campaign c:campaigns){
            if(c.getBook().getId()==theBookId){
                    modifiedCampaigns.add(c);
            }
        }
        // return the subcategory
        return modifiedCampaigns;
    }

    @Override
    @Transactional
    public Campaign findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Campaign theBook = currentSession.get(Campaign.class, theId);

        return theBook;
    }


    @Override
    @Transactional
    public void save(Campaign theCampaign) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(theCampaign);
    }


    @Override
    @Transactional
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key
        Query theQuery = currentSession.createQuery( "delete from Campaign where id=:campaignId");
        theQuery.setParameter("campaignId", theId);
        theQuery.executeUpdate();
    }

}
