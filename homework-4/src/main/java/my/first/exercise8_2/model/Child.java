package my.first.exercise8_2.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "child_info")
@ToString(callSuper = true)
public class Child extends Kindergarten {

    @Column(name = "child_name")
    private String childName;

    @Column(name = "child_surname")
    private String childSurname;

    @Column(name = "child_group_number")
    private int groupNumber;

    @Builder
    public Child(Integer id, String kindergartenHead, int kindergartenNum, boolean swimmingPool,
                 String childName, String childSurname, int groupNumber) {
        super(id, kindergartenHead, kindergartenNum, swimmingPool);
        this.childName = childName;
        this.childSurname = childSurname;
        this.groupNumber = groupNumber;
    }
}
