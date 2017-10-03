package pl.traning.groovy.bank.accounts

import pl.traning.groovy.bank.accounts.generators.AccountNumberGenerator
import pl.traning.groovy.bank.accounts.repository.AccountsRepository

import java.text.NumberFormat

class AccountsService implements Accounts {

    AccountsRepository accountsRepository
    AccountNumberGenerator accountNumberGenerator

    private Closure currencyFormater

    Account createAccount() {
        Account account = new Account(number: accountNumberGenerator.next)
        accountsRepository.save(account)
    }

    void deposit(String accountNumber, Long funds) {
        process (accountNumber) { account ->
            account.deposit(funds)
            println "${accountNumber} <== ${currencyFormater(funds)}"
        }
    }

    void withdraw(String accountNumber, Long funds) {

        process (accountNumber) { account ->
            account.hasFunds(funds)
            account.withdraw(funds)
            println "${accountNumber} ==> ${currencyFormater(funds)}"
        }
    }

    private void process (String accountNumber, Closure<Void> operation) {
        Account account = accountsRepository.getByNumber(accountNumber)
        operation(account)
        accountsRepository.update(account)
    }
}
