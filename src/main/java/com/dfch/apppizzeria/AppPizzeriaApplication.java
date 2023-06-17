package com.dfch.apppizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AppPizzeriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppPizzeriaApplication.class, args);
    }

    //Aplicacion de prueba creada desde el curso de Platzi con el repositorio de github
    //https://github.com/platzi/curso-java-spring-data-jpa/tree/clase-23--listener-personalizado-auditoria

}
