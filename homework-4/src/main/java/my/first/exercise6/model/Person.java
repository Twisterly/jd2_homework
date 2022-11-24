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
@Table(name = "person_info")
@Builder
public class Person implements Serializable, BaseEntity<BigInteger> {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator =
            "personTableGen")
    @TableGenerator(name = "personTableGen", table = "person_id_table",
            valueColumnName = "pk_value", allocationSize = 1)
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;


    public String toString() {
        return "Person: " +
                " id = " + getId() +
                ", name = " + getName() +
                ", surname = " + getSurname() +
                ", age = " + getAge();
    }
}
