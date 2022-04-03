package fr.inalco.im2021.bottero;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Cette classe permet de représenter une Correction. 
 * Une correction est une liste d'éléments corrigés et une Phrase.
 * Cette classe pourrait être améliorée en ayant comme attributs la phrase de Prof 
 * et la réponse de l'élève correspondante.
 * @author noelie
 * @see ElementCorrige, Phrase
 */
public class Correction {
	
	protected List<ElementCorrige> elementsCorriges;
	protected Phrase phrase;

	/**
	 * 
	 * @param unePhrase Phrase issue de l'analyse d'une phrase de professeur. 
	 * et non la réponse d'un élève.
	 */
	public Correction(Phrase unePhrase) {
		phrase = unePhrase;
		elementsCorriges = new LinkedList<>();
	}
	
	/**
	 * L'appel de cette méthode va valoriser la liste d'éléments corrigés.
	 * Dans la variante à deux attributs, cette méthode ne prendrait pas de paramètre.
	 * @param reponseEleve 
	 */
	public void corrige(Phrase reponseEleve) {
		
		
		Iterator<MorceauVariable> itPhrase = phrase.getIterateurMorceauxVariable();
		Iterator<MorceauVariable> itRe = reponseEleve.getIterateurMorceauxVariable();
		
		while(itPhrase.hasNext() && itRe.hasNext()) {
			MorceauVariable mv = itPhrase.next();
			MorceauVariable re = itRe.next();
			ElementCorrige ec = null;
			String noteExercice = "";
			
			if (re.getChaine().equals(mv.pourEleve())){
				// Est-ce que l'élève a laissé un trou ?
				ec = new ElementCorrige(mv, new Morceau(re.getChaine(), re.getPosition()));
				ec.setCorrection(REPONSE.NR); // Non répondu.
				int bareme = note(REPONSE.NR);
				noteExercice = noteMax(REPONSE.VRAI);
			} else {
			  // Bonne réponse !
			   if (mv.getChaine().equals(re.getChaine())){
				   ec = new ElementCorrige(mv, new Morceau(re.getChaine(), re.getPosition()));
				   ec.setCorrection(REPONSE.VRAI);
				   int bareme = note(REPONSE.VRAI);
				   noteExercice = noteMax(REPONSE.VRAI);
				   
			   }else {
				   // Ce n'est pas la bonne réponse.
				
					ec = new ElementCorrige(mv, 
					                        new Morceau(re.getChaine(), re.getPosition()));
					ec.setCorrection(REPONSE.FAUX);
					int bareme = note(REPONSE.FAUX);
					noteExercice = noteMax(REPONSE.VRAI);
			   }
			}
			
			elementsCorriges.add(ec);		
		}	
	}
	
	
	public int note(REPONSE correcte) {
		int bareme = 0;
		if (correcte == REPONSE.VRAI) {
			bareme += 2;
		}else if (correcte == REPONSE.FAUX) {
			bareme += 0;
		}else if (correcte == REPONSE.NR) {
			bareme += 0;
		}
		//System.out.println(bareme);
		return bareme;
	}	
	
	public String noteMax(REPONSE correcte) {
		String notefinale = "";
		int bareme = 0;
		if (correcte == REPONSE.VRAI) {
			bareme += 2;
		}else if (correcte == REPONSE.FAUX) {
			bareme += 2;
		}else if (correcte == REPONSE.NR) {
			bareme += 2;
		}
		notefinale = note(correcte) + "/" + bareme;
		System.out.println("Votre note est de : " + notefinale);
		return notefinale;
	}
	
	

	@Override
	public String toString() {
		return "[elementsCorriges=" + elementsCorriges + "]"; 
	}
	
	
	
}