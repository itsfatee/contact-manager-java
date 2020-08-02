package com.esmt.contactapp.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.esmt.contactapp.model.Contact;
import com.esmt.contactapp.model.ContactManagerException;


public class ContactDaoImpl implements IContactDao<Contact> {

	@Override
	public void create(Contact entity) throws DAOException {
		// TODO Auto-generated method stub
		try (Connection connexion = DBContact.getConnection()) { 
			
			PreparedStatement preparedStatement = connexion.prepareStatement("Insert into T_Contact (nom, prenom, telephone, email) values (?,?,?,?)");
			preparedStatement.setString(1, entity.getNom()); 
			preparedStatement.setString(2, entity.getPrenom()); 
			preparedStatement.setString(3, entity.getTelephone()); 
			preparedStatement.setString(4, entity.getEmail());
			preparedStatement.executeUpdate();
			
		} catch (Exception e) { 
			throw new DAOException(e.getMessage()); 
		}
	}

	//afficher la liste de tous les contact de la bd
	@Override
	public List<Contact> liste() throws DAOException {
		try (Connection connection = DBContact.getConnection()) {
			java.sql.Statement statement = connection.createStatement(); 
			String query = "Select * From T_Contact";
		ResultSet resultSet = statement.executeQuery(query);
		List<Contact> contacts = new ArrayList<>();
		while (resultSet.next()) { 
			int identifiant = resultSet.getInt("id"); 
			String nom = resultSet.getString("nom"); 
			String prenom = resultSet.getString("prenom"); 
			String telephone = resultSet.getString("telephone"); 
			String email = resultSet.getString("email");
			Contact contact = new Contact (nom, prenom, telephone, email); 
			contact.setId(identifiant); 
			contacts.add(contact);
		}

			return contacts;
		}catch(Exception e) {
			throw new DAOException(e.getMessage());
		}
	}
	
	public List<Contact> search_by_name(String le_nom) throws DAOException {
		try (Connection connection = DBContact.getConnection()) {
			java.sql.Statement statement = connection.createStatement(); 
			String query = "Select * From T_Contact where nom like '%"+le_nom+"%'";
		ResultSet resultSet = statement.executeQuery(query);
		List<Contact> contacts = new ArrayList<>();
		while (resultSet.next()) { 
			int identifiant = resultSet.getInt("id"); 
			String nom = resultSet.getString("nom"); 
			String prenom = resultSet.getString("prenom"); 
			String telephone = resultSet.getString("telephone"); 
			String email = resultSet.getString("email");
			Contact contact = new Contact (nom, prenom, telephone, email); 
			contact.setId(identifiant); 
			contacts.add(contact);
		}

			return contacts;
		}catch(Exception e) {
			throw new DAOException(e.getMessage());
		}
	}
	//Mise à jour
	@Override
	public void update(Contact entity) throws DAOException, ContactManagerException {
		try (Connection connection = DBContact.getConnection()) { 
			PreparedStatement preparedStatement = connection.prepareStatement ("Update T_Contact Set nom = ?, prenom = ?, telephone = ?, email = ? Where id = ?");

			preparedStatement.setString(1, entity.getNom()); 
			preparedStatement.setString(2, entity.getPrenom()); 
			preparedStatement.setString(3, entity.getTelephone()); 
			preparedStatement.setString(4, entity.getEmail()); 
			preparedStatement.setInt(5, entity.getId());
			preparedStatement.executeUpdate();
			
		} catch (Exception e) { 
			throw new DAOException(e.getMessage()); 
			}

		
	}

	@Override
	public void delete(int id) throws DAOException {
		try (Connection connection = DBContact.getConnection()) { 
			PreparedStatement preparedStatement = connection. prepareStatement("Delete From T_Contact where id = ?"); 
			preparedStatement.setInt(1, id); 
			preparedStatement.executeUpdate(); 
			} catch (Exception e) { 
				throw new DAOException(e.getMessage()); 
				}
		}

	@Override
	public <C> Contact  search(C critere) throws DAOException {
		// TODO Auto-generated method stub
		
		try(Connection connection = DBContact.getConnection()){
			
			if(critere instanceof Integer)
				
	{   	PreparedStatement preparedStatement = connection. prepareStatement("Select * From T_Contact where id = ?"); 
			preparedStatement.setInt(1,(int)critere);
			
			ResultSet resultSet= preparedStatement.executeQuery();
			
			if(resultSet.first())
				
		{	int identifiant= resultSet.getInt("id");
			String nom= resultSet.getString("nom");
			String prenom= resultSet.getString("prenom");
			String telephone= resultSet.getString("telephone");
			String email= resultSet.getString("email");
			
			Contact contact = new Contact (identifiant ,nom, prenom,telephone,email);
		
			return contact;
		
		}
	}
			else if(critere instanceof String)
			{	
				PreparedStatement preparedStatement = connection. prepareStatement("Select * From T_Contact where nom = ?"); 
				preparedStatement.setString(1,(String)critere);
				
				ResultSet resultSet= preparedStatement.executeQuery();
				if(resultSet.first())
					
				{	int identifiant= resultSet.getInt("id");
					String nom= resultSet.getString("nom");
					String prenom= resultSet.getString("prenom");
					String telephone= resultSet.getString("telephone");
					String email= resultSet.getString("email");
					
					Contact contact = new Contact (identifiant ,nom, prenom,telephone,email);
				
					return contact;
				
				}
			
		}
			}
		catch (Exception e) { 
		System.out.println(e.getMessage()); 
		}
		return null;
		
	}

	

	

}
