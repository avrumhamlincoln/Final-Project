import java.util.*;

public class Main{
	public static UserList masterList = new UserList();
	public static void main(String[] args) {
		/*
		PersonalTracker tracker;
		tracker = new PersonalTracker();
		tracker.setBalanceGoal(2000.00);
		
		Transaction t1;
        	t1 = new Transaction("04/19/2026", 2500.0, "Paycheck");
        	t1.incomeTrans(tracker);
        	tracker.storeTransaction(t1);

		Transaction t2;
        	t2 = new Transaction("04/20/2026", 800.0, "Rent");
        	t2.expenseTrans(tracker);
        	tracker.storeTransaction(t2);

		tracker.getTransactions();
		*/

		Scanner input = new Scanner(System.in);
		boolean keepGoing = true;

		System.out.println("---WELCOME TO BUDGET BUDDY---");

		while (keepGoing){
			System.out.println("\n---Main Menu---");
			System.out.println("1) Login to your Budget Buddy Account");
			System.out.println("2) Create a Budget Buddy Account");
			System.out.println("0) Exit Budget Buddy");
			System.out.print("Choice: ");

			String choice = input.nextLine();

			if (choice.equals("1")){
				if (masterList.isEmpty()){
					System.out.println("\nNo accounts exist yet! Please create one first ;)");
					User newUser = new PersonalUser();
					masterList.add(newUser);
				}
				
				System.out.println("\n---Budget Buddy Login---");
				System.out.print("Username: ");
				String username = input.nextLine();

				boolean found = false;

				for (User user : masterList){
					if (user.getUsername().equals(username)){
						found = true;

						if (user.login()){
							user.start();
						}
					}
				}

				if (!found){
					System.out.println("\nUsername not found. :/");
				}
			}

			else if (choice.equals("2")){
				User newUser = new PersonalUser();
				masterList.add(newUser);
			}

			else if (choice.equals("0")){
				System.out.println("Goodbye!");
				keepGoing = false;
			}

			else {
				System.out.println("Invalid choice. Please enter 0-3.");
			}
		}
	}
}
