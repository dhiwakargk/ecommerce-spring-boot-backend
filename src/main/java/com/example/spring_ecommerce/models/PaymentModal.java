package com.example.spring_ecommerce.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PaymentModal 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int paymentId;

    @Column(nullable=false)
    private int paymentOrderId;

    @Column(nullable=false)
    private int paymentUserId;

    @Column(nullable=false)
    private double paymentPrice;

    private Date paymentDate=new Date();

    private String paymentStatus;

    @JsonBackReference("user-payment")
    @ManyToOne
    @JoinColumn(name="userId")
    private UserModal user;

    public PaymentModal(){}

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(double paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public UserModal getUser() {
        return user;
    }

    public void setUser(UserModal user) {
        this.user = user;
    }

    public int getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(int paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

    public int getPaymentUserId() {
        return paymentUserId;
    }

    public void setPaymentUserId(int paymentUserId) {
        this.paymentUserId = paymentUserId;
    }

   

    
}
