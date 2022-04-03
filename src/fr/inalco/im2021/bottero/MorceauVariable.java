package fr.inalco.im2021.bottero;

/**
 * Cette classe représente un MorceauVariable, 
 * c'est à dire un morceau dont la représentation de 
 * la chaîne varie en fonction de qui
 * elle est présentée. 
 * @author noélie
 * @see Morceau
 */

public class MorceauVariable extends Morceau{

	/**
	 * Ce constructeur ne fait qu'appeler le constructeur de Morceau 
	 * en lui passant les paramètres fournis en argument.
	 * @param unSigne
	 * @param unePosition
	 */
	
	public MorceauVariable(String unSigne, int unePosition) {
		super(unSigne, unePosition);
	}
	
	@Override
	public String pourProf() {
		return '#' + super.chaine + '#';
	}
	
	@Override
	public String pourEleve() {
		return "...";
	}
}