package my.first.exercise8_2.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "educator_info")
@ToString(callSuper = true)
public class Educator extends Kindergarten {

    @Column(name = "educator_name")
    private String educatorName;

    @Column(name = "educator_surname")
    private String educatorSurname;

    @Column(name = "educator_experience")
    private int educatorExperience;

    @Builder
    public Educator(Integer id, String kindergartenHead, int kindergartenNum, boolean swimmingPool,
                    String educatorName, String educatorSurname,
                    int educatorExperience) {
        super(id, kindergartenHead, kindergartenNum, swimmingPool);
        this.educatorName = educatorName;
        this.educatorSurname = educatorSurname;
        this.educatorExperience = educatorExperience;
    }
}
