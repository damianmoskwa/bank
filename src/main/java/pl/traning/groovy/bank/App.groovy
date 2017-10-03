package pl.traning.groovy.bank

import pl.traning.groovy.bank.accounts.Account
import pl.traning.groovy.bank.accounts.Accounts
import pl.traning.groovy.bank.accounts.AccountsService
import pl.traning.groovy.bank.accounts.generators.AccountNumberGenerator
import pl.traning.groovy.bank.accounts.generators.FakeAccountNumberGenerator
import pl.traning.groovy.bank.accounts.repository.AccountsRepository
import pl.traning.groovy.bank.accounts.repository.HashMapAccountsRepository

import java.text.NumberFormat

class App {

    static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance()
        AccountsRepository accountsRepository = new HashMapAccountsRepository()
        AccountNumberGenerator accountNumberGenerator = new FakeAccountNumberGenerator()
        Accounts accounts = new AccountsService(accountsRepository: accountsRepository, accountNumberGenerator: accountNumberGenerator, currencyFormater:  {formatter.format(it)})

        Account account = accounts.createAccount()
        accounts.deposit(account.number, 100_000_000)
        accounts.withdraw(account.number, 1000_001)
        println account

    }
}