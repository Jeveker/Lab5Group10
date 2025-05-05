package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Handles loading and managing ALL pets for the shelter (regular and exotic)
 */
public class PetController {

    private Shelter<Pet> shelter;

    /**
     * Constructor initializing the shelter and loads pets from both JSON sources
     */
    
    public PetController() {
    	
        this.shelter = new Shelter<>();
        
        loadRegularPets();
        loadExoticPets();
        
    }

    /**
     * Loads pets from directly from pets.json (already formatted) and adds them to the shelter
     */
    
    private void loadRegularPets() {
    	
        try (
        		
            InputStream input = getClass().getClassLoader().getResourceAsStream("pets.json");
            InputStreamReader reader = new InputStreamReader(input)
        ) 
        
        {
            if (input == null) {
            	
                throw new IOException("pets.json not found in /src/main/resources/ directory.");
                
            }

            
            Gson gson = new Gson();
            Type listType = new TypeToken<List<PetJson>>(){}.getType();
            List<PetJson> rawPets = gson.fromJson(reader, listType);
            

            for (PetJson p : rawPets) {
            	
                Pet pet = null;
                
                switch (p.type) {
                
                    case "Dog":
                        pet = new Dog(p.name, p.age, p.adopted);
                        break;
                        
                    case "Cat":
                        pet = new Cat(p.name, p.age, p.adopted);
                        break;
                        
                    case "Rabbit":
                        pet = new Rabbit(p.name, p.age, p.adopted);
                        break;
                    
                    //Note: This line should never have to be used, it is a fail-safe. Error checking for unknown/different pet types already occurs elsewhere.
                    default:
                        System.out.println("An unexpected pet type was encountered: " + p.type);
                        break;
                        
                }
                
                
                if (pet != null) {
                	
                    shelter.addPet(pet);
                    
                }
            
            }

        }
        
        catch (IOException e) {
            System.err.println("An error occurred while trying to read the pets.json file: " + e.getMessage());
        }
        
    }
    

    /**
     * Loads exotic pets using the ExoticAnimalImporter class (creates adapted/formatted version of exotic pets that mirrors standard pets.json format)
     */
    
    private void loadExoticPets() {
    	
        List<Pet> exoticPets = ExoticAnimalImporter.importExoticPets();
        
        for (Pet pet : exoticPets) {
        	
            shelter.addPet(pet);
            
        }
    }

    /**
     * Returns the shelter instance with all uniform loaded pets
     * @return shelter instance
     */
    public Shelter<Pet> getShelter() {
    	
        return shelter;
        
    }

    
    /**
     * Helper class to match pets.json structure
     */
    
    private static class PetJson {
    	
        int id;
        String name;
        String type;
        String species;
        int age;
        boolean adopted;
        
    }
}
