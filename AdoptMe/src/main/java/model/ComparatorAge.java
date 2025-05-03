package model;

import java.util.Comparator;

public class ComparatorAge implements Comparator<Pet> {

	/**
	 * Returns negative, 0, or positive value if first Pet is younger than, same age as,
	 * or older than the second Pet respectfully. Helps to sort Pets in ascending order.
	 */
	@Override
	public int compare(Pet o1, Pet o2) {
		return Integer.compare(o1.getAge(), o2.getAge());
	}

}
