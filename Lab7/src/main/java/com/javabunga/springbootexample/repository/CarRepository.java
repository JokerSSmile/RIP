package com.javabunga.springbootexample.repository;

import com.javabunga.springbootexample.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

}
