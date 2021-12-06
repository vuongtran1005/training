package com.bluebelt.training.configurations;

import com.bluebelt.training.entities.Collection;
import com.bluebelt.training.entities.Option;
import com.bluebelt.training.entities.Product;
import com.bluebelt.training.entities.common.Slug;
import com.bluebelt.training.repositories.CollectionRepository;
import com.bluebelt.training.repositories.OptionRepository;
import com.bluebelt.training.repositories.ProductRepository;
import com.bluebelt.training.repositories.impl.BaseRepositoryImpl;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@EnableJpaRepositories(basePackages = "com.bluebelt.training.*", repositoryBaseClass = BaseRepositoryImpl.class, repositoryImplementationPostfix = "Impl") // kích hoạt BaseRepositoryImpl
@RequiredArgsConstructor
@Configuration
@EnableJpaAuditing // khởi chạy CreatedDate, LastModifiedDate
public class Config {

    private final ProductRepository productRepository;

    private final CollectionRepository collectionRepository;

    private final OptionRepository optionRepository;

    @PostConstruct
    public void initData() {
        // Insert 100 products vào H2 Database sau khi
        // DatasourceConfig được khởi tạo
        final Random r = new Random();
        productRepository.saveAll(IntStream.range(0, 20)
                .mapToObj(i -> Product.builder()
                        .title("Product " + i)
                        .description("Description product " + i)
                        .seo(Slug.setSlugify("Product-" + i))
                        .tags("tag")
                        .build())
                .collect(Collectors.toList())
        );
        log.info("INSERT 20 PRODUCTS SUCCESSFUL!!!");

        collectionRepository.saveAll(IntStream.range(0, 10)
                .mapToObj(i -> Collection.builder()
                        .title("Collection " + i)
                        .description("Description collection " + i)
                        .sortOrder("BEST-SELLING")
                        .build()
                ).collect(Collectors.toList())
        );
        log.info("INSERT 20 COLLECTION SUCCESSFUL!!!");

//        optionRepository.saveAll(IntStream.range(0, 10)
//                .mapToObj(i -> Option.builder()
//                        .title("Option " + i)
//                        .build()
//                ).collect(Collectors.toList())
//        );
//        log.info("INSERT 20 OPTION SUCCESSFUL!!!");
    }

//    @Bean
//    CommandLineRunner iniCommandLineRunner(ProductRepository productDAO) { // Code first
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//
//                Product productA = Product.builder().title("Product A").importPrice(BigDecimal.valueOf(3000)).description("Description product A").build();
//
//                log.info("Insert data: " + productDAO.save(productA));
//
//            }
//        };
//    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // Thiết lập các server dùng để test api
                .servers(Arrays.asList(
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
