package edu.jsp.ShoperStack.repository;

import edu.jsp.ShoperStack.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update Product p SET p.productPrice=?2 WHERE p.productId=?1")
    void updateProductPrice(int productId, double newPrice);
}
