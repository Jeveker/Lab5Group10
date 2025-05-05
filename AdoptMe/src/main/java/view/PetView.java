package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Pet;

public class PetView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JComboBox<String> sortComboBox;
	private JButton addPetButton;
	private JButton adoptPetButton;
	private JButton removePetButton;
	private JButton viewDetailsButton;
	private JButton saveButton;
	private JList<Pet> petJList;
	private DefaultListModel<Pet> petListModel;
	
	//constructor for PetView GUI
	public PetView() {
		setTitle("Pet Shelter");
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		sortComboBox = new JComboBox<>(new String[] {"Name", "Age", "Species"});
		addPetButton = new JButton("Add Pet");
		adoptPetButton = new JButton("Adopt Pet");
		removePetButton = new JButton("Remove Pet");
		viewDetailsButton = new JButton("View Details");
		saveButton = new JButton("Save Pet");
		
		panel.add(new JLabel("Sort by:"));
		panel.add(sortComboBox);
		panel.add(addPetButton);
		panel.add(adoptPetButton);
		panel.add(removePetButton);
		panel.add(viewDetailsButton);
		panel.add(saveButton);
		
	
		petListModel = new DefaultListModel<>();
		petJList = new JList<>(petListModel);
		JScrollPane scrollPane = new JScrollPane(petJList);
		add(panel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	//GETTERS AND SETTERS
	public JComboBox<String> getSortComboBox() {
		return sortComboBox;
	}

	public JButton getAddPetButton() {
		return addPetButton;
	}

	public JButton getAdoptPetButton() {
		return adoptPetButton;
	}

	public JList<Pet> getPetJList() {
		return petJList;
	}

	public JButton getRemovePetButton() {
		return removePetButton;
	}

	public void setRemovePetButton(JButton removePetButton) {
		this.removePetButton = removePetButton;
	}

	public JButton getViewDetailsButton() {
		return viewDetailsButton;
	}

	public void setViewDetailsButton(JButton viewDetailsButton) {
		this.viewDetailsButton = viewDetailsButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}
		

	/**
	 * Allows for direct manipulation of the list model
	 * @return
	 */
	public DefaultListModel<Pet> getPetListModel() {
		return petListModel;
	}
		
	//updates the pet list
		public void updatePetList(List<Pet> pets) {
			petListModel.clear();
			for(int i = 0; i < pets.size(); i++) {
				petListModel.addElement(pets.get(i));
			}
		}
	
}
