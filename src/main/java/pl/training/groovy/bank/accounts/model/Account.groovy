package pl.training.groovy.bank.accounts.model

import groovy.transform.Canonical
import pl.training.groovy.bank.accounts.service.InsuficentFundsException

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
