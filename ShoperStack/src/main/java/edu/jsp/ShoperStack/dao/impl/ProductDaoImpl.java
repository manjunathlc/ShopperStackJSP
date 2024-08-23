package edu.jsp.ShoperStack.dao.impl;

import edu.jsp.ShoperStack.dao.ProductDao;
import edu.jsp.ShoperStack.entity.Product;
import edu.jsp.ShoperStack.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductDaoImpl implements ProductDao {

    private final ProductRepository repository;

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public Optional<Product> findById(int productId) {
        return repository.findById(productId);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(int productId) {
        repository.deleteById(productId);

    }

    @Override
    public boolean isPresent(int productId) {
        return repository.existsById(productId);
    }

    @Override
    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public void updateProductPrice(int productId, double newPrice) {

    }

    @Override
    public void updateProductPrice(double newPrice, int productId) {
//        Product product = findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
//        product.setPrice(newPrice);
//        saveProduct(product);

        repository.updateProductPrice( productId,newPrice);

    }


}
