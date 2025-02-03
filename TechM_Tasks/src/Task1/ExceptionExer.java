package Task1;

import java.util.Scanner;
class Account1
{
    private double balance;
    public Account1(double initial_Balance)
    {
        this.balance = initial_Balance;
    }
    public synchronized void deposit(double amount)
    {
        if (amount > 0)
        {
            balance = balance + amount;
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + ", New Balance: " + balance);
        }
    }
    public synchronized void withdraw(double amount) 
    {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", New Balance: " + balance);
        }
        else
        {
            System.out.println(Thread.currentThread().getName() + " attempted to withdraw " + amount + " but insufficient balance.");
        }
    }
    public double get_Balance()
    {
        return balance;
    }
}
class User1 extends Thread
{
    private Account1 account;
    private boolean deposit;
    private double amount;
    public User1(Account1 account, boolean deposit, double amount)
    {
        this.account = account;
        this.deposit = deposit;
        this.amount = amount;
    }
    @Override
    public void run()
    {
        if (deposit)
        {
            account.deposit(amount);
        } else
        {
            account.withdraw(amount);
        }
    }
}
class ExceptionExer
{
    public static void main(String[] args)
    {
        Account1 account = new Account1(1000);
        Thread user1 = new User1(account, true, 500);
        Thread user2 = new User1(account, false, 200);
        Thread user3 = new User1(account, false, 1500);
        Thread user4 = new User1(account, true, 100);
        user1.start();
        user2.start();
        user3.start();
        user4.start();
        process_In();
    }
    public static void process_In()
    {
        Scanner scanner = new Scanner(System.in);
        try
        {
            System.out.print("Enter a number: ");
            double number = scanner.nextDouble();
            double reciprocal = 1 / number;
            System.out.println("Reciprocal: " + reciprocal);
        } 
        catch (ArithmeticException e)
        {
            System.out.println("Error: Cannot divide by zero.");
        }
        catch (Exception e)
        {
            System.out.println("Invalid input. Please enter a valid number.");
        }
        finally
        {
            scanner.close();
        }
    }
}