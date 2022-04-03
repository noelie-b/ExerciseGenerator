package fr.inalco.im2021.bottero;

/**
 * Cette classe représente un élément corrigé. Elle n'offre aucune méthode à part un constructeur et un accesseur pour valoriser la correction.
 * @author Noelie
 * @see REPONSE
 *
 */
public class ElementCorrige {
	
	protected Morceau reponseAttendue;
	protected Morceau reponse;
	REPONSE correction; /// correction vaudra l'une des valeur possible de réponse.

	/**
	 * 
	 * @param reponseAttendue
	 * @param reponse
	 */
	public ElementCorrige(Morceau reponseAttendue, Morceau reponse) {
	
		this.reponseAttendue = reponseAttendue;
		this.reponse = reponse;
	}
	
	public void setCorrection(REPONSE correcte) {
		this.correction = correcte;
	}

	@Override
	public String toString() {
		return "ElementCorrige [reponseAttendue=" + reponseAttendue + ", reponse=" + reponse + ", correction="
				+ correction + "]";
	}
}
