package masha.cha.util;

import java.math.BigDecimal;
import java.sql.*;

public class DbManager {

    public static void getAll() {

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

                System.out.println(num + " " + paydate + " " + receiver + " " + value);

            }

            result.close();
            statement.close();
            myConnection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addExpense(String a1, String a2, String a3, String a4) {
        int num = Integer.parseInt(a1);
        Date paydate = Date.valueOf(a2);
        int receiver = Integer.parseInt(a3);
        BigDecimal value = new BigDecimal(a4);

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


}
