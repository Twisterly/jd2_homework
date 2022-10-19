package masha.cha.util;

import masha.cha.dao.ExpensesDAO;
import masha.cha.model.Expenses;

import java.util.List;

public class MyMain {
    public static void main(String[] args) {
        ExpensesDAO expensesDAO = new ExpensesDAO();
        List<Expenses> resultList = expensesDAO.getReceiversSumm();
        expensesDAO.printReceiverAndSumm(resultList);
        expensesDAO.getSumOfLargestPaymentDay();
        expensesDAO.getLargestPayment();


    }
}
