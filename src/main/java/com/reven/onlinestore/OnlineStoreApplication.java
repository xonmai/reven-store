package com.reven.onlinestore;

import com.reven.onlinestore.common.model.Product;
import com.reven.onlinestore.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import java.time.Instant;

@Slf4j
@SpringBootApplication
@EnableJms
public class OnlineStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDB(ProductRepository productRepository) {
        return (args) -> {
            log.info("Run OnlineStoreApplication.initDB()");
            Product iphone = new Product()
                    .setId(1L)
                    .setName("iPhone Pro Max")
                    .setBrand("Apple")
                    .setColor("Black")
                    .setDescription("Latest apple design")
                    .setPrice(1500.0)
                    .setQuantity(10L)
                    .setUpdatedDate(Instant.now().toEpochMilli());

            productRepository.save(iphone);
        };
    }

}
