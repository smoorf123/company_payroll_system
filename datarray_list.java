package businessOffice;

/*
 * DatarrayList class to create arrayList-like objects to store employee data.
 * Works in the same way as arrayLists work in the sense that it also has a 
 * get() and add() but also has some added functionality. An object of this
 * class, whenever created, creates a new java array which is used to store
 * objects. This array is created of increment size and can be expanded using
 * the private addSpace() method, which copies each element in the current array
 * to a larger sized tempArray and then assigns the reference of the larger
 * array to the default array. This class also has 4 fields, namely the main
 * array to be created, the current element (used in the add() method), the
 * current increment (used in the addSpace() method) and a temp int (used in the
 * addSpace()).
 */
public class DatarrayList {
	// Main array arrData which is used to store Employee objects
	private Object[] arrData;
	private int currElement = 0;
	private int currIncrement = 0;
	// temp is only used to store a temporary variable used outside of
	// the addSpace method (for intended functionality)
	private int temp = 2;

	/*
	 * If the object for the DatarrayList class is constructed without any
	 * argument, a default size of array to be initialized is set to 1.
	 */
	public DatarrayList() {
		arrData = new Object[1];
		currIncrement = 1;
	}

	/*
	 * This constructor initializes the new DatarrayList object having an array
	 * of Object type references. If increment is positive, it is directly used
	 * to define size of array otherwise in case of being negative or 0 it is
	 * defined as an array of size 1.
	 */
	public DatarrayList(int increment) {
		if (increment > 0) {
			arrData = new Object[increment];
		} else {
			arrData = new Object[1];
		}
		currIncrement = arrData.length;
	}

	/*
	 * Method to add an element to array. Checks two things- whether element
	 * being added is null or not, and checks if specified position to add
	 * exceeds current length of array. If element is null (case 1), false is
	 * returned with no effect to the array and if no more space is left to add
	 * (case 2), the array is expanded in length using the addSpace method. If
	 * element is successfully added, true is returned.
	 */
	public boolean add(Object newElement) {
		boolean flag = false;

		if (currElement >= arrData.length) {
			addSpace();
		}

		if (newElement != null) {
			arrData[currElement++] = newElement;
			flag = true;
		}
		return flag;
	}

	/*
	 * Returns number of elements in the array (Note: NOT length of array)
	 */
	public int getSize() {
		return currElement;
	}

	/*
	 * Returns length of array, even if values are null
	 */
	public int getCapacity() {
		return arrData.length;
	}

	/*
	 * Retrieves object from array at a specified int position. Returns null if
	 * object does not exist at specified position.
	 */
	public Object get(int pos) {
		Object retVal = null;
		if (pos <= arrData.length) {
			retVal = arrData[pos];
		}
		return retVal;
	}

	/*
	 * Manually created method to add space to a pre-existing array. This is
	 * done by creating a temporary duplicate of the array, having the intended
	 * greater size. This temporary array is then assigned to the main array
	 * (arrData) to change the object it refers to to the larger length array.
	 */
	private void addSpace() {
		int counter = 0;

		Object[] arrTemp = new Object[currIncrement * temp++];
		for (Object element : arrData) {
			arrTemp[counter++] = element;
		}
		arrData = arrTemp;
	}
}
