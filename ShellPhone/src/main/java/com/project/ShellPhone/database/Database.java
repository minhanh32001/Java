package com.project.ShellPhone.database;

import com.project.ShellPhone.models.*;
import com.project.ShellPhone.repo.CartItemsRepo;
import com.project.ShellPhone.repo.CartRepo;
import com.project.ShellPhone.repo.ProductRepo;
import com.project.ShellPhone.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepo productRepo, UserRepo userRepo, CartItemsRepo cartItemsRepo, CartRepo cartRepo){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Ipad 16", Type.IPAD ,3000.0, 30, 300, "M1 ipad", "ShellPhone/image/ipadm1-pro.webp");
                Product productB = new Product("Iphone 19",Type.IPHONE, 6000.0,10, 100, "Iphone 14 promax", "ShellPhone/image/iphone14-promsx.png");
                Product productC = new Product("Ipad 13", Type.IPAD ,1500.0,20, 269, "Ipad mini", "ShellPhone/image/ipad-mini.webp");
//                Product productD = new Product("Iphone 16", Type.IPHONE ,3000.0, 128, "An old 16 iphone", "");
//                Product productE = new Product("Imac 12", Type.IMAC ,1200.0, 30, "An old 12 imac", "");
//                Product productF = new Product("Ipod 10", Type.IPOD ,1000.0, 200, "An old 16 ipod", "");
//                Product productG = new Product("Airpod 16", Type.AIRPODS ,3000.0, 300, "An old 16 airpod", "");
//                Product productH = new Product("Watch 16", Type.WATCH ,3000.0, 300, "An old 16 ipad", "");

                logger.info("insert data"+productRepo.save(productA));
                logger.info("insert data"+productRepo.save(productB));
                logger.info("insert data"+productRepo.save(productC));
//                logger.info("insert data"+productRepo.save(productD));
//                logger.info("insert data"+productRepo.save(productE));
//                logger.info("insert data"+productRepo.save(productF));
//                logger.info("insert data"+productRepo.save(productG));
//                logger.info("insert data"+productRepo.save(productH));
//                /*Cart cart1 = new Cart();*/
                User user3 = new User("minhanh", "minhanh", "name","url");
                User user1 = new User("minhanh", "minhanh", "name","url");
                logger.info("insert data"+userRepo.save(user3));
                logger.info("insert data"+userRepo.save(user1));
                Cart cart = new Cart(user1);
                logger.info("insert data"+cartRepo.save(cart));
                CartItems cartItems = new CartItems(cart, productA, 3);
                logger.info("insert data"+cartItemsRepo.save(cartItems));
            }
        };

    }

}
