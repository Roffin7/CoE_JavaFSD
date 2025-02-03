package Task1;

class Account 
{
    private double balance;
    public Account(double initial_Balance) 
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
        if (amount > 0 && amount <= balance)
        {
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
class User extends Thread 
{
    private Account account;
    private boolean deposit;
    private double amount;
    public User(Account account, boolean deposit, double amount)
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
        } 
        else
        {
            account.withdraw(amount);
        }
    }
}
 class BankAccount
 {
    public static void main(String[] args)
    {
        Account account = new Account(1000);
        Thread user1 = new User(account, true, 500);
        Thread user2 = new User(account, false, 200);
        Thread user3 = new User(account, false, 1500);
        Thread user4 = new User(account, true, 100);
        user1.start();
        user2.start();
        user3.start();
        user4.start();
    }
}