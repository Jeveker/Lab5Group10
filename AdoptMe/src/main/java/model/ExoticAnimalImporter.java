package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads and converts exotic animals from  'exotic_animals.json' file in the resources directory into adopt-able Pet objects
 */

public class ExoticAnimalImporter {

    /**
     * Loads exotic animals from exotic_animals.json in resources and converts them into Pet objects using an adapter
     * @return List of Pet objects adapted from exotic animals
     */
	
    public static List<Pet> importExoticPets() {
        List<Pet> adaptedPets = new ArrayList<>();

        
        try (
        		
            InputStream input = ExoticAnimalImporter.class.getClassLoader().getResourceAsStream("exotic_animals.json");
            InputStreamReader reader = new InputStreamReader(input)
            		
        ) 
        
        
        {
            if (input == null) {
            	
                throw new IOException("exotic_animals.json not found in 'src/main/resources/' directory of Maven project.");
            
            }

            Gson gson = new Gson();
            
            Type listType = new TypeToken<List<ExoticAnimal>>(){}.getType();
            
            List<ExoticAnimal> exoticAnimals = gson.fromJson(reader, listType);

            
            for (ExoticAnimal exotic : exoticAnimals) {
            	
                adaptedPets.add(new ExoticPetAdapter(exotic));
                
            }

            
        } 
        
        catch (IOException e) {
            System.err.println("Failed to load exotic animals: " + e.getMessage());
        }

        return adaptedPets;
    }
}
