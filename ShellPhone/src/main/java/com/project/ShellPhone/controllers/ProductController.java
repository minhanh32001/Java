package com.project.ShellPhone.controllers;

import com.project.ShellPhone.models.Comment;
import com.project.ShellPhone.models.DTO.ProductDTO;
import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.RespondObject;
import com.project.ShellPhone.models.Type;
import com.project.ShellPhone.models.user.User;
import com.project.ShellPhone.repo.CommentRepo;
import com.project.ShellPhone.repo.ProductRepo;
import com.project.ShellPhone.service.DTOService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private DTOService dtoService;
    @Autowired
    private CommentRepo commentRepo;
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return currentUser;
    }

    @GetMapping("")
    List<ProductDTO> getAllProducts(@RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 15);
        Page<Product> productPage = productRepo.findAll(pageable);
        List<ProductDTO> products = dtoService.getProducts(productPage.getContent());
        return products;
    }

    @GetMapping("/byType")
    ResponseEntity<RespondObject> getProductByType(@RequestParam("type") Type type) {
        List<Product> productByType = productRepo.findByType(type);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Get products successfully", productByType));
    }

    private Optional<Product> getProductById(Long id){
        Optional<Product> optionalProductFound = productRepo.findById(id);
        return optionalProductFound;
    }

    @GetMapping("/{id}")
    ResponseEntity<RespondObject> getProduct(@PathVariable Long id) {
        Optional<Product> product = getProductById(id);
        return getProductById(id).isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("ok", "found product", dtoService.convertProductIntoDTO(product.get()), commentRepo.findByProduct(product.get()))
                ):
                ResponseEntity.status(HttpStatus.OK).body(
                        new RespondObject("ok", "product not found", ""));
    };
    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/add")
    ResponseEntity<RespondObject> addProduct(@RequestBody @Valid Product newProduct) {
        newProduct.setLastPrice(); // Tính toán giá cuối cùng
        productRepo.save(newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Add product successfully", newProduct)
        );
    }
    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("/{id}/update")
    ResponseEntity<RespondObject> updateProduct(@RequestBody Map<String, Object> updates, @PathVariable("id") Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = optionalProduct.get();
        Field[] fields = Product.class.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            if (updates.containsKey(fieldName)) {
                field.setAccessible(true);
                try {
                    Object newValue = updates.get(fieldName);

                    // Xử lý trường hợp đặc biệt cho trường "type"
                    if (fieldName.equals("type")) {
                        Type type = Type.valueOf((String) newValue);
                        field.set(product, type);
                    } else {
                        field.set(product, newValue);
                    }

                    // Tính toán lại lastPrice nếu có cập nhật discount hoặc price
                    if (fieldName.equals("discount") || fieldName.equals("price")) {
                        product.setLastPrice();
                    }
                } catch (IllegalAccessException e) {
                    // Xử lý ngoại lệ nếu cần thiết
                    e.printStackTrace();
                }
            }
        }

        Product updatedProduct = productRepo.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Update product successfully", updatedProduct)
        );
    }




    private boolean isNumericField(Field field) {
        Class<?> fieldType = field.getType();
        return fieldType == int.class || fieldType == Integer.class || fieldType == long.class || fieldType == Long.class;
    }



    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/{id}/delete")
    ResponseEntity<RespondObject> deleteProduct(@PathVariable("id") Long id) {
        productRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "Delete product Successfully", ""));
    }


    @PostMapping("/{id}/comment")
    ResponseEntity<RespondObject> comment(@PathVariable("id") Long id, @RequestBody @Valid Comment comment) {
        Comment comment1 = comment;
        comment1.setProduct(getProductById(id).get());
        comment1.setTimestamp(new Timestamp(System.currentTimeMillis()));
        comment1.setUser(getCurrentUser());
        commentRepo.save(comment1);
        return ResponseEntity.status(HttpStatus.OK).body(
                new RespondObject("ok", "You have left a comment", comment1.getContent()));
    }
}
