package model;

/**
 * Adapter class to make ExoticAnimal compatible with simpler Pet.java structure
 */

public class ExoticPetAdapter extends Pet {

    private ExoticAnimal exoticAnimal;

    
    public ExoticPetAdapter(ExoticAnimal exoticAnimal) {
    	
        super(exoticAnimal.getAnimalName(), exoticAnimal.getYearsOld(), exoticAnimal.getCategory(), false);
        this.exoticAnimal = exoticAnimal;
        
    }

    
    public String getSubSpecies() {
    	
        return exoticAnimal.getSubSpecies();
        
    }

    
    public String getUniqueId() {
    	
        return exoticAnimal.getUniqueId();
        
    }

}
