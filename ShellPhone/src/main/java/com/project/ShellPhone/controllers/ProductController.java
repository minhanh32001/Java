package com.project.ShellPhone.controllers;

import com.project.ShellPhone.models.Comment;
import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.RespondObject;
import com.project.ShellPhone.models.Type;
import com.project.ShellPhone.repo.CommentRepo;
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
    @GetMapping("/combine")
    ResponseEntity<RespondObject> getMacProduct(@RequestParam("type1") Type type1, @RequestParam("type2") Type type2){
        List<Product> productByType = productRepo.findByType(type1);
        productByType.addAll(productRepo.findByType(type2));
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Get combine products successfully", productByType));
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

