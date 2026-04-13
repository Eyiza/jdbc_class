package lytebank.service;

import lytebank.db.DatabaseConnectionManager;
import lytebank.exceptions.AccountNotFoundException;
import lytebank.model.Account;
import lytebank.repository.AccountRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class MoneyTransferService {
    private final AccountRepository accountRepository;
    private final DatabaseConnectionManager databaseConnectionManager;

    public MoneyTransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.databaseConnectionManager = DatabaseConnectionManager.getInstance();
    }

    public void transfer(Integer senderId, Integer receiverId, BigDecimal amount) {
        Connection connection = databaseConnectionManager.getDatabaseConnection();
        try {
            Account senderAccount = accountRepository.findById(connection, senderId)
                    .orElseThrow(() -> new AccountNotFoundException("Sender Account not found"));
            BigDecimal senderBalance = senderAccount.getBalance();
            senderAccount.setBalance(senderBalance.subtract(amount));

            Account recipientAccount = accountRepository.findById(connection, receiverId)
                    .orElseThrow(() -> new AccountNotFoundException("Recipient Account not found"));
            BigDecimal recipientBalance = recipientAccount.getBalance();

            accountRepository.updateAccount(connection, senderId, senderAccount.getBalance());

            recipientAccount.setBalance(recipientBalance.add(amount));
            accountRepository.updateAccount(connection, receiverId, recipientAccount.getBalance());

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
