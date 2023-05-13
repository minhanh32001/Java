package com.project.ShellPhone.repo;

import com.project.ShellPhone.models.Comment;
import com.project.ShellPhone.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
     List<Comment> findByProduct(Product product);

}