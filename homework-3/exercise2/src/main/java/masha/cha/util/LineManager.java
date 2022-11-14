package masha.cha.util;

import masha.cha.dao.PersonDaoImpl;
import java.util.Scanner;

public class LineManager {

    Scanner console = new Scanner(System.in);
    PersonDaoImpl personDaoImpl = new PersonDaoImpl();

    public  String checkYesOrNo(String line) {
        String regex = "[ynNY]";
        if (!(line.matches(regex))) {
            do {
                System.out.println("Please, try again");
                line = console.next();
            } while (!(line.matches(regex)));
        }
        String result = line;
        return result;

    }

    public int checkInteger(String line) {
        String regex = "[0-9]*";
        if (!(line.matches(regex))) {
            do {
                System.out.println("Please, try again");
                line = console.next();
            } while (!(line.matches(regex)));
        }
        int result = Integer.valueOf(line);
        return result;
    }

    public String checkString(String line) {
        String regex = "[A-ZА-Я][a-zа-я]*";
        if (!(line.matches(regex))) {
            do {
                System.out.println("Please, try again");
                line = console.next();
            } while (!(line.matches(regex)));
        }
        return line;
    }

    public int checkUpdateMenu(String line) {
        String regex = "[123]";
        if (!(line.matches(regex))) {
            do {
                System.out.println("Please, try again");
                line = console.next();
            } while (!(line.matches(regex)));
        }
        int num = Integer.valueOf(line);
        return num;
    }

    public int checkId(String line) {
        int result = checkInteger(line);
        if (personDaoImpl.getById(Long.valueOf(result)) == null) {
            do {
                System.out.println("ERROR! There is no person with such ID in" +
                        " DataBase!");
                System.out.println("Please, try again");
                line = console.next();
                checkInteger(line);
            } while (personDaoImpl.getById(Long.valueOf(line)) == null);
            result = Integer.valueOf(line);
        }

        return result;

    }

    public int checkAge(String line) {
        String regex = "(([1-9])|([1-9][0-9])|([1][0-2][0-3]))";
        if (!(line.matches(regex))) {
            do {
                System.out.println("Please, try again");
                line = console.next();
            } while (!(line.matches(regex)));
        }
        return Integer.valueOf(line);

    }



}
