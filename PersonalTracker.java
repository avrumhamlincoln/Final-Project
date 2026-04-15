import java.util.*

public class PersonalTracker{
	private double balanceGoal;
	private double balance;
	private TransList transactions;

	public PersonalTracker(){
		this.balanceGoal = 0.0;
		this.balance = 0.0;
		this.transactions = new TransList();
	}

	public void setBalanceGoal(double goal){
		this.balanceGoal = goal;
	}

	public void increaseBalanceGoal(double ammount){
		this.balanceGoal = this.balanceGoal + amount;
	}

	public void decreaseBalanceGoal(double amount){
		this.balanceGoal = this.balanceGoal - amount;
	}

	public void getTransactions(){
		System.out.println("\n--- Transaction History ---");

		if (this.transactions.size() == 0){
			System.out.println("No transactions to review yet.");
		}

		
	}

class TransList extends ArrayList<Transaction>{}
