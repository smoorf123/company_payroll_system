package businessOffice;

/*
 * The Employee class is used to create employee objects with fields for the 
 * name of the employee as empName, how many hours they work as empHours and
 * the amount of sales they have done as empSaleAmt. This class is 
 * meant to have methods that are overridden by methods from the 
 * SlariedEmployee and CommissionedEmployee classes to provide 
 * class-specific functionality. Two of the fields empHours and empSaleAmt used
 * here are protected as they need to be accessed by the subclasses as well as
 * the Account class for methods newPayPeriod(), amtSalesMade() and numHours().
 * This is done to prevent the use of getter and setters, as this information is
 * heavily accessed throughout the system.
 */
public class Employee {
	private String empName;

	// Protected fields are used here as they allow subclasses to access these
	// fields without the use of getters and setters. As a lot of the
	// functionality of the subclasses relies on these fields, they are
	// protected instead of private.
	protected int empHours;
	protected double empSaleAmt;

	// Default constructor
	public Employee() {
	}

	// Overloaded Constructor with name parameter to assign name to employee
	public Employee(String name) {
		empName = name;
	}

	/*
	 * This method checks if the name of the employee from the current object is
	 * the same as the employee name passed as an argument. Returns true is same
	 * name, false if not.
	 */
	public boolean empExistCheck(String name) {
		boolean flag = false;

		if (empName.equals(name)) {
			flag = true;
		}
		return flag;
	}

	/*
	 * This method adds hours to the employee's total hours worked and returns
	 * true on a successful addition. This method lacks in functionality as it
	 * is meant to be overridden by methods from the SlariedEmployee and
	 * CommissionedEmployee classes.
	 */
	public boolean addHours(int hours) {
		empHours += hours;
		return true;
	}

	/*
	 * This method adds to the number of the employee's total sales however does
	 * not have a return type. This method lacks in functionality as it is meant
	 * to be overridden by methods from the SlariedEmployee and
	 * CommissionedEmployee classes.
	 */
	public void addSaleAmt(double saleAmt) {
		empSaleAmt += saleAmt;
	}

	/*
	 * This method calculates the total pay an employee is entitled to however
	 * lacks in functionality as it is meant to be overridden by methods from
	 * the SlariedEmployee and CommissionedEmployee classes. Returns a double
	 * for the pay amount.
	 */
	public double totalPay() {
		return -1.0;
	}
}
