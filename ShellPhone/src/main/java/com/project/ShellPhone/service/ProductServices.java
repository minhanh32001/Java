package com.project.ShellPhone.service;

import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {
    @Autowired
    private ProductRepo productRepo;

    public void makeOrder(Long id, int Quantity){
        Product product = productRepo.findById(id).get();
        product.setNumber(product.getNumber()- Quantity);
        productRepo.save(product);
    }
    public void cancelOrder(Long id, int Quantity){
        Product product = productRepo.findById(id).get();
        product.setNumber(product.getNumber()+ Quantity);
        productRepo.save(product);
    }
}
