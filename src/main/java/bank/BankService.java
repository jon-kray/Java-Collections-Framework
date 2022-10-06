package bank;

import java.util.*;

public class BankService {

    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет пользователя, изначательно у пользователя 0 счетов
     */
    public boolean addUser(User user) {
       return users.putIfAbsent(user, new ArrayList<>()) == null;
    }

    /**
     * Метод добавляет счет пользователю
     */
    public boolean addAccount(String passport, Account accountAdded) {
        Optional<Account> account = findByRequisite(passport, accountAdded.getRequisite());
        if (account.isEmpty()) {
            Optional<User> user = findByPassport(passport);
            if (user.isPresent()) {
                List<Account> accounts = users.get(user.get());
                accounts.add(accountAdded);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод добавляет деньги на счет пользователя
     */
    public boolean addBalanceToAccount(String passport, String requisite, double money) {
        Optional<Account> account = findByRequisite(passport, requisite);
        if (account.isPresent()) {
            account.get().upBalance(money);
            return true;
        }
        return false;
    }

    /**
     * Метод должен снять деньги с аккаунта пользователя
     */
    public boolean withDrawBalanceFromAccount(String passport, String requisite, double money) {
        Optional<Account> account = findByRequisite(passport, requisite);
        if (account.isPresent()) {
            if (account.get().getBalance() >= money) {
                account.get().withDrawMoney(money);
                return true;
            }
        }
        return false;
    }

    /**
     * Перевести деньги с счета на счет
     */
    public boolean transferMoney(String passportFrom, String requisiteFrom,
                                 String passportTo, String requisiteTo, double money) {

        Optional<Account> accountFrom = findByRequisite(passportFrom, requisiteFrom);
        Optional<Account> accountTo = findByRequisite(passportTo, requisiteTo);
        if (accountFrom.isEmpty() || accountTo.isEmpty()) {
            return false;
        }
        if (accountFrom.get().getBalance() >= money) {
            accountTo.get().upBalance(money);
            accountFrom.get().withDrawMoney(money);
            return true;
        }
        return false;
    }



    /**
     * Метод удаляет счет пользователя, если на счету лежат деньги, то их нужно перевести на рандомный
     * любой другой счет пользователя, если же такого счета не существует, то отменить операцию.
     * @param passport паспорт пользователя
     * @param requisite реквизиты пользователя
     */
    public boolean deleteAccountByRequisite(String passport, String requisite) {
        Optional<Account> account = findByRequisite(passport, requisite);
        if (account.isPresent()) {
            User user = findByPassport(passport).get();
            List<Account> accounts = users.get(user);
            accounts.remove(account.get());
            int randomAccountIndex = randomAccount(accounts.size());
            accounts.get(randomAccountIndex).upBalance(account.get().getBalance());
            return true;
        }
        return false;
    }

    /**
     * Метод ищет счет по его реквизитам
     * @param passport паспорт
     * @param requisite реквизиты
     * @return счет пользователя
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        return user.flatMap(value -> users.get(value)
                .stream()
                .filter(account -> account.getRequisite().equals(requisite))
                .findFirst());
    }

    /**
     * Возвращает пользователя по его паспорту
     * @param passport паспорт пользователя
     * @return пользователь
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    private int randomAccount(int accountsSize) {
        return (int) (Math.random() * (accountsSize));
    }
}
