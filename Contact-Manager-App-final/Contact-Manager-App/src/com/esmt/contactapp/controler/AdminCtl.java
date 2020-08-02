package com.esmt.contactapp.controler;

import java.io.IOException;

import com.esmt.contactapp.model.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class AdminCtl {
	
	    @FXML
	    private TextField champLogin;

	    @FXML
	    private PasswordField champPwd;

	    @FXML
	    private Button ConnexionBtn;

	    @FXML
	    void seConnecter(ActionEvent event) throws IOException {
	    	
	    	if(champLogin.getText().equals("admin") && champPwd.getText().equals("admin"))
	    	{
	    		
	    		    Utils.load(event, "/com/esmt/contactapp/view/ContactView.fxml");
	  ;
	    		   

	    	}else {
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Erreur");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Login ou Password incorrects veuillez réessayer !!");

	    		alert.showAndWait();
	    		
	    	}
	    	

	    }
	    ;

	}

	


