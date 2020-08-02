package com.esmt.contactapp.controler;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.esmt.contactapp.controler.Application;

import com.esmt.contactapp.model.DAOException;
import com.esmt.contactapp.model.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class ChoixCtrl implements Initializable {
	
		    @FXML
	    private TextField choix;
		    @FXML
		    private Button ok;

		    @FXML
		    void entreer(ActionEvent event) throws IOException, DAOException {

		    	
		    	if(choix.getText().equals("1"))
		    	{
		    	
		    		Application console= new Application();
		    		console.run();
		    		
		    	}
		    	else if (choix.getText().equals("2"))
		    	{
		    		Utils.load(event,"/com/esmt/contactapp/view/AdminRegistration.fxml");
		  	    		
		    	}
		    	  
		    }
	 

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}


}
