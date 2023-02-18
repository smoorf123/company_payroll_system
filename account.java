package businessOffice;

/*
 * This is the main account class for the payroll management system. It contains
 * all of the required methods to effectively manage the payroll of any company.
 * To store employees, an object of the DatarrayList is used which functions 
 * almost identically to the Java arrayList. A new DatarrayList object is 
 * initialized and stores them in a employee object called empList. There are
 * also fields for the name of the account (the company using making the 
 * account, whether the account is limited (checked through seeing if empLimit 
 * is true), and if the account is limited, an int to keep track of the maximum 
 * amount of employees that can be stored in the payroll software (maxEmployees)
 * The methods in this class range from methods to hire employees, add working 
 * hours, sales amounts, check how much they should earn, see the payroll of the
 * company as a whole and check if employees exist in an account. This class
 * also contains two private helper methods to prevent code repetition.
 */
public class Account {
	private String accName;
	private boolean empLimit;
	private int maxEmployees;

	// Creating a new list for employees to be stored through the class
	// DatarrayList.
	DatarrayList empList = new DatarrayList();

	// This is the constructor used for paid accounts, without assigning the
	// true value for empLimit.
	public Account(String name) {
		accName = name;
		empLimit = false;
	}

	// This is the constructor used for free accounts, assigning the
	// true value for empLimit and assigning the max employees that can be
	// stored at a time.
	public Account(String name, boolean empLim, int maxEmp) {
		accName = name;
		empLimit = empLim;
		maxEmployees = maxEmp;
	}

	/*
	 * This method returns a string of the name of the account holder, i.e. the
	 * company.
	 */
	public String getAccountName() {
		return accName;
	}

	/*
	 * This is a custom private method I wrote to check if an employee being
	 * added has a valid name i.e. it isn't a null and it isn't a blank string.
	 * The code trims the possible name to having no extra whitespace and checks
	 * if it's an empty string. The method returns a true if the name is a valid
	 * name. This return value is used in multiple functions below to check if
	 * valid Employee names are being stored.
	 */
	private boolean isEmployeeValid(String name) {
		boolean flag = false;
		if (name != null && !name.trim().isEmpty()) {
			flag = true;
		}
		return flag;
	}

	/*
	 * This method hires commissioned employees. It takes in the parameters name
	 * of employee and the commission rate they charge. The code then checks if
	 * the name of the employee is valid, if the commission percentage is
	 * between 0 and 100 and whether a pre-existing employee with the same name
	 * already previously exists. The method then checks if the account is
	 * limited (free) and if so, is it within the alloted free number of
	 * employees. Only after all of these checks does it get added to the object
	 * referencing an array of employees (empList).
	 */
	public boolean hireCommissionedWorker(String name, double commissionRate) {
		boolean flag = false;
		if (isEmployeeValid(name) && commissionRate > 0.0
				&& commissionRate < 100.0 && !isEmployee(name)) {
			// Checks whether the account is limited by confirming empLimit
			// is false and if that is not the case, it is under the allotted
			// limit for free accounts. If these conditions aren't met, no
			// employee is added and the method returns false.
			if (!empLimit || numEmployees() < maxEmployees) {
				empList.add(new CommissionEmployee(name, commissionRate));
				flag = true;
			}
		}
		return flag;
	}

	/*
	 * This method hires salaried employees. It takes in the parameters name of
	 * employee and the yearly salary. The code then checks if the name of the
	 * employee is valid, if the yearly salary is greater than 0 and whether a
	 * pre-existing employee with the same name already previously exists. The
	 * method then checks if the account is limited (free) and if so, is it
	 * within the alloted free number of employees. Only after all of these
	 * checks does it get added to the object referencing an array of employees
	 * (empList).
	 */
	public boolean hireSalariedWorker(String name, double yearlySalary) {
		boolean flag = false;
		if (isEmployeeValid(name) && yearlySalary > 0.0 && !isEmployee(name)) {
			// Checks whether the account is limited by confirming empLimit
			// is false and if that is not the case, it is under the allotted
			// limit for free accounts. If these conditions aren't met, no
			// employee is added and the method returns false.
			if (!empLimit || numEmployees() < maxEmployees) {
				empList.add(new SalariedEmployee(name, yearlySalary));
				flag = true;
			}
		}
		return flag;
	}

	/*
	 * This method checks whether a specified employee is already present in the
	 * DatarrayList object i.e. stored in the employee list. It references
	 * another method called empExistCheck which only checks whether a name
	 * passed as an argument is equal to the name of the current object's name
	 */
	public boolean isEmployee(String name) {
		boolean flag = false;

		// Loop iterating through each employee stored
		for (int i = 0; i < empList.getSize(); i++) {
			// flag becomes true if the same employee is found
			if (employeeFound(i, name)) {
				flag = true;
			}
		}
		// returns true if same name found, false if not
		return flag;
	}

	// Method to return an int of the number of employees stored in the account
	public int numEmployees() {
		return empList.getSize();
	}

	/*
	 * Method to return an int of the max number of employees that can be stored
	 * returns max employees allowed for free accounts (as mentioned by
	 * constructor) and if the account is a paid one, it returns the largest
	 * possible int value in Java (Integer.Max_Value)
	 */
	public int employeeLimit() {
		int retVal = 0;
		// For free accounts
		if (empLimit) {
			retVal = maxEmployees;
			// For paid accounts
		} else {
			retVal = Integer.MAX_VALUE;
		}
		return retVal;
	}

	/*
	 * This method adds the number of hours an employee has worked. The hours an
	 * employee has worked is purely for legal reasons and has no greater effect
	 * on the payroll aspect of the program. One use of the hour logging system
	 * is to make sure salaried employees do not clock more than 80 hours in a
	 * week. This method returns true if the hours were added and false
	 * otherwise.
	 */
	public boolean workHours(String name, int numHours) {
		boolean flag = false;

		// Checks number of hours being added is greater than 0 and if the
		// employee is in employee list
		if (numHours > 0 && isEmployee(name)) {
			// iterates through each employee to find correct employee
			for (int i = 0; i < empList.getSize(); i++) {
				// if block to confirm correct employee
				if (employeeFound(i, name)) {
					// using addHours method from Employee class to add hours to
					// associated employee.
					flag = ((Employee) empList.get(i)).addHours(numHours);
				}
			}
		}
		return flag;
	}

	/*
	 * This method returns the number of hours a specified employee has worked.
	 * It returns a -1 if an invalid name is passed as an argument and returns
	 * the number of hours if the employee is found.
	 */
	public int numHours(String name) {
		int retVal = -1;

		// Checks if employee name is valid and if the
		// employee is in employee list
		if (isEmployee(name)) {
			// iterates through each employee to find correct employee
			for (int i = 0; i < empList.getSize(); i++) {
				// if block to confirm correct employee
				if (employeeFound(i, name)) {
					// assigns the number of hours to retVal by retrieving the
					// data from the specified employee.
					retVal = ((Employee) empList.get(i)).empHours;
				}
			}
		}
		return retVal;
	}

	/*
	 * This method adds a specified sale to a specified employee. It iterates
	 * through employees in the employee list and upon finding the specified one
	 * it adds the sale amount as a double to the total sale amount of an
	 * employee.
	 */
	public boolean makeSale(String name, double saleAmt) {
		boolean flag = false;

		// makes sure sale amount is not less than 0 and employee exists
		if (saleAmt > 0 && isEmployee(name)) {
			// iterates through each employee to find correct employee
			for (int i = 0; i < empList.getSize(); i++) {
				// if block to confirm correct employee
				if (employeeFound(i, name)) {
					// Uses addSaleAmt method from Employee Class to add the
					// sale amount to the specified Employee.
					((Employee) empList.get(i)).addSaleAmt(saleAmt);
					flag = true;
				}
			}
		}
		// returns true if sale amount is added, false if not
		return flag;
	}

	/*
	 * This method returns -1.0 if the name specified is invalid or the employee
	 * does not exist. Otherwise, the retValue is set to the total amount of
	 * sales an employee has as a double.
	 */
	public double amtSalesMade(String name) {
		double retVal = -1.0;

		// makes sure that the employee name exists.
		if (isEmployee(name)) {
			// iterates through each employee to find correct employee
			for (int i = 0; i < empList.getSize(); i++) {
				// if block to confirm correct employee
				if (employeeFound(i, name)) {
					retVal = ((Employee) empList.get(i)).empSaleAmt;
				}
			}
		}
		return retVal;
	}

	/*
	 * This method returns the pay that an employee is entitled to on the basis
	 * of whether they are salaried or commissioned. The totalPay method is
	 * called from the Employee class. -1.0 is returned if an invalid name is
	 * passed as an argument.
	 */
	public double getPayAmount(String name) {
		double retVal = -1.0;

		// makes sure that the employee name exists.
		if (isEmployee(name)) {
			// iterates through each employee to find correct employee
			for (int i = 0; i < empList.getSize(); i++) {
				// if block to confirm correct employee
				if (employeeFound(i, name)) {
					retVal = ((Employee) empList.get(i)).totalPay();
				}
			}
		}
		return retVal;
	}

	/*
	 * This method returns the total amount to be paid by the company to all of
	 * its employees. The totalPay method from the Employee class is called and
	 * incremented to a totalAmt double which is returned at the end.
	 */
	public double getPayroll() {
		double totalAmt = 0.0;
		// iterates through each employee
		for (int i = 0; i < empList.getSize(); i++) {
			// totalAmt incrementing by each Employee's amount to be paid.
			totalAmt += ((Employee) empList.get(i)).totalPay();
		}
		return totalAmt;
	}

	/*
	 * This method resets the Pay period i.e it resets the employee hours and
	 * employee sale amounts to 0.
	 */
	public void newPayPeriod() {
		// iterates through each employee
		for (int i = 0; i < empList.getSize(); i++) {
			((Employee) empList.get(i)).empHours = 0;
			((Employee) empList.get(i)).empSaleAmt = 0;
		}
	}

	/*
	 * This method returns the amount owed by a company for the usage of this
	 * payroll software. If a company has a free account the method returns 0.0
	 * otherwise the method returns 10* number of employees the company has
	 * registered in the system.
	 */
	public double billAmount() {
		double amtDue = 0.0;

		if (!empLimit) {
			for (int i = 0; i < empList.getSize(); i++) {
				amtDue += 10.0;
			}
		}
		return amtDue;
	}

	/*
	 * Private method to check if the current element in a for loop iterating
	 * through each element in the Employee array is the right one as in the
	 * method's parameter. Used as a helper method for many classes using a for
	 * loop to iterate through the employees. Prevents code repetition
	 */
	private boolean employeeFound(int i, String name) {
		boolean flag = false;

		// Checking if current element (using i from for loop) is same as
		// name returned from empExistCheck method in Employee class.
		// Returns true if yes, else false.
		if (((Employee) empList.get(i)).empExistCheck(name)) {
			flag = true;
		}
		return flag;
	}
}
