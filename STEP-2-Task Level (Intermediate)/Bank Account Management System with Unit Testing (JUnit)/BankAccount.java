import java.util.ArrayList;

public class BankAccount {

    private String name;
    private double balance;
    private ArrayList<String> history = new ArrayList<>();

    public BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
        history.add("Account created with balance: " + balance);
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public void deposit(double amount) {

        if (amount <= 0)
            throw new IllegalArgumentException("Invalid deposit");

        balance += amount;
        history.add("Deposited: " + amount);
    }

    public void withdraw(double amount) {

        if (amount <= 0)
            throw new IllegalArgumentException("Invalid withdraw");

        if (amount > balance)
            throw new IllegalArgumentException("Insufficient balance");

        balance -= amount;
        history.add("Withdrawn: " + amount);
    }
}