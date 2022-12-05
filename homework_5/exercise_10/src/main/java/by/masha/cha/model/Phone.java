package by.masha.cha.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Phone extends Product implements IProduct {

    private Integer camera;

    @Override
    public Double getPrice() {
        if (super.price == null) {
            return 2500.0;
        } else
            return super.price;
    }
}
