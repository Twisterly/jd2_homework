package masha.cha.util;

import masha.cha.dao.ExpenseDAO;
import masha.cha.model.Expense;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class MyMain {

    public static void main(String[] args) {
        ExpenseDAO expenseDAO = new ExpenseDAO();
        int num = Integer.parseInt(args[0]);
        Date paydate = Date.valueOf(args[1]);
        int receiver = Integer.parseInt(args[2]);
        BigDecimal value = new BigDecimal(args[3]);

        expenseDAO.addExpense(new Expense(num, paydate, receiver,
                value));
        List<Expense> resultList = expenseDAO.getAll();
        expenseDAO.printAll(resultList);


    }

}
