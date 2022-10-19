package masha.cha.dao;

import masha.cha.model.Expense;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    public List<Expense> getAll() {
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
            Statement statement = myConnection.createStatement();
            String query = "SELECT * FROM expenses";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int num = result.getInt("num");
                Date paydate = result.getDate("paydate");
                int receiver = result.getInt("receiver");
                BigDecimal value = result.getBigDecimal("value");

                Expense expenses = new Expense(num, paydate, receiver, value);
                resultList.add(expenses);

            }

            result.close();
            statement.close();
            myConnection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Data Retrieved Successfully");

        return resultList;
    }

    public void addExpense(Expense expenses) {
        int num = expenses.getNum();
        Date paydate = expenses.getPaydate();
        int receiver = expenses.getReceiver();
        BigDecimal value = expenses.getValue();

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
            Statement statement = myConnection.createStatement();
            String query = "INSERT INTO expenses(num, paydate, receiver, " +
                    "value) VALUES " + "('" + num + "', '" + paydate + "', " + receiver + ", '" + value + "')";

            int i = statement.executeUpdate(query);
            if (i > 0) {
                System.out.println("Data Inserted Successfully");
            } else if (i == 0) {
                System.out.println("Data was NOT Inserted");
            }

            statement.close();
            myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printAll(List<Expense> expenses) {
        for (Expense exp : expenses) {
            System.out.println(exp.toString());

        }

    }


}
