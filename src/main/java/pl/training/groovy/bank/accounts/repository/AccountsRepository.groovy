package pl.training.groovy.bank.accounts.repository

import pl.training.groovy.bank.accounts.Account

interface AccountsRepository {

    Account save(Account account)

    void update(Account account)

    Account getByNumber(String number)

}