package com.project.ShellPhone.controllers;

import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.RespondObject;
import com.project.ShellPhone.models.Type;
import com.project.ShellPhone.repo.CommentRepo;
import com.project.ShellPhone.repo.ProductRepo;
import jakarta.validation.Valid;
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
    @Autowired
    private CommentRepo commentRepo;

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

    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getProduct(@PathVariable Long id) {
        Optional<Product> optionalProductFound = productRepo.findById(id);
        Product productFound = optionalProductFound.get();
        return optionalProductFound.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("ok", "found product", productFound, commentRepo.findByProduct(productFound))
                ):
             ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("ok", "product not found", ""));
        };
    @PostMapping("/add")
    ResponseEntity<RespondObject> addProduct(@RequestBody @Valid Product newProduct) {
        Product addProduct = newProduct;
         productRepo.save(addProduct);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Update product Successfully", productRepo.save(newProduct))
        );
    }

    @PutMapping("/{id}/update")
    ResponseEntity<RespondObject> updateProduct(@RequestBody Product newProduct, @PathVariable("id") Long id) {
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

    @DeleteMapping("/{id}/delete")
    ResponseEntity<RespondObject> deleteProduct(@PathVariable("id") Long id) {
        productRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Delete product Successfully", ""));
    }
}

