package bank;

import java.util.Objects;

public class Account {

    private String requisite;

    private double balance;

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    public String getRequisite() {
        return requisite;
    }

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void upBalance(double money) {
        this.balance += money;
    }
    public void withDrawMoney(double money) {
        this.balance -= money;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "requisite='" + requisite + '\'' +
                ", balance=" + balance +
                '}';
    }
}