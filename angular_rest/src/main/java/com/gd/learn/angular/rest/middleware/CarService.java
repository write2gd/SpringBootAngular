package com.gd.learn.angular.rest.middleware;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gd.learn.angular.rest.dao.CarRepository;
import com.gd.learn.angular.rest.model.Car;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getAllLuxuryCars() {
        return carRepository.findAll().stream().filter(this::isCool).collect(Collectors.toList());

    }

    private boolean isCool(Car car) {
        return !car.getName().equals("Jaguar") && !car.getName().equals("Triumph") && !car.getName().equals("Ford")
                && !car.getName().equals("Honda");
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    public Car getById(Long id) {
        return carRepository.findById(id).get();
    }
}
