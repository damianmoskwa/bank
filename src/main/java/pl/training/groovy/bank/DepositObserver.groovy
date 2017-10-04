package pl.training.groovy.bank

interface DepositObserver {

    void onBigDeposit(String accountNumber, Long funds)

}