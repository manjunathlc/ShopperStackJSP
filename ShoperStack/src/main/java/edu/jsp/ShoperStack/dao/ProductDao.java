package edu.jsp.ShoperStack.dao;

import edu.jsp.ShoperStack.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDao {
    Product saveProduct(Product product);

    Optional<Product> findById(int productId);

    List<Product> findAll();

    void deleteById(int productId);

    boolean isPresent(int productId);

    Product updateProduct(Product product);

    void updateProductPrice(int productId, double newPrice);

    void updateProductPrice(double newPrice, int productId);
}
