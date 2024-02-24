package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarService carService;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCarPage() {
        assertEquals("CreateCar", carController.createCarPage(model));
        verify(model).addAttribute(eq("car"), any(Car.class));
    }

    @Test
    void testCreateCarPost() {
        Car car = new Car();
        assertEquals("redirect:listCar", carController.createCarPost(car, model));
        verify(carService).create(car);
    }

    @Test
    void testCarListPage() {
        List<Car> carList = new ArrayList<>();
        when(carService.findAll()).thenReturn(carList);

        assertEquals("CarList", carController.carListPage(model));
        verify(model).addAttribute(eq("cars"), eq(carList));
    }

    @Test
    void testEditCarPage() {
        String carId = "1";
        Car car = new Car();
        when(carService.findById(carId)).thenReturn(car);

        assertEquals("EditCar", carController.editCarPage(carId, model));
        verify(model).addAttribute(eq("car"), eq(car));
    }

    @Test
    void testEditCarPost() {
        Car car = new Car();
        car.setCarId("1");
        assertEquals("redirect:listCar", carController.editCarPost(car, model));
        verify(carService).update(eq("1"), eq(car));
    }

    @Test
    void testDeleteCar() {
        String carId = "1";
        assertEquals("redirect:listCar", carController.deleteCar(carId));
        verify(carService).deleteCarById(carId);
    }
}
