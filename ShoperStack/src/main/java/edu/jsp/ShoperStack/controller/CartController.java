package edu.jsp.ShoperStack.controller;

import edu.jsp.ShoperStack.dao.ProductDao;
import edu.jsp.ShoperStack.entity.Cart;
import edu.jsp.ShoperStack.service.CartService;
import edu.jsp.ShoperStack.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/version/cart")
public class CartController {

    private final CartService cartService;


    @PostMapping("/saveCart")
    public ResponseEntity<ResponseStructure<Cart>> saveCart(@RequestBody Cart cart,
                                                            @RequestParam(required = false, name = "productId") int productId) {

        return cartService.saveCart(cart,productId);
    }

    @GetMapping("/getAllCarts")
    public ResponseEntity<ResponseStructure<List<Cart>>> getAllCarts() {
        return cartService.getAllCarts();

    }

    @GetMapping("/getCartById/{cartId}")
    public ResponseEntity<ResponseStructure<Cart>> getCartById(@PathVariable int cartId) {
        return cartService.getCartById(cartId);
    }

    @DeleteMapping("/deleteCart/{cartId}")
    public ResponseEntity<ResponseStructure<Cart>> deleteCart(@PathVariable int cartId) {
        return cartService.deleteCart(cartId);
    }

    @PutMapping("/addProduct")
    public ResponseEntity<ResponseStructure<Cart>> addProduct(@RequestParam int cartId,
                                                              @RequestParam(required = false, name = "productIds") List<Integer> productIds) {
        return cartService.addProduct(cartId, productIds);
    }


}
