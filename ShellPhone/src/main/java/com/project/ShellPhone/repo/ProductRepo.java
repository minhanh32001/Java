package com.project.ShellPhone.repo;
import com.project.ShellPhone.models.Product;
import com.project.ShellPhone.models.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);
    List<Product> findByType(Type type);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findAll();
    Optional<Product> findByName(String name);
    Optional<Product> findById(Long id);
}
