package my.first.exercise7.model;

import lombok.*;
import my.first.base.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_info")
@Builder
public class Delivery implements Serializable, BaseEntity<Integer> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
            "delivery_seq")
    @SequenceGenerator(name = "delivery_seq", sequenceName = "t_delivery_seq",
            allocationSize = 1)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "address")
    private Address address;

    @Column(name = "lorry")
    private Lorry lorry;



}
