package com.BoomBook.DAO;

import com.BoomBook.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {
    public List<Customer> findAll();

    public Customer findById(int theId);

    //public void save(Customer theCustomer);

    public void deleteById(int theId);


    //@Query(value = "select * from Customer cust, Purchase_Request purch, Cart cart, In_Cargo inCargo where cust.id = cart.customer_id and purch.cart_id = cart.id ", nativeQuery = true)
    public Page<Customer> findCustomerById(int id, Pageable pageable);

    public List<Customer> findByEmail(String email);




}
