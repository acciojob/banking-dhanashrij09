package com.driver;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {

    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
       if( sum > 9 * digits){
          throw new Exception( "Account Number can not be generated");

       }
        String accountNumber = "";
        // ex sum = 11 & digits = 5
       //adding max possible 9s (sum/9  i.e. 11/9 = 1)
       while(sum > 9){
           accountNumber += 9;   // therefore 1 9 is added to the a/c no
           sum -= 9;
       }
       //adding remainder i.e 11/9 = 2
       accountNumber += (sum + "");
       //filling extra digits if remaining with 0
       while(accountNumber.length() < digits){
         accountNumber += '0';  // a/c no = 92000
       }
        return accountNumber;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance

        if (balance - amount >= minBalance) {
            balance -= amount;
        } else
            throw new Exception("Insufficient Balance");
    }
}