package id.ac.ui.cs.advprog.eshop.model;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


@Getter
@Setter
public class Car {
    private String carId;
    private String carName;
    private String carColor;
    private int carQuantity;

    public Car() {
        UUID uuid = UUID.randomUUID();
        this.setCarId(uuid.toString());
    }
}