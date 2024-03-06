package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void testCreate() {
        Car car = new Car();
        when(carRepository.create(car)).thenReturn(car);

        Car createdCar = carService.create(car);

        assertEquals(car, createdCar);
        verify(carRepository).create(car);
    }

    @Test
    void testFindAll() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car());
        carList.add(new Car());

        Iterator<Car> iterator = carList.iterator();
        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> result = carService.findAll();

        assertEquals(carList.size(), result.size());
        verify(carRepository).findAll();
    }

    @Test
    void testFindById() {
        String carId = "1";
        Car car = new Car();
        when(carRepository.findById(carId)).thenReturn(car);

        Car foundCar = carService.findById(carId);

        assertEquals(car, foundCar);
        verify(carRepository).findById(carId);
    }

    @Test
    void testUpdate() {
        String carId = "1";
        Car car = new Car();

        carService.update(carId, car);

        verify(carRepository).update(carId, car);
    }

    @Test
    void testDeleteCarById() {
        String carId = "1";

        carService.deleteCarById(carId);

        verify(carRepository).delete(carId);
    }
}
