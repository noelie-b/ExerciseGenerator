package fr.inalco.im2021.bottero;


public interface Notation {
	
	public int note(ElementCorrige corrige);

	// Prendre une liste d'éléments corrigés
	// la parcourir
	// Si le dernier élément est une REPONSE
	// SI REPONSE = REPONSE.VRAI
	// Ajoute +2 à la note finale
	
	// ...
	
	// Créer une méthode qui calcule la note maximale DONC où toutes les réponses seront vraies
	// Note eleve / note maximale (Affichage de la note de l'étudiant sur la note maximale
	// Si la note est supérieure à la moyenne
	// Alors l'éleve aura accès au niveau supérieur
	// ... 
	// INTERMEDIAIRE = getNotationEleve pour voir si l'élève peut y accéder

}
