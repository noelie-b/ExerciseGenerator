package fr.inalco.im2021.bottero;

/*
 * Cette classe hérite d'Exception,
 * qui fait déjà parti de Java
 */
public class ParseurException extends Exception{
	
	// une classe est transformée et envoyé sur le réseau, 
	// il y'a donc besoin d'un serial ID
	private static final long serialVersionUID = -1147606436648303192L;

	public ParseurException(String chaineAvecErreur) {
		super("Phrase à parser mal formée : " + chaineAvecErreur);
	}
}
