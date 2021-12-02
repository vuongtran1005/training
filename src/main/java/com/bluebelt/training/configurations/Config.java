package com.bluebelt.training.configurations;

import com.bluebelt.training.entities.Product;
import com.bluebelt.training.repositories.ProductRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Configuration
@EnableJpaAuditing // khởi chạy CreatedDate, LastModifiedDate
public class Config {

    @Bean
    CommandLineRunner iniCommandLineRunner(ProductRepository productDAO) { // Code first
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                Product productA = Product.builder().title("Product A").importPrice(BigDecimal.valueOf(3000)).description("Description product A").build();

                log.info("Insert data: " + productDAO.save(productA));

            }
        };
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Thiết lập các server dùng để test api
                .servers(List.of(
                        new Server().url("http://localhost:8080"),
                        new Server().url("http://localhost:8090")
                ))
                // info
                .info(new Info().title("Bluebelt Training Application API")
                        .description("Sample OpenAPI 3.0")
                        .contact(new Contact()
                                .email("vuong.tran@bluebelt.asia")
                                .name("training")
                                .url("http://localhost:8080/api/v1/products"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .version("1.0.0"));
    }

}
