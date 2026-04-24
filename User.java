import java.util.*;

public class User implements HasMenu{
	protected String username;
	protected String password;
	protected PersonalTracker personalTracker;
	protected UserList familyMembers;

	public User(){
		Scanner input = new Scanner(System.in);

		System.out.println("\n---New Profile Setup---");

		System.out.print("Create a Username: ");
		this.username = input.nextLine();

		System.out.print("Create a Password: ");
		this.password = input.nextLine();

		System.out.println("Profile created successfully for " + this.username + "! Nice to meet you ;)");
		this.personalTracker = new PersonalTracker();
		this.familyMembers = new UserList();
		this.familyMembers.add(this);
	} 
	
	public boolean login(){
		Scanner input = new Scanner(System.in);
		boolean result = false;

		System.out.print("Password: ");
		String passwordIn = input.nextLine();

		if (passwordIn.equals(this.password)){
			System.out.println("\nLogin Successful! Welcome " + this.username);
			result = true;
		}

		else{
			System.out.println("\nIncorrect Password");
		}

		return result;
	}

	public PersonalTracker getTracker(){
		return this.personalTracker;
	}

	public void getReport(){
		this.personalTracker.getTransactions();
	}
	
	protected double getDouble(){
		Scanner input =  new Scanner(System.in);
		String restultString = input.nextLine();
		double result = 0d;

		try{
			result = Double.parseDouble(restultString);
		}

		catch (Exception e){
			System.out.println("Not a good value. Changing to 0");
			result = 0d;
		}

		return result;
	}

	public String getUsername(){
		return this.username;
	}

	
	public void menu(){
	}
	
	public void addFamilyMember(User user){
		if (this.familyMembers.contains(user)){
			System.out.println("This user is already in your household!");
		}

		else {
			this.familyMembers.add(user);
			System.out.println(user.getUsername() + " successfully added to your household!");
		}
	}

	public void start() {
		Scanner input = new Scanner(System.in);

		System.out.println("\n---" + this.username + "'s Dashboard---");
		System.out.println("1) Personal Budgeting");
		System.out.println("2) Household Management");
		System.out.println("0) Logout");
		System.out.print("Choice: ");
		String choice = input.nextLine();
		boolean keepGoing2 = true;

		if (choice.equals("1")){
			System.out.println("\n---Your Household Dashbord---");
		
			while(keepGoing2){
				System.out.println("\nWhat would you like to do?");
				System.out.println("1) Record Income");
				System.out.println("2) Record Expense");
				System.out.println("3) Set Budget Goal");
				System.out.println("4) View Your Report");
				System.out.println("0) Logout");
				System.out.print("Choice: ");
	
				choice = input.nextLine();
	
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
					System.out.println("Logging out");
					keepGoing2 = false;
				}	
	
				else{
					System.out.println("Invalid choice. Please enter a number 0-4.");
				}
			}
		}

		else if (choice.equals("2")){
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
	
				choice = input.nextLine();
	
				if (choice.equals("1")){
					double grandTotal = 0.0;
					grandTotal += this.personalTracker.getBalance();
	
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
					this.getReport();
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
					this.personalTracker.storeTransaction(t);
	
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
                        	        this.personalTracker.storeTransaction(t);

         	                       System.out.println("Expense recorded!");
               		         }

				else if (choice.equals("6")){
					boolean keepGoing3 = true;
	
					while (keepGoing3) {
						System.out.print("Enter the username of the person you want to add or enter 0 to go back: ");
						String username = input.nextLine();
						
						boolean found = false;
	
						if (username.equals("0")){
							keepGoing3 = false;
							found = true;
						}
	
						for (User user : Main.masterList){
							if (user.getUsername().equals(username)){
								this.addFamilyMember(user);
								found = true;
								keepGoing3 = false;
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
		else if (choice.equals("6")){
			System.out.println("Logging out");
		}

		else {
		System.out.println("Invalid choice. Please enter a number 0-2.");
		}
	}
}

class UserList extends java.util.ArrayList<User> {}
