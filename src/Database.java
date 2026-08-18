import java.sql.*;
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

}
