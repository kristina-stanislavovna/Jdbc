import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Database database = new Database("root",
                "331331mama", "jdbc:mysql://localhost:3306/jdbc");
//        database.findAllLaptops();
//      List<Employee> emps = database.findByName("Anna");
//        System.out.println(emps);
//        List<Account> accounts = database.allAccounts();
//        System.out.println(accounts);
//        List<Account> accounts1 = database.diffBalance(1, 555);
//        System.out.println(accounts1);
//        int sum = database.allAccountsSum();
//        System.out.println("SUM ALL ACOUNTS : " + sum + "$");
//        List<Account> x = database.findByChar('a');
//        System.out.println(x);
        List<User> users = database.findAllUsers();
        System.out.println(users);

        /*
         * Создать метод который найдет самый дорогой аккаунт и сохранит его в файл где название аккаунта будет его логин
         *
         * Создать метод который найдет общую сумму всех заблакированных аккаунтов при этом отфилтровать блокировку на стороне sql
         *
         * Создать метод который найдет колличетсво аккаунтов чей баланс будет лежать в диапазоне и быть заблокированным
         *
         * Создать метод который посчитает колличество аккаунтов чей логин будет иметь четную длину
         *  и заканчиваться на словосочетание переданное через аргумент*/
//
//        User user = database.findMaxUsers();
//        System.out.println(user);

//        int sumIsBlock = database.sumIsBlock();
//        System.out.println("SUM isBLOCK users: " + sumIsBlock + " $");
//        int count = database.countUsersisBlock(70, 160);
//        System.out.println("Isblock users " + count);
//        int countbyName = database.countByName("ah");
//        System.out.println("Count by name: " + countbyName);

        List<User> users1 = database.findAllUsers();
        System.out.println(users1);

//        User max = database.findMaxUser(); // не печатаю на консоль потому что там запись в файле
//        int sum = database.sumIsBlockUsers();
//        System.out.println("SUM is block users: " + sum);
//        int count = database.countIsBlockUsersBydiff(80, 120);
//        System.out.println("Count is block users with diff " + count);
//        int countBylogin = database.countByLogin("ick");
//        System.out.println("Count users by login:  " + countBylogin);

        int count = database.countIsBlock(80, 120);
        System.out.println(count);


    }
}
