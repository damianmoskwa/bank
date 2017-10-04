package pl.training.groovy.bank

import com.zaxxer.hikari.HikariDataSource
import org.postgresql.Driver as PostgreDriver
import pl.training.groovy.bank.accounts.model.Account
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.service.AccountsService
import pl.training.groovy.bank.accounts.logger.ConsoleLogger
import pl.training.groovy.bank.accounts.generators.AccountNumberGenerator
import pl.training.groovy.bank.accounts.generators.PostgreAccountNumberGenerator
import pl.training.groovy.bank.accounts.repository.AccountsRepository
import pl.training.groovy.bank.accounts.repository.PostgreAccountsRepository
import pl.training.groovy.bank.accounts.logger.PostgreeTransactionHistoryLogger

import javax.sql.DataSource
import java.text.NumberFormat
import java.util.logging.Level
import java.util.logging.Logger

class App {

    private static def createFormatter = {
        NumberFormat formatter = NumberFormat.getCurrencyInstance()
        formatter.format(it)
    }

    private createAccounts() {
        DataSource dataSource = new HikariDataSource()
        dataSource.jdbcUrl = "jdbc:postgresql://localhost:5432/bank"
        dataSource.username = "postgres"
        dataSource.password = "admin"
        dataSource.driverClassName = PostgreDriver.class.name
       /* AccountsRepository accountsRepository = new HashMapAccountsRepository()
        AccountNumberGenerator accountNumberGenerator = new FakeAccountNumberGenerator()*/
        AccountsRepository accountsRepository = new PostgreAccountsRepository(dataSource)
        AccountNumberGenerator accountNumberGenerator = new PostgreAccountNumberGenerator(dataSource)
        AccountsService accountsService = new AccountsService(accountsRepository: accountsRepository, accountNumberGenerator: accountNumberGenerator)
        //AccountsService accountsService = new PostgreeTransactionHistoryLogger(accountsRepository: accountsRepository, accountNumberGenerator: accountNumberGenerator)
        accountsService.addObserver{
                println "Deposit Limit on ${it.number}"
        }
        //new ConsoleLogger(accounts: accountsService, currencyFormater: createFormatter)
        new PostgreeTransactionHistoryLogger(accountsService, dataSource)
    }

    static void main(String[] args) {
        App app = new App()
        Logger.getLogger(ConsoleLogger.class.name).setLevel(Level.INFO)
        Accounts accounts = app.createAccounts()

        Account account = accounts.createAccount()
        accounts.deposit(account.number, 100_000_000)
        accounts.withdraw(account.number, 1000_001)
        FileDataImporter dataImporter = new FileDataImporter(accounts: accounts)
        dataImporter.importData("data.txt")
        println account


    }
}