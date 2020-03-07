package org.nuts.pecan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mashun
 */
@EnableTransactionManagement
@SpringBootApplication
public class PecanApplication {

    public static void main(String[] args) {
        SpringApplication.run(PecanApplication.class, args);
    }

}
