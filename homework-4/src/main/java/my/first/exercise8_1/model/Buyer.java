package my.first.exercise8_1.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buyer_info")
@PrimaryKeyJoinColumn(name = "product_id")
public class Buyer extends ProductInfo {
    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_surname")
    private String buyerSurname;

    @Column(name = "buyer_age")
    private int buyerAge;

    @Builder
    public Buyer(Integer id, String productName, double price,
                 String buyerName, String buyerSurname, int buyerAge) {
        super(id, productName, price);
        this.buyerName = buyerName;
        this.buyerSurname = buyerSurname;
        this.buyerAge = buyerAge;
    }
}
