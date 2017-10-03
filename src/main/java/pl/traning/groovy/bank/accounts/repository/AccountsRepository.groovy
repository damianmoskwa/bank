package pl.traning.groovy.bank.accounts.repository

import pl.traning.groovy.bank.accounts.Account

interface AccountsRepository {

    Account save(Account account)

    void update(Account account)

    Account getByNumber(String number)

}