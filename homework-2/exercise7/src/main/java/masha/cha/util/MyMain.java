package masha.cha.util;

import masha.cha.dao.Expense;
import masha.cha.dao.MyDao;
import masha.cha.dao.Receiver;
import java.math.BigDecimal;
import java.sql.Date;


public class MyMain {

    public static void main(String[] args) {
        MyDao myDao = new MyDao();
        myDao.addReceiver(new Receiver(6, "Viktor Viktorov"));
        System.out.println("======================================");
        System.out.println(myDao.getReceiver(6));
        System.out.println("======================================");
        myDao.printAll(myDao.getReceivers());
        System.out.println("======================================");
        myDao.printAll(myDao.getExpenses());
        System.out.println("======================================");
        System.out.println(myDao.getExpense(2));
        System.out.println("======================================");
        myDao.addExpense(new Expense(13, Date.valueOf("2011-05-15"),
                new Receiver(5), new BigDecimal(9000.0)));
    }


}
