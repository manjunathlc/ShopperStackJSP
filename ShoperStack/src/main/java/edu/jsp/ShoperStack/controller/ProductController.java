package edu.jsp.ShoperStack.controller;

import edu.jsp.ShoperStack.entity.Product;
import edu.jsp.ShoperStack.service.ProductService;
import edu.jsp.ShoperStack.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/version/product")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService service;


    @PostMapping("/save")
     ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product){

        return service.saveProduct(product);
    }

    @GetMapping("/getProductById/productId")
    ResponseEntity<ResponseStructure<Product>> getProductById(@RequestParam int productId){
        return service.getProductById(productId);
    }

    @GetMapping("/getAllProducts")
    ResponseEntity<ResponseStructure<List<Product>>> getAllProducts(){
        return service.getAllProducts();
    }

    @DeleteMapping("/deleteProductById/productId")
    ResponseEntity<ResponseStructure<String>> deleteProductById(@RequestParam int productId){
        return service.deleteProductById(productId);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestParam int productId,
                                                                    @RequestBody Product product) {
        return service.updateProduct(productId, product);
    }


    @PatchMapping("/modifyProduct")
    ResponseEntity<ResponseStructure<Product>> modifyProduct(@RequestBody Product product){
        return service.modifyProduct(product);
    }

    @PatchMapping("/updatePrice")
    public ResponseEntity<ResponseStructure<String>> updateProductPrice
            (@RequestParam int productId,@RequestParam double productPrice) {
        return service.updateProductprice(productId, productPrice);
    }

}
