package fr.inalco.im2021.bottero;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CreateExoBDD {

	public static void main(String[] args) throws SQLException {
		if (args.length != 1) {
			System.out.println("java -classpath \".:sqlite-jbdc-3.36.0.3.jar\" fr.inalco.im2021 CreateExoBDD testexo.bd");
			System.exit(1);
		}
		String fileName = args[0];
		
		String url = "jdbc:sqlite:" + fileName;



		//Connexion et création si nécessaire à une base de donnée.

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("Le nom du pilote est " + meta.getDriverName());


		//Ordre SQL pour la création d'une table si elle
		//n'existe pas.
		String sql = "CREATE TABLE IF NOT EXISTS Professeur ("
					+ "professeur_id integer PRIMARY KEY"
					+ ", nom varchar(255) NOT NULL"
					+ ", prenom varchar(255) NOT NULL"
					+ ");";
		Statement stmt = conn.createStatement();

		//execute l'ordre SQL.
		stmt.execute(sql);
		System.out.println("Table Professeur créée.");

		
		String matiere = "CREATE TABLE IF NOT EXISTS Matiere ("
				+ "matiere_id integer PRIMARY KEY"
				+ ", nom_matiere varchar(255) NOT NULL"
				+ ");";
		Statement stmt2 = conn.createStatement();
		stmt2.execute(matiere);
		System.out.println("Table Matiere créée.");

		
		String matiere_professeur = "CREATE TABLE IF NOT EXISTS Matiere_Professeur (\n"
				+ " matiere_k integer"
				+ " , professeur_k integer"
				+ " , FOREIGN KEY(matiere_k) REFERENCES Matiere(matiere_id)"
				+ " , FOREIGN KEY(professeur_k) REFERENCES Professeur(professeur_id)"
				+ ");";
		
		Statement stmt3 = conn.createStatement();
		stmt3.execute(matiere_professeur);
		
		System.out.println("Table Matiere_professeur créée.");
		
			}
		}
	}
}