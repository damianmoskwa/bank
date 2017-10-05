package pl.training.groovy.bank.accounts.model

import org.junit.Test
import pl.training.groovy.bank.accounts.service.InsuficentFundsException

import static org.junit.Assert.assertEquals

class AccountTest {

    private static final Long FUNDS = 1000

    private Account account = new Account(balance: 0)

    @Test
    void shouldIncreaseBalanceAfterDeposit() {
        account.deposit(FUNDS)
        assertEquals(FUNDS, account.balance)

    }
    @Test
    void shouldDecreaseBalanceAfterWithdraw(){
        account.withdraw(FUNDS)
        assertEquals(-FUNDS, account.balance)
    }

    @Test(expected = InsuficentFundsException.class)
    void shoudThrowExceptionThereIsNoFunds(){
        account.hasFunds(account.balance+FUNDS)

    }

}
