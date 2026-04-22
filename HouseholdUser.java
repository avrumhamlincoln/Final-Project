import java.util.*;

public class HouseholdUser extends User{
	private UserList familyMembers;


	public HouseholdUser(){
		super();
		this.familyMembers = new UserList();
	}

	public void addFamilyMember(User newMember){
		this.familyMembers.add(newMember);
		System.out.println(newMember.username + " has been added to the household! I hope you got them a welcome gift.");
	}

	public void start(){
		Scanner input = new Scanner(System.in);
		boolean keepGoing = true;

		System.out.println("\n---Your Household Dashbord---");

		while (keepGoing){
			System.out.println("\nWhat would you like to do?");
			System.out.println("1) View Combined Household Total");
			System.out.println("2) View Household Transactions");
			System.out.println("3) View My Personal Report");
			System.out.println("4) Record Personal Income");
			System.out.println("5) Record Personal Expense");
			System.out.println("0) Logout");
			System.out.print("Choice: ");

			String choice = input.nextLine();

			if (choice.equals("1")){
				double grandTotal = 0.0;
				grandTotal += super.personalTracker.getBalance();

				for (User user : this.familyMembers){
					PersonalTracker tracker  = user.getTracker();
					double balance = tracker.getBalance();
					grandTotal += balance;
				}

				System.out.println("\nTotal Household Balance: $" + grandTotal);
			}

			else if (choice.equals("2")){
				
			}
		}
	}
}

class UserList extends ArrayList<User> {}
