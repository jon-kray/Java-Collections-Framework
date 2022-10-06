package bank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    private final BankService bankService = new BankService();

    @Test
    public void addUser() {
        boolean resultAdded = bankService.addUser(new User("Evgeniy", "12345"));
        boolean resultAddedDuplicate = bankService.addUser(new User("Evgeniy", "12345"));
        Assertions.assertTrue(resultAdded);
        Assertions.assertFalse(resultAddedDuplicate);

    }

    @Test
    public void addAccount() {
        boolean resultAddUser = bankService.addUser(new User("Evgeniy", "12345"));
        boolean resultAddAccountFirst = bankService.addAccount("12345", new Account("15", 15));
        boolean resultAddAccountSecond = bankService.addAccount("12345", new Account("16", 25));
        boolean resultAddAccountDuplicate = bankService.addAccount("12345", new Account("15", 10));

        Assertions.assertTrue(resultAddUser);
        Assertions.assertTrue(resultAddAccountFirst);
        Assertions.assertTrue(resultAddAccountSecond);
        Assertions.assertFalse(resultAddAccountDuplicate);
        Assertions.assertEquals(15, bankService.findByRequisite("12345", "15").get().getBalance());
    }

    @Test
    public void addBalanceToAccount() {
        boolean resultAddUser = bankService.addUser(new User("Evgeniy", "12345"));
        boolean resultAddAccountFirst = bankService.addAccount("12345", new Account("15", 15));
        boolean addBalanceSuccess = bankService.addBalanceToAccount("12345", "15", 25);
        boolean addBalanceSuccessSecond = bankService.addBalanceToAccount("12345", "15", 30);
        boolean addBalanceInIncorrectAccount = bankService.addBalanceToAccount("12345", "17", 35);

        Assertions.assertTrue(resultAddUser);
        Assertions.assertTrue(resultAddAccountFirst);
        Assertions.assertTrue(addBalanceSuccess);
        Assertions.assertTrue(addBalanceSuccessSecond);
        Assertions.assertFalse(addBalanceInIncorrectAccount);
        Assertions.assertEquals(70, bankService.findByRequisite("12345", "15").get().getBalance());
    }

    @Test
    public void withDrawBalanceFromAccount() {
        boolean resultAddUser = bankService.addUser(new User("Evgeniy", "12345"));
        boolean resultAddAccountFirst = bankService.addAccount("12345", new Account("15", 15));
        boolean addBalanceSuccess = bankService.addBalanceToAccount("12345", "15", 25);
        boolean addBalanceSuccessSecond = bankService.addBalanceToAccount("12345", "15", 30);
        boolean unsuccessResult = bankService.withDrawBalanceFromAccount("12345", "15", 75);
        boolean successResult = bankService.withDrawBalanceFromAccount("12345", "15", 69);

        Assertions.assertTrue(resultAddUser);
        Assertions.assertTrue(resultAddAccountFirst);
        Assertions.assertTrue(addBalanceSuccess);
        Assertions.assertTrue(addBalanceSuccessSecond);
        Assertions.assertFalse(unsuccessResult);
        Assertions.assertTrue(successResult);
        Assertions.assertEquals(1, bankService.findByRequisite("12345", "15").get().getBalance());
    }

    @Test
    public void transferMoney() {
        boolean resultAddUserFrom = bankService.addUser(new User("Evgeniy", "12345"));
        boolean resultAddAccountFrom = bankService.addAccount("12345", new Account("15", 150));
        boolean resultAddUserTo = bankService.addUser(new User("Petr", "12346"));
        boolean resultAddAccountTo = bankService.addAccount("12346", new Account("16", 180));
        boolean transferUnsuccessByMoney = bankService.transferMoney("12345", "15", "12346", "16", 160);
        boolean transferUnsuccessByRequisite = bankService.transferMoney("12345", "15", "12346", "17", 20);
        boolean transferUnsuccess = bankService.transferMoney("12345", "15", "12346", "16", 140);

        Assertions.assertTrue(resultAddUserFrom);
        Assertions.assertTrue(resultAddAccountFrom);
        Assertions.assertTrue(resultAddUserTo);
        Assertions.assertTrue(resultAddAccountTo);
        Assertions.assertFalse(transferUnsuccessByMoney);
        Assertions.assertFalse(transferUnsuccessByRequisite);
        Assertions.assertTrue(transferUnsuccess);

        Assertions.assertEquals(10, bankService.findByRequisite("12345", "15").get().getBalance());
        Assertions.assertEquals(320, bankService.findByRequisite("12346", "16").get().getBalance());
    }

    @Test
    public void removeAccountAndTransferMoney() {
        boolean addUserSuccess = bankService.addUser(new User("Evgeniy", "12345"));
        boolean addAccountFirst = bankService.addAccount("12345", new Account("29", 220));
        boolean addAccountSecond = bankService.addAccount("12345", new Account("13", 220));
        boolean addAccountThird = bankService.addAccount("12345", new Account("883", 120));
        boolean addAccountFourth = bankService.addAccount("12345", new Account("120", 220));

        boolean result = bankService.deleteAccountByRequisite("12345", "883");
        Assertions.assertTrue(result);
        List<Account> accounts = List.of(
                bankService.findByRequisite("12345", "29").get(),
                bankService.findByRequisite("12345", "13").get(),
                bankService.findByRequisite("12345", "120").get()
        );

        boolean resultDeleted = bankService.findByRequisite("12345", "883").isEmpty();
        Assertions.assertTrue(resultDeleted);
        List<Account> rsl = accounts.stream().filter(account -> account.getBalance() == 340).collect(Collectors.toList());
        Assertions.assertEquals(1, rsl.size());
        System.out.println(rsl.get(0).toString());
    }
}