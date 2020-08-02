package com.esmt.contactapp.controler;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.esmt.contactapp.model.Contact;
import com.esmt.contactapp.model.ContactDaoImpl;
import com.esmt.contactapp.model.ContactManagerException;
import com.esmt.contactapp.model.DAOException;

import com.esmt.contactapp.model.IContactDao;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory; 
import javafx.stage.Stage;


	public class ContactCtl implements Initializable{

		private ObservableList<Contact> data = FXCollections.observableArrayList();
		//private ObservableList<Contact> dataList = FXCollections.observableArrayList();
		
		Contact contact ;
		private static int id_contact ;
		
		

	    @FXML
	    private Button updateBtn;

	    @FXML
	    private Button exitBtn;

	    @FXML
	    private Button searchBtn;
	    @FXML
	    private TextField champNom;
	    
	    @FXML
	    private TextField champPrenom;
	    
	    @FXML
	    private TextField champPhone;

	    @FXML
	    private TextField champMail;
	    @FXML
	    private TextField champId;

	   

	    @FXML
	    private Button addBtn;

	    @FXML
	    private Button deleteBtn;

	    @FXML
	    private Button resetBtn;
	    
	    @FXML
	    private TextField combo;
	    
	    @FXML
	    private TextField searchBar;
	    
		@FXML
	    private TableView<Contact> tableView;

	   
	    @FXML
	    private TableColumn<Contact, Integer> idContact;

	    @FXML
	    private TableColumn<Contact, String> nomContact;
	    
	    @FXML
	    private TableColumn<Contact, String> prenomContact;

	    @FXML
	    private TableColumn<Contact, String> phoneContact;

	    @FXML
	    private TableColumn<Contact, String> mailContact;

	    
	    

	    @FXML
	    void ajouterContact(ActionEvent event) throws DAOException {
	    	String lastName = champNom.getText();
	    	String firstName =  champPrenom.getText();
	    	String phone = champPhone.getText();
	    	String mail = champMail.getText();
	    	Contact contact = new Contact(lastName,firstName,phone,mail);
	    	IContactDao<Contact> icd = new ContactDaoImpl();
	    	icd.create(contact);
	    	liste();
	    	
	    	champNom.setText("");	
	    	champPrenom.setText("");
	    	champPhone.setText("");
	    	champMail.setText("");

	    }

	    @FXML
	    void annulerSaisie(ActionEvent event) {
	    	
	    	champNom.setText("");	
	    	champPrenom.setText("");
	    	champPhone.setText("");
	    	champMail.setText("");

	    }
	    
	   

	    
		@FXML
	    void supprimerContact(ActionEvent event) throws Exception {
			
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Confirmation");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Voulez-vous supprimer ce contact ?");
	    	ButtonType buttonTypeOui = new ButtonType("Oui");
	    	ButtonType buttonTypeCancel = new ButtonType("Non", ButtonData.CANCEL_CLOSE);

	    	alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeCancel);
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == buttonTypeOui){
	    		
	    		IContactDao<Contact> dao = new ContactDaoImpl() ;
				int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
				int selectedItem = idContact.getCellData(selectedIndex);
				if(selectedIndex >= 0){
				tableView.getItems().remove(selectedIndex);
				dao.delete(selectedItem);
				
				}
	    	    
	    	} else if (result.get() == buttonTypeCancel) {
	    	    
	    	}
			
				
	    	
	    }
		
	    @FXML
	    void modifierContact(ActionEvent event) throws DAOException, ContactManagerException {
	    	int id = id_contact ;
	    	String lastName = champNom.getText();
	    	String firstName =  champPrenom.getText();
	    	String phone = champPhone.getText();
	    	String mail = champMail.getText();
	    	Contact contact = new Contact(lastName,firstName,phone,mail);
	    	contact.setId(id);
	    	IContactDao<Contact> icd = new ContactDaoImpl();
	    	icd.update(contact);
	    	//contact.setId(Integer.parseInt(idContact.getText()));
	    	methode();
	    	
	    }

	    private void methode() throws DAOException {
	    	IContactDao<Contact> dao = new ContactDaoImpl();
	    	ObservableList<Contact> attribute = FXCollections.observableArrayList();
			dao.liste().stream().forEach(c->attribute.add(c));
			tableView.setItems(attribute);
		}

		@FXML
	    void quitterAppli(ActionEvent event) {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Confirmation");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Voulez-vous quitter l'application ?");
	    	ButtonType buttonTypeOui = new ButtonType("Oui");
	    	ButtonType buttonTypeCancel = new ButtonType("Non", ButtonData.CANCEL_CLOSE);

	    	alert.getButtonTypes().setAll(buttonTypeOui, buttonTypeCancel);
	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == buttonTypeOui){
	    		
	    	    Stage stage = (Stage) exitBtn.getScene().getWindow();
	    	    stage.close();
	    	    System.exit(0);
	    	} else if (result.get() == buttonTypeCancel) {
	    	    
	    	}

	    }
	    
	   
	    

	    @FXML
	    
	    
	    void chercherContact(ActionEvent event) throws DAOException {
	    	recherche();
	    	FilteredList<Contact> filterContact = new FilteredList<>(data, e-> true);
	    	searchBar.setOnKeyReleased(e ->{
	    			searchBar.textProperty().addListener((observableValue, oldValue, newValue) ->{
	    				filterContact.setPredicate((Predicate<? super Contact>) contact->{
	    					if(newValue == null || newValue.isEmpty()) {
	    						return true;
	    					}
	    					String lowerCaseFilter = newValue.toLowerCase();
	    					if(contact.getNom().contains(newValue)) {
	    						return true;
	    					}else if(String.valueOf(contact.getId()).contains(lowerCaseFilter)) {
	    						return true;
	    					}
	    					return false;
	    				});
	    			});
	    			SortedList<Contact> sortedData = new SortedList<>(filterContact);
	    			sortedData.comparatorProperty().bind(tableView.comparatorProperty());
	    			tableView.setItems(sortedData);
	    			liste();
	    	});
			
	    }
	    
	    private void recherche() throws DAOException {
			// TODO Auto-generated method stub
	    	ContactDaoImpl dao = new ContactDaoImpl();
	    	Contact cont = dao.search(searchBar.getText());
	    	ObservableList<Contact> attribute = FXCollections.observableArrayList();
			dao.search_by_name(searchBar.getText()).stream().forEach(c->attribute.add(c));
			attribute.add(cont);
			tableView.setItems(attribute);
		}

		public void liste()  {
	    	IContactDao<Contact> idao = new ContactDaoImpl() ;
	    	ObservableList<Contact> listes = FXCollections.observableArrayList() ; 
	    	try {
				idao.liste().stream().forEach(c-> listes.add(c));
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	tableView.setItems(listes);
	    }

		@Override
		public void initialize(URL url, ResourceBundle rb) {
			idContact.setCellValueFactory(new PropertyValueFactory<>("id"));
	    	nomContact.setCellValueFactory(new PropertyValueFactory<>("nom"));
	    	prenomContact.setCellValueFactory(new PropertyValueFactory<>("prenom"));
	    	phoneContact.setCellValueFactory(new PropertyValueFactory<>("telephone"));
	    	mailContact.setCellValueFactory(new PropertyValueFactory<>("email"));
	    	liste();
	    	
	    	
	    	
	    	tableView.getSelectionModel().selectedItemProperty().addListener((c , c1 ,c2 )->{
	    		id_contact = c2.getId() ; 
	    		champId.setText( ((Integer)c2.getId()).toString());
	    		champNom.setText(c2.getNom());   
	    		champPrenom.setText(c2.getPrenom());
	    		champMail.setText(c2.getEmail());
	    		champPhone.setText(c2.getTelephone());
	    	});
		}
		}