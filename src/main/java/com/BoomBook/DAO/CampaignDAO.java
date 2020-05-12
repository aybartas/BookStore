package com.BoomBook.DAO;

import com.BoomBook.Model.Book;
import com.BoomBook.Model.Campaign;

import java.util.List;

public interface CampaignDAO {

    public List<Campaign> findAll();

    public Campaign findById(int theId);

    public void save(Campaign theCampaign);

    public void deleteById(int theId);

    public List<Campaign> findBooksCampaign(int theBookId);

}
