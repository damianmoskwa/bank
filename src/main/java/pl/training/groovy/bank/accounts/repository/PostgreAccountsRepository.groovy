package pl.training.groovy.bank.accounts.repository

import groovy.sql.Sql
import pl.training.groovy.bank.accounts.model.Account

import javax.sql.DataSource

class PostgreAccountsRepository implements  AccountsRepository{

    private static final String SELECT_ACCOUNT_BY_NUMBER ="select * from accounts where number = :number"
    private static final String UPDATE_ACCOUNT_WITH_ID ="update accounts set balance = :balance where id =:id"
    private static final String INSERT_ACCOUNT = "insert into accounts (number, balance) values (:number, :balance)"
    private Sql sql

    PostgreAccountsRepository(DataSource dataSource){
        sql = new Sql(dataSource)
    }
    Account save(Account account) {
        def keys = sql.executeInsert(INSERT_ACCOUNT, [number: account.number, balance: account.balance])
        account.id = keys[0][0] as Long
        return account
    }

    void update(Account account) {
        Integer updatedRecords =sql.executeUpdate(UPDATE_ACCOUNT_WITH_ID, [id:account.id, balance: account.balance])
        if (!updatedRecords){
            throw new AccountNotFoundException()
        }
    }

    Account getByNumber(String number) {
        Account account
        sql.eachRow(SELECT_ACCOUNT_BY_NUMBER, ['number': number]) {
            account = new Account(id: it.id, number: it.number, balance: it.balance)
        }
        if (!account) {
            throw new AccountNotFoundException()
        }
        return account
    }
}
