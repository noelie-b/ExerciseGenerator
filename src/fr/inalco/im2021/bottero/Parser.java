package fr.inalco.im2021.bottero;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

/*
* Une classe implémentant l'interface Parseur
* qui parse une phrase en morceaux
*/
public class Parser implements Parseur {
    private char d;

/*
* un constructeur definissant le délimiteur de morceaux
*/
    public Parser(){
        d='#';
    }
/*
* une méthode pour parser une phrase en morceaux, se basant sur les morceaux variables 
* @param s, une chaine de caractère constituant une pharse
* @return phrase, une liste de morceaux de la phrase
*/
    public List<Morceau> parse(String s) {
        
        List<Morceau> phrase = new ArrayList<>();

        Pattern p = Pattern.compile("(#([^#]*)#)");
        //Pattern p = Pattern.compile("("+d+"(.*?)"+d+")");
        Matcher m = p.matcher(s);
        
        int i=0; //un curseur parcourant la phrase du premier caractère au dernier
        int z=0; //la position du morceau dans la phrase
        while(m.find()) { //tant qu'on trouve des morceaux variables
            if (m.start()==i){ //si le morceau variable trouvé commence au début de la phrase s
            	MorceauVariable p2 = new MorceauVariable(m.group(2), z);
                phrase.add(p2);
                z++;
                i = m.end(); //le curseur se positionne à la fin du morceau trouvé
            }
            else { // dans les autres cas 
                String s1 = s.substring(i, m.start());
                Morceau p1 = new Morceau(s1, z);
                System.out.println(s1);
                phrase.add(p1);
                z++;
                MorceauVariable p2 = new MorceauVariable(m.group(2), z);
                phrase.add(p2);
                z++;
                i = m.end(); //le curseur se positionne à la fin du morceau trouvé
            }
        }
        if (i != s.length()){ //si le curseur n'a pas atteint le dernier caractère de la phrase s
            String s2 = s.substring(i, s.length());
            Morceau p3 = new Morceau(s2, z);
            System.out.println(s2);
            phrase.add(p3);
        }
        
        return phrase;
    }
    
   public static void main(String[] args) {
		String[] phraseTest = {"cette chaîne est #ok#."
								, "#Cette# chaine est ok."
								, "cette chaîne n'est pas ok ##."
								, "cette chaîne est #ok##ok#."
								, "cette chaîne n'est pas ok #fdsfsdfsd."
								, "cette chaîne n'est #pas# ok #fdsfsdfsd."
								, "cette chaîne n'est pas ok."
							};

		Parseur p = new Parser();
		

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