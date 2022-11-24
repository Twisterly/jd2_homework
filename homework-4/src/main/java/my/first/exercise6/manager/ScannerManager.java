package my.first.exercise6.manager;

import my.first.exercise6.dao.CarDao;
import my.first.exercise6.dao.PersonDao;
import my.first.exercise6.dao.ProductDao;
import my.first.exercise6.dao.ProjectDao;
import my.first.exercise6.model.Car;
import my.first.exercise6.model.Person;
import my.first.exercise6.model.Product;
import my.first.exercise6.model.Project;

import java.util.Scanner;

public class ScannerManager {

    private final Scanner console = new Scanner(System.in);

    String line = null;

    public void startConsole() {
        System.out.println("Let's generate Identity! Make your choice:\n" +
                "1 - GenerationType.SEQUENCE\n" +
                "2 - GenerationType.TABLE\n" +
                "3 - GenerationType.IDENTITY\n" +
                "4 - UUID\n" +
                "5 - EXIT\n" +
                "Insert:\n");

        int num = checkInteger(console.next());
        switch (num) {
            case 1:
                new ProductDao().saveAndPrintIdentity(Product.builder().build());
                break;
            case 2:
                new PersonDao().saveAndPrintIdentity(Person.builder().build());
                break;
            case 3:
                new ProjectDao().saveAndPrintIdentity(Project.builder().build());
                break;
            case 4:
                new CarDao().saveAndPrintIdentity(Car.builder().build());
                break;
            case 5:
                closeConsole();
                System.exit(0);
        }
        startNewTransaction();
    }

    public int checkInteger(String line) {
        String regex = "[12345]";
        if (!(line.matches(regex))) {
            do {
                System.out.println("Please, print 1,2,3,4 or 5");
                line = console.next();
            } while (!(line.matches(regex)));
        }
        int result = Integer.valueOf(line);
        return result;
    }

    public void closeConsole() {
        System.out.println("Have a nice day! Goodbye!");
        console.close();
    }

    public void startNewTransaction() {
        System.out.println("Do yo want one more transaction? Y/N");
        line = console.next();
        String answer = checkYesOrNo(line);
        if (answer.equalsIgnoreCase("y")) {
            startConsole();
        } else if (answer.equalsIgnoreCase("n")) {
            System.out.println("Have a nice day! Goodbye!");
        } else {
            System.out.println("Please, print Y or N");
            startConsole();
        }
    }

    public  String checkYesOrNo(String line) {
        String regex = "[ynNY]";
        if (!(line.matches(regex))) {
            do {
                System.out.println("Please, print Y or N");
                line = console.next();
            } while (!(line.matches(regex)));
        }
        String result = line;
        return result;

    }

}
