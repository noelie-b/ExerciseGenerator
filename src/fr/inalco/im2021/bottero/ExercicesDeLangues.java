package fr.inalco.im2021.bottero;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Programme principal
 * @author noélie
 *
 */

public class ExercicesDeLangues {
	
	public static void main(String[] args) throws IOException {
		
		// ------------------------------------------------------------- //
								//ETUDIANT//
		// ------------------------------------------------------------- //
		
		
		Etudiant e1 = new Etudiant(); // Création d'un nouvel objet Etudiant
		String nom = e1.input_nom(); // L'étudiant peut rentrer son nom
		System.out.println(nom); // Affiche le nom de l'étudiant
		Enum niveau = e1.niveau_debutant(); // Assigne automatiquement le niveau débutant à l'élève
		Enum niv = e1.get_difficulty(); 
		System.out.println(niveau); // Affiche le niveau de l'étudiant
		
		
		String choixLg = e1.choix_langue(); // Choix de la langue
		System.out.println("Langue de l'étudiant : " + choixLg);
		
		// ------------------------------------------------------------- //
						//CHARGEMENT DE L'EXERCICE//
		// ------------------------------------------------------------- //

		
		BanqueExercice be1 = new BanqueExercice();
		List<String> listeFichiers = new ArrayList<>();
		be1.recherche_rep(listeFichiers, choixLg);
		
		String exercice = be1.selectionExercice(listeFichiers, niveau.toString());
		System.out.println("Exercice : " + exercice);
		
		// ------------------------------------------------------------- //
						//PARSING DES DIFFERENTES PHRASES//
		// ------------------------------------------------------------- //

		Parseur p = new ParseurAEF('#'); // Le délimiteur des MorceauxVariables et Fixes est le caractère '#'
		Exercice Prof = new Exercice(exercice); // Création d'un nouvel exercice, il prend un fichier txt contenant les phrases du prof

		List<String> phrasesProf = Prof.lecture_ajout(exercice); // Création d'une liste de String contenant les phrases du prof
		Phrase unePhrase = null; // Déclaration et instanciation de deux objets de type Phrase
		Phrase phraseEleve = null;
		
	    for(String st:phrasesProf) { // Pour chaque phrase, on affiche son contenu
			try { // La phrase est parsée, c'est-à-dire qu'elle sera composée de Morceaux Fixes et Variables
				unePhrase = new Phrase (p.parse(st));
				System.out.println(unePhrase);
			
			}catch(ParseurException pe) { // Si les caractère dièses ne sont pas correctement placés, cela renvoi une erreur
				System.out.println(pe.getMessage());
				System.exit(-1);
			}
			
		System.out.println("pourEleve : " + unePhrase.pourEleve()); // On affiche la version de la phrase pour l'élève, avec '...' à la place des '#'
		System.out.println("pourRéponseAttendue : " + unePhrase.pourReponseAttendue()); // La réponse attendue est la phrase corigée sans les dièses
	    
		
		// ------------------------------------------------------------- //
							//REPONSE DE L'ELEVE//
		// ------------------------------------------------------------- //

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String reponseEleve = br.readLine();
		
		
		try {
			
			phraseEleve = unePhrase.analyseReponseEleve(reponseEleve);
		}catch (ParseurException pe) {
			System.out.println(pe.getMessage());
			System.exit(-1);
		}
		
		System.out.println(phraseEleve);
		
		// ------------------------------------------------------------- //
						//CORRECTION DE L'EXERCICE//
		// ------------------------------------------------------------- //

		
		Correction maCorrection = new Correction(unePhrase);
		maCorrection.corrige(phraseEleve);
	  
		// A cause de la boucle for un ElementCorrige vide est renvoyé [elementsCorriges=[]]
		System.out.println(maCorrection);
		
		
	   }
		

		
	
		//Notation noteEleve = new Notation(maCorrection);
		
		
		/*
		REPONSE reponse;
		Notation maNote = new Notation(reponse);
		maNote.note();
		maNote.getNotation();
		System.out.println(maNote);*
		 */		
		
		/*
		try {
			
			File fichierSortie = new File("exoProf/fichier-test.txt");
			fichierSortie.getParentFile().mkdirs(); // Créer un le répertoire parent si nécessaire.
			
			// Création d'un flux de Sortie
			OutputStream fichierSortieStream = new FileOutputStream(fichierSortie);
			
			// Création d'un scribe pour le flux de sortie
			OutputStreamWriter fichierSortieWriter = new OutputStreamWriter(fichierSortieStream, StandardCharsets.UTF_8);
			
			fichierSortieWriter.write(maCorrection);
			
			//FileWriter fw = new FileWriter("fichier.txt");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
}
	

