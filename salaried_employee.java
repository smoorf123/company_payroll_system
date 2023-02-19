package businessOffice;

/*
 * The SalariedEmployee class is used to create objects of the Employee class
 * which are SPECIFICALLY salaried i.e. paid a yearly salary to work at their
 * company. Apart from the fields extended to it by the Employee class such as
 * empName and empSaleAmt, it has its own field, namely a double representing
 * the employee's yearly salary (empYearlySalary). This class also includes 
 * three methods that it uses to override the parent class's methods of the
 * same name.
 */
public class SalariedEmployee extends Employee {
	private double empYearlySalary;

	// This constructor overrides the default constructor from the Employee
	// class it extends, using the parameters employee name and yearlySalary.
	public SalariedEmployee(String name, double yearlySalary) {
		super(name);
		empYearlySalary = yearlySalary;
	}

	/*
	 * This method adds hours to the total number of hours a SalariedEmployee
	 * has worked, overriding the method of the same name in the Employee class
	 * however the key difference being that this method checks if the Salaried
	 * Employee has worked more than 80 hours in a single pay period. If so, the
	 * method will not add to the number of hours and just return false so as to
	 * avoid paying overtime. If hours are successfully added, true is returned.
	 */
	@Override
	public boolean addHours(int hours) {
		boolean flag = false;
		if (empHours < 80) {
			flag = true;
			super.addHours(hours);
		}
		return flag;
	}

	/*
	 * This method adds sales to the total number of sales a SalariedEmployee
	 * has, overriding the method of the same name in the Employee class however
	 * the key difference being that since the sales of the Salaried Employees
	 * are irrelevant as they receive a fixed amount of pay, it is unnecessary
	 * to store this information hence the method simply returns 0.0.
	 */
	@Override
	public void addSaleAmt(double saleAmt) {
		super.addSaleAmt(0.0);
	}

	/*
	 * This method calculates the total pay of a SalariedEmployee has,
	 * overriding the method of the same name in the Employee class however it
	 * is specific to the Salaried class as it divides the yearly pay the
	 * salaried employee has by the 26 pay periods in the year.
	 */
	@Override
	public double totalPay() {
		return empYearlySalary / 26;
	}
}
