package masha.cha.util;

import masha.cha.dao.ExpenseDAO;
import masha.cha.model.Expense;
import java.util.List;

public class MyMain {
    public static void main(String[] args) {
        ExpenseDAO expensesDAO = new ExpenseDAO();
        List<Expense> resultList = expensesDAO.getReceiversAndSum();
        expensesDAO.printReceiverAndSum(resultList);
        expensesDAO.getSumOfLargestPaymentDay();
        expensesDAO.getLargestPayment();


    }
}
