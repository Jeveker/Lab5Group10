package model;

public abstract class Pet implements Comparable<Pet>{
	private String name;
	private int age;
	private String species;
	private boolean adopted;
	
	/**
	 * Constructor for Pet with specified attributes.
	 * @param name 
	 * @param age
	 * @param species
	 * @param adopted
	 */
	public Pet(String name, int age, String species, boolean adopted) {
		super();
		this.name = name;
		this.age = age;
		this.species = species;
		this.adopted = adopted;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public boolean isAdopted() {
		return adopted;
	}

	public void setAdopted(boolean adopted) {
		this.adopted = adopted;
	}

	/**
	 * Compares this pet to another based on their names, ignoring case.
	 * @param other The other pet to compare to
	 * @return Allows for names to be sorted alphabetically.
	 */
	@Override
	public int compareTo(Pet other) {
	    return this.name.compareToIgnoreCase(other.name);
	}

	/**
	 * Generates a string representation of the Pet object.
	 */
	@Override
	public String toString() {
		return "Pet [name=" + name + ", age=" + age + ", species=" + species + ", adopted=" + adopted + "]";
	}
	
	
	
	
	
}
