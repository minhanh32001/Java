package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByType(Type type);
    Optional<Product> findByName(String name);
}
