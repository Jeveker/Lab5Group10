package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    
    public void savePetsToJson() {
    	
        List<ExportablePet> exportableList = new ArrayList<>();

        for (Pet pet : shelter.getAllPets()) {
        	
            exportableList.add(new ExportablePet(
            		
                pet.getName(),
                pet.getAge(),
                pet.getSpecies(),
                pet.isAdopted()
                
            ));
            
        }

        // Using YYYYMMDD HHMMSS format for exported file name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        
        String filename = timestamp + "_pets.json";

        try (FileWriter writer = new FileWriter(filename)) {
        	
            Gson gson = new Gson();
            
            gson.toJson(exportableList, writer);
            
            System.out.println("Export Successful! Exported Filename: " + filename);
            
        } 
        
        catch (IOException e) {
        	
            System.err.println("An unexpected error occured while saving pet information to a file: " + e.getMessage());
        
        }
    }
    
    /**
     * Represents a pet in format for saving to JSON file
     */
    private static class ExportablePet {
    	
        String name;
        int age;
        String species;
        boolean adopted;

        public ExportablePet(String name, int age, String species, boolean adopted) {
        	
            this.name = name;
            this.age = age;
            this.species = species;
            this.adopted = adopted;
            
        }
    }
    
    /**
     * Attempts to adopt a pet by name
     * @param name name of the pet to adopt
     * @return success message or error
     */
    public String adoptPetByName(String name) {
    	
        for (Pet pet : shelter.getAllPets()) {
        	
            if (pet.getName().equalsIgnoreCase(name)) {
            	
                if (pet.isAdopted()) {
                	
                    return name + " has already been adopted.";
                    
                    
                } 
                else {
                    pet.setAdopted(true);
                    return name + " has been adopted! Enjoy your new pet!";
                    
                }
            }
        }
        
        return "No pet found with the name: " + name;
    }

    /**
     * Adds a new pet to the shelter
     * @param type Dog, Cat, or Rabbit
     * @param name pet's name
     * @param age pet's age
     * @return success message or error
     */
    public String addPet(String type, String name, int age) {
    	
        Pet newPet = null;
        
        switch (type) {
        
            case "Dog":
                newPet = new Dog(name, age, false);
                break;
                
            case "Cat":
                newPet = new Cat(name, age, false);
                break;
                
            case "Rabbit":
                newPet = new Rabbit(name, age, false);
                break;
                
            default:
                return "Unexpected pet type.";
                
        }

        shelter.addPet(newPet);
        
        return type + " named " + name + " has been added.";
        
    }

    /**
     * Removes a pet from the shelter by name
     * @param name name of the pet to remove
     * @return success message or error
     */
    public String removePetByName(String name) {
    	
        for (Pet pet : shelter.getAllPets()) {
        	
            if (pet.getName().equalsIgnoreCase(name)) {
            	
                shelter.removePet(pet);
                return name + " has been removed from the shelter.";
                
            }
        }
        
        return "No pet found with the name: " + name;
    }


}
