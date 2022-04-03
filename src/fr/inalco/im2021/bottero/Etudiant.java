package fr.inalco.im2021.bottero;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Etudiant {
	List<Etudiant> etudiant;
	NIVEAU niv;
	LANGUE lang;
	String nom_etudiant;
	

		
		
	public String input_nom() throws IOException {
		System.out.println("Veuillez renseigner votre nom : ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nom_etudiant = br.readLine();
		return nom_etudiant;
	}
	
	
	public Enum niveau_debutant() {
		NIVEAU niv = NIVEAU.DEBUTANT;
		return niv;
	}
	
	
	public String get_nom() {
		return "Nom de l'étudiant : " + nom_etudiant;
	}
	
	
	public Enum<LANGUE> get_lg() {
		System.out.println("Langue de l'étudiant : ");
		return lang;
	}
	
	
	public Enum<NIVEAU> get_difficulty() {
		System.out.println("Niveau de l'étudiant : ");
		return niv;
	}	

	public static String choix_langue() throws IOException {
			
		
		System.out.println("Veuillez sélectionner une langue parmi cette liste :");
		System.out.println("Langue 1 : " + LANGUE.FR);
		System.out.println("Langue 2 : " + LANGUE.EN);
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String choix_langue = br.readLine();
		
		List<String> exo = null;
		
		if ((choix_langue == "FR") || (choix_langue == "EN")) {
			System.out.println("");
		}else {
			System.out.println(br);
		} return choix_langue;
	}
}


