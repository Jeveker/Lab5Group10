package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Stores pets using generics
 * @param <T>
 */
public class Shelter<T extends Pet> {
	private List<T> pets;
	
	/**
	 * Constructor to create a new (empty) shelter.
	 */
	public Shelter() {
		this.pets = new ArrayList<>();
	}
	
	/**
	 * Adds a pet to the shelter.
	 * @param pet
	 */
	public void addPet(T pet) {
		pets.add(pet);
	}
	
	/**
	 * Removes a pet from the shelter.
	 * @param pet
	 */
	public void removePet(T pet) {
		pets.remove(pet);
	}
	
	/**
	 * Returns all pets in the shelter.
	 * @return
	 */
	public List<T> getAllPets() {
		return pets;
	}
	
	/** Pets are sorted by name using the compareTo() method in Pets
	 * 
	 */
	public void sortPetsByName() {
		Collections.sort(pets);
	}
	
	/**
	 * Sorts pets using a given comparator.
	 * @param comparator
	 */
	public void sortPetsBy(Comparator<T> comparator) {
		pets.sort(comparator);
	}
	
	/**
	 * If a pet is not yet adopted, this method sets the given pet to adopted.
	 * @param pet
	 */
	public void adoptPet(T pet) {
		if (pet.isAdopted()) {
			throw new IllegalStateException(pet.getName() + "cannot be adopted!");
		}
		pet.setAdopted(true);
	}
}
