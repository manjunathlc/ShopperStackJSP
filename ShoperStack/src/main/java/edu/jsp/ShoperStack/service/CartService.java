package edu.jsp.ShoperStack.service;

import edu.jsp.ShoperStack.dao.CartDao;
import edu.jsp.ShoperStack.dao.ProductDao;
import edu.jsp.ShoperStack.entity.Cart;
import edu.jsp.ShoperStack.entity.Product;
import edu.jsp.ShoperStack.exception.CartNotFoundException;
import edu.jsp.ShoperStack.exception.ProductNotFoundException;
import edu.jsp.ShoperStack.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDao cartDao;

    private final ProductDao productDao;

    public ResponseEntity<ResponseStructure<Cart>> saveCart(Cart cart,int productId) {

        Optional<Product> optional = productDao.findById(productId);
        if(optional.isPresent()) {

            List<Product> listOfProducts = cart.getProducts();
            if (listOfProducts == null) {
                listOfProducts = new ArrayList<Product>();
            }
            listOfProducts.add(optional.get());
            cart.setProducts(listOfProducts);
        }

         cart = calculateTotalPrice(cart);

        Cart saveCart = cartDao.saveCart(cart);
            ResponseStructure<Cart> structure = new ResponseStructure<Cart>();
            structure.setData(saveCart);
            structure.setMessage("Cart saved successfully");
            structure.setStatusCode(HttpStatus.CREATED.value());
            return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.CREATED);
        }



    private Cart calculateTotalPrice(Cart cart) {
        if (cart.getProducts() != null && !cart.getProducts().isEmpty()) {
            List<Product> listOfproducts = cart.getProducts();

            int numberOfProducts = listOfproducts.size();
            cart.setNumberOfProducts(numberOfProducts);

            // calculate total price
            double totalPrice = 0;
            for (Product product : listOfproducts) {
                totalPrice += product.getProductPrice();
            } 
            cart.setTotalPrice(totalPrice);
            return cart;
        }
        throw new IllegalArgumentException("Cart must contain at least one product");
    }

    public ResponseEntity<ResponseStructure<List<Cart>>> getAllCarts() {
        List<Cart> carts = cartDao.findAll();
        if (!carts.isEmpty()) {
//            return ResponseEntity.ok(ResponseStructure.builder()
//                                                       .data(carts)
//                                                       .build());
            ResponseStructure<List<Cart>> structure = new ResponseStructure<List<Cart>>();
            structure.setData(carts);
            structure.setMessage("All carts found successfully");
            structure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure<List<Cart>>>(structure, HttpStatus.OK);
        }
        return new ResponseEntity<ResponseStructure<List<Cart>>>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<ResponseStructure<Cart>> getCartById(int cartId) {
        Optional<Cart> cart = cartDao.findById(cartId);
        if (cart.isPresent()) {
            //return ResponseEntity.ok(ResponseStructure.builder())
            //                                             .data(cart.get())
            //                                             .build());
            ResponseStructure<Cart> structure = new ResponseStructure<Cart>();
            structure.setData(cart.get());
            structure.setMessage("Cart found successfully");
            structure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.OK);
        }
        return new ResponseEntity<ResponseStructure<Cart>>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResponseStructure<Cart>> deleteCart(int cartId) {
        Optional<Cart> cart = cartDao.findById(cartId);
        if (cart.isPresent()) {
            cartDao.delete(cartId);
            //return ResponseEntity.ok(ResponseStructure.builder())
            //                                             .build());
            ResponseStructure<Cart> structure = new ResponseStructure<Cart>();
            structure.setMessage("Cart deleted successfully");
            structure.setStatusCode(HttpStatus.NO_CONTENT.value());
            return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<ResponseStructure<Cart>>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ResponseStructure<Cart>> addProduct(int cartId, List<Integer> productIds){
        Optional<Cart> optional=cartDao.findById(cartId);
        if(optional.isPresent()){
            Cart cart=optional.get();

            if(cart.getProducts()==null){
                cart.setProducts(new ArrayList<>());
            }

            for(int productId:productIds){
                Optional<Product> productOptional=productDao.findById(productId);
                if(productOptional.isPresent()){
                    cart.getProducts().add(productOptional.get());
                } else{
                    throw new ProductNotFoundException("Product id with given id " + productId+" is not present");
                }
            }

            cart=calculateTotalPrice(cart);
            Cart modifiedCart = cartDao.saveCart(cart);
            //return ResponseEntity.ok(ResponseStructure.builder())
            //                                             .data(cart)
            //                                             .build());
            ResponseStructure<Cart> structure = new ResponseStructure<Cart>();
            structure.setData(modifiedCart);
            structure.setMessage("Products added to cart successfully Modification completed");
            structure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.OK);
        }
        throw new CartNotFoundException("Cart with given Id not found");
    }

}
