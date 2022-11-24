package my.first.exercise8_2.model;

import lombok.*;
import my.first.base.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Kindergarten implements Serializable, BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kindergarten_seq")
    @SequenceGenerator(name = "kindergarten_seq", sequenceName = "t_kindergarten_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "kindergarten_head")
    private String kindergartenHead;

    @Column(name = "kindergarten_number")
    private int kindergartenNum;

    @Column(name = "kindergarten_swim_pool")
    private boolean swimmingPool;
}
