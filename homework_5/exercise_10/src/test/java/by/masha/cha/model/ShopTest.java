package by.masha.cha.model;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class ShopTest {
    @Test
    @SneakyThrows
    public void testCreateCompany() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        Shop shop = context.getBean("shop", Shop.class);

        assertEquals("Basket total price: 8400.0", shop.checkBasket());
    }

}
