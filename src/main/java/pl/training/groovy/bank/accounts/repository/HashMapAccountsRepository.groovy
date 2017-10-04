package pl.training.groovy.bank.accounts.repository

import pl.training.groovy.bank.accounts.model.Account

import java.util.concurrent.atomic.AtomicLong

class HashMapAccountsRepository implements AccountsRepository{

    private Map<String, Account> accounts = [:]
    private AtomicLong counter = new AtomicLong()

    Account save(Account account) {
        account.id = counter.incrementAndGet()
        accounts[account.number] = account
    }

    void update(Account account) {
        String accountNumber = account.number
        checkAccountExistence(accountNumber)
        accounts[accountNumber] = account
    }

    Account getByNumber(String number) {
        checkAccountExistence(number)
        accounts[number]
    }

    private void checkAccountExistence (String accountNumber) {
        if(!(accountNumber in accounts)) {
            throw new AccountNotFoundException()
        }
    }

}
