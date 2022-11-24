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
@DiscriminatorValue("Equipment")
public class Equipment extends Company {

    @Column(name = "equipment_model")
    private String model;

    @Builder
    public Equipment(Integer id, String companyName, int stuffNumber, String model) {
        super(id, companyName, stuffNumber);
        this.model = model;
    }
}

