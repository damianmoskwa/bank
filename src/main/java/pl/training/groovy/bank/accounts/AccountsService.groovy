package pl.training.groovy.bank.accounts

import pl.training.groovy.bank.accounts.generators.AccountNumberGenerator
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import pl.training.groovy.bank.util.Subject


class AccountsService implements Accounts, Subject<Account> {

    private static final Long DEPOSIT_LIMIT = 10000

    AccountsRepository accountsRepository
    AccountNumberGenerator accountNumberGenerator

    private Closure currencyFormatter

    Account createAccount() {
        Account account = new Account(number: accountNumberGenerator.next)
        accountsRepository.save(account)
    }

    void deposit(String accountNumber, Long funds) {
        process (accountNumber) { account ->
            account.deposit(funds)
            checkDeposit(account, funds)
        }
    }

    private void checkDeposit (Account account, Long deposit){
        if(deposit >= DEPOSIT_LIMIT){
           notifyObserver(account)
            }
        }

    void withdraw(String accountNumber, Long funds) {

        process(accountNumber) { account ->
            account.hasFunds(funds)
            account.withdraw(funds)
        }
    }

    private void process (String accountNumber, Closure<Void> operation) {
        Account account = accountsRepository.getByNumber(accountNumber)
        operation(account)
        accountsRepository.update(account)
    }
}
