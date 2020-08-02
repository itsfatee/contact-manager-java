package com.esmt.contactapp.model;

import com.esmt.contactapp.model.DAOException;
import com.esmt.contactapp.model.ContactManagerException;

public interface IService {
	
	public void creerNouvelleListe() throws ContactManagerException;
	public void ajouterUnContact() throws ContactManagerException, DAOException;
	public void afficherListeDesContacts() throws ContactManagerException, DAOException;
	public void rechercherUnContactSelonSonId() throws ContactManagerException, DAOException;
	public void rechercherUnContactSelonSonNom() throws ContactManagerException, DAOException;
	public void supprimerUnContact() throws ContactManagerException, DAOException;
	public void mettreAJourUnContact() throws ContactManagerException, DAOException;

}
