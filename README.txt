Projet Java  - Noélie B

Le programme principal est ExercicesDeLangues.java. Le programme n'a pas besoin d'arguments. 

Quand on lance le programme, l'élève renseigne son nom et sa langue. Comme l'objet etudiant est crée pour la première fois, il est au niveau le plus bas (débutant).

Un parser cherche l'exercice parmi les langues et les niveaux, puis le charge au sein du programme principal.
Pour que la réponse de l'élève soit correcte, il faut réécrire la phrase telle qu'elle en plus du mot à compléter.

Problèmes rencontrés : 
1. Chaque phrase est affichée, répondue et corrigée l'une après l'autre. Cependant, la correction de la phrase ne s'effectue pas (méthode corrige) si le dernier morceau n'est pas un morceau variable mais je n'ai pas trouvé comment résoudre le problème. Le programme principal fonctionne si vous sélectionnez la langue EN, mais il n'y aura pas de correction si vous sélectionner la langue FR.

Exemple : 
FR : Phrase = [581. Les fleurs que tu as , 90 sentent bon. (Acheter)]
	[elementsCorriges=[]]
	
EN : Phrase = [581. C'est une voiture anglaise - It is , 96, 96an English car]
	Votre note est de : 2/2
	[elementsCorriges=[ElementCorrige [reponseAttendue=96an English car, reponse=96an English car, 		correction=VRAI]]]


