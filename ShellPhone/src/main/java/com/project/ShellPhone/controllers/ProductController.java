package com.project.ShellPhone.controllers;

import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.RespondObject;
import com.project.ShellPhone.models.Type;
import com.project.ShellPhone.repo.ProductRepo;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("")
    ResponseEntity<RespondObject> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Get all products successfully", allProducts));

    }

    @GetMapping("/byType")
    ResponseEntity<RespondObject> getProductByType(@RequestParam("type") Type type) {
        List<Product> productByType = productRepo.findByType(type);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Get products successfully", productByType));
    }

    @GetMapping("/byId}")
    ResponseEntity<RespondObject> getProduct(@RequestParam Long id) {
        Optional<Product> productFound = productRepo.findById(id);
        return productFound.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("ok", "found product", productFound)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RespondObject("false", "can not found", "")
                );
    }

    @PutMapping("/add")
    ResponseEntity<RespondObject> insertProduct(@RequestBody Product newProduct, @RequestParam Long id) {
        Product updateProduct = productRepo.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setType(newProduct.getType());
                    product.setNumber(newProduct.getNumber());
                    product.setUrl(newProduct.getUrl());
                    product.setDescribe(newProduct.getDescribe());
                    return productRepo.save(product);
                }).orElseGet(() -> {
                    return productRepo.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Update product Successfully", productRepo.save(newProduct))
        );

    }

    @DeleteMapping("/delete")
    ResponseEntity<RespondObject> deleteProduct(@RequestParam Long id) {
        productRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Delete product Successfully", ""));
    }
}

