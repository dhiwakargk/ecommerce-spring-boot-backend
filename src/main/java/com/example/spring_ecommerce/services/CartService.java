package com.example.spring_ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring_ecommerce.errorhandler.CartIdNotFound;
import com.example.spring_ecommerce.errorhandler.ProductIdNotFound;
import com.example.spring_ecommerce.errorhandler.UserIdNotFound;
import com.example.spring_ecommerce.models.CartModal;
import com.example.spring_ecommerce.models.OrderModal;
import com.example.spring_ecommerce.models.ProductModal;
import com.example.spring_ecommerce.models.UserModal;
import com.example.spring_ecommerce.repository.CartRepo;
import com.example.spring_ecommerce.repository.ProductRepo;
import com.example.spring_ecommerce.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class CartService 
{
    @Autowired
    private CartRepo cart_repo;

    @Autowired
    private ProductRepo product_repo;

    @Autowired
    private UserRepo user_repo;

    @Autowired
    private OrderService order_service;

    public List<CartModal> All_Carts_DB()
    {
        return cart_repo.findAll();
    }

    public ResponseEntity<Object> Create_Cart_DB(CartModal cart,int user_id,int product_id)
    {
        ProductModal product=product_repo.findById(product_id).orElse(null);

        UserModal user=user_repo.findById(user_id).orElse(null);

        if(product==null)
        {
            throw new ProductIdNotFound("The product id: "+product_id+" is not found for cart add");
        }

        if(user==null)
        {
            throw new UserIdNotFound("The user id: "+user_id+" is not found for cart add");
        }

        CartModal carts=new CartModal();
        carts.setCartProductId(product.getProductId());
        carts.setCartProductName(product.getProductName());
        carts.setCartProductImage(product.getProductImageUrl());
        carts.setCartProductPrice(product.getProductPrice());
        carts.setCartProductQuantity(cart.getCartProductQuantity());
        carts.setCartTotalPrice(carts.getCartProductQuantity()*product.getProductPrice());
        carts.setUser(user);
        user.getCarts().add(carts);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(cart_repo.save(carts));
    }

    public ResponseEntity<Object> Cart_Order_DB(int cart_id)
    {
        CartModal cart=cart_repo.findById(cart_id).orElse(null);
        if(cart==null)
        {
            throw new CartIdNotFound("The cart id: "+cart_id+" is not found for cart order");
        }
        OrderModal order=new OrderModal();
        order.setOrderQuantity(cart.getCartProductQuantity());
        ResponseEntity<Object> res= order_service.Create_Order_DB(order, cart.getUser().getUserId(), cart.getCartProductId());
        cart_repo.deleteById(cart_id);
        return res;
    } 

    @Transactional
    public ResponseEntity<Object> Cart_Total_Order_DB(int user_id)
    {
       List<OrderModal> all_orders=new ArrayList<>();
       
       UserModal user=user_repo.findById(user_id).orElse(null);

       if(user==null)
       {
        throw new UserIdNotFound("The User id: "+user_id+" is not found for cart total order");
       }
       
       List<CartModal> carts=new ArrayList<>(user.getCarts());

       for(CartModal cart:carts)
       {
         OrderModal order = new OrderModal();
        order.setOrderQuantity(cart.getCartProductQuantity());

        ResponseEntity<Object> res = order_service.Create_Order_DB(order, user_id, cart.getCartProductId());
        
        all_orders.add((OrderModal) res.getBody());        
 
        user.getCarts().remove(cart);
       }
       

       return ResponseEntity.status(HttpStatus.ACCEPTED).body(all_orders);
       
    }

    
}
