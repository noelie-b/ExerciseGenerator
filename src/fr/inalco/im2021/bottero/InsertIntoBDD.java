package fr.inalco.im2021.bottero;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertIntoBDD {
    /**
    * Connect to the test.db database
    *
    * @return the Connection object
     * @throws SQLException 
    */
	
   public static void main(String[] args) throws SQLException {
		if (args.length != 1) {
			System.out.println("java -classpath \".;sqlite-jdbc-3.36.0.3.jar\" net.sqlitetutorial.InsertData fichier.db");
		}
		
	String fileName = args[0];
	String url = "jdbc:sqlite:" + fileName;
	
	try (Connection conn = DriverManager.getConnection(url)){
		if (conn != null) {
			
			DatabaseMetaData meta = conn.getMetaData();
			System.out.println("Le nom du pilote est " + meta.getDriverName());
			
			String sql = "INSERT INTO Matiere(nom_matiere) VALUES(?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// Insertion de 5 matières
			String[] mesMatieres = {"JAPONAIS", "ANGLAIS", "TURC", "LATIN", "MATHEMATIQUES"};
			int [] mesMatieresId = new int[mesMatieres.length];
			
			for (int i=0; i < mesMatieres.length; i++) {
				pstmt.setString(1, mesMatieres[i]);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				mesMatieresId[i] = rs.getInt(1);
			}
			
			System.out.println("Table matière traitée");
			
			String sql2 = "INSERT INTO Professeur(nom, prenom) VALUES(?,?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			
			// Insertion des professeurs
			String[] nomProfesseurs = {"BEAN", "MALETTE", "WOUVEL"};
			String[] prenomProfesseurs = {"Harry", "Marc", "Luc"};
			int [] professeursId = new int[nomProfesseurs.length];
			
			for (int i=0; i < nomProfesseurs.length; i++) {
				pstmt2.setString(1, nomProfesseurs[i]);
				pstmt2.setString(2, prenomProfesseurs[i]);
				pstmt2.executeUpdate();
				ResultSet rs = pstmt2.getGeneratedKeys();
				professeursId[i] = rs.getInt(1);
			}
			
			String sql3 = "INSERT INTO Matiere_professeur(matiere_k, professeur_k) VALUES(?,?)";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			
			// Harry enseigne le japonais
			pstmt3.setInt(1, mesMatieresId[0]); 
			pstmt3.setInt(2, professeursId[0]);
			pstmt3.executeUpdate();
			
			// LATIN Mark
			pstmt3.setInt(1, mesMatieresId[3]); 
			pstmt3.setInt(2, professeursId[1]);
			pstmt3.executeUpdate();
			
			// Harry enseigne le japonais
			pstmt3.setInt(1, mesMatieresId[4]); 
			pstmt3.setInt(2, professeursId[1]);
			pstmt3.executeUpdate();
			
			// Luc Turc
			pstmt3.setInt(1, mesMatieresId[2]); 
			pstmt3.setInt(2, professeursId[2]);
			pstmt3.executeUpdate();
			
			
			
			
		}
	}
	
}
}