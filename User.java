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

		System.out.print("Set your Budget: $");
		double goal = this.getDouble();

		System.out.println("\nProfile created successfully for " + this.username + "! Nice to meet you ;)");
		this.personalTracker = new PersonalTracker(goal);
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
			System.out.println("\nNot a good value. Changing to 0");
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
			System.out.println("\nThis user is already in your household!");
		}

		else {
			this.familyMembers.add(user);
			System.out.println("\n" + user.getUsername() + " successfully added to your household!");
		}
	}

	public void start() {
		Scanner input = new Scanner(System.in);

		System.out.println("\n---" + this.username + "'s Dashboard---");
		System.out.println("1) Personal Budgeting");
		System.out.println("2) Household Budgeting");
		System.out.println("3) Delete Account");
		System.out.println("0) Logout");
		System.out.print("Choice: ");
		String choice = input.nextLine();
		boolean keepGoing2 = true;

		if (choice.equals("1")){
			System.out.println("\n---Your Personal Dashbord---");
		
			while(keepGoing2){
				System.out.println("\nWhat would you like to do?");
				System.out.println("1) Record Income");
				System.out.println("2) Record Expense");
				System.out.println("3) Delete Last Transaction");
				System.out.println("4) Change Budget Amount");
				System.out.println("5) View Your Transaction Report");
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
	
					System.out.println("\nIncome recorded!");
				}
	
				else if (choice.equals("2")){
					System.out.print("Enter Date [MM/DD/YYYY]: ");
                        	        String date = input.nextLine();
	
        	                        System.out.print("Enter Amount: $");
                	                double amount = this.getDouble();
					amount = amount * -1;
	
        	                        System.out.print("Enter Memo: ");
                	                String memo = input.nextLine();
	
					Transaction t = new Transaction(date, amount, memo);
                	                t.expenseTrans(this.personalTracker);
                        	        this.personalTracker.storeTransaction(t);
	
					System.out.println("\nExpense recorded!");
				}
				
				else if (choice.equals("3")){
					this.personalTracker.removeLastTran();
				}

				else if (choice.equals("4")){
					System.out.print("Enter your new budget: $");
					double goal = this.getDouble();
	
					this.personalTracker.setBalanceGoal(goal);
					System.out.println("\nBudget goal updated!");
				}
	
				else if (choice.equals("5")){
					this.getReport();
					this.personalTracker.getTranSumary();
				}
	
				else if (choice.equals("0")){
					System.out.println("Logging out");
					keepGoing2 = false;
				}	
	
				else{
					System.out.println("\nInvalid choice. Please enter a number 0-5.");
				}
			}
		}

		else if (choice.equals("2")){
			boolean keepGoing = true;
	
			System.out.println("\n---Your Household Dashbord---");
	
			while (keepGoing){
				System.out.println("\nWhat would you like to do?");
				System.out.println("1) View Combined Household Total");
				System.out.println("2) View Household Transaction Report");
				System.out.println("3) Add a Family Member");
				System.out.println("4) Remove a Family Member");
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
						System.out.println("\n---" + user.username + "'s Transactions---");
						user.getReport();			
					}					
				}

				else if (choice.equals("3")){
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
							System.out.println("\nThe user you are looking for does not exist. Try again!");
						}
					}
				}

				else if (choice.equals("4")){
                                        boolean keepGoing4 = true;

                                        while (keepGoing4) {
                                                System.out.print("\nEnter the username of the person you want to remove or enter 0 to go back: ");
                                                String username = input.nextLine();

                                                boolean found = false;
						boolean removing = true;

                                                if (username.equals("0")){
                                                        keepGoing4 = false;
                                                        found = true;
                                                }
						
						int i = 0;

						while (i < this.familyMembers.size()){
							User user = this.familyMembers.get(i);
							if (user.getUsername().equals(username)){
								if (user == this){
									System.out.println("\nYou cannot remove yourself from your own household!");
								}

								else {
									this.familyMembers.remove(i);
									System.out.println("\n" + username + " has been removed from your household.");	
								}

								found = true;
								keepGoing4 = false;
							}

							i++;
						}
                                                
                                                if (!found){
                                                        System.out.println("\nThe user you are looking for is not in your household. Try again!");
                                                }
                                        }
                                }

				else if (choice.equals("0")){
					System.out.println("Logging out");
					keepGoing = false;
				}
	
				else {
					System.out.println("\nInvalid choice. Please enter a number 0-4.");	
				}
			}
		}

		else if (choice.equals("3")){
			System.out.println("\n---DELETE ACCOUNT---");
			System.out.print("\nWARNING: Are you SURE you want to delete your account? Enter your password to confirm: ");
			String password = input.nextLine();

			if (password.equals(this.password)){
				for (User user : Main.masterList){
					user.familyMembers.remove(this);
				}

				Main.masterList.remove(this);
				System.out.println("Account permanently deleted. We're sad to see you go!");
			}
		}

		else if (choice.equals("0")){
			System.out.println("Logging out");
		}

		else {
		System.out.println("\nInvalid choice. Please enter a number 0-3.");
		}
	}
}

class UserList extends java.util.ArrayList<User> {}
