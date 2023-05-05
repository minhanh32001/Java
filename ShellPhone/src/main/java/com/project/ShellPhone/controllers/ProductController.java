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
@RequestMapping(path = "api/v1/products")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;
    @GetMapping("")

    List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    @GetMapping("/byType")
    List<Product> getProductByType(@RequestParam("type") Type type) {
        return productRepo.findByType(type);
    }

    @GetMapping("byId/{id}")
    ResponseEntity<RespondObject> getProduct(@PathVariable Long id){
        Optional<Product> productFound = productRepo.findById(id);
        return productFound.isPresent()?
            ResponseEntity.status(HttpStatus.OK).body(
                    new RespondObject("ok", "found product", productFound)
          ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new RespondObject("false", "can not found", "")
            );
    }
    @PostMapping("/add")
    ResponseEntity<RespondObject> insertProduct(@RequestBody Product newProduct){

        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Insert product Successfully", productRepo.save(newProduct))
        );
    }
}
