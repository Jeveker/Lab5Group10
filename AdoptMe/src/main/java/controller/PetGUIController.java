package controller;

import model.*;
import view.PetView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

/**
 * Connects the PetView with the PetController logic.
 */
public class PetGUIController {

    private PetView view;
    
    private PetController controller;

    public PetGUIController() {
    	
        view = new PetView();
        controller = new PetController();
        initListeners();
        view.setVisible(true);
        updatePetList();
        
    }

    private void initListeners() {
    	
        view.getSortComboBox().addActionListener(e -> {
        	
            String selected = (String) view.getSortComboBox().getSelectedItem();
            
            if (selected.equals("Name")) {
            	
                controller.getShelter().sortPetsByName();
                
            } else if (selected.equals("Age")) {
            	
                controller.getShelter().sortPetsBy(new ComparatorAge());
                
            } else if (selected.equals("Species")) {
            	
                controller.getShelter().sortPetsBy(new ComparatorSpecies());
            }
            
            updatePetList();
        });

        
        view.getAddPetButton().addActionListener(e -> showAddPetDialog());

        
        view.getAdoptPetButton().addActionListener(e -> {
        	
            Pet selected = view.getPetJList().getSelectedValue();
            
            if (selected == null) {
                showMessage("Please select a pet to adopt.");
                return;
            }
            
            String result = controller.adoptPetByName(selected.getName());
            showMessage(result);
            updatePetList();
            
        });

        view.getRemovePetButton().addActionListener(e -> {
        	
            Pet selected = view.getPetJList().getSelectedValue();
            
            if (selected == null) {
                showMessage("Please select a pet to remove.");
                return;
            }
            
            String result = controller.removePetByName(selected.getName());
            showMessage(result);
            updatePetList();
            
        });

        view.getViewDetailsButton().addActionListener(e -> {
        	
            Pet selected = view.getPetJList().getSelectedValue();
            
            if (selected == null) {
                showMessage("Please select a pet to view.");
                return;
            }
            
            String details = String.format("Name: %s\nAge: %d\nSpecies: %s\nAdopted: %s",
                    selected.getName(), selected.getAge(), selected.getSpecies(),
                    selected.isAdopted() ? "Yes" : "No");
            
            showMessage(details);
            
        });

        
        view.getSaveButton().addActionListener(e -> {
            controller.savePetsToJson();
            showMessage("Pet list saved to JSON.");
            
        });
        
    }
    

    private void showAddPetDialog() {
        JTextField nameField = new JTextField();
        
        JTextField ageField = new JTextField();
        
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Dog", "Cat", "Rabbit"});

        JPanel panel = new JPanel(new java.awt.GridLayout(0, 1));
        
        panel.add(new JLabel("Pet Type:"));
        panel.add(typeBox);
        
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        
        panel.add(new JLabel("Age:"));
        panel.add(ageField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Pet", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
            	
                String type = (String) typeBox.getSelectedItem();
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());

                String response = controller.addPet(type, name, age);
                showMessage(response);
                updatePetList();
                
            } 
            catch (NumberFormatException ex) {
                showMessage("Please enter a valid number for age.");
            }
            
        }
    }

    private void updatePetList() {
    	
        List<Pet> pets = controller.getShelter().getAllPets();
        view.updatePetList(pets);
        
    }

    private void showMessage(String msg) {
    	
        JOptionPane.showMessageDialog(view, msg);
        
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PetGUIController::new);
    }
}
