public class Transaction{
	private String date;
	private double amount;
	private String memo;
	private String formattedAmount;

	public Transaction(String tDate, double tAmount, String tMemo) {
		this.date = tDate;
		this.amount = tAmount;
		this.memo = tMemo;
		this.formattedAmount = String.format("%.2f", tAmount);
	}

	public void incomeTrans(PersonalTracker tracker){
		tracker.increaseBalance(this.amount);
	}

	public void expenseTrans(PersonalTracker tracker){
		tracker.decreaseBalance(this.amount);
	}

	public void menu() {
		System.out.println("---Transaction Menu---");
	}
	
	public void printTransaction() {
		System.out.println("Date: " + this.date + " | Amount: $" + this.formattedAmount + " | Memo: " + this.memo);
	}

	public String getMemo(){
		return this.memo;	
	}

	public double getAmount(){
		return this.amount;
	}

	public String getFormattedAmount(){
		if (this.amount < 0){
			return String.format("%.02f", Math.abs(this.amount));
		}

		else {
			return this.formattedAmount;
		}
	}
}

