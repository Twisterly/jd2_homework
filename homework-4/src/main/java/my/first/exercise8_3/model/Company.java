package my.first.exercise8_3.model;

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
@Table(name = "company_info")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "object", discriminatorType =
 DiscriminatorType.STRING)
@DiscriminatorValue("Company")
public class Company implements Serializable, BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "company_id")
    private Integer id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "staff_number")
    private int staffNumber;
}
