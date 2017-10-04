package pl.training.groovy.bank.accounts.logger

import groovy.sql.Sql
import pl.training.groovy.bank.accounts.model.Account
import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.model.Operation

import javax.sql.DataSource

class PostgreeTransactionHistoryLogger implements Accounts{

    private Sql sql
    private Accounts accounts
    private static final String INSERT_TRANSACTION ="insert into transaction_log (timestamp, type, number, funds) values (:timestamp, :type, :number, :funds)"

    PostgreeTransactionHistoryLogger(Accounts accounts, DataSource dataSource){
        this.accounts = accounts
        sql = new Sql(dataSource)
    }

    Account createAccount() {
        accounts.createAccount()
    }



    void deposit(String accountNumber, Long funds) {
        accounts.deposit(accountNumber, funds)
        insertTransactionLog(accountNumber, funds, Operation.DEPOSIT.name())

    }

    void withdraw(String accountNumber, Long funds) {
        accounts.withdraw(accountNumber, funds)
        insertTransactionLog(accountNumber, funds, Operation.WITHDRAW.name())

    }

    private void insertTransactionLog(String accountNumber, Long funds, String type){
        sql.executeInsert(INSERT_TRANSACTION, [timestamp: System.currentTimeMillis(), type: type, number:accountNumber, funds: funds])
    }
}
