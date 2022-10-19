package masha.cha.util;


import masha.cha.dao.ExpenseDAO;
import masha.cha.model.Expense;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class MyMain {

    public static void main(String[] args) {
        ExpenseDAO expensesDAO = new ExpenseDAO();
        int num = Integer.parseInt(args[0]);
        Date paydate = Date.valueOf(args[1]);
        int receiver = Integer.parseInt(args[2]);
        BigDecimal value = new BigDecimal(args[3]);

        expensesDAO.addExpense(new Expense(num, paydate, receiver, value));
        List<Expense> resultList = expensesDAO.getAll();
        expensesDAO.printAll(resultList);


    }

}
