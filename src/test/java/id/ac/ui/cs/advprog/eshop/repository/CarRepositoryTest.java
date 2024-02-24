package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    @InjectMocks
    CarRepository carRepository;

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testUpdateExistingCar() {
        Car car = new Car();
        car.setCarId("123");
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity(10);
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Honda");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(20);

        Car result = carRepository.update("123", updatedCar);

        assertNotNull(result);
        assertEquals("123", result.getCarId());
        assertEquals("Honda", result.getCarName());
        assertEquals("Blue", result.getCarColor());
        assertEquals(20, result.getCarQuantity());
    }

    @Test
    void testUpdateExistingCarWithSeveralCarsInRepository() {
        Car car = new Car();
        car.setCarId("234");
        car.setCarName("Pajero");
        car.setCarColor("Hitam");
        car.setCarQuantity(10);
        carRepository.create(car);

        Car car2 = new Car();
        car2.setCarId("123");
        car2.setCarName("Avanza");
        car2.setCarColor("Biru");
        car2.setCarQuantity(1);
        carRepository.create(car2);

        Car updatedCar = new Car();
        updatedCar.setCarName("Brio");
        updatedCar.setCarColor("Merah");
        updatedCar.setCarQuantity(2);

        Car result = carRepository.update("123", updatedCar);

        assertNotNull(result);
        assertEquals("123", result.getCarId());
        assertEquals("Brio", result.getCarName());
        assertEquals("Merah", result.getCarColor());
        assertEquals(2, result.getCarQuantity());
    }

    @Test
    void testUpdateNonExistingCar() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Honda");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(20);

        Car result = carRepository.update("non-existing-id", updatedCar);

        assertNull(result);
    }

    @Test
    void testDeleteExistingCar() {
        Car car = new Car();
        car.setCarId("123");
        carRepository.create(car);

        carRepository.delete("123");

        assertNull(carRepository.findById("123"));
    }

    @Test
    void testDeleteNonExistingCar() {
        carRepository.delete("fake-id");

        assertNull(carRepository.findById("fake-id"));
    }

    @Test
    void testFindByIdExistingCar() {
        Car car = new Car();
        car.setCarId("123");
        carRepository.create(car);

        Car foundCar = carRepository.findById("123");

        assertNotNull(foundCar);
        assertEquals("123", foundCar.getCarId());
    }

    @Test
    void testFindByIdNonExistingCar() {
        Car foundCar = carRepository.findById("fake-id");

        assertNull(foundCar);
    }

    @Test
    void testFindByIdNonExistingCarButRepositoryIsNotEmpty() {
        Car car = new Car();
        car.setCarId("123");
        carRepository.create(car);

        Car foundCar = carRepository.findById("234");

        assertNull(foundCar);
    }
    @Test
    void testDeleteByIdExistingCar() {
        Car car = new Car();
        car.setCarId("123");
        carRepository.create(car);

        carRepository.delete("123");

        assertNull(carRepository.findById("123"));
    }

    @Test
    void testDeleteByIdNonExistingCar() {
        carRepository.delete("fake-id");

        assertNull(carRepository.findById("fake-id"));
    }
}
