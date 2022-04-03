package fr.inalco.im2021.bottero;

import java.util.ArrayList;
import java.util.List;


/**
* Enumération des Etats pouvant être pris par l'automate a état fini du scanner.
* @author noelie
*
*/
enum EtatMorceau {
	FIXE, VARIABLE;
}

/**
 * Cette classe implémente l'interface Parseur en utilisant un simple automate a état fini constitué de deux états. 
 * La transition d'un état à l'autre s'effectue par la lecture du caractère donné dans le constructeur ou # par défaut.
 * @author christian
 * @see ParseurException
 */
public class ParseurAEF implements Parseur {
	
	private char d;
	
	public ParseurAEF() {
		d = '#';
	}
	
	/**
	 * 
	 * @param unCararactere servant de délimiteur. Un morceau variable est entouré avec ce caractère.
	 **/
	public ParseurAEF(char unCararactere) {
		// TODO Auto-generated constructor stub
		d = unCararactere;
	}
	
	
	
	/**
	 * Les morceaux retournés soit des instance de Morceau, soit de MorceauVariable.
	 * @exception ParseurException est lancée lorsqu'une phase est mal formée.
	 * Voici les cas de phrases mal formées : 
	 * Une phrase sans morceau : ex "cette chaîne n'est pas ok."
	 * Une pharse contenant au moins un morceau vide, délimité par deux délimiteurs. ex : cette chaîne n'est pas ok ##.
	 * Une pharse contenant un début de morceau variable dont la fin n'est pas délimité. ex : cette chaîne n'est #pas# ok #fdsfsdfsd.
	 * Pour éliminer le problème du caractère . confondu avec ..., on pourrait imposer un caractère espace après le point final
	 */
	
	@Override
	public List<Morceau> parse(String chaine) throws ParseurException {
		// TODO Auto-generated method stub
		
		List<Morceau> mp = new ArrayList<>(20);
		
		
		int i=0; // indice du parcours de la chaîne à scanner.
		int posCorrecte = 0; // position réelle, qui élimine le caractère délimiteur des mots variables.
		
		EtatMorceau etatCourant=EtatMorceau.FIXE; // MORCEAUFIXE est l'état initiale.
		
		int posVariable=0; // index du début du motFocus,
		StringBuffer mVariable = new StringBuffer(200);
		
		StringBuffer mFixe = new StringBuffer(200);
		int posFixe = 0;
		char carPrecedent = '\0'; // affectation avec le caractère null, arbitraire.
		
		// Parcours de la chaîne
		while(true) {
			if (i == chaine.length()) {
				// On a atteind la fin de la chaîne
				// Il faut vérifier que l'automate est dans un étant correcte.
				if ((etatCourant == EtatMorceau.VARIABLE) || (mp.size() == 0)){
					// La fin de chaîne a été atteinte avant de rencontrer le caractère délimiteur de fin de morceau.
					// La chaîne n'est pas bien formée.
					throw new ParseurException(chaine);
				}else {
					// Le dernier morceau à ajouter
					if (etatCourant == EtatMorceau.FIXE) {
						Morceau morceauFixe = new Morceau(mFixe.toString(), posFixe);
						mp.add(morceauFixe);
					}
				}
				
				
				break;
			}
			
			
			char c = chaine.charAt(i);
				
			switch (etatCourant) {
			
				case FIXE: {
					if (c == d) {
						
						
						
						Morceau morceauFixe = new Morceau(mFixe.toString(), posFixe);
						mp.add(morceauFixe);
						mFixe = new StringBuffer(25);
						posVariable = posCorrecte;
						etatCourant = EtatMorceau.VARIABLE;
						
					}else {
						mFixe.append(c);
						posCorrecte++;
					}
					break;
				}
				case VARIABLE: {
					
					if (c != d) {
						//fsm = Etat.MOTPOSITION;
						mVariable.append(c);
						posCorrecte++;
					}else {
						
						if (carPrecedent == d) {
							throw new ParseurException(chaine);
						}
						Morceau morceauVariable = new MorceauVariable(mVariable.toString(), posVariable);
						mp.add(morceauVariable);
						mVariable = new StringBuffer(25);
						posFixe = posCorrecte; // i c'est l'indice du délimiteur mais 
						              // qui sera l'indice du premier caractère de motFocus 
						              // une fois le délimiteur retiré.
						
						
						
						etatCourant = EtatMorceau.FIXE;
						
					}
					break;
				}	
			}
			
			carPrecedent = c;
			i++; // i est l'indice du caractère à lire. Mon invariant est à nouveau vrai.
		}		
			
		return mp;
	}
	
	/**
	 * Un main pour tester uniquement cette classe.
	 * @param args
	 */
	public static void main(String[] args) {
		String[] phraseTest = {"cette chaîne est #ok#."
								, "cette chaîne n'est pas ok ##."
								, "cette chaîne est #ok##ok#."
								, "cette chaîne n'est pas ok #fdsfsdfsd."
								, "cette chaîne n'est #pas# ok #fdsfsdfsd."
								, "cette chaîne n'est pas ok."
							};

		Parseur p = new ParseurAEF('#');
		

		for(int i = 0; i < phraseTest.length; i++) {
			try {
				List<Morceau> maListe = p.parse(phraseTest[i]);
				System.out.println(maListe);
			}catch(ParseurException pe) {
				System.out.println(pe.getMessage());
			}
		}
		
	
		
	}
	
}