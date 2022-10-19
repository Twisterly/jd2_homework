package masha.cha.dao;

import masha.cha.model.Expense;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ExpenseDAO {

    public List<Expense> getReceiversAndSum() {
        List<Expense> resultList = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver: " + cnfe);
        }

        String dbURL = "jdbc:mysql://localhost:3306/listexpenses";
        String username = "root";
        String password = "root";
        Connection myConnection;

        try {
            myConnection = DriverManager.getConnection(dbURL, username,
                    password);
            String query = "SELECT receiver, SUM(value) AS \"total value\" " +
                    "FROM expenses GROUP " +
                    "BY receiver";
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement(query);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                int receiver = result.getInt("receiver");
                BigDecimal totalValue = result.getBigDecimal("total value");

                Expense expenses = new Expense(receiver, totalValue);
                resultList.add(expenses);

            }

            result.close();
            preparedStatement.close();
            myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;

    }

    public BigDecimal getSumOfLargestPaymentDay() {
        BigDecimal result = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver: " + cnfe);
        }

        String dbURL = "jdbc:mysql://localhost:3306/listexpenses";
        String username = "root";
        String password = "root";
        Connection myConnection;

        try {
            myConnection = DriverManager.getConnection(dbURL, username,
                    password);
            String query = "SELECT SUM(value) AS \"total value\" FROM " +
                    "expenses WHERE paydate=(SELECT paydate FROM expenses " +
                    "WHERE value=(SELECT MAX(value) FROM expenses) )";

            PreparedStatement preparedStatement =
                    myConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BigDecimal totalValue = resultSet.getBigDecimal("total value");
                result = totalValue;

            }

            resultSet.close();
            preparedStatement.close();
            myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Payment sum(day with highest payment) = " + result);

        return result;

    }

    public BigDecimal getLargestPayment() {
        BigDecimal result = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver: " + cnfe);
        }

        String dbURL = "jdbc:mysql://localhost:3306/listexpenses";
        String username = "root";
        String password = "root";
        Connection myConnection;

        try {
            myConnection = DriverManager.getConnection(dbURL, username,
                    password);
            String query = "SELECT MAX(value) AS \"max value\" FROM expenses " +
                    "WHERE paydate = (SELECT paydate FROM expenses GROUP BY " +
                    "paydate ORDER BY sum(value) DESC LIMIT 1) ";

            PreparedStatement preparedStatement =
                    myConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BigDecimal value = resultSet.getBigDecimal("max value");
                result = value;

            }

            resultSet.close();
            preparedStatement.close();
            myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Largest payment = " + result);

        return result;

    }


    public void printReceiverAndSum(List<Expense> expenses) {
        for (Expense exp : expenses) {
            System.out.println("Receiver " + exp.getReceiver()
                    + ": total= " + exp.getTotalValue());

        }
    }

}
