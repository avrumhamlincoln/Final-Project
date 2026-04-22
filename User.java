import java.util.*;

public abstract class User implements HasMenu{
	protected String username;
	protected String password;
	protected PersonalTracker personalTracker;

	public User(){
		Scanner input = new Scanner(System.in);

		System.out.println("\n---New Profile Setup---");

		System.out.print("Create a Username: ");
		this.username = input.nextLine();

		System.out.print("Create a Password: ");
		this.password = input.nextLine();

		System.out.println("Profile created successfully for " + this.username + "! Nice to meet you ;)");
		this.personalTracker = new PersonalTracker();
	} 
	
	public boolean login(){
		Scanner input = new Scanner(System.in);
		boolean result = false;

		System.out.print("\nUsername: ");
		String userNameIn = input.nextLine();

		if (userNameIn.equals(this.username)){
			System.out.print("Password: ");
			String passwordIn = input.nextLine();

			if (passwordIn.equals(this.password)){
				System.out.println("\nLogin Successful! Welcome " + this.username);
				result = true;
			}

			else{
				System.out.println("\nIncorrect Password");
			}
		}

		else{
			System.out.println("\nIncorrect Username");
		}

		return result;
	}

	public PersonalTracker getTracker(){
		return this.personalTracker;
	}

	public void getReport(){
		this.personalTracker.getTransactions();
	}
	
	private double getDouble(){
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

	public void menu(){
		System.out.println("---User Menu---");
	}

	public void start() {
	}
}
