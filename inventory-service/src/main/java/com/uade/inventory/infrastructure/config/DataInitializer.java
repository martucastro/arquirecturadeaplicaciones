package com.uade.inventory.infrastructure.config;

import com.uade.inventory.domain.model.Product;
import com.uade.inventory.domain.port.out.ProductRepositoryPort;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    @Value("${app.max-items-per-page}")
    private int maxItemsPerPage;

    private final ProductRepositoryPort productRepositoryPort;

    public DataInitializer(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @PostConstruct
    public void logConfig() {
        log.info("Max items per page: {}", maxItemsPerPage);
    }

    @Override
    public void run(String... args) {
        if (productRepositoryPort.count() == 0) {
            productRepositoryPort.save(new Product("Laptop", 10, 999.99));
            productRepositoryPort.save(new Product("Mouse", 50, 29.99));
            productRepositoryPort.save(new Product("Teclado", 30, 79.99));
            productRepositoryPort.save(new Product("Monitor", 15, 349.99));
            productRepositoryPort.save(new Product("Auriculares", 25, 59.99));
        }
    }

}
