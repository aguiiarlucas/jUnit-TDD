package tests.entities;

import entities.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.factory.AccountFactory;


public class AccountTest {
    @Test
    public void depositShouldIncreaseBalanceAndDiscountFeeWhenPositiveAmount() {

        double amount = 200.0;
        double expectValue = 196.0;
        Account acc = AccountFactory.createEmptyAccount();
        acc.deposit(amount);

        Assertions.assertEquals(expectValue, acc.getBalance());
    }

    @Test
    public void depositShouldDoNotWhenNegativeAmount() {
        double expectValue = 100.0;
        Account acc = AccountFactory.createAccount(expectValue);

        double amount = -200.0;

        acc.deposit(amount);

        Assertions.assertEquals(expectValue, acc.getBalance());
    }

    @Test
    public void fullWhitDrawShouldClearBalance() {
        double expectValue = 0;
        double initialBalance = 800;
        Account acc = AccountFactory.createAccount(initialBalance);
        double res =acc.fullWhitdraw();

        Assertions.assertEquals(expectValue, acc.getBalance());
        Assertions.assertEquals(res, initialBalance);
    }

    @Test
    public void withDrawShouldDecreaseBalanceWhenSufficientBalance(){
        Account acc = AccountFactory.createAccount(800);
        acc.whitdraw(400);
        Assertions.assertEquals(400,acc.getBalance());

    }

    @Test
    public void withDrawShouldThrowExceptionWhenInsufficientBalance(){

        Assertions.assertThrows(IllegalArgumentException.class,()->{
            Account acc = AccountFactory.createAccount(800);
            acc.whitdraw(900);
        });

    }
}
