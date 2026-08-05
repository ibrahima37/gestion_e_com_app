package maboutique.shop.boutiqueservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {
        "maboutique.shop.boutiqueservice",
        "maboutique.shop.commonsecurity"
})
public class BoutiqueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoutiqueServiceApplication.class, args);
    }
}
