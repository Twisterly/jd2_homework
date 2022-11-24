package my.first.exercise6.model;

import lombok.*;
import my.first.base.entity.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project implements Serializable, BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public String toString() {
        return "Project: " +
                " id = " + getId() +
                ", name = " + getName();
    }

}
