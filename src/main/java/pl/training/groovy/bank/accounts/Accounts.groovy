package pl.training.groovy.bank.accounts

import pl.training.groovy.bank.accounts.model.Account

interface Accounts {

    Account createAccount ()

    void deposit (String accountNumber, Long funds)

    void withdraw (String accountNumber, Long funds)

}