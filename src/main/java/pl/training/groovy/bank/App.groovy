package pl.training.groovy.bank

import pl.training.groovy.bank.accounts.Account
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.AccountsService
import pl.training.groovy.bank.accounts.ConsoleLogger
import pl.training.groovy.bank.accounts.generators.AccountNumberGenerator
import pl.training.groovy.bank.accounts.generators.FakeAccountNumberGenerator
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import pl.training.groovy.bank.accounts.repository.HashMapAccountsRepository

import java.text.NumberFormat
import java.util.logging.Level
import java.util.logging.Logger

class App {

    private static def createFormatter = {
        NumberFormat formatter = NumberFormat.getCurrencyInstance()
        formatter.format(it)
    }

    private createAccounts() {
        AccountsRepository accountsRepository = new HashMapAccountsRepository()
        AccountNumberGenerator accountNumberGenerator = new FakeAccountNumberGenerator()
        AccountsService accountsService = new AccountsService(accountsRepository: accountsRepository, accountNumberGenerator: accountNumberGenerator)
        accountsService.addObserver{
                println "Deposit Limit on ${it.number}"
        }
        new ConsoleLogger(accounts: accountsService, currencyFormater: createFormatter)
    }

    static void main(String[] args) {
        App app = new App()
        Logger.getLogger(ConsoleLogger.class.name).setLevel(Level.INFO)
        Accounts accounts = app.createAccounts()

        Account account = accounts.createAccount()
        accounts.deposit(account.number, 100_000_000)
        accounts.withdraw(account.number, 1000_001)
        println account


    }
}