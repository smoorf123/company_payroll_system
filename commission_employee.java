package businessOffice;

/*
 * The CommissionedEmployee class is used to create objects of the Employee
 * class which are SPECIFICALLY commissioned i.e. paid a salary as a percentage
 * of the amount of sales they make, as decided by their commission rate.
 * Apart from the fields extended to it by the Employee class such as
 * empName and empSaleAmt, it has its own field, namely a double representing
 * the employee's commission rate (empCommRate). This class also includes three
 * methods that it uses to override the parent class's methods of the same name.
 */
public class CommissionEmployee extends Employee {
	private double empCommRate;

	// This constructor overrides the default constructor from the Employee
	// class it extends, using the parameters employee name and commissionRate.
	public CommissionEmployee(String name, double commissionRate) {
		super(name);
		empCommRate = commissionRate;
	}

	/*
	 * This method adds hours to the total number of hours a
	 * CommissionedEmployee has worked, overriding the method of the same name
	 * in the Employee class. Since it is irrelevant how many hours a
	 * CommissionedEmployee has worked as they can work an infinite amount of
	 * hours in a pay period, the hours are simply added and true is returned.
	 */
	@Override
	public boolean addHours(int hours) {
		super.addHours(hours);
		return true;
	}

	/*
	 * This method adds sales to the total number of sales a
	 * CommissionedEmployee has, overriding the method of the same name in the
	 * Employee class. All this method does is simply add the amount of its
	 * sales to the extended field of saleAmt from its parent class Employee.
	 */
	@Override
	public void addSaleAmt(double saleAmt) {
		super.addSaleAmt(saleAmt);
	}

	/*
	 * This method calculates the total pay of a CommissioneddEmployee has,
	 * overriding the method of the same name in the Employee class however it
	 * is specific to the Salaried class as it multiplies the commission rate a
	 * Commissioned Employee has (empCommRate) as fraction of 100 with the
	 * amount of sales they have done to calculate their final pay. A double of
	 * this value is returned.
	 */
	@Override
	public double totalPay() {
		return empCommRate / 100 * empSaleAmt;
	}

}
