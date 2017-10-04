package pl.training.groovy.bank

import pl.training.groovy.bank.accounts.Accounts
import pl.training.groovy.bank.accounts.model.Operation

class FileDataImporter {

    private static final String SEPARATOR =";"

    private Accounts accounts
    void importData(String path){
        new File(path).eachLine {
            def (accountNumber, funds, operationType) = it.split(SEPARATOR)
            switch(Operation.valueOf(operationType)) {
                case Operation.DEPOSIT: accounts.deposit(accountNumber, funds as Long)
                    break
                case Operation.WITHDRAW: accounts.withdraw(accountNumber, funds as Long)
                    break
                default:
                    throw new IllegalArgumentException()
            }
        }

    }
}
