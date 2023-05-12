package com.project.ShellPhone.database;

import com.project.ShellPhone.models.*;
import com.project.ShellPhone.models.Cart.CartItem;
import com.project.ShellPhone.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Time;
import java.sql.Timestamp;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(ProductRepo productRepo, UserRepo userRepo, CartItemsRepo cartItemsRepo, OrderRepo orderRepo, OrderItemsRepo orderItemsRepo){
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
                User user3 = new User("minhanh", "minhanh", "name","url");
                User user1 = new User("minhanh", "minhanh", "name","url");
                logger.info("insert data"+userRepo.save(user3));
                User user4 = new User("minhanh", "minhanh", "name","url");
                logger.info("insert data"+userRepo.save(user4));
                logger.info("insert data"+userRepo.save(user1));
                CartItem cartItem1 = new CartItem();
                cartItem1.setUser(user1);
                cartItem1.setProduct(productA);
                cartItem1.setQuantity(3);
                cartItemsRepo.save(cartItem1);
                CartItem cartItem2 = new CartItem();
                cartItem2.setUser(user1);
                cartItem2.setProduct(productB);
                cartItem2.setQuantity(5);
                cartItemsRepo.save(cartItem2);
                DonHang donHang = new DonHang();
                donHang.setUser(user1);
                Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
                donHang.setTimestamp(timeStamp);
                orderRepo.save(donHang);
                OrderItem orderItem1 = new OrderItem();
                orderItem1.setDonHang(donHang);
                orderItem1.setProduct(productC);
                orderItem1.setQuantity(9);
                orderItemsRepo.save(orderItem1);
                // Them Don hang
                DonHang donHang1 = new DonHang();
                donHang1.setUser(user1);
                orderRepo.save(donHang1);
                OrderItem orderItem2 = new OrderItem();
                orderItem2.setDonHang(donHang1);
                orderItem2.setProduct(productB);
                orderItem2.setQuantity(3);
                orderItemsRepo.save(orderItem2);
                OrderItem orderItem3 = new OrderItem();
                orderItem3.setDonHang(donHang1);
                orderItem3.setProduct(productA);
                orderItem3.setQuantity(4);
                orderItemsRepo.save(orderItem3);


            }
        };

    }

}
