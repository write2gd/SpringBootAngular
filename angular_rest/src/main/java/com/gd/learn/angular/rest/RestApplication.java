package com.gd.learn.angular.rest;

import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gd.learn.angular.rest.middleware.CarService;
import com.gd.learn.angular.rest.middleware.UserAccountService;
import com.gd.learn.angular.rest.model.Car;
import com.gd.learn.angular.rest.model.User;

@SpringBootApplication
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
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

}
