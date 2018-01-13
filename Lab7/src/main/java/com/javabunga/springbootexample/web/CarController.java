package com.javabunga.springbootexample.web;

import com.javabunga.springbootexample.model.Car;
import com.javabunga.springbootexample.repository.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Controller
public class CarController {

    private CarRepository repository;

    CarController(CarRepository carRepository) {
        this.repository = carRepository;
    }

    private String getText() {
        String url = "http://example.com/";
        String message = "";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            message = response.toString();
        } catch (Exception err) {
            message = err.getMessage();
        }

        return message;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getCarsList(Map<String, Object> model) {
        model.put("cars", repository.findAll());
        model.put("car", new Car());
        model.put("sampleText", getText());

        return "index";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String addCar(@ModelAttribute Car car) {
        repository.save(car);

        return "redirect:/";
    }
}
