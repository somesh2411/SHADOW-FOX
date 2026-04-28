// BankAccountTest.java

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    void testDeposit() {
        BankAccount acc = new BankAccount("Somesh", 1000);
        acc.deposit(500);
        assertEquals(1500, acc.getBalance());
    }

    @Test
    void testWithdraw() {
        BankAccount acc = new BankAccount("Somesh", 1000);
        acc.withdraw(300);
        assertEquals(700, acc.getBalance());
    }

    @Test
    void testOverWithdraw() {
        BankAccount acc = new BankAccount("Somesh", 1000);

        Exception ex = assertThrows(
                IllegalArgumentException.class,
                () -> acc.withdraw(2000));

        assertEquals("Insufficient balance", ex.getMessage());
    }

    @Test
    void testNegativeDeposit() {
        BankAccount acc = new BankAccount("Somesh", 1000);

        assertThrows(
                IllegalArgumentException.class,
                () -> acc.deposit(-50));
    }

    @Test
    void testBalanceInquiry() {
        BankAccount acc = new BankAccount("Somesh", 1000);
        assertEquals(1000, acc.getBalance());
    }

    @Test
    void testTransactionHistory() {
        BankAccount acc = new BankAccount("Somesh", 1000);

        acc.deposit(500);
        acc.withdraw(200);

        assertEquals(3, acc.getHistory().size());
    }
}