package lytebank.service;

import lytebank.db.DatabaseConnectionManager;
import lytebank.exceptions.AccountNotFoundException;
import lytebank.model.Account;
import lytebank.repository.AccountRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTransferServiceTest {
    @Test
    void canTransferMoneyTest(){
        Integer senderId = 1;
        Integer receiverId = 2;
        BigDecimal amount = new BigDecimal("500");

        AccountRepository accountRepository = new AccountRepository();
        MoneyTransferService transferService = new MoneyTransferService(accountRepository);
        Connection connection = DatabaseConnectionManager.getInstance().getDatabaseConnection();

        Account senderAccountBefore = accountRepository.findById(connection, senderId)
                .orElseThrow(() -> new AccountNotFoundException("Sender Account not found"));
        Account receiverAccountBefore = accountRepository.findById(connection, receiverId)
                .orElseThrow(() -> new AccountNotFoundException("Recipient Account not found"));

        transferService.transfer(senderId, receiverId, amount);

        Account senderAccountAfter = accountRepository.findById(connection, senderId)
                .orElseThrow(() -> new AccountNotFoundException("Sender Account not found"));
        Account receiverAccountAfter = accountRepository.findById(connection, receiverId)
                .orElseThrow(() -> new AccountNotFoundException("Recipient Account not found"));

        assertEquals(senderAccountBefore.getBalance().subtract(amount), senderAccountAfter.getBalance());
        assertEquals(receiverAccountBefore.getBalance().add(amount), receiverAccountAfter.getBalance());

    }
}
