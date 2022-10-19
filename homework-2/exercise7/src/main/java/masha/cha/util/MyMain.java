package masha.cha.util;

import masha.cha.dao.MyDao;

public class MyMain {

    public static void main(String[] args) {
        MyDao myDao = new MyDao();
 //       myDao.addReceiver(new Receiver(5, "Aglaya Taasova"));
        myDao.getReceiver(1);
        myDao.getReceivers()
                .forEach(receiver -> System.out.println(receiver.getNum() +
                        " " + receiver.getName()));
        myDao.getExpenses()
                .forEach(expense -> System.out.println(expense.getNum() + " " +
                        expense.getDate() + " " + expense.getReceiver().getNum() + " " +
                        expense.getValue()));
        myDao.getExpense(2);
  //      myDao.addExpense(new Expense(13, Date.valueOf("2011-05-15"),
  //              new Receiver(5), new BigDecimal(9000.0)));
    }


}
