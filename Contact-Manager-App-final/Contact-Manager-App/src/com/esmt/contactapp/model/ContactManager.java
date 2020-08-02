package com.esmt.contactapp.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.esmt.contactapp.model.ContactDaoImpl;
import com.esmt.contactapp.model.DAOException;
import com.esmt.contactapp.model.Contact;
import com.esmt.contactapp.model.Contacts;
import com.esmt.contactapp.model.ContactManagerException;





public class ContactManager {
	
	private static String fileName = "contacts.ser";
	public static ContactDaoImpl dao = new ContactDaoImpl();
	
	private ContactManager () {}
	
	/**
	 * @param contact
	 * @throws ContactManagerException 
	 */
	public static void addContact (Contact contact) throws ContactManagerException {
		// Deserialisation ...
		Contacts contacts = deserialiseContacts();
		
		// Ajout ...
		contacts.add(contact);
		
		// Serialisation ...
		serialiseContacts(contacts);
	}
	
	/**
	 * @param contacts
	 * @param contactFileName
	 * @throws ContactManagerException 
	 */
	public static void serialiseContacts (Contacts contacts, String contactFileName) throws ContactManagerException {
		try (FileOutputStream fos = new FileOutputStream(contactFileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			
			oos.writeObject(contacts);
			
		} catch (IOException  e) {
			throw new ContactManagerException(e.getMessage());
		}
	}
	
	/**
	 * @param contacts
	 * @throws ContactManagerException 
	 */
	public static void serialiseContacts (Contacts contacts) throws ContactManagerException {
		serialiseContacts(contacts, getFileName());
	}
	
	/**
	 * @param critere
	 * @return
	 * @throws ContactManagerException 
	 */
	public static <T> Contact searchContact (T critere) throws ContactManagerException {
		Contacts contacts = deserialiseContacts();
		
		if (critere instanceof Integer) {
			return contacts.find((int) critere);
		} else if (critere instanceof String) {
			return contacts.find((String) critere);
		}
		
		return null;
	}
			
	/**
	 * @param contactFileName
	 * @return
	 * @throws ContactManagerException 
	 */
	public static Contacts deserialiseContacts (String contactFileName) throws ContactManagerException {
		try (FileInputStream fis = new FileInputStream(contactFileName);
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			
			return (Contacts) ois.readObject();
			
		} catch (IOException | ClassNotFoundException  e) {
			throw new ContactManagerException(e.getMessage());
		}
	}
	
	/**
	 * @return
	 * @throws ContactManagerException 
	 */
	public static Contacts deserialiseContacts () throws ContactManagerException {
		return deserialiseContacts(getFileName());
	}
	
	/**
	 * @param contact
	 * @throws ContactManagerException 
	 */
	public static void updateContact (Contact contact) throws ContactManagerException {
		// Deserialisation ...
		Contacts contacts = deserialiseContacts();
		
		if (null == contacts.find(contact.getId()))
			throw new ContactManagerException("Le contact avec l'id " + contact.getId() + " n'existe pas.");
		
		// Mise � jour ...
		contacts.update(contact);		
		
		// Serialisation ...
		serialiseContacts(contacts);
	}

	/**
	 * @return 
	 * @return 
	 * @throws ContactManagerException 
	 */
	public static  Contact deleteContact (int identifiant) throws ContactManagerException {
		// Deserialisation ...
		Contacts contacts = deserialiseContacts();
		
		if (null == contacts.find(identifiant))
			throw new ContactManagerException("Le contact avec l'id " + identifiant + " n'existe pas.");
		
		// Suppression ...
		contacts.delete(identifiant);
		
		// Serialisation ...
		serialiseContacts(contacts);
		return null;
	}
	
	public static Contact lireContact () throws ContactManagerException {
		System.out.print("Nom : ");
		String nom = Clavier.lireString();
		
		System.out.print("Pr�nom : ");
		String prenom = Clavier.lireString();
		
		System.out.print("Telephone : ");
		String telephone = Clavier.lireString();
		
		System.out.print("Email : ");
		String email = Clavier.lireString();

		Contact contact = new Contact (nom, prenom, telephone, email);
		
		// Id - Unique.
		contact.setId(Contacts.getUniqueId());
		
		return contact;
	}
	
	public static void displayContacts () throws ContactManagerException {
		Contacts contacts = deserialiseContacts();
		
		if(contacts.getListe().isEmpty()) {
			System.out.println("Aucun contact enregistr�.");
		} else {
			System.out.println(contacts);
		}
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		ContactManager.fileName = fileName;
	}
	
	
	
	
	//-----------------------DB--------------------
	
	
	
	public static List<Contact> listDb() throws DAOException {
		return dao.liste();
	}
	
	public static void afficheDb() throws ContactManagerException, DAOException {
		List<Contact> contacts = dao.liste();
		System.out.println("La liste de contacts : "+contacts);
		
		
	}
	
	public static void updateDb (Contact contact) throws ContactManagerException, DAOException {
		dao.update(contact);
	}

	public static <T> Contact searchDb (T critere) throws DAOException {
		// TODO Auto-generated method stub
		return (Contact) dao.search(critere);
	}
	
}
