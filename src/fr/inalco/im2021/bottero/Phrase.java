package fr.inalco.im2021.bottero;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/***
 * Cette classe représente une Phrase, c'est à dire une suite 
 * de Morceaux de chaine fixes ou variables.
 * 
 * @author noélie
 *
 */

public class Phrase {

	protected List<Morceau> phrase;	// une phrase sous forme d'une liste de morceau.
	private List<MorceauVariable> morceauxVariables;
	private List<Morceau> morceauxFixes;
	
	
	public Phrase(List<Morceau> phrase) {
		this.phrase = phrase;
		morceauxVariables = new LinkedList<MorceauVariable>();
		morceauxFixes = new LinkedList<Morceau>();
		
		for (Morceau m : phrase) {
			if (m instanceof MorceauVariable) {
				morceauxVariables.add((MorceauVariable)m);
			}else {
				morceauxFixes.add(m);
			}
		}
	}
	
	public String pourEleve() {
		StringBuffer sb = new StringBuffer(500);
		for (Morceau m : phrase) {
			sb.append(m.pourEleve());
		}
		return sb.toString();
	}
	
	public String pourReponseAttendue() {
		StringBuffer sb = new StringBuffer(500);
		for (Morceau m : phrase) {
			sb.append(m.pourReponseAttendue());
		}
		return sb.toString();
	}
	
	Iterator<MorceauVariable> getIterateurMorceauxVariable(){
		return morceauxVariables.iterator();
	}
	
	public List<MorceauVariable> getMorceauxVariables(){
		return morceauxVariables;
	}
	
	@Override
	public String toString() {
		return "Phrase = " + phrase;
	}
	
	/**
	 * Cette méthode construit une Phrase représentant la réponse d'un élève.
	 * 
	 * @param reponseEleve la réponse de l'élève.
	 * @return une réponse d'élève sous forme de Phrase.
	 * @throws ParseurException 
	 * @exception ParseurException : Cette exception est lancée si 
	 * les morceaux fixes de la phrases ne sont pas trouvés car cela signifie 
	 * que l'élève a modifié indûment sa réponse. On aurait pu créer une exception spécifique.
	 */
	public Phrase analyseReponseEleve(String reponseEleve) throws ParseurException {
		List<Morceau> morceauxEleves = new LinkedList<>();
		int dPosV = 0; // début de la position variable
		int fPosV = 0; // Fin de la position variable
		int plusLoin = 0;
		/* Par définition les morceaux fixes sont fixes donc nous pouvons les chercher dans 
		 * la réponse de l'élève, les morceaux variables seront à côté des morceaux fixes.
		 * Ne marche donc pas si deux morceaux variables sont côte à côte, 
		 * sans aucun espace.
		 */
		
		for (Morceau m : morceauxFixes) {
			// Début de position fixe
			int dPosF = reponseEleve.indexOf(m.getChaine(), plusLoin);
			if (dPosF == -1) {
				throw new ParseurException("La réponse de l'élève a été indument modifiée, morceaux fixe non trouvé : " + m + " dans " + reponseEleve);
			}
			
			int fPosF = dPosF + m.length(); // Fin de position fixe
			fPosV = dPosF; // Fin d'une position variable et début d'une pos fixe
			
			if ((fPosV - dPosF) > 0 ) {
				String morceauVariable = reponseEleve.substring(dPosV, fPosV);
				MorceauVariable mv = new MorceauVariable(morceauVariable, dPosV);
				morceauxEleves.add(mv);
			}
			Morceau mf = new Morceau(reponseEleve.substring(dPosF, fPosF), dPosF);
			morceauxEleves.add(mf);
			
			dPosV = fPosF;
			plusLoin = fPosF;
			
		}
			// On vérifie le dernier morceau
			if (dPosV < reponseEleve.length()) {
				String morceauVariable = reponseEleve.substring(dPosV, reponseEleve.length());
				MorceauVariable mv = new MorceauVariable(morceauVariable, dPosV);
				morceauxEleves.add(mv);
			}
			
			Phrase phraseEleve = new Phrase (morceauxEleves);
			return phraseEleve;
		
		
	}
}
