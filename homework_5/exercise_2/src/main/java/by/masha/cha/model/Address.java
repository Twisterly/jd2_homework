package by.masha.cha.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Address implements AbstractAddress, DisposableBean {

    private Integer id;
    private String country;
    private String city;
    private String street;
    private Integer homeNumber;

    private Address() {
    }

    public Address(Integer id) {
        this.id = id;
    }

    public static Address getAddress() {
        return new Address();
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", homeNumber=" + homeNumber +
                '}';
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public List<Object> getAddressList() {
        List<Object> address = new ArrayList<>();
        address.add(id);
        address.add(country);
        address.add(city);
        address.add(street);
        address.add(homeNumber);
        return address;
    }

    public void init() {
        System.out.println("Call init() "
                + id + " "
                + country + " "
                + city + " "
                + street + " "
                + homeNumber);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Call destroy() in Address.class");
    }
}

