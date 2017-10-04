package pl.training.groovy.bank.accounts.logger

import groovy.transform.TupleConstructor
import pl.training.groovy.bank.BankException
import pl.training.groovy.bank.accounts.model.Account
import pl.training.groovy.bank.accounts.Accounts

import java.util.logging.Level
import java.util.logging.Logger

@TupleConstructor
class ConsoleLogger implements Accounts{

    private Accounts accounts
    private Logger logger = Logger.getLogger(getClass().name)
    private Closure<String> currencyFormater

    Account createAccount() {
       Account account = accounts.createAccount()
        logger.log(Level.INFO, "New account created ${account.number}")
        account
    }

    void deposit(String accountNumber, Long funds) {
        process(accountNumber) { ->
            accounts.deposit(accountNumber, funds)
            logger.log(Level.INFO, "${accountNumber} <== ${currencyFormater(funds)}")
        }
    }

    void withdraw(String accountNumber, Long funds) {
        process(accountNumber) {
            accounts.withdraw(accountNumber, funds)
            logger.log(Level.INFO, "${accountNumber} ==> ${currencyFormater(funds)}")
        }
    }



    private void process(String accountNumber, Closure<Void> operation) {
        try {
            operation()
            logger.log(Level.INFO, "Status: Success")
        } catch (BankException ex) {
            logger.log(Level.INFO,"Status: Failure ${ex.class.simpleName}")
        }

    }
}
