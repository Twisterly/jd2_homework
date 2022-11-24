package my.first.exercise6.model;

import lombok.*;
import my.first.base.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "car_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car implements Serializable, BaseEntity<UUID> {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid" +
                                    ".CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id")
    private UUID id;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private String year;


    public String toString() {
        return "Car: " +
                " id = " + getId() +
                ", model = " + getModel() +
                ", year = " + getYear();
    }

}
