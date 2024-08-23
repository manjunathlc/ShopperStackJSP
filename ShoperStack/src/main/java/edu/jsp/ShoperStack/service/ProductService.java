package edu.jsp.ShoperStack.service;

import edu.jsp.ShoperStack.dao.ProductDao;
import edu.jsp.ShoperStack.entity.Product;
import edu.jsp.ShoperStack.exception.ProductNotFoundException;
import edu.jsp.ShoperStack.exception.ProductNotFoundException;
import edu.jsp.ShoperStack.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
  public  ResponseEntity<ResponseStructure<Product>> saveProduct(Product product){
     Product savedProduct = productDao.saveProduct(product);
     ResponseStructure<Product> structure= new ResponseStructure<Product>();
     structure.setData(savedProduct);
     structure.setMessage("Product saved successfully");
     structure.setStatusCode(HttpStatus.OK.value());

     return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<Product>> getProductById(int productId){
       Optional<Product> product = productDao.findById(productId);
       if(product.isPresent()){
           //return new ResponseEntity<ResponseStructure<Product>>(new ResponseStructure<Product>(product.get()), HttpStatus.OK);
           ResponseStructure<Product> structure= new ResponseStructure<Product>();
           structure.setData(product.get());
           structure.setMessage("Product found successfully");
           structure.setStatusCode(HttpStatus.OK.value());
           return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.FOUND);
       } else {
           //return new ResponseEntity<ResponseStructure<Product>>(HttpStatus.NOT_FOUND);
           throw new ProductNotFoundException("Product not found with product id = "+productId+" Not found");
       }
    }

    public ResponseEntity<ResponseStructure<List<Product>>> getAllProducts(){
       List<Product> products = productDao.findAll();
       if(!products.isEmpty()){
           //return new ResponseEntity<ResponseStructure<List<Product>>>(new ResponseStructure<List<Product>>(products), HttpStatus.OK);
           ResponseStructure<List<Product>> structure= new ResponseStructure<List<Product>>();
           structure.setData(products);
           structure.setMessage("All products found successfully");
           structure.setStatusCode(HttpStatus.OK.value());
           return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
       } else {
           return new ResponseEntity<ResponseStructure<List<Product>>>(HttpStatus.NO_CONTENT);
       }
    }

    public ResponseEntity<ResponseStructure<String>> deleteProductById(int productId){
      if(productDao.findById(productId).isPresent()){
          productDao.deleteById(productId);
          //return new ResponseEntity<ResponseStructure<String>>(new ResponseStructure<String>("Product deleted successfully"), HttpStatus.OK);
          ResponseStructure<String> structure= new ResponseStructure<String>();
          structure.setData("Product deleted successfully");
          structure.setMessage("Product deleted successfully");
          structure.setStatusCode(HttpStatus.OK.value());
          return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
      }
      return new ResponseEntity<ResponseStructure<String>>(HttpStatus.NOT_FOUND);
    }


    public ResponseEntity<ResponseStructure<Product>> updateProduct
            (int productId, Product product) {
        if (productDao.isPresent(productId)) {
            product.setProductId(productId);
            Product updatedProduct = productDao.saveProduct(product);
            ResponseStructure<Product> structure = new
                    ResponseStructure<Product>();
            structure.setData(updatedProduct);
            structure.setMessage("Updated");

            return new ResponseEntity<ResponseStructure<Product>>
                    (structure,HttpStatus.OK);
        }
        throw new ProductNotFoundException("Product Not FOund");
    }

    public ResponseEntity<ResponseStructure<Product>> modifyProduct(Product product){
      Optional<Product> existingProduct = productDao.findById(product.getProductId());
      if(existingProduct.isPresent()){

          Product product1=existingProduct.get();
          
      }
      return new ResponseEntity<ResponseStructure<Product>>(HttpStatus.NOT_FOUND);
    }



    public ResponseEntity<ResponseStructure<String>> updateProductprice(int productId, double productPrice) {
        if(productDao.isPresent(productId)){
            productDao.updateProductPrice(productPrice, productId);
            //return new ResponseEntity<ResponseStructure<Product>>(new ResponseStructure<Product>(productDao.findById(productId).get()), HttpStatus.OK);
            ResponseStructure<String > structure= new ResponseStructure<String>();
            structure.setMessage("Product price updated successfully");
            structure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
        }
        throw new ProductNotFoundException("Product not found with product id = "+productId+" Not found");
    }
}
