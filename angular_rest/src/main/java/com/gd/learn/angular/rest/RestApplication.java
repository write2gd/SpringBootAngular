package com.gd.learn.angular.rest;

import java.util.Collections;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.gd.learn.angular.rest.middleware.CarService;
import com.gd.learn.angular.rest.middleware.UserAccountService;
import com.gd.learn.angular.rest.model.Car;
import com.gd.learn.angular.rest.model.User;

@SpringBootApplication
public class RestApplication {
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
    @PostConstruct
    private void init(){
        System.out.println("Spring Boot - active profile: " + environment.getActiveProfiles());

    }
    @Bean
    ApplicationRunner init(CarService carService, UserAccountService accountService) {

        return args -> {
            System.out.println("***Initializing Cars...***");
            Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti", "Aston Martin", "Triumph", "Ford", "Wolkswagan", "Tata", "Honda", "Suzuki")
                  .forEach(name -> {
                      Car car = new Car();
                      car.setName(name);
                      carService.saveCar(car);
                  });
            carService.getAllCars()
                      .forEach(System.out::println);

            System.out.println("***Initializing Users...***");
            User user = new User();
            user.setEmail("write2gd@india.com");
            user.setUserName("angular");
            user.setFirstName("GD");
            user.setLastName("Gouranga");
            user.setPassword("qwerty");
            try {
                accountService.saveUser(user);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            accountService.getAllUser()
                          .forEach(System.out::println);
        };

    }
    @Bean
    @SuppressWarnings("unchecked")
    public FilterRegistrationBean simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/api/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
