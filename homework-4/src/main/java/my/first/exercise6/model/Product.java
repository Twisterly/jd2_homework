package my.first.exercise6.model;

import lombok.*;
import my.first.base.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_info")
@Builder
public class Product implements Serializable, BaseEntity<BigInteger> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "t_product_seq",
            allocationSize = 10)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    public String toString() {
        return "Product: " +
                " id = " + getId() +
                ", name = " + getName() +
                ", price = " + getPrice();
    }
}
