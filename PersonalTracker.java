import java.util.*;

public class PersonalTracker{
	private double balanceGoal;
	public double balance;
	private TransList transactions;

	public PersonalTracker(){
		this.balanceGoal = 0.0;
		this.balance = 0.0;
		this.transactions = new TransList();
	}

	public void setBalanceGoal(double goal){
		this.balanceGoal = goal;
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

	public void getTransactions(){
		int tranNum = 1;
	
		System.out.println("\n--- Transaction History ---");

		if (this.transactions.size() == 0){
			System.out.println("No transactions to review yet.");
		}

		for (Transaction transaction : transactions){
			System.out.print("Transaction " + tranNum + ": ");
			transaction.printTransaction();
			tranNum += 1;
		}

		System.out.println("\n--- Budget Overview ---");
		System.out.println("Target Balance: $" + balanceGoal);
		System.out.println("Current Balance: $" + balance);

		if (balance > balanceGoal){
			 double over;
			 over = balance - balanceGoal;
			 System.out.println("You are $" + over + " over budget!\n");
		}

		if (balance == balanceGoal){
			System.out.println("You are at your budget goal! You are exactly where you wanted to be. Give yourelf a pat on the back!\n");
		}

		if (balance <  balanceGoal){
			double under;
			under = balanceGoal - balance;
			System.out.println("You are $" + under + " under budget!\n");
		}
	}
}
class TransList extends ArrayList<Transaction>{}
