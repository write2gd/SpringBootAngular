package com.gd.learn.angular.rest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gd.learn.angular.rest.dao.CarRepository;
import com.gd.learn.angular.rest.middleware.CarService;
import com.gd.learn.angular.rest.model.Car;

@RestController
@RequestMapping("/api/cars")
class CarController {
    @Autowired
    private CarService carService;


    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200/*")
    public List<Car> allCars() {
        return carService.getAllCars();
    }

    @GetMapping("/luxury")
    @CrossOrigin(origins = "http://localhost:4200/*")
    public List<Car> luxuryCars() {
        return carService.getAllLuxuryCars();

    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200/*")
    public void deleteCars(@PathVariable Long id) {
        carService.deleteById(id);

    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200/*")
    public Car getCar(@PathVariable Long id) {
        return carService.getById(id);

    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200/*")
    public void addCar(@RequestBody Car car) {
        carService.saveCar(car);
    }

    @PutMapping
    @CrossOrigin(origins = "http://localhost:4200/*")
    public void updatedCar(@RequestBody Car car) {
        carService.saveCar(car);
    }
}