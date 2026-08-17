import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database database = new Database("root",
                "331331mama", "jdbc:mysql://localhost:3306/shop");
//        database.findAllLaptops();
      List<Employee> emps = database.findByName("Anna");
        System.out.println(emps);

    }
}
