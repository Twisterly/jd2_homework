package masha.cha.util;

public class MyMain {

    public static void main(String[] args) {
        DbManager.addExpense(args[0], args[1], args[2], args[3]);
        DbManager.getAll();

    }

}
