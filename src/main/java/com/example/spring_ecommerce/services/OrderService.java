package com.example.spring_ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring_ecommerce.errorhandler.OrderIdNotFound;
import com.example.spring_ecommerce.errorhandler.ProductIdNotFound;
import com.example.spring_ecommerce.errorhandler.ProductQuantity;
import com.example.spring_ecommerce.errorhandler.UserIdNotFound;
import com.example.spring_ecommerce.models.OrderModal;
import com.example.spring_ecommerce.models.PaymentModal;
import com.example.spring_ecommerce.models.ProductModal;
import com.example.spring_ecommerce.models.UserModal;
import com.example.spring_ecommerce.repository.OrderRepo;
import com.example.spring_ecommerce.repository.PaymentRepo;
import com.example.spring_ecommerce.repository.ProductRepo;
import com.example.spring_ecommerce.repository.UserRepo;

@Service
public class OrderService 
{
    @Autowired
    private OrderRepo order_repo;

    @Autowired
    private ProductRepo product_repo;

    @Autowired
    private UserRepo user_repo;

    @Autowired
    private PaymentRepo payment_repo;

    @Autowired
    private PaymentService payment_service;

    @Autowired
    private EmailService email_service;

    public ResponseEntity<Object> Create_Order_DB(OrderModal order,int user_id,int product_id)
    {
        ProductModal product=product_repo.findById(product_id).orElse(null);
        UserModal user=user_repo.findById(user_id).orElse(null);
        if(product==null)
        {
            throw new ProductIdNotFound("The product id: "+product_id+" is not found for order service");
        }

        if(order.getOrderQuantity()>product.getProductStock())
        {
            throw new ProductQuantity("The product Quantity is: "+order.getOrderQuantity()+" is not in stock");
        }

        if(user==null)
        {
            throw new UserIdNotFound("The user id: "+user_id+" is not found for order service");
        }
        
       
        order.setOrderProductId(product.getProductId());
        order.setOrderName(product.getProductName());
        order.setOrderProductImage(product.getProductImageUrl());
        order.setOrderPrice(product.getProductPrice());
        order.setOrderQuantity(order.getOrderQuantity());
        order.setOrderTotalPrice(product.getProductPrice()*order.getOrderQuantity());
        
        order.setUser(user);
        user.getOrders().add(order);
        product.setProductStock(product.getProductStock()-order.getOrderQuantity());
        product_repo.save(product);
        
        PaymentModal payment=new PaymentModal();
        payment.setPaymentUserId(user_id);
        payment.setPaymentPrice(order.getOrderTotalPrice());
        payment.setPaymentStatus("Success");
        payment.setPaymentOrderId(order.getOrderId());
        payment_service.Save_Payment(payment);
        
        OrderModal orders=order_repo.save(order);

        email_service.Send_Email(order, user_id);

        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
        
    }

    public ResponseEntity<Object> Delete_Order_DB(int id)
    {
        OrderModal order=order_repo.findById(id).orElse(null);

        if(order==null)
        {
           throw new OrderIdNotFound("The order id: "+id+" is not found for delete order");
        }

        ProductModal product=product_repo.findById(order.getOrderProductId()).orElse(null);
        product.setProductStock(order.getOrderQuantity()+product.getProductStock());
        product_repo.save(product);
        
        PaymentModal payment=payment_repo.findByPaymentOrderId(id);
        payment.setPaymentStatus("Amount will be returned with in 5 working days");
        payment_repo.save(payment);

        order_repo.deleteById(id);
       
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The order is Canceled Successfully");
    }
}
