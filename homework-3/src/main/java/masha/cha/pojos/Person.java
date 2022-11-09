package masha.cha.pojos;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@Entity
@Getter
@Setter
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator"
    )
    @Column(name = "person_id")
    private Long id;


    @Column(name = "age")
    private Integer age;


    @Column(name = "name")
    private String name;


    @Column(name = "surname")
    private String surname;

    public Person() {
    }

    public Person(Integer age, String name, String surname) {
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    public Person(Long id, Integer age, String name, String surname) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    public String toString() {
        return "Person " +
                " id = " + getId() +
                ", age = " + getAge() +
                ", name = " + getName() +
                ", surname = " + getSurname();
    }
}
