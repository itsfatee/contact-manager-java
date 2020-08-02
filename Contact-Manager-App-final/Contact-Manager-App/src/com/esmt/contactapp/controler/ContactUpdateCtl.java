package com.esmt.contactapp.controler;



import com.esmt.contactapp.model.Contact;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ContactUpdateCtl {
		private Main mainapp;
		private Contact contact;
		private Stage editDialogStage;
	    private boolean okClicked = false;
	

	    @FXML
	    private TextField champPrenom;

	    @FXML
	    private TextField champId;

	    @FXML
	    private Button cancelBtn;

	    @FXML
	    private TextField champMail;

	    @FXML
	    private TextField champNom;

	    @FXML
	    private TextField champPhone;

	    @FXML
	    private Button okBtn;
	    
	    public void setUpdateDialogStage(Stage dialogStage) {
	        this.editDialogStage = dialogStage;
	    }
	    
	    public void setContact(Contact contact) {
	        this.contact = contact;
	        //on copie les elements du contatc selectionne dans le pannel edition
	        champId.setText(String.valueOf(contact.getId()));
	        champNom.setText(contact.getNom());
	        champPrenom.setText(contact.getPrenom());
	        champPhone.setText(contact.getTelephone());
	        champMail.setText(contact.getEmail());
	    }
	    
	    public boolean isOkClicked() {
	        return okClicked;
	    }
	    
	    
	    @FXML
	    void modifier(ActionEvent event) {
	    	   contact.setNom(champNom.getText());
	           contact.setPrenom(champPrenom.getText());
	           contact.setTelephone(champPhone.getText());
	           contact.setEmail(champMail.getText());
	           
	           okClicked = true;
	           editDialogStage.close();

	    }
	    @FXML
	    void annulerModif(ActionEvent event) {
	    	editDialogStage.close();
	    }
	    
	    public Main getMainapp() {
			return mainapp;
		}
		
		public void setMainapp(Main mainapp) {
			this.mainapp = mainapp;
		}



	

}
