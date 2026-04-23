import java.util.*;

public class HouseholdUser extends User{
	private UserList familyMembers;


	public HouseholdUser(){
		super();
		this.familyMembers = new UserList();
	}

	public void addFamilyMember(User newMember){
		familyMembers.add(newMember);
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
			System.out.println("6) Add a Family Member");
			System.out.println("0) Logout");
			System.out.print("Choice: ");

			String choice = input.nextLine();

			if (choice.equals("1")){
				double grandTotal = 0.0;
				grandTotal += super.personalTracker.getBalance();

				for (User user : familyMembers){
					PersonalTracker tracker  = user.getTracker();
					double balance = tracker.getBalance();
					grandTotal += balance;
				}

				System.out.println("\nTotal Household Balance: $" + grandTotal);
			}

			else if (choice.equals("2")){
				System.out.println("\n---Household Transactions---");
				for (User user : familyMembers){
					user.getReport();			
				}				
			}

			else if (choice.equals("3")){
				super.getReport();
			}

			else if (choice.equals("4")){
				System.out.print("Enter Date [MM/DD/YYYY]: ");
				String date = input.nextLine();

				System.out.print("Enter Amount: $");
				double amount = this.getDouble();

				System.out.print("Enter Memo: ");
				String memo = input.nextLine();

				Transaction t = new Transaction(date, amount, memo);
				t.incomeTrans(this.personalTracker);
				super.personalTracker.storeTransaction(t);

				System.out.println("Income recorded!");
			}

			else if (choice.equals("5")){
                                System.out.print("Enter Date [MM/DD/YYYY]: ");
                                String date = input.nextLine();

                                System.out.print("Enter Amount: $");
                                double amount = this.getDouble();

                                System.out.print("Enter Memo: ");
                                String memo = input.nextLine();

                                Transaction t = new Transaction(date, amount, memo);
                                t.expenseTrans(this.personalTracker);
                                super.personalTracker.storeTransaction(t);

                                System.out.println("Expense recorded!");
                        }

			else if (choice.equals("6")){
				boolean keepGoing2 = true;

				while (keepGoing2) {
					System.out.print("Enter the username of the person you want to add or enter 0 to go back: ");
					String username = input.nextLine();
					
					boolean found = false;

					if (username.equals("0")){
						keepGoing2 = false;
						found = true;
					}

					for (User user : Main.masterList){ //add to Main.java later
						if (user.getUsername().equals(username)){
							this.addFamilyMember(user);
							found = true;
							keepGoing2 = false;
						}
					}

					if (!found){
						System.out.println("The user you are looking for does not exist. Try again!");
					}
				}
			}

			else if (choice.equals("0")){
				System.out.println("Logging out");
				keepGoing = false;
			}

			else {
				System.out.println("Invalid choice. Please enter a number 0-6.");
			}
		}
	}
}

class UserList extends ArrayList<User> {}
