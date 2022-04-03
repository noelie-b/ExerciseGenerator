package fr.inalco.im2021.bottero;
import java.util.List;

public interface Parseur {

	public List<Morceau> parse(String chaine) throws ParseurException;
}