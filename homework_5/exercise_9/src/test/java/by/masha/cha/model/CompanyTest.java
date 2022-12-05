package by.masha.cha.model;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;

public class CompanyTest {

    @Test
    @SneakyThrows
    public void testCreateCompany() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");
        Company company = context.getBean("company", Company.class);

        assertEquals("Readiness: today FedExProject is ready: true; " +
                        "ReiffeisenProject is ready: false",
                company.checkProjectManager());

    }

}