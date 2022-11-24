package my.first.exercise8_1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.first.base.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productJ_info")
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductInfo implements Serializable, BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "productInfo_seq")
    @SequenceGenerator(name = "productInfo_seq", sequenceName =
            "t_productInfo_seq",
            allocationSize = 1)
    @Column(name = "product_id")
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private double productPrice;

    public String toString() {
        return "Product: " +
                " id = " + getId() +
                ", name = " + getProductName() +
                ", price = " + getProductPrice();
    }
}


