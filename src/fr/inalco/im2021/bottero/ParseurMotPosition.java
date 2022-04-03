package fr.inalco.im2021.bottero;

import java.util.ArrayList;
import java.util.List;

/**
* Enumération des Etats pouvant être pris par l'automate a état fini du scanner.
* @author noelie
*
*/
enum Etat {
	MORCEAUFIXE, MORCEAUVARIABLE;
}


public class ParseurMotPosition implements Parseur {
	
	private char d;
	
	public ParseurMotPosition() {
		d = '*';
	}
	
	public ParseurMotPosition(char unCararactere) {
		// TODO Auto-generated constructor stub
		d = unCararactere;
	}
	
	
	
	@Override
	public List<Morceau> parse(String chaine) {
		// TODO Auto-generated method stub
		
		List<Morceau> mp = new ArrayList<>(20);
		
		
		int i=0; // indice du parcours de la chaîne à scanner.
		int posCorrecte = 0; // position réelle, qui élimine le caractère délimiteur des mots variables.
		
		Etat fsm=Etat.MORCEAUFIXE; // MORCEAUFIXE est l'état initiale.
		
		int posVariable=0; // index du début du motFocus,
		StringBuffer mVariable = new StringBuffer(200);
		
		StringBuffer mFixe = new StringBuffer(200);
		int posFixe = 0;
		
		
		// Parcours de la chaîne
		while(true) {
			if (i == chaine.length()) {
				// On a atteint la fin de la chaîne
				break;
			}
			
			char c = chaine.charAt(i);
				
			switch (fsm) {
			
				case MORCEAUFIXE: {
					if (c == d) {
						
						Morceau morceauFixe = new Morceau(mFixe.toString(), posFixe);
						mp.add(morceauFixe);
						mFixe = new StringBuffer(200);
						posVariable = posCorrecte;
						fsm = Etat.MORCEAUVARIABLE;
						
					}else {
						mFixe.append(c);
						posCorrecte++;
					}
					break;
				}
				case MORCEAUVARIABLE: {
					
					if (c != d) {
						//fsm = Etat.MOTPOSITION;
						mVariable.append(c);
						posCorrecte++;
					}else {
							
						Morceau morceauVariable = new MorceauVariable(mVariable.toString(), posVariable);
						mp.add(morceauVariable);
						mVariable = new StringBuffer(200);
						posFixe = posCorrecte; // i c'est l'indice du délimiteur mais 
						              // qui sera l'indice du premier caractère de motFocus 
						              // une fois le délimiteur retiré.
						
						
						
						fsm = Etat.MORCEAUFIXE;
						
					}
					break;
				}	
			}
				
			i++; // i est l'indice du caractère à lire. Mon invariant est à nouveau vrai.
		}		
			
		return mp;
	}
}
