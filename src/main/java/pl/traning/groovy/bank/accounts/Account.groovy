package pl.traning.groovy.bank.accounts

import groovy.transform.Canonical

@Canonical
class Account {

    Long id
    String number =''
    Long balance = 0

    void deposit (Long funds) {
        balance += funds
    }

    void withdraw (Long funds) {
        balance -= funds
    }
    void hasFunds(Long funds) {
        if((balance < funds)) {
            throw new InsuficentFundsException()
        }
    }

}
