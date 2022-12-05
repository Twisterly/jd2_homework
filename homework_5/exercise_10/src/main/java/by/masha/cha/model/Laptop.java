package by.masha.cha.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Laptop extends Product implements IProduct {

    private Integer usbSlot;

    @Override
    public Double getPrice() {
        if (super.price == null) {
            return 5900.0;
        } else
            return super.price;
    }
}
