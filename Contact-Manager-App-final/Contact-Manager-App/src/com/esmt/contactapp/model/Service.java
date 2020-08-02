package com.esmt.contactapp.model;

import com.esmt.contactapp.model.ContactDaoImpl;
import com.esmt.contactapp.model.DAOException;
import com.esmt.contactapp.model.Contact;
import com.esmt.contactapp.model.Contacts;
import com.esmt.contactapp.model.ContactManagerException;
import com.esmt.contactapp.model.Clavier;



public class Service implements IService  {
	
	

	@Override
	public void creerNouvelleListe() throws ContactManagerException {
		
		System.out.println("\n- DEBUT - CREATION NOUVELLE LISTE - ");
		String fileName = getDataSource();
		//--------------
		if (!fileName.equals("default")) {
			ContactManager.setFileName(fileName);
		}
		//----------------------
		ContactManager.serialiseContacts(new Contacts());
		//----------------------
		System.out.println("\n- FIN - CREATION NOUVELLE LISTE - ");
	}

	@Override
	public void ajouterUnContact() throws ContactManagerException, DAOException {
		
		System.out.println("\n- DEBUT - AJOUT DE CONTACT - ");
		int choix = getTypeEnregistrement();
		//----------------------
		if (choix==1) {
		//-------------	
		String fileName = getDataSource();
		if (!fileName.equals("default")) {
			ContactManager.setFileName(fileName);
		}
		
		Contact contact = ContactManager.lireContact();
		
		ContactManager.addContact(contact);		
		System.out.println("Ajout effectuée avec succès.");
		
		System.out.println("\n- FIN - AJOUT DE CONTACT - ");
		
		}else if (choix==2) {
			try {
					
				Contact contact = ContactManager.lireContact();
				 //-----------------
				ContactDaoImpl dao= new ContactDaoImpl();
				dao.create(contact);
				//----------
				System.out.println("\n création effectué avec succès!!!");
				System.out.println("\n- FIN - CREATION DE CONTACT - ");
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
				System.out.println("veuillez choisir svp soit 1 , soit 2");
			}
				
		
	}

	private String getDataSource () {
		
		System.out.println("Entrez le nom du fichier de contact svp (tapez 'default' si vous utilisez le fichier par defaut) : ");
		return Clavier.lireString();
	}
	
	private int getTypeEnregistrement() {
		System.out.println("Tapez 1 si vous souhaitez travailler avec les fichier ou \n tapez 2 si vous souhaitez travailler avec la base de donnée\n");
		return Clavier.lireInt();
		
	}

	@Override
	public 
	
	
	void rechercherUnContactSelonSonId() throws ContactManagerException, DAOException {
		
		System.out.println("\n- DEBUT - RECHERCHER CONTACT SELON ID - ");
		int choix = getTypeEnregistrement();
		//-----------
		if (choix==1) {
			//---
		String fileName = getDataSource();
		if (!fileName.equals("default")) {
			ContactManager.setFileName(fileName);
		}
		
		System.out.println("Entrez l'id du contact que vous cherchez : ");
		Contact contact = ContactManager.searchContact(Clavier.lireInt());
		
		System.out.println(" "+contact);
		System.out.println("\n- FIN - RECHERCHER CONTACT SELON ID - ");
		}
		else if(choix==2) {
			
			ContactDaoImpl dao= new ContactDaoImpl();
			System.out.println("Entrez l'id du contact que vous cherchez : ");
			int id =Clavier.lireInt();
			System.out.println(dao.search(id));
			
			System.out.println("\n- FIN - RECHERCHER CONTACT SELON ID - ");	
			
		}
		

	}

	@Override
	public void rechercherUnContactSelonSonNom() throws ContactManagerException, DAOException {
		
		System.out.println("\n- DEBUT - RECHERCHER CONTACT SELON NOM - ");
		int choix = getTypeEnregistrement();
		//---------------------
		if (choix==1) {
		//---------------------
		String fileName = getDataSource();
		if (!fileName.equals("default")) {
			ContactManager.setFileName(fileName);
		}
		
		System.out.println("Entrez le nom du contact que vous cherchez : ");
		Contact contact = ContactManager.searchContact(Clavier.lireString());
		
		System.out.println(" "+contact);
		System.out.println("\n- FIN - RECHERCHER CONTACT SELON NOM - ");
		}else if(choix==2) {
			
			ContactDaoImpl dao= new ContactDaoImpl();
			System.out.println("Entrez le nom du contact que vous cherchez : ");
			String nom =Clavier.lireString();
			System.out.println(dao.search_by_name(nom));
			
			System.out.println("\n- FIN - RECHERCHER CONTACT SELON LE NOM - ");	
			
		}
		
		

	}

	@Override
	public void supprimerUnContact() throws ContactManagerException, DAOException {
		
		System.out.println("\n- DEBUT - SUPPRESSION CONTACT - ");
		int choix = getTypeEnregistrement();
		//--------------------------------------
		if (choix==1) {
			//-------------------------------
		String fileName = getDataSource();
		if (!fileName.equals("default")) {
			ContactManager.setFileName(fileName);
		}
		System.out.println("Entrez l'id de l'utilisateur que vous voulez supprimer : ");
		int id = Clavier.lireInt();
		
		ContactManager.deleteContact(id);
		System.out.println("Contact supprimé avec succès!");
		
		ContactManager.displayContacts();
		System.out.println("\n- FIN - SUPPRESSION CONTACT - ");
		}else if(choix==2) {
			//----
			ContactDaoImpl dao= new ContactDaoImpl();
			
			System.out.println("Entrez l'id de l'utilisateur que vous voulez supprimer : ");
			int id = Clavier.lireInt();
			
			dao.delete(id);
			
			System.out.println("Contact supprimé avec succès!");
			ContactManager.afficheDb();
			System.out.println("\n- FIN - SUPPRESSION CONTACT - ");
		}

	}

	@Override
	public void mettreAJourUnContact() throws ContactManagerException, DAOException {

		System.out.println("\n- DEBUT - MIS A JOUR D'UN CONTACT - ");
		int choix = getTypeEnregistrement();
		//------------
		if (choix==1) {
			//----------
		String fileName = getDataSource();
		if (!fileName.equals("default")) {
			
			ContactManager.setFileName(fileName);
		}
		System.out.println("Donner l'ID du contact à modifier:");
		Integer idRechercher=Clavier.lireInt();
		//---------------
		if (ContactManager.searchContact(idRechercher)!=null) {
			
			Contact exContact=ContactManager.searchContact(idRechercher);
			
			Contact newContact=new Contact(exContact.getId());
			//---------------
			newContact=ContactManager.lireContact();
			newContact.setId(exContact.getId());
			//----------------
			ContactManager.updateContact(newContact);
			System.out.println("Contact modifié avec succès !!");
			
			ContactManager.displayContacts();
			
			System.out.println("\n- FIN - DEBUT - MIS A JOUR D'UN CONTACT - \n");
		}
		
		} else if (choix==2)
		{
			System.out.println("\n- DEBUT - UPDATE DE CONTACT - ");
			
			System.out.println("Entrez l'id du contact que vous cherchez : ");
			int id =Clavier.lireInt();
			Contact contact= ContactManager.lireContact();
			contact.setId(id);
			ContactManager.updateDb(contact);
			ContactManager.afficheDb();
			System.out.println("Le contact a bien été  modifié !");
			System.out.println("\n- FIN - UPDATE DE CONTACT - ");
			
		}else {
			throw new ContactManagerException("Aucun contact pour cet ID!");
		}
		
	

	}

	@Override
	public void afficherListeDesContacts() throws ContactManagerException, DAOException {
		// TODO Auto-generated method stub
		
		System.out.println("\n- DEBUT - AFFICHER LA LISTE DE CONTACTS - ");
		int choix = getTypeEnregistrement();
		
		if (choix==1) {
		
		String fileName = getDataSource();
		if (!fileName.equals("default")) {
			ContactManager.setFileName(fileName);
		}
		
		ContactManager.displayContacts();
		System.out.println("\n- FIN - AFFICHER LA LISTE DE CONTACTS - ");
		
		}else if(choix==2) {
			System.out.println("\n- DEBUT - AFFICHER LA LISTE DE CONTACTS - ");
			ContactManager.afficheDb();
			System.out.println("\n- FIN - AFFICHER LA LISTE DE CONTACTS - ");
	
		}
	}




}