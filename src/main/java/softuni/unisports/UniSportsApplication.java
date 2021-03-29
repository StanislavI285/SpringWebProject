package softuni.unisports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UniSportsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniSportsApplication.class, args);
    }

}
