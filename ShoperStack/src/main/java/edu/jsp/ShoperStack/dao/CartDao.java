package edu.jsp.ShoperStack.dao;

import edu.jsp.ShoperStack.entity.Cart;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CartDao {
    Cart saveCart(Cart cart);

    List<Cart> findAll();

    Optional<Cart> findById(int cartId);

    void delete (int cartId);

    boolean isPresent(int cartId);

}
