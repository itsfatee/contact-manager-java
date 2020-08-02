package com.esmt.contactapp.model;

import java.util.List;
import com.esmt.contactapp.model.ContactManagerException;

public interface IContactDao<T> {
	
	public  void create (T entity) throws DAOException; 
	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	public List<T> liste () throws DAOException; 
	/**
	 * 
	 * @param entity
	 * @throws DAOException
	 * @throws ContactManagerException
	 */
	public void update (T entity) throws DAOException, ContactManagerException;
	/**
	 * 
	 * @param id
	 * @throws DAOException
	 */
	public void delete (int id) throws DAOException;
	/**
	 * 
	 * @param <C>
	 * @param critere
	 * @return
	 * @throws DAOException
	 */
	public  <C> T search(C critere) throws DAOException;
	

}
