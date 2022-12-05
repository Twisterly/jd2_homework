package by.masha.cha.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HomeAddress implements AbstractAddress {

    private Integer id;
    private String country;
    private String city;
    private Integer zipcode;

    private HomeAddress() {
    }

    public static HomeAddress getHomeAddress() {
        return new HomeAddress();
    }

    @Override
    public String toString() {
        return "HomeAddress{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipcode=" + zipcode +
                '}';
    }

    @Override
    public List<Object> getAddressList() {
        List<Object> address = new ArrayList<>();
        address.add(id);
        address.add(country);
        address.add(city);
        address.add(zipcode);
        return address;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getCity() {
        return city;
    }

    public void init() {
        System.out.println("Call init() "
                + id + " "
                + country + " "
                + city + " "
                + zipcode);
    }

    public void destroy() {
        System.out.println("Call destroy() in HomeAddress.class");
    }


}
