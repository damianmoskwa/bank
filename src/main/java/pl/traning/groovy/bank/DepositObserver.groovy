package pl.traning.groovy.bank

import pl.traning.groovy.bank.accounts.Account

interface DepositObserver {

    void onBigDeposit(String accountNumber, Long funds)

}