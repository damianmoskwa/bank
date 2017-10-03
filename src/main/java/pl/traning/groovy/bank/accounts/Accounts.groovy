package pl.traning.groovy.bank.accounts

interface Accounts {

    Account createAccount ()

    void deposit (String accountNumber, Long funds)

    void withdraw (String accountNumber, Long funds)

}