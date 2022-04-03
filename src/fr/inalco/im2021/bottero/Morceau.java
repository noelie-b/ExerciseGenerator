package fr.inalco.im2021.bottero;

/* Cette classe représente un morceau de chaine fixe
 * 
 */
public class Morceau {
	
	protected String chaine; // La suite de caractères du morceau sans caractère délimiteur.
	protected int position; // L'indice du début du morceau dans la chaine représentée sous la forme réponse attendue.
	
	// Constructeur de Morceau
	public Morceau(String unSigne, int unePosition) {
		chaine = unSigne;
		position = unePosition;
	}
	
	/**
	 * 
	 * @return la chaine en la gardant intègre
	 */
	public String getChaine() {
		return new String(chaine);
	}
	
	public int getPosition() {
		return position;
	}
	
	/**
	 * 
	 * @return la chaine en la gardant intègre
	 */
	public String pourProf() {
		return new String(chaine);
	}
	
	public String pourEleve() {
		return chaine;
	}
	
	/**
	 * 
	 * @return la chaine en la gardant intègre
	 */
	public String pourReponseAttendue() {
		return new String(chaine);
	}
	
	public void affiche() {
		System.out.println(position + " " + chaine);
	}
	
	@Override
	public String toString() {
		return position + ':' + chaine;
	}
	
	public int length() {
		return chaine.length();
	}
	
	public boolean equals(MorceauVariable mv) {
		return chaine.equals(mv.chaine) && position == mv.position;
	}
}
