package pl.training.groovy.bank.accounts.service

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.*
import pl.training.groovy.bank.accounts.generators.AccountNumberGenerator
import pl.training.groovy.bank.accounts.model.Account
import pl.training.groovy.bank.accounts.repository.AccountsRepository

import static org.junit.Assert.assertEquals
import static org.mockito.AdditionalAnswers.returnsFirstArg
import static org.mockito.Mockito.*


class AccountsServiceTest {

    private static final String ACCOUNT_NUMBER = '00000000000000000000000001'
    private AccountNumberGenerator numberGenerator
    private AccountsRepository accountsRepository
    private AccountsService accountsService
    private Account expectedAccount = new Account(number:ACCOUNT_NUMBER, balance: 0 )

    @Before
    void init() {
        numberGenerator = mock(AccountNumberGenerator.class)
        when(numberGenerator.next).thenReturn(ACCOUNT_NUMBER)
        accountsRepository = mock(AccountsRepository.class)
        when(accountsRepository.save(any(Account.class))).then(returnsFirstArg())
        accountsService = new AccountsService(accountNumberGenerator: numberGenerator, accountsRepository: accountsRepository)
    }

    @Test
    void shouldSaveCreatedAccount(){
        accountsService.createAccount()
        verify(accountsRepository).save(expectedAccount)
    }

    @Test
    void shouldProperlyInitializeCreatedAccount(){
        assertEquals(expectedAccount, accountsService.createAccount())
    }

}
