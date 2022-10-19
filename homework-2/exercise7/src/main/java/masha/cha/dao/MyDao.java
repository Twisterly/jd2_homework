package masha.cha.dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;


public class MyDao implements Dao {

    @Override
    public Receiver getReceiver(int num) {
        Receiver receiver = new Receiver();
        receiver.setNum(num);

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
            String query = "SELECT name FROM  receivers WHERE num=" + num + ";";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                receiver.setName(result.getString("name"));

            }

            result.close();
            statement.close();
            myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Data Retrieved Successfully");

        return receiver;
    }

    @Override
    public ArrayList<Receiver> getReceivers() {
        ArrayList<Receiver> resultList = new ArrayList<>();

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
            String query = "SELECT * FROM receivers";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int num = result.getInt("num");
                String name = result.getString("name");
                Receiver receiver = new Receiver(num, name);
                resultList.add(receiver);

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


    @Override
    public Expense getExpense(int num) {
        Expense expense = new Expense();
        expense.setNum(num);

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
            String query = "SELECT * FROM  expenses WHERE num=" + num + ";";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                expense.setDate(result.getDate("paydate"));
                int receiverNum = result.getInt("receiver");
                expense.setReceiver(getReceiver(receiverNum));
                expense.setValue(result.getBigDecimal("value"));

            }

            result.close();
            statement.close();
            myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Data Retrieved Successfully");
        //     System.out.println(expense.getNum() + " " + expense.getDate()
        //     + " " + expense.getReceiver().getNum() + " " + expense
        //     .getReceiver().getName() + " " + expense.getValue());
        return expense;
    }

    @Override
    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> resultList = new ArrayList<>();

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
                Date date = result.getDate("paydate");
                int receiver = result.getInt("receiver");
                BigDecimal value = result.getBigDecimal("value");

                Expense expenses = new Expense(num, date,
                        new Receiver(receiver), value);
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


    @Override
    public int addReceiver(Receiver receiver) {
        int num = receiver.getNum();
        String name = receiver.getName();
        int i = 0;

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
            String query =
                    "INSERT INTO receivers(num, name) VALUES " + "('" + num + "', '" + name + "')";

            i = statement.executeUpdate(query);
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

        return i;
    }

    @Override
    public int addExpense(Expense expense) {
        int num = expense.getNum();
        Date paydate = expense.getDate();
        Receiver receiver = expense.getReceiver();
        BigDecimal value = expense.getValue();
        int i = 0;

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
            preparedStatement.setInt(3, receiver.getNum());
            preparedStatement.setBigDecimal(4, value);

            i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println("Data Inserted Successfully");
            } else if (i == 0) {
                System.out.println("Data was NOT Inserted");
            }

            preparedStatement.close();
            myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return i;
    }
}
