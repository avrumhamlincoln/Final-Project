import java.util.*;

public class PersonalUser extends User{
	public PersonalUser(){}

	public void start(){
		Scanner input = new Scanner(System.in);
		boolean keepGoing = true;

		System.out.println("\n---" + this.username + "'s personal Dashboard---");
		
		while(keepGoing){
			System.out.println("\nWhat would you like to do?");
			System.out.println("1) Record Income");
			System.out.println("2) Record Expense");
			System.out.println("3) Set Budget Goal");
			System.out.println("4) View Your Report");
			System.out.println("0) Logout");
			System.out.print("Choice: ");

			String choice = input.nextLine();

			if (choice.equals("1")){
				System.out.print("Enter Date [MM/DD/YYYY]: ");
				String date = input.nextLine();

				System.out.print("Enter Amount: $");
				double amount = this.getDouble();

				System.out.print("Enter Memo: ");
				String memo = input.nextLine();

				Transaction t = new Transaction(date, amount, memo);
				t.incomeTrans(this.personalTracker);
				this.personalTracker.storeTransaction(t);

				System.out.println("Income recorded!");
			}

			else if (choice.equals("2")){
				System.out.print("Enter Date [MM/DD/YYYY]: ");
                                String date = input.nextLine();

                                System.out.print("Enter Amount: $");
                                double amount = this.getDouble();

                                System.out.print("Enter Memo: ");
                                String memo = input.nextLine();

				Transaction t = new Transaction(date, amount, memo);
                                t.expenseTrans(this.personalTracker);
                                this.personalTracker.storeTransaction(t);

				System.out.println("Expense recorded!");
			}

			else if (choice.equals("3")){
				System.out.print("Enter your new savings goal: $");
				double goal = this.getDouble();

				this.personalTracker.setBalanceGoal(goal);
				System.out.println("Budget goal updated!");
			}

			else if (choice.equals("4")){
				this.getReport();
			}

			else if (choice.equals("0")){
				System.out.println("Logging out!");
				keepGoing = false;
			}

			else{
				System.out.println("Invalid choice. Please enter a number 0-4.");
			}
		}
	}
}
