package com.example.spring_ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.spring_ecommerce.errorhandler.ProductIdNotFound;
import com.example.spring_ecommerce.errorhandler.UserIdNotFound;
import com.example.spring_ecommerce.models.AdminModal;
import com.example.spring_ecommerce.models.ProductModal;
import com.example.spring_ecommerce.repository.AdminRepo;
import com.example.spring_ecommerce.repository.ProductRepo;

@Service
public class ProductService 
{
    @Autowired
    private ProductRepo product_repo;

    @Autowired
    private AdminRepo admin_repo;

    public List<ProductModal> All_Product_DB()
    {
        return product_repo.findAll();
    }

    public ResponseEntity<Object> Create_Product_DB(ProductModal product,int admin_id)
    {
        AdminModal admin=admin_repo.findById(admin_id).orElse(null);
        if(admin!=null)
        {
            product.setAdmin(admin);
            admin.getProducts().add(product);
            return ResponseEntity.status(HttpStatus.OK).body(product_repo.save(product));
        }
        else 
        {
            throw new UserIdNotFound("The admin id: "+admin_id+" is not exists for adding product");
        }

       
    }

    public ResponseEntity<Object> Update_Product_DB(ProductModal product)
    {
        ProductModal existingProduct=product_repo.findById(product.getProductId()).orElse(null);
        if(existingProduct!=null)
        {
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setProductStock(product.getProductStock());
        existingProduct.setProductImageUrl(product.getProductImageUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(product_repo.save(existingProduct));

        }
        else 
        { 
          throw new ProductIdNotFound("The Product id: "+product.getProductId()+" is not found for update");
        }
    }

    public ResponseEntity<Object> Delete_product_DB(int id)
    {
        ProductModal product=product_repo.findById(id).orElse(null);
        if(product!=null)
        {
           product_repo.deleteById(id);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Deleted Successfully...");
        }
        else 
        {
            throw new ProductIdNotFound("The Product id: "+id+" is not found for delete");
        }
    }
}
