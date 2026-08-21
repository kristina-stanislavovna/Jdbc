import java.time.LocalDate;

public class Account {
    private int id;
    private String login;
    private String password;
    private int balance;
    private LocalDate dateIssued;

    public Account(int id, String login, String password, int balance, LocalDate dateIssued) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.dateIssued = dateIssued;
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

    public LocalDate getDateIssued() {
        return dateIssued;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", dateIssued=" + dateIssued +
                '}' + '\n';
    }
}
