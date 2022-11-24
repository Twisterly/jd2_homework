package my.first.exercise8_3.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Operator")
public class Operator extends Company {

    @Column(name = "operator_name")
    private String operatorName;

    @Column(name = "operator_surname")
    private String operatorSurname;

    @Column(name = "operator_salary")
    private double salary;

    @Builder
    public Operator(Integer id, String companyName, int stuffNumber,
                    String operatorName, String operatorSurname,
                    double salary) {
        super(id, companyName, stuffNumber);
        this.operatorName = operatorName;
        this.operatorSurname = operatorSurname;
        this.salary = salary;
    }
}
