package com.esmt.contactapp.model;

import java.util.Scanner;

public class Clavier {
	private static  Scanner scan = null;
	
	public Clavier () {}
	
	public static  void init ( ) {
		if (scan == null) {
			scan = new Scanner (System.in);
		}
	}
	//Fermeture du flux scanner 
	/*public static  void close( ) {
		if(scan != null) {
			scan.close();
		}
	}*/
	
	//Les méthodes 
	
	//lecture d'une chaine au clavier
	
	public static String lireString() {
		init();
		String chaineLue = scan.nextLine();
		//close();
		return chaineLue;
	}
	// lecture d'un entier au clavier
	
	public static int lireInt() {
		init();
		int entierLue = Integer.parseInt(lireString());
		//close();
		return entierLue;
	}
	//lecture d'un flottant au clavier
	
	public float lireFloat() {
		init();
		float floatLue = Float.parseFloat(lireString());
		//close();
		return floatLue;
	}
	//lecture d'un double au clavier
	
	public double lireDouble() {
		init();
		double doubleLue = Double.parseDouble(lireString());
		//close();
		return doubleLue;
	}
	// lecture d'un caractère au clavier
	
	public char lireChar() {
		String chaine = lireString();
		if(chaine.length()>0)
		{
			return chaine.charAt(0);
			
		}
		else return 'x';
	}
	
	public boolean lireBoolean() {
		init();
		boolean booleanLue = Boolean.parseBoolean(lireString());
		//close();
		return booleanLue;
	}
	
	

	/*public static void main(String[] args) {
		

	}*/

}
