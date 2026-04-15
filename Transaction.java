public class Transaction implements HasMenu {
	private String date;
	private double amount;
	private String memo;

	public Transaction(String tDate, double tAmount, String tMemo) {
		this.date = tDate
		this.ammount = tAmmount
		this.memo = tMeme
	}

	public void incomeTrans(PersonalTracker tracker){
	
	}

	public void expenseTrans(PersonalTracker tracker){
	
	}

	public void menu() {
		System.out.println("---Transaction Menu---");
	}

	public void start() {
	}
	
	public void printTransaction() {
		System.out.println("Date: " + this.date + " | Amount: $" + this.amount + " | Memo: " + this.memo);
	}

