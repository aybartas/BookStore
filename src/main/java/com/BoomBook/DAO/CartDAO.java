package com.BoomBook.DAO;

import com.BoomBook.Model.Cart;

import java.util.List;

public interface CartDAO {
    public List<Cart> findAll();

    public Cart findById(int theId);

    public void save(Cart theCart);

    public void deleteById(int theId);
}
