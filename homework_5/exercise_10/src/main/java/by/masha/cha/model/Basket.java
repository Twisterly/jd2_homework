package by.masha.cha.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Basket {

    private IProduct product1;
    private IProduct product2;

    @Autowired
    public Basket(@Qualifier("phone") IProduct product1,
                  @Qualifier("laptop") IProduct product2) {
        this.product1 = product1;
        this.product2 = product2;
    }

    public Double getSum() {
        return product1.getPrice() + product2.getPrice();

    }

}
