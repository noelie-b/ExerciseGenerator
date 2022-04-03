package fr.inalco.im2021.bottero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercice extends BanqueExercice{
	
	// Prend en entrée un fichier txt
	// Sépare le fichier en strings
	BufferedReader lecteurAvecBuffer = null;
	String phrase;
	File fichiertxt;
	
	/**
	 * 
	 * @param List<Phrase> phrases issue de l'analyse d'un fichier txt. 
	 * 
	 */
	public Exercice(String args) {

	}
	
	/**
	 * La fonction lecture_ajout permet de générer un exercice, et de créer une liste de
	 * phrases qui seront ensuite affichées une par une à l'élève.
	 * @param String arg : il s'agit du nom du fichier .txt de l'exercice selectionné au préalable
	 * @return phrases, une liste de String comportant les phrases de l'exercice
	 * @throws IOException
	 */
	
	public List<String> lecture_ajout(String arg) throws IOException {
		
		
		
		// Lecture d'un exercice, c'est-à-dire d'un fichier txt avec les phrases du prof 
		//4. Les jumeaux ont #cassé# le vase. (Casser)

		List<String> phrases = new ArrayList<>();
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader(arg));
			String st;
			while ((st = br.readLine()) != null) {
				phrases.add(st);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
			}
		}
		System.out.println(phrases);
		return phrases;
		
			
	
	}
}
	

	

