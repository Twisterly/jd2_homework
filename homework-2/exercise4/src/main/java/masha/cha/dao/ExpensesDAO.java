package masha.cha.dao;

import masha.cha.model.Expenses;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpensesDAO {

    public List<Expenses> getAll() {
        List<Expenses> resultList = new ArrayList<>();

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

                Expenses expenses = new Expenses(num, paydate, receiver, value);
                resultList.add(expenses);

            }

            myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Data Retrieved Successfully");

        return resultList;
    }

    public void addExpenses(Expenses expenses) {

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
            String query = "INSERT INTO expenses(num, paydate, receiver, " +
                    "value) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement =
                    myConnection.prepareStatement(query);
            preparedStatement.setInt(1, num);
            preparedStatement.setDate(2, paydate);
            preparedStatement.setInt(3, receiver);
            preparedStatement.setBigDecimal(4, value);

            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Data Inserted Successfully");
            } else if (i == 0) {
                System.out.println("Data was NOT Inserted Successfully");
            }

            myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void printAll(List<Expenses> expenses) {
        for (Expenses exp : expenses) {
            System.out.println(exp);
        }

    }

}
