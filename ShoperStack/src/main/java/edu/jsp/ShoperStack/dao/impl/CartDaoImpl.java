package edu.jsp.ShoperStack.dao.impl;

import edu.jsp.ShoperStack.dao.CartDao;
import edu.jsp.ShoperStack.entity.Cart;
import edu.jsp.ShoperStack.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CartDaoImpl implements CartDao {

    private final CartRepository repository;

    @Override
    public Cart saveCart(Cart cart) {
        return  repository.save(cart);
    }

    @Override
    public List<Cart> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Cart> findById(int cartId) {
        return repository.findById(cartId);
    }

    @Override
    public void delete(int cartId) {
        repository.deleteById(cartId);

    }

    @Override
    public boolean isPresent(int cartId) {
        return repository.existsById(cartId);
    }
}
