import java.util.*;
import java.io.*;

public class PersonalTracker implements Serializable{
	private double balanceGoal;
	public double balance;
	private TransList transactions;
	private String formattedBalanceGoal;
	private String formattedBalance;

	public PersonalTracker(double goal){
		if (goal > 0){
                        this.balanceGoal = goal;
                }

                else {
                        System.out.println("\nYou must have a positive goal. I am setting it to 0. You can change it later!");
                        this.balanceGoal = 0;
                }

		this.balance = 0.00;
		this.transactions = new TransList();
	}

	public void setBalanceGoal(double goal){
		if (goal > 0){
			this.balanceGoal = goal;
		}

		else {
			System.out.println("\nYou must have a positive goal. I am setting it to 0. You can change it later!");
			this.balanceGoal = 0;
		}
	}

	public double getBalanceGoal(){
		return this.balanceGoal;
	}

	public void increaseBalance(double amount){
		this.balance = this.balance + amount;
	}

	public void decreaseBalance(double amount){
		this.balance = this.balance - amount;
	}

	public void storeTransaction(Transaction newTrans){
		this.transactions.add(newTrans);
	}

	public double getBalance(){
		double bal = 0.00;
		for (Transaction transaction : this.transactions){
			bal += transaction.getAmount();
		}

		return bal;
	}

	public void getTransactions(){
		int tranNum = 1;
	
		System.out.println("\n--- Transaction History ---");

		if (this.transactions.size() == 0){
			System.out.println("\nNo transactions to review yet.");
		}

		for (Transaction transaction : transactions){
			System.out.print("Transaction " + tranNum + ": ");
			transaction.printTransaction();
			tranNum += 1;
		}
	}

	public void removeLastTran(){
		if (this.transactions.isEmpty()){
			System.out.println("There are no transactions to remove!");
			return;
		}

		else {
			int lastTranNum = this.transactions.size() - 1;
			Transaction lastTran = this.transactions.get(lastTranNum);
			this.transactions.remove(lastTranNum);

			System.out.println("Success! Removed: " + lastTran.getMemo() + " for $" + lastTran.getFormattedAmount());
		}
	}

	public void getTranSumary(){
		this.formattedBalanceGoal = String.format("%.2f", balanceGoal);
		this.formattedBalance = String.format("%.2f", getBalance());

		System.out.println("\n--- Budget Overview ---");
                System.out.println("Budget amount: $" + this.formattedBalanceGoal);
                System.out.println("Current Balance: $" + this.formattedBalance);
	
		if (balance < 0){
			System.out.println("\nYou are in the negative!!! Save money quick to return to the positives!");
		}

		else if (getBalance()  > balanceGoal){
                         double over;
                         over = getBalance() - balanceGoal;
			 String overf = String.format("%.2f", over);
                         System.out.println("You are $" + overf + " over your goal!\n");
                }

		else if (getBalance() == balanceGoal){
                        System.out.println("\nYou are at your budget goal! You are exactly where you wanted to be. Give yourelf a pat on the back!\n");
                }

		else if (getBalance() <  balanceGoal){
                        double under;
                        under = balanceGoal - getBalance();
			String underf = String.format("%.2f", under);
                        System.out.println("You are $" + underf + " under your goal!\n");
		}
	}
}
class TransList extends ArrayList<Transaction>{}
