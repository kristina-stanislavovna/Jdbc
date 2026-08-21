import java.time.LocalDate;

public class User {
    private int id;
    private String login;
    private String password;
    private int balance;
    private LocalDate dateIssue;
    private boolean isBlock;

    public User(int id, String login, String password, int balance, LocalDate dateIssue, boolean isBlock) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.dateIssue = dateIssue;
        this.isBlock = isBlock;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public LocalDate getDateIssue() {
        return dateIssue;
    }

    public boolean isBlock() {
        return isBlock;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", dateIssue=" + dateIssue +
                ", isBlock=" + isBlock +
                '}' + '\n';
    }

}
