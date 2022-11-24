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
@Table(name = "shop_info")
@PrimaryKeyJoinColumn(name = "product_id")
public class Shop extends ProductInfo {

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_size")
    private double shopSize;

    @Builder
    public Shop(Integer id, String productName, double price,
                String shopName, double shopSize) {
        super(id, productName, price);
        this.shopName = shopName;
        this.shopSize = shopSize;

    }
}
