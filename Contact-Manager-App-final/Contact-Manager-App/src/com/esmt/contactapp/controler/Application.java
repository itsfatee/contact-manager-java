package com.esmt.contactapp.controler;

import com.esmt.contactapp.model.DAOException;
import com.esmt.contactapp.model.Fonctionnalites;
import com.esmt.contactapp.model.ContactManagerException;
import com.esmt.contactapp.model.Clavier;
import com.esmt.contactapp.model.IService;
import com.esmt.contactapp.model.Service;

public class Application {

	public static void main(String[] args){
		Application application = new Application();
		try {
			application.run();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run () throws DAOException {
		
		Fonctionnalites fonctionnaliteChoisie = null;
		IService service = new Service ();		
		
		do {
			try {
				displayMenu();
				fonctionnaliteChoisie = getFonctionnalite ();
				
				switch (fonctionnaliteChoisie) {
					case ADD:
						service.ajouterUnContact();
						break;
						
					case CREATE:
						service.creerNouvelleListe();
						break;
						
					case DELETE:
						service.supprimerUnContact();
						break;
						
					case DISPLAY:
						service.afficherListeDesContacts();
						break;
						
					case READBYID:
						service.rechercherUnContactSelonSonId();
						break;
						
					case READBYNAME:
						service.rechercherUnContactSelonSonNom();
						break;
						
					case UPDATE:
						service.mettreAJourUnContact();
						break;
						
					case EXIT:
						System.out.println("Merci de votre visite ! Aurevoir !");
				}
			} catch (ContactManagerException e) {
				System.err.println("Error : " + e.getMessage());
			}
		} while (fonctionnaliteChoisie != Fonctionnalites.EXIT);
		
	}
	
	/**
	 * @return
	 * @throws ContactManagerException
	 */
	private Fonctionnalites getFonctionnalite() throws ContactManagerException {
		Fonctionnalites [] fonctionnalites = Fonctionnalites.values();
		int selection = Clavier.lireInt ();
		
		if (selection <= 0 || selection > fonctionnalites.length) {
			throw new ContactManagerException("La selection n'est pas correcte.");
		}
		
		return fonctionnalites[selection - 1];	
	}

	/**
	 * 
	 */
	public void displayMenu () {
		Fonctionnalites [] fonctionnalites = Fonctionnalites.values();
		for (int i = 0 ; i < fonctionnalites.length ; i ++) {
			System.out.println( (i + 1) + ">> " + fonctionnalites[i]);
		}
		
		System.out.print(">_ ");
	}
}