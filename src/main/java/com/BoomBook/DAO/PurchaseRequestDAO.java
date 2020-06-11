package com.BoomBook.DAO;

import com.BoomBook.Model.Book;
import com.BoomBook.Model.PurchaseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRequestDAO extends JpaRepository<PurchaseRequest,Integer> {
    public List<PurchaseRequest> findAll();

    public PurchaseRequest findById(int id);

    //public void save(PurchaseRequest purchaseRequest);

    public void deleteById(int id);

    @Query("select p from PurchaseRequest p, Cart c, InCargo i where p.cartID = c.id and i.purchaseRequest.id = p.id and c.customer.id = ?1")
    List<PurchaseRequest> findPurchaseRequestByCartID(int id);
}
