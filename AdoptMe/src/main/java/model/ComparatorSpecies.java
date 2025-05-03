package model;

import java.util.Comparator;

public class ComparatorSpecies implements Comparator<Pet> {

		/** Returns a negative value if the first pet's species come's before
		 * the second pet's species, 0 if equal, and a positive value if
		 * the first pet's species comes after the second pet's species.
		 * Helps to sort Pets by species from A to Z.
		 */
		@Override
		public int compare(Pet o1, Pet o2) {
			return o1.getSpecies().compareToIgnoreCase(o2.getSpecies());
		}

	}

