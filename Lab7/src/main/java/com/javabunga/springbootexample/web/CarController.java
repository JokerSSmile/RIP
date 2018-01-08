package com.javabunga.springbootexample.web;

import com.javabunga.springbootexample.model.Car;
import com.javabunga.springbootexample.repository.CarRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class CarController {

    private CarRepository repository;

    CarController(CarRepository carRepository) {
        this.repository = carRepository;
    }

    @RequestMapping("/get-cars")
    public Iterable<Car> GetCarsList() {
        return repository.findAll();
    }

    @RequestMapping("/add-car")
    public void AddCar(@RequestBody Car car) {
        repository.save(car);
    }
}
