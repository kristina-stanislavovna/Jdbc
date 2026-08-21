import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private final String login;
    private final String passwrod;
    private final String url;

    public Database(String login, String passwrod, String url) {
        this.login = login;
        this.passwrod = passwrod;
        this.url = url;
    }

    void findAllLaptops() throws SQLException {
        String sql = "SELECT * from laptop"; // просто скпипт
        Connection connection = DriverManager.getConnection(url, login, passwrod); // Оператор соедениения с базой данных
        // три аргумента
        Statement statement = connection.createStatement(); // создание заявление
        ResultSet resultSet = statement.executeQuery(sql); // передача заявлению SQL запрос
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");
            System.out.println(name + " - " + price + "$");
        }
    }

    List<Employee> findByName(String nameEmp) throws SQLException {
        // SELECT * FROM employee where name='Anna';
        String sql = "SELECT * from employee where name = \'" + nameEmp + "\'";
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            Employee employee = new Employee(id, name, salary);
            employees.add(employee);
        }
        return employees;
    }

    List<Account> allAccounts() throws SQLException {
        String sql = "SELECT * FROM account";
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Account> accounts = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String login = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");
            int balance = resultSet.getInt("BALANCE");
            LocalDate dateIssued = resultSet.getDate("DATEISSUED").toLocalDate();
            Account account = new Account(id, login, password, balance, dateIssued);
            accounts.add(account);
        }
        return accounts;
    }

    List<Account> diffBalance(int min, int max) throws SQLException {
        String sql = "SELECT * FROM account where balance >= " + min + " and balance <= " + max;
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Account> accounts = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String login = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");
            int balance = resultSet.getInt("BALANCE");
            LocalDate dateIssued = resultSet.getDate("DATEISSUED").toLocalDate();
            Account account = new Account(id, login, password, balance, dateIssued);
            accounts.add(account);
        }
        return accounts;
    }

    int allAccountsSum() throws SQLException {
        String sql = "SELECT * FROM ACCOUNT";
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int sum = 0;
        while (resultSet.next()) {
            sum += resultSet.getInt("BALANCE");
        }
        return sum;
    }

    List<Account> findByChar(char letter) throws SQLException {
        String sql = "SELECT * FROM ACCOUNT";
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Account> x = new ArrayList<>();
        while (resultSet.next()) {
            String login = resultSet.getString("LOGIN");
            if (login.substring(0, 1)
                    .toUpperCase().equals(String.valueOf(letter).toUpperCase())
                    & login.endsWith(letter + "")) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                int balance = resultSet.getInt("balance");
                LocalDate date = resultSet.getDate("dateIssued").toLocalDate();
                Account account = new Account(id, login, password, balance, date);
                x.add(account);
            }
        }
        return x;
    }

    List<User> findAllUsers() throws SQLException {
        String sql = "SELECT * FROM USERS";
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<User> x = new ArrayList<>();
        while (resultSet.next()) {
            x.add(new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getInt("balance"),
                    resultSet.getDate("dateIssue").toLocalDate(),
                    resultSet.getBoolean("isBlock")));
        }
        return x;
    }

    User findMaxUser() throws SQLException, IOException {
        String sql = "SELECT * FROM USERS";
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int max = 0;
        User maxUser = null;
        while (resultSet.next()) {
            int balance = resultSet.getInt("balance");
            if (max < balance) {
                max = balance;
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                LocalDate dateIssue = resultSet.getDate("dateIssue").toLocalDate();
                boolean isBlock = resultSet.getBoolean("isBlock");
                maxUser = new User(id, login, password, max, dateIssue, isBlock);
            }
        }
        if (maxUser == null) {
            throw new IllegalArgumentException();
        }
        FileWriter fileWriter = new FileWriter(new File(maxUser.getLogin()));
        fileWriter.write("MAX balance user: " + maxUser.getLogin() + " " + maxUser.getBalance() + " $ ");
        fileWriter.flush();
        fileWriter.close();
        return maxUser;
    }

    int sumIsBlockUsers() throws SQLException {
        String sql = "SELECT BALANCE FROM USERS WHERE ISBLOCK = TRUE";
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int sum = 0;
        while (resultSet.next()) {
            sum += resultSet.getInt("balance");

        }
        return sum;
    }

    int countIsBlockUsersBydiff(int min, int max) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE ISBLOCK = TRUE";
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int count = 0;
        while (resultSet.next()) {
            int balance = resultSet.getInt("balance");
            if (balance >= min & balance <= max) {
                count++;
            }
        }
        return count;
    }

    int countIsBlock(int min, int max) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE ISBLOCK = TRUE AND BALANCE >= " + min + " AND BALANCE <= " + max;
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        return count;
    }

    int countByLogin(String letter) throws SQLException {
        String sql = "SELECT * FROM USERS";
        Connection connection = DriverManager.getConnection(url, login, passwrod);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int count = 0;
        while (resultSet.next()) {
            String login = resultSet.getString("login");
            if (login.length() % 2 == 0 & login.endsWith(letter)) {
                count++;
            }
        }
        return count;
    }
}
