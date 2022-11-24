package my.first.exercise7.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @NotNull
    @Column(name="city")
    private String city;

    @NotNull
    @Column(name="street")
    private String street;

    @NotNull
    @Column(name="house_number")
    private int houseNumber;

    @NotNull
    @Column(name="postcode")
    private int postcode;


}