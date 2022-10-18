package masha.cha.util;


import masha.cha.dao.ExpensesDAO;
import masha.cha.model.Expenses;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class MyMain {

    public static void main(String[] args) {
        ExpensesDAO expensesDAO = new ExpensesDAO();
        int num = Integer.parseInt(args[0]);
        Date paydate = Date.valueOf(args[1]);
        int receiver = Integer.parseInt(args[2]);
        BigDecimal value = new BigDecimal(args[3]);

        expensesDAO.addExpenses(new Expenses(num, paydate, receiver, value));
        List<Expenses> resultList = expensesDAO.getAll();
        expensesDAO.printAll(resultList);


    }

}
