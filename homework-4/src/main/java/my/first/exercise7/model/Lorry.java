package my.first.exercise7.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lorry {

    @NotNull
    @Column(name = "lorry_model")
    private String model;

    @NotNull
    @Column(name = "lorry_number")
    private String number;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name =
                    "driver_name")),
            @AttributeOverride(name = "surname", column = @Column(name =
                    "driver_surname"))
    })
    private Driver driver;

}
