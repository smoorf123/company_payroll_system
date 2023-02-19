package businessOffice;

/*
 * This class creates the accounts for all companies wishing to use the payroll
 * software. Contains two overloaded static methods to create and return objects
 * of type account. Companies that create accounts with this class may have
 * free or paid accounts, demonstrated by the two separate methods to create an
 * account. This class also contains a private method called isNameValid that
 * checks and returns a boolean true or false depending on whether a valid
 * account name is entered an argument. This private method is used by both 
 * other classes to confirm the validity of the name used. It is private as the
 * required scope of that method is only this class.
 */
public class BusinessOffice {

	/*
	 * This method creates an account for PAID users; companies PAYING for the
	 * usage of the software will use this account constructor.
	 */
	public static Account createAccount(String name) {
		// Constructor only takes name of company, while checking if name is
		// valid using isNameValid private method of this class
		if (isNameValid(name)) {
			Account account = new Account(name);
			return account;
		} else {
			return null;
		}
	}

	/*
	 * This method creates an account for FREE users; companies NOT PAYING for
	 * the usage of the software will use this account constructor. The key
	 * difference is that the constructor has an added parameter of empLim which
	 * is a boolean confirming if the account is limited or not. In case of the
	 * free account, the account will be limited and hence have according
	 * functionality.
	 */
	public static Account createAccount(String name, int maxEmployees) {
		// Constructor takes name of company, boolean of whether account is
		// limited and the maximum amount of employees it can manage, owing to
		// limited free functionality of the software.

		// If block to check if name is valid and if maxEmployees is not 0
		if (isNameValid(name) && maxEmployees > 0) {
			Account account = new Account(name, true, maxEmployees);
			return account;
		} else {
			return null;
		}
	}

	/*
	 * Manual method used to check validity of name of Account held by company
	 */
	private static boolean isNameValid(String name) {
		boolean flag = false;
		// Checks if not null and name trimmed is NOT empty
		// If it is either, method returns true else false
		if (name != null && !name.trim().isEmpty()) {
			flag = true;
		}

		return flag;
	}
}
