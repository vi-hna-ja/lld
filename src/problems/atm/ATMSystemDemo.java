package problems.atm;

import problems.atm.models.BankAccount;
import problems.atm.models.Card;
import problems.atm.models.User;

/*
Requirements
    The ATM system should support basic operations such as balance inquiry, cash withdrawal, and cash deposit.
    Users should be able to authenticate themselves using a card and a PIN (Personal Identification Number).
    The system should interact with a bank's backend system to validate user accounts and perform transactions.
    The ATM should have a cash dispenser to dispense cash to users.
    The system should handle concurrent access and ensure data consistency.
    The ATM should have a user-friendly interface for users to interact with.
 */
public class ATMSystemDemo {

    public void run() {
        ATMSystem demo = ATMSystem.getAtmSystemInstance(1000);
        User user = demo.createUser("janu");
        BankAccount account = demo.createBankAccount(user.getUserId());
        Card card = demo.addCard("card number", account.getBankAccountNumber());
        demo.checkBalance(user, card);
        demo.depositCash(user, account);
        demo.withdrawCash(user, card);
    }
}
