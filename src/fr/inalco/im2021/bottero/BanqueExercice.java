package fr.inalco.im2021.bottero;

import java.io.File;
import java.util.List;

public class BanqueExercice {

	// Prend en entrée un répertoire
	// Cherche dans ce répertoire le fichier correspondant
	// A l'input de l'étudiant

	
public List<String> recherche_rep(List<String> listeFichiers, String langue){
		File repertoire = new File("./");
		String fichiers[] = repertoire.list();
		if (fichiers != null) {
			for (int i = 0; i < fichiers.length; i++) {
				if (fichiers[i].startsWith(langue)) {
					listeFichiers.add(fichiers[i]);
				}else {
					System.err.println("");
				}
			}
		}return listeFichiers;
	}
	
	
	
	
	public String selectionExercice(List<String> fichiers, String niveau) {
		String exercice = "";
		for (String f : fichiers) {
			if (f.contains(niveau)) {
				exercice = f;
			}
		}return exercice;
		
	}								
}
	

